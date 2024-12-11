package ir.famastudio.melodify.view.screens.home

import android.net.Uri
import android.provider.CalendarContract.Colors
import android.util.Log
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil3.compose.AsyncImage
import ir.famastudio.melodify.view.ui.theme.MelodifyTheme
import ir.famastudio.melodify.viewmodel.HomeViewModel

@Composable
fun ViewHome() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "listScreen") {
        composable(route = "listScreen") {
            ViewMusicList(navController = navController)
        }
        composable(route = "playerScreen/{musicUri}/{musicName}/{musicImage}",
            arguments = listOf(
                navArgument("musicImage") {
                    defaultValue = ""
                },
                navArgument("musicName") {
                    type = NavType.StringType
                },
                navArgument("musicUri") {
                    defaultValue = ""
                }
            )) {
            ViewPlayer(navController=navController)
        }
    }
}

@Composable
fun ViewMusicList(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController,
) {
    val musicsData = viewModel.musicsData.collectAsState()
    viewModel.getMusics()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        if (musicsData.value != null) {
            LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                items(musicsData.value!!.musics.size) { index ->
                    Card(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                            .height(150.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary),
                        onClick = {
                            navController.navigate(
                                "playerScreen/${Uri.encode(musicsData.value!!.musics[index].music)}/${musicsData.value!!.musics[index].name}/${
                                    Uri.encode(
                                        musicsData.value!!.musics[index].image
                                    )
                                }"
                            )
                        }
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            AsyncImage(
                                modifier = Modifier
                                    .padding(5.dp)
                                    .weight(0.8f),
                                model = musicsData.value!!.musics[index].image,
                                contentDescription = null
                            )
                            Text(
                                modifier = Modifier
                                    .padding(5.dp)
                                    .weight(0.2f)
                                    .align(Alignment.CenterHorizontally),
                                text = musicsData.value!!.musics[index].name,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        } else {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(30.dp)
                        .align(Alignment.Center),
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun ViewPlayer(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val isPlaying = viewModel.isPlaying.collectAsState()
    val name = remember {
        navController.currentBackStackEntry?.arguments?.getString("musicName").toString()
    }
    val image = remember {
        navController.currentBackStackEntry?.arguments?.getString("musicImage").toString()
    }
    val uri = remember {
        navController.currentBackStackEntry?.arguments?.getString("musicUri").toString()
    }
    viewModel.setUpExoPlayer(Uri.decode(uri))
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AsyncImage(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .height(250.dp)
                    .background(MaterialTheme.colorScheme.secondary),
                model = Uri.decode(image),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = name, color = Color.White)
            Spacer(modifier = Modifier.height(50.dp))
            Button(onClick = {
                if (isPlaying.value){
                    viewModel.pauseSound()
                }else{
                    viewModel.playSound()
                }
            }) {
                Text(text = if (isPlaying.value) "pause" else "play")
            }
        }
    }
}