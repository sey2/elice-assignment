package org.elice.assignment.domain.entities

import com.google.gson.annotations.SerializedName

data class CourseDetailEntity(
    val id: Int,
    val title: String,
    val description: String?,
    @SerializedName("short_description")
    val shortDescription: String?,
    @SerializedName("image_file_url")
    val imageFileUrl: String?,
    @SerializedName("logo_file_url")
    val logoFileUrl: String
)