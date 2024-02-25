package org.elice.assignment.ui.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.elice.assignment.ui.theme.AssignmentTheme
import org.elice.assignment.ui.theme.ElicePurple
import org.elice.assignment.ui.theme.EliceRed
import org.elice.assignment.ui.theme.NotoBold

@Composable
internal fun CourseDetailScreen(
    navHostController: NavHostController
) {
    var isEnrolled by rememberSaveable { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 64.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Sharp.KeyboardArrowLeft,
                    contentDescription = "Back Button",
                    modifier = Modifier.size(42.dp)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                CourseTitleAreaWithImage()
                CourseDetailDescriptionArea()
                CourseDetailCurriculumArea()
            }
        }

        EnrollButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(84.dp)
                .padding(16.dp),
            isEnrolled = isEnrolled,
            onClick = { isEnrolled = !isEnrolled }
        )
    }
}

@Composable
fun EnrollButton(
    modifier: Modifier,
    isEnrolled: Boolean,
    onClick: () -> Unit = {},
) {
    Button(
        modifier = modifier,
        onClick = { onClick() },
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isEnrolled) EliceRed else ElicePurple,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Text(
            text = if (isEnrolled) "수강 취소" else "수강 신청",
            fontSize = 16.sp,
            fontFamily = NotoBold,
            lineHeight = 24.sp,
            modifier = Modifier
                .padding(start = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCourseDetailScreen() {
    AssignmentTheme {
        CourseDetailScreen(navHostController = rememberNavController())
    }
}