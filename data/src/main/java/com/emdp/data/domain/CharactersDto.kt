package com.emdp.data.domain

import com.google.gson.annotations.SerializedName

data class CharactersDto(
    @SerializedName("attributionHTML") val attributionHTML: String,
    @SerializedName("attributionText") val attributionText: String,
    @SerializedName("code") val code: Int,
    @SerializedName("copyright") val copyright: String,
    @SerializedName("data") val `data`: DataDto,
    @SerializedName("etag") val etag: String,
    @SerializedName("status") val status: String
)

data class ComicsDto(
    @SerializedName("available") val available: Int,
    @SerializedName("collectionURI") val collectionURI: String,
    @SerializedName("items") val items: List<ItemDto>,
    @SerializedName("returned") val returned: Int
)

data class DataDto(
    @SerializedName("count") val count: Int,
    @SerializedName("limit") val limit: Int,
    @SerializedName("offset") val offset: Int,
    @SerializedName("results") val results: List<ResultDto>,
    @SerializedName("total") val total: Int
)

data class EventsDto(
    @SerializedName("available") val available: Int,
    @SerializedName("collectionURI") val collectionURI: String,
    @SerializedName("items") val items: List<ItemDto>,
    @SerializedName("returned") val returned: Int
)

data class ItemDto(
    @SerializedName("name") val name: String,
    @SerializedName("resourceURI") val resourceURI: String
)

data class ItemXXXDto(
    @SerializedName("name") val name: String,
    @SerializedName("resourceURI") val resourceURI: String,
    @SerializedName("type") val type: String
)

data class ResultDto(
    @SerializedName("comics") val comics: ComicsDto,
    @SerializedName("description") val description: String,
    @SerializedName("events") val events: EventsDto,
    @SerializedName("id") val id: Int,
    @SerializedName("modified") val modified: String,
    @SerializedName("name") val name: String,
    @SerializedName("resourceURI") val resourceURI: String,
    @SerializedName("series") val series: SeriesDto,
    @SerializedName("stories") val stories: StoriesDto,
    @SerializedName("thumbnail") val thumbnail: ThumbnailDto,
    @SerializedName("urls") val urls: List<UrlDto>
)

data class SeriesDto(
    @SerializedName("available") val available: Int,
    @SerializedName("collectionURI") val collectionURI: String,
    @SerializedName("items") val items: List<ItemDto>,
    @SerializedName("returned") val returned: Int
)

data class StoriesDto(
    @SerializedName("available") val available: Int,
    @SerializedName("collectionURI") val collectionURI: String,
    @SerializedName("items") val items: List<ItemXXXDto>,
    @SerializedName("returned") val returned: Int
)

data class ThumbnailDto(
    @SerializedName("extension") val extension: String,
    @SerializedName("path") val path: String
)

data class UrlDto(
    @SerializedName("type") val type: String,
    @SerializedName("url") val url: String
)