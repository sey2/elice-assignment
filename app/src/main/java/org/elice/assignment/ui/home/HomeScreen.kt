package org.elice.assignment.ui.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import org.elice.assignment.domain.entities.CourseEntity
import org.elice.assignment.domain.usecase.course.GetEliceCourseList
import org.elice.assignment.viewmodel.EliceHomeUiState
import org.elice.assignment.viewmodel.EliceHomeViewModel

@Composable
fun HomeScreen(navHostController: NavHostController) {
    val getEliceCourseListUseCase = remember { GetEliceCourseList() }

    val viewModel: EliceHomeViewModel = viewModel(factory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return EliceHomeViewModel(getEliceCourseListUseCase) as T
        }
    })

    val eliceHomeUiState by viewModel.homeState.collectAsStateWithLifecycle()
    val eliceCourseList by viewModel.eliceCourseList.collectAsState()

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

        when(eliceHomeUiState){
            EliceHomeUiState.LOADING -> {
                // todo loading
            }
            EliceHomeUiState.SUCCESS -> {
                Log.d("API CALL", eliceCourseList.toString())
            }
        }
    }
}