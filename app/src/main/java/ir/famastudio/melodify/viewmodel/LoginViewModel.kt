package ir.famastudio.melodify.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.famastudio.melodify.data.model.remote.ApiRequestLogin
import ir.famastudio.melodify.data.repository.LoginRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {
    @Inject
    lateinit var repository: LoginRepository
    private val _loginStatus = MutableStateFlow<Boolean>(false)
    val loginStatus: StateFlow<Boolean> = _loginStatus
    fun login(username: String, password: String) {
        viewModelScope.launch {
            _loginStatus.value = repository.apiLogin(body = ApiRequestLogin(username, password))
        }
    }
    fun saveLoginData(){
        repository.saveLoginData()
    }
}