package com.emdp.data.domain

import com.google.gson.annotations.SerializedName

data class CharactersDto(
    @SerializedName("code") val code: Int,
    @SerializedName("data") val `data`: DataDto,
    @SerializedName("status") val status: String
)

data class DataDto(
    @SerializedName("results") val results: List<ResultDto>
)

data class ResultDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("comics") val comics: ElementsDto,
    @SerializedName("events") val events: ElementsDto,
    @SerializedName("series") val series: ElementsDto,
    @SerializedName("stories") val stories: ElementsDto,
    @SerializedName("thumbnail") val thumbnail: ThumbnailDto,
    @SerializedName("urls") val urls: List<UrlDto>
)

data class ElementsDto(
    @SerializedName("available") val available: Int,
    @SerializedName("items") val items: List<ItemDto>?
)

data class ItemDto(
    @SerializedName("name") val name: String,
    @SerializedName("resourceURI") val resourceURI: String,
    @SerializedName("type") val type: String?
)

data class ThumbnailDto(
    @SerializedName("extension") val extension: String,
    @SerializedName("path") val path: String
)

data class UrlDto(
    @SerializedName("type") val type: String,
    @SerializedName("url") val url: String
)