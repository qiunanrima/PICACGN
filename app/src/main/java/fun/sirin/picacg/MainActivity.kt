package fun.sirin.picacg

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import fun.sirin.picacg.ui.screen.PicacgApp
import fun.sirin.picacg.ui.screen.SessionViewModel
import fun.sirin.picacg.ui.theme.PicaCgTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PicaCgTheme {
                val vm: SessionViewModel = viewModel(factory = SessionViewModel.factory(applicationContext))
                PicacgApp(vm)
            }
        }
    }
}
