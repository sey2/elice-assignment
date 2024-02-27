package org.elice.assignment.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.elice.assignment.R
import org.elice.assignment.ui.component.MarkdownText
import org.elice.assignment.ui.theme.AssignmentTheme
import org.elice.assignment.ui.theme.EliceDeepPurple
import org.elice.assignment.ui.theme.NotoBold
import org.elice.assignment.util.createMockMarkDownText

@Composable
internal fun CourseDetailDescriptionArea(
    modifier: Modifier = Modifier,
    description: String
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
            color = EliceDeepPurple,
            modifier = Modifier.padding(top = 8.dp)
        )
        Divider(modifier = Modifier.padding(vertical = 10.dp))

        MarkdownText(
            markdownText = description,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
internal fun PreviewCourseDetailDescriptionArea() {
    AssignmentTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.subject_introduction),
                fontSize = 14.sp,
                fontFamily = NotoBold,
                lineHeight = 20.sp,
                color = EliceDeepPurple,
                modifier = Modifier.padding(top = 8.dp)
            )
            Divider(modifier = Modifier.padding(vertical = 10.dp))

            MarkdownText(
                markdownText = createMockMarkDownText(),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}