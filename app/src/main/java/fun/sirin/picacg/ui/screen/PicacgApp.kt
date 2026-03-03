package fun.sirin.picacg.ui.screen

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import fun.sirin.picacg.R
import fun.sirin.picacg.core.network.PicaEpisode

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PicacgApp(vm: SessionViewModel) {
    val ui by vm.uiState.collectAsState()
    val snackbars = remember { SnackbarHostState() }

    LaunchedEffect(ui.message) {
        ui.message?.let { snackbars.showSnackbar(it) }
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text(stringResource(R.string.title_app)) }) },
        snackbarHost = { SnackbarHost(snackbars) }
    ) { padding ->
        if (!ui.loggedIn) {
            LoginScreen(
                loading = ui.loading,
                onLogin = vm::login,
                modifier = Modifier.padding(padding)
            )
        } else {
            HomeScreen(
                uiState = ui,
                onSelectCategory = vm::loadComicsByCategory,
                onReloadCategories = vm::loadCategories,
                onSearchQueryChange = vm::updateSearchQuery,
                onSearch = vm::searchComics,
                onOpenDetail = vm::loadComicDetail,
                onDismissDetail = vm::clearComicDetail,
                onStartReader = vm::startReaderFromDetail,
                onCloseReader = vm::closeReader,
                onOpenEpisode = vm::openEpisode,
                onPrevPage = vm::prevPage,
                onNextPage = vm::nextPage,
                modifier = Modifier.padding(padding)
            )
        }
    }
}

@Composable
private fun HomeScreen(
    uiState: SessionUiState,
    onSelectCategory: (String) -> Unit,
    onReloadCategories: () -> Unit,
    onSearchQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
    onOpenDetail: (String) -> Unit,
    onDismissDetail: () -> Unit,
    onStartReader: () -> Unit,
    onCloseReader: () -> Unit,
    onOpenEpisode: (PicaEpisode) -> Unit,
    onPrevPage: () -> Unit,
    onNextPage: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = uiState.searchQuery,
                onValueChange = onSearchQueryChange,
                modifier = Modifier.weight(1f),
                label = { Text(stringResource(R.string.label_search_comic)) },
                singleLine = true
            )
            Button(onClick = onSearch, enabled = !uiState.loading) {
                Text(stringResource(R.string.action_search))
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(stringResource(R.string.label_categories), fontWeight = FontWeight.SemiBold)
            Button(onClick = onReloadCategories, enabled = !uiState.loading) {
                Text(stringResource(R.string.action_refresh))
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            uiState.categories.forEach { category ->
                val selected = category.title == uiState.selectedCategory
                AssistChip(
                    onClick = { onSelectCategory(category.title) },
                    label = { Text(category.title) },
                    colors = if (selected) {
                        AssistChipDefaults.assistChipColors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer
                        )
                    } else AssistChipDefaults.assistChipColors()
                )
            }
        }

        if (uiState.loading) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }

        Text(
            text = stringResource(R.string.label_comic_list),
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(top = 12.dp, bottom = 8.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(uiState.comics, key = { it.id }) { comic ->
                Surface(
                    tonalElevation = 2.dp,
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(comic.title, fontWeight = FontWeight.Medium)
                        comic.author?.let { Text(stringResource(R.string.label_author, it)) }
                        comic.likesCount?.let { Text("❤️ $it") }
                        TextButton(onClick = { onOpenDetail(comic.id) }) {
                            Text(stringResource(R.string.action_view_detail))
                        }
                    }
                }
            }
        }
    }

    uiState.selectedComicDetail?.let { detail ->
        AlertDialog(
            onDismissRequest = onDismissDetail,
            confirmButton = {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    TextButton(onClick = onStartReader) {
                        Text(stringResource(R.string.action_start_reading))
                    }
                    TextButton(onClick = onDismissDetail) {
                        Text(stringResource(R.string.action_close))
                    }
                }
            },
            title = { Text(detail.title) },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    detail.author?.let { Text(stringResource(R.string.label_author, it)) }
                    detail.description?.let { Text(it) }
                    Text(stringResource(R.string.label_likes, detail.likesCount ?: 0))
                    Text(stringResource(R.string.label_eps_pages, detail.epsCount ?: 0, detail.pagesCount ?: 0))
                }
            }
        )
    }

    if (uiState.pageUrls.isNotEmpty()) {
        val page = uiState.currentPageIndex + 1
        val total = uiState.pageUrls.size
        val currentUrl = uiState.pageUrls.getOrNull(uiState.currentPageIndex).orEmpty()
        AlertDialog(
            onDismissRequest = onCloseReader,
            confirmButton = {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    TextButton(onClick = onPrevPage, enabled = uiState.currentPageIndex > 0) {
                        Text(stringResource(R.string.action_prev_page))
                    }
                    TextButton(onClick = onNextPage, enabled = uiState.currentPageIndex < total - 1) {
                        Text(stringResource(R.string.action_next_page))
                    }
                    TextButton(onClick = onCloseReader) {
                        Text(stringResource(R.string.action_exit))
                    }
                }
            },
            title = {
                Text(
                    stringResource(
                        R.string.label_reader_title,
                        uiState.currentEpisode?.title ?: stringResource(R.string.label_default_episode)
                    )
                )
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        uiState.episodes.forEach { episode ->
                            val selected = episode.id == uiState.currentEpisode?.id
                            AssistChip(
                                onClick = { onOpenEpisode(episode) },
                                label = { Text(stringResource(R.string.label_episode_order, episode.order)) },
                                colors = if (selected) {
                                    AssistChipDefaults.assistChipColors(
                                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                                    )
                                } else AssistChipDefaults.assistChipColors()
                            )
                        }
                    }
                    Text(stringResource(R.string.label_page_progress, page, total))
                    AsyncImage(
                        model = currentUrl,
                        contentDescription = stringResource(R.string.content_reader_page),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp)
                    )
                    Text(currentUrl, style = MaterialTheme.typography.bodySmall)
                }
            }
        )
    }
}

@Composable
private fun LoginScreen(
    loading: Boolean,
    onLogin: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(stringResource(R.string.label_email)) },
            singleLine = true
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            label = { Text(stringResource(R.string.label_password)) },
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true
        )
        Button(
            onClick = { onLogin(email.trim(), password) },
            enabled = !loading && email.isNotBlank() && password.isNotBlank(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            if (loading) {
                CircularProgressIndicator()
            } else {
                Text(stringResource(R.string.action_login))
            }
        }
    }
}
