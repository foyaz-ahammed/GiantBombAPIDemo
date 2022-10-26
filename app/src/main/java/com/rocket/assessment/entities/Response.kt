package com.rocket.assessment.entities

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

/**
 * Network response class
 */
class Response {
    @JsonClass(generateAdapter = true)
    data class GameResult(
        @Json(name = "error") val error: String,
        @Json(name = "limit") val limit : Int,
        @Json(name = "offset") val offset : Int,
        @Json(name = "number_of_page_results") val numberOfPageResults: Int,
        @Json(name = "number_of_total_results") val numberOfTotalResults: Int,
        @Json(name = "status_code") val statusCode: Int,
        @Json(name = "results") val results: List<GameItem>
    )

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class GameItem(
        @Json(name = "date_added") val dateAdded: String,
        @Json(name = "date_last_updated") val dateLastUpdated: String,
        @Json(name = "description") val description: String? = null,
        @Json(name = "guid") val guid: String,
        @Json(name = "id") val id: Int,
        @Json(name = "image") val image: ImageInfo,
        @Json(name = "image_tags") val imageTags: List<ImageTagInfo>,
        @Json(name = "name") val name: String,
        @Json(name = "number_of_user_reviews") val numberOfUserReviews: Int,
        @Json(name = "platforms") val platforms: List<PlatformInfo>?,
        @Json(name = "site_detail_url") val siteDetailUrl: String
    ): Parcelable

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class ImageInfo(
        @Json(name = "icon_url") val iconUrl: String,
        @Json(name = "medium_url") val mediumUrl: String,
        @Json(name = "screen_url") val screenUrl: String,
        @Json(name = "screen_large_url") val screenLargeUrl: String,
        @Json(name = "small_url") val smallUrl: String,
        @Json(name = "super_url") val superUrl: String,
        @Json(name = "thumb_url") val thumbUrl: String,
        @Json(name = "tiny_url") val tinyUrl: String,
        @Json(name = "original_url") val originalUrl: String,
        @Json(name = "image_tags") val imageTags: String
    ): Parcelable

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class ImageTagInfo(
        @Json(name = "api_detail_url") val apiDetailUrl: String,
        @Json(name = "name") val name: String,
        @Json(name = "total") val total: Int
    ): Parcelable

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class PlatformInfo(
        @Json(name = "api_detail_url") val apiDetailUrl: String,
        @Json(name = "id") val id: Int,
        @Json(name = "name") val name: String,
        @Json(name = "site_detail_url") val siteDetailUrl: String,
        @Json(name = "abbreviation") val abbreviation: String
    ): Parcelable
}