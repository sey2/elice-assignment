package org.elice.assignment.navigation

sealed class Screen(val route: String) {
    object Home : Screen(route = "Home")
    object CourseDetail : Screen(route = "CourseDetail")
}