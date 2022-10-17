package com.emdp.marvel.presentation.domain


data class CharactersVo(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val `data`: DataVo,
    val etag: String,
    val status: String
)

data class ComicsVo(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemVo>,
    val returned: Int
)

data class DataVo(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<ResultVo>,
    val total: Int
)

data class EventsVo(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemVo>,
    val returned: Int
)

data class ItemVo(
    val name: String,
    val resourceURI: String
)

data class ItemXXXVo(
    val name: String,
    val resourceURI: String,
    val type: String
)

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
)

data class SeriesVo(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemVo>,
    val returned: Int
)

data class StoriesVo(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemXXXVo>,
    val returned: Int
)

data class ThumbnailVo(
    val extension: String,
    val path: String
)

data class UrlVo(
    val type: String,
    val url: String
)