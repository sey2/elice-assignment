package org.elice.assignment.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.elice.assignment.ui.prepartion.ElicePreparingScreen
import org.elice.assignment.ui.detail.CourseDetailScreen
import org.elice.assignment.ui.home.HomeScreen

@Composable
fun SetUpNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(
            route = Screen.Home.route
        ) {
            HomeScreen(navHostController = navController)
        }
        composable(
            route = Screen.CourseDetail.route + "/{courseId}"
        ) { backStackEntry ->
            val courseId = backStackEntry.arguments?.getString("courseId")
            CourseDetailScreen(
                navHostController = navController,
                courseId = courseId
            )
        }
        composable(
            route = Screen.UnReady.route
        ) {
            ElicePreparingScreen(navController = navController)
        }
    }
}
