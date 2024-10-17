package ir.famastudio.melodify.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.famastudio.melodify.data.model.local.SplashModel
import ir.famastudio.melodify.data.repository.LoginRepository
import ir.famastudio.melodify.data.repository.SplashRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {
    @Inject
    lateinit var model : SplashModel
    @Inject
    lateinit var repository: SplashRepository
    private val _finishSplashScreen = MutableLiveData<Boolean>()
    val finishSplashScreen: LiveData<Boolean> = _finishSplashScreen
    fun onStart() {
        viewModelScope.launch {
            flow {
                kotlinx.coroutines.delay(model.delayTime)
                emit(true)
            }.collect { result ->
                _finishSplashScreen.value = result
            }
        }
    }

    suspend fun userIsLoggedIn(): Boolean {
        return repository.getLoginData()
    }
}