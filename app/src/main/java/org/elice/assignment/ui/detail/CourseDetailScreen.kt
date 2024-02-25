package org.elice.assignment.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.elice.assignment.ui.theme.AssignmentTheme

@Composable
internal fun CourseDetailScreen(
    navHostController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, top = 16.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Sharp.KeyboardArrowLeft,
                contentDescription = "Back Button",
                modifier = Modifier.size(42.dp)
            )
        }

        CourseTitleAreaWithImage()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCourseDetailScreen() {
    AssignmentTheme {
        CourseDetailScreen(navHostController = rememberNavController())
    }
}