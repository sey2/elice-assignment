package org.elice.assignment.ui.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import org.elice.assignment.domain.entities.CourseEntity
import org.elice.assignment.ui.theme.AssignmentTheme
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        HomeHeader()

        CourseGridList()

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

@Preview
@Composable
fun HomePreview() {
    AssignmentTheme {
        HomeContent(
            eliceHomeUiState = EliceHomeUiState.SUCCESS,
            eliceCourseList = listOf()
        )
    }
}