package org.elice.assignment.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import org.elice.assignment.domain.entities.CourseEntity
import org.elice.assignment.domain.entities.createMockCourseEntity
import org.elice.assignment.ui.component.EliceTag
import org.elice.assignment.ui.theme.AssignmentTheme
import org.elice.assignment.ui.theme.NotoBold
import org.elice.assignment.ui.theme.NotoRegular

@Composable
internal fun CourseGridList(
    modifier: Modifier = Modifier,
    courseList: List<CourseEntity> = listOf(),
    title: String
) {
    Column(
        modifier = modifier
            .padding(start = 16.dp, top = 8.dp)
            .fillMaxWidth()
            .heightIn(min = 252.dp)
    ) {
        Text(
            text = title,
            modifier.padding(bottom = 16.dp),
            fontFamily = NotoBold,
            fontSize = 16.sp
        )
        
        LazyRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(courseList.size) { index ->
                val imageUrl =
                    if (courseList[index].imageFileUrl == null) courseList[index].logoFileUrl else courseList[index].imageFileUrl
                CourseCard(
                    title = courseList[index].title,
                    description = courseList[index].shortDescription ?: "",
                    imageUrl = imageUrl ?: "",
                    tags = courseList[index].tagList,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
        }
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CourseCard(
    title: String,
    description: String,
    imageUrl: String,
    tags: List<String> = listOf(),
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier.padding(end= 16.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = imageUrl),
            contentDescription = "Course Image",
            modifier = Modifier
                .size(width = 200.dp, height = 100.dp)
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Fit
        )

        Text(
            text = title,
            fontSize = 14.sp,
            fontFamily = NotoBold,
            maxLines = 2,
            lineHeight = 24.sp,
            modifier = Modifier
                .padding(top = 8.dp)
                .widthIn(max = 200.dp)
        )

        Spacer(modifier = Modifier.padding(vertical = 2.dp))

        if (description != "") {
            Text(
                text = description,
                fontSize = 10.sp,
                fontFamily = NotoRegular,
                maxLines = 2,
                lineHeight = 14.sp,
                modifier = Modifier
                    .padding(top = 2.dp)
                    .widthIn(max = 200.dp)
            )
        }

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            maxItemsInEachRow = 4
        ) {
            tags.forEach { tag ->
                EliceTag(text = tag)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CourseGridPreview() {
    AssignmentTheme {
        CourseGridList(
            courseList = createMockCourseEntity(),
            title = "무료 과목"
        )
    }
}