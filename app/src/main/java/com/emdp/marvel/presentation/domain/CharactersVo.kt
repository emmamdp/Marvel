package com.emdp.marvel.presentation.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


data class CharactersVo(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val `data`: DataVo,
    val etag: String,
    val status: String
)

data class DataVo(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<ResultVo>,
    val total: Int
)

@Parcelize
data class ResultVo(
    val comics: ComicsVo,
    val description: String,
    val events: EventsVo,
    val id: Int,
    val modified: String,
    val name: String,
    val resourceURI: String,
    val series: SeriesVo,
    val stories: StoriesVo,
    val thumbnail: ThumbnailVo,
    val urls: List<UrlVo>
): Parcelable

@Parcelize
data class ComicsVo(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemVo>,
    val returned: Int
): Parcelable

@Parcelize
data class EventsVo(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemVo>,
    val returned: Int
): Parcelable

@Parcelize
data class SeriesVo(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemVo>,
    val returned: Int
): Parcelable

@Parcelize
data class StoriesVo(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemXXXVo>,
    val returned: Int
): Parcelable

@Parcelize
data class ThumbnailVo(
    val extension: String,
    val path: String
): Parcelable

@Parcelize
data class UrlVo(
    val type: String,
    val url: String
): Parcelable

@Parcelize
data class ItemVo(
    val name: String,
    val resourceURI: String
): Parcelable

@Parcelize
data class ItemXXXVo(
    val name: String,
    val resourceURI: String,
    val type: String
): Parcelable