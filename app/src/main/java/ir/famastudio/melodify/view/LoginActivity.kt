package ir.famastudio.melodify.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import ir.famastudio.melodify.view.screens.login.ViewLogin
import ir.famastudio.melodify.view.ui.theme.MelodifyTheme

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MelodifyTheme {
                ViewLogin()
            }
        }
    }
}
