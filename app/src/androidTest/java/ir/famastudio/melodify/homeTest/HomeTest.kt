package ir.famastudio.melodify.homeTest

import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import ir.famastudio.melodify.data.repository.HomeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class HomeTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var repository: HomeRepository

    @Before
    fun onStart(){
        hiltRule.inject()
    }

    @Test
    fun testGetMusicApi(){
        CoroutineScope(Dispatchers.IO).launch {
            val (response,_) = repository.apiGetMusics()
            Assert.assertNotNull(response)
        }
    }
}