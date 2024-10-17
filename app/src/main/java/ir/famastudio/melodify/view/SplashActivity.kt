package ir.famastudio.melodify.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import ir.famastudio.melodify.view.screens.splash.ViewSplash
import ir.famastudio.melodify.view.ui.theme.MelodifyTheme
import ir.famastudio.melodify.viewmodel.SplashViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    private val viewModel: SplashViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewModel.onStart()
        viewModel.finishSplashScreen.observe(this) { result ->
            if (result) {
                CoroutineScope(Dispatchers.IO).launch {
                    startActivity(
                        if (viewModel.userIsLoggedIn()) Intent(
                            this@SplashActivity,
                            HomeActivity::class.java
                        ) else Intent(
                            this@SplashActivity,
                            LoginActivity::class.java
                        )
                    )
                }
            }
        }
        setContent {
            MelodifyTheme {
                ViewSplash()
            }
        }
    }
}
