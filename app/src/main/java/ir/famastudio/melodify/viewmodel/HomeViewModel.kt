package ir.famastudio.melodify.viewmodel

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import ir.famastudio.melodify.data.model.remote.ApiResponseGetMusics
import ir.famastudio.melodify.data.repository.HomeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    @Inject
    lateinit var repository: HomeRepository
    @Inject
    lateinit var exoPlayer : ExoPlayer
    private val _musicsData = MutableStateFlow<ApiResponseGetMusics?>(null)
    val musicsData: StateFlow<ApiResponseGetMusics?> = _musicsData
    private val _isPlaying = MutableStateFlow<Boolean>(false)
    val isPlaying:StateFlow<Boolean> = _isPlaying

    fun getMusics() {
        viewModelScope.launch {
            val (response, _) = repository.apiGetMusics()
            _musicsData.value = response
        }
    }
    fun setUpExoPlayer(uri:String){
        exoPlayer.setMediaItem(MediaItem.fromUri(uri))
        exoPlayer.prepare()
    }

    fun playSound(){
        _isPlaying.value = true
        exoPlayer.play()
    }
    fun pauseSound(){
        _isPlaying.value = false
        exoPlayer.pause()
    }

    override fun onCleared() {
        exoPlayer.release()
        super.onCleared()
    }
}