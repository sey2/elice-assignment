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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import org.elice.assignment.R
import org.elice.assignment.domain.entities.CourseDetailEntity
import org.elice.assignment.ui.theme.AssignmentTheme
import org.elice.assignment.ui.theme.NotoBold
import org.elice.assignment.ui.theme.NotoRegular

@Composable
internal fun CourseTitleAreaWithImage(
    modifier: Modifier = Modifier,
    courseDetail: CourseDetailEntity?
) {
    Column(modifier = modifier.fillMaxWidth()) {

        Row(
            modifier = modifier.padding(bottom = 8.dp, start = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = courseDetail?.logoFileUrl),
                contentDescription = "Course Logo",
                modifier = Modifier
                    .size(width = 36.dp, height = 36.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Fit
            )

            Text(
                text = courseDetail?.title ?: "",
                fontSize = 16.sp,
                fontFamily = NotoBold,
                lineHeight = 24.sp,
                modifier = Modifier
                    .padding(start = 8.dp)
            )
        }

        Image(
            painter = rememberAsyncImagePainter(model = courseDetail?.imageFileUrl),
            contentDescription = "Course Image",
            modifier = Modifier
                .aspectRatio(2f),
            contentScale = ContentScale.FillWidth
        )
    }
}

@Composable
internal fun CourseTitleAreaWithoutImage(
    modifier: Modifier = Modifier,
    courseDetail: CourseDetailEntity?
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = courseDetail?.logoFileUrl),
            contentDescription = "Course logo",
            modifier = Modifier
                .size(width = 56.dp, height = 56.dp)
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Fit
        )

        Text(
            text = courseDetail?.title ?: "",
            fontSize = 28.sp,
            fontFamily = NotoBold,
            lineHeight = 36.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(vertical = 8.dp)
        )

        Text(
            text = courseDetail?.shortDescription ?: "",
            fontSize = 12.sp,
            fontFamily = NotoRegular,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            lineHeight = 20.sp,
            modifier = Modifier
                .padding(vertical = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
internal fun PreviewCourseTitleAreaWithImage() {
    AssignmentTheme {
        Column(modifier = Modifier.fillMaxWidth()) {

            Row(
                modifier = Modifier.padding(bottom = 8.dp, start = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter =
                    painterResource(id = R.drawable.detail_title_logo),
                    contentDescription = "Course Logo",
                    modifier = Modifier
                        .size(width = 36.dp, height = 36.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Fit
                )

                Text(
                    text = stringResource(R.string.c_level_test),
                    fontSize = 16.sp,
                    fontFamily = NotoBold,
                    lineHeight = 24.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
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
}

@Preview(showBackground = true)
@Composable
internal fun PreviewCourseTitleAreaWithoutImage() {
    AssignmentTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.detail_title_logo),
                contentDescription = "Course logo",
                modifier = Modifier
                    .size(width = 56.dp, height = 56.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Fit
            )

            Text(
                text = stringResource(id = R.string.c_level_test),
                fontSize = 28.sp,
                fontFamily = NotoBold,
                lineHeight = 36.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(vertical = 8.dp)
            )

            Text(
                text = stringResource(id = R.string.c_level_test),
                fontSize = 12.sp,
                fontFamily = NotoRegular,
                lineHeight = 20.sp,
                modifier = Modifier
                    .padding(vertical = 8.dp)
            )
        }
    }
}