package org.elice.assignment.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.elice.assignment.ui.component.MarkdownText
import org.elice.assignment.ui.theme.AssignmentTheme
import org.elice.assignment.ui.theme.NotoBold
import org.elice.assignment.util.createMockMarkDownText

@Composable
fun CourseDetailDescriptionArea(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
    ) {
        Text(
            text = "과목 소개",
            fontSize = 14.sp,
            fontFamily = NotoBold,
            lineHeight = 20.sp,
            color = Color(0xFF524FA1),
            modifier = Modifier.padding(top = 8.dp)
        )
        Divider(modifier = Modifier.padding(vertical = 10.dp))

        MarkdownText(
            markdownText = createMockMarkDownText(),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCourseDetailDescriptionArea() {
    AssignmentTheme {
        CourseDetailDescriptionArea()
    }
}