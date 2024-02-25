package org.elice.assignment.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.elice.assignment.R
import org.elice.assignment.ui.theme.AssignmentTheme
import org.elice.assignment.ui.theme.NotoBold
import org.elice.assignment.ui.theme.NotoRegular

@Composable
fun CourseTitleAreaWithImage(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {

        Row(
            modifier = modifier.padding(top = 8.dp, bottom = 8.dp, start = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.detail_title_logo),
                contentDescription = "Course Image",
                modifier = Modifier
                    .size(width = 36.dp, height = 36.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Fit
            )

            Text(
                text = "C언어 레벨 테스트",
                fontSize = 16.sp,
                fontFamily = NotoBold,
                lineHeight = 24.sp,
                modifier = Modifier
                    .padding(start = 8.dp)
            )
        }

        Image(
            painter = painterResource(id = R.drawable.course_detail_image_file),
            contentDescription = "Course Image",
            modifier = Modifier
                .aspectRatio(2f),
            contentScale = ContentScale.FillWidth
        )
    }
}

@Composable
fun CourseTitleAreaWithoutImage(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.detail_title_logo),
            contentDescription = "Course Image",
            modifier = Modifier
                .size(width = 56.dp, height = 56.dp)
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Fit
        )

        Text(
            text = "C언어 레벨 테스트",
            fontSize = 28.sp,
            fontFamily = NotoBold,
            lineHeight = 36.sp,
            modifier = Modifier
                .padding(vertical = 8.dp)
        )

        Text(
            text = "C언어 레벨 테스트",
            fontSize = 12.sp,
            fontFamily = NotoRegular,
            lineHeight = 20.sp,
            modifier = Modifier
                .padding(vertical = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCourseTitleAreaWithImage() {
    AssignmentTheme {
        CourseTitleAreaWithImage()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCourseTitleAreaWithoutImage() {
    AssignmentTheme {
        CourseTitleAreaWithoutImage()
    }
}