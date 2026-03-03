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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
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
        topBar = { TopAppBar(title = { Text("PICACG") }) },
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
                label = { Text("搜索漫画") },
                singleLine = true
            )
            Button(onClick = onSearch, enabled = !uiState.loading) {
                Text("搜索")
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("分类", fontWeight = FontWeight.SemiBold)
            Button(onClick = onReloadCategories, enabled = !uiState.loading) {
                Text("刷新")
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
            text = "漫画列表",
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
                        comic.author?.let { Text("作者：$it") }
                        comic.likesCount?.let { Text("❤️ $it") }
                        TextButton(onClick = { onOpenDetail(comic.id) }) {
                            Text("查看详情")
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
                        Text("开始阅读")
                    }
                    TextButton(onClick = onDismissDetail) {
                        Text("关闭")
                    }
                }
            },
            title = { Text(detail.title) },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    detail.author?.let { Text("作者：$it") }
                    detail.description?.let { Text(it) }
                    Text("点赞：${detail.likesCount ?: 0}")
                    Text("章节：${detail.epsCount ?: 0}，页数：${detail.pagesCount ?: 0}")
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
                        Text("上一页")
                    }
                    TextButton(onClick = onNextPage, enabled = uiState.currentPageIndex < total - 1) {
                        Text("下一页")
                    }
                    TextButton(onClick = onCloseReader) {
                        Text("退出")
                    }
                }
            },
            title = { Text("阅读器（${uiState.currentEpisode?.title ?: "章节"}）") },
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
                                label = { Text("第${episode.order}话") },
                                colors = if (selected) {
                                    AssistChipDefaults.assistChipColors(
                                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                                    )
                                } else AssistChipDefaults.assistChipColors()
                            )
                        }
                    }
                    Text("第 $page / $total 页")
                    AsyncImage(
                        model = currentUrl,
                        contentDescription = "reader page",
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
            label = { Text("邮箱") },
            singleLine = true
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            label = { Text("密码") },
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
                Text("登录")
            }
        }
    }
}
