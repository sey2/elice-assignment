package org.elice.assignment.ui.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import org.elice.assignment.domain.entities.CourseEntity
import org.elice.assignment.viewmodel.EliceHomeUiState
import org.elice.assignment.viewmodel.EliceHomeViewModel

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    homeViewModel: EliceHomeViewModel = hiltViewModel()
) {
    val eliceHomeUiState by homeViewModel.homeState.collectAsStateWithLifecycle()
    val eliceCourseList by homeViewModel.eliceCourseList.collectAsState()

    HomeContent(
        eliceHomeUiState = eliceHomeUiState,
        eliceCourseList = eliceCourseList
    )
}

@Composable
internal fun HomeContent(
    eliceHomeUiState: EliceHomeUiState,
    eliceCourseList: List<CourseEntity>
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Text(modifier = Modifier, text = "Home")

        when (eliceHomeUiState) {
            EliceHomeUiState.LOADING -> {
                // todo loading
            }

            EliceHomeUiState.SUCCESS -> {
                Log.d("API CALL", eliceCourseList.toString())
            }
        }
    }
}