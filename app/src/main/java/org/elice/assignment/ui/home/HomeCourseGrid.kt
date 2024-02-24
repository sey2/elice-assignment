package org.elice.assignment.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.elice.assignment.R
import org.elice.assignment.ui.component.EliceTag
import org.elice.assignment.ui.theme.AssignmentTheme
import org.elice.assignment.ui.theme.NotoBold
import org.elice.assignment.ui.theme.NotoRegular

@Composable
internal fun CourseGridList(
    modifier: Modifier = Modifier
) {
    val mockTags = listOf("태그 1", "태그 2")

    Column(
        modifier = modifier.padding(top = 8.dp)
    ) {
        Text(
            text = "무료 과목",
            modifier.padding(start = 16.dp, bottom = 16.dp),
            fontFamily = NotoBold,
            fontSize = 16.sp
        )
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            CourseCard(
                title = "C언어 챌린지C언어 챌린지C언어 챌린지",
                description = "나의 C언어 실력을 테스트 해보세요!나의 C언어 실력을 테스트 해보세요!",
                tags = mockTags,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            CourseCard(
                title = "Title",
                description = "Short",
                tags = mockTags,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}


@Composable
fun CourseCard(
    title: String,
    description: String,
    tags: List<String>,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
    ) {
        Image(
            painterResource(id = R.drawable.course_info),
            contentDescription = "Course Image",
            modifier = Modifier
                .size(width = 200.dp, height = 100.dp)
                .clip(RoundedCornerShape(10.dp))
        )

        Text(
            text = title,
            fontSize = 14.sp,
            fontFamily = NotoBold,
            maxLines = 2,
            modifier = Modifier
                .padding(top = 8.dp)
                .widthIn(max = 200.dp)
        )

        Spacer(modifier = Modifier.padding(vertical = 2.dp))

        Text(
            text = description,
            fontSize = 10.sp,
            fontFamily = NotoRegular,
            maxLines = 2
        )

        LazyRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(tags.size) { index ->
                EliceTag(text = tags[index])
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun CourseGridPreview() {
    AssignmentTheme {
        CourseGridList()
    }
}