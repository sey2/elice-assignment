package org.elice.assignment.domain.entities

import com.google.gson.annotations.SerializedName

data class CourseDetailEntity(
    val id: Int,
    val title: String,
    @SerializedName("image_file_url")
    val imageFileUrl: String,
    @SerializedName("logo_file_url")
    val logoFileUrl: String
)