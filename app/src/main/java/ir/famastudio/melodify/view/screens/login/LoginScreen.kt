package ir.famastudio.melodify.view.screens.login

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ir.famastudio.melodify.view.HomeActivity
import ir.famastudio.melodify.viewmodel.LoginViewModel

@Composable
fun ViewLogin(viewModel: LoginViewModel = hiltViewModel()) {
    val username = remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }
    val isLoading = remember {
        mutableStateOf(false)
    }
    val loginStatus = viewModel.loginStatus.collectAsState()
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.background
                    ),
                    endY = 1000f
                )
            )
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 2.dp)
                    .fillMaxWidth()
                    .padding(5.dp),
                value = username.value,
                onValueChange = {
                    username.value = it
                },
                label = {
                    Text(text = "Username", color = Color.White)
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )
            OutlinedTextField(
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 2.dp)
                    .fillMaxWidth()
                    .padding(5.dp),
                value = password.value,
                onValueChange = {
                    password.value = it
                },
                label = {
                    Text(text = "Password", color = Color.White)
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )
            Button(
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 2.dp)
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = Color.White
                ),
                onClick = {
                    viewModel.saveLoginData()
                    if (username.value.isNotBlank() && password.value.isNotBlank() && !isLoading.value) {
                        viewModel.login(username.value, password.value)
                        isLoading.value = true
                    }
                }
            ) {
                if (isLoading.value){
                    CircularProgressIndicator(modifier = Modifier.size(30.dp), color = Color.White)
                }else{
                    Text(text = "Login", color = Color.White)
                }
            }
        }
        Text(
            modifier = Modifier
                .padding(40.dp)
                .align(Alignment.BottomCenter),
            text = "Melodify",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 20.sp
        )
        LaunchedEffect(Unit) {
            if (loginStatus.value) {
                viewModel.saveLoginData()
                context.startActivity(Intent(context,HomeActivity::class.java))
            }else{
                isLoading.value = false
            }
        }
    }
}