package com.emdp.domain.domain


data class CharactersBo(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val `data`: DataBo,
    val etag: String,
    val status: String
)

data class ComicsBo(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemBo>,
    val returned: Int
)

data class DataBo(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<ResultBo>,
    val total: Int
)

data class EventsBo(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemBo>,
    val returned: Int
)

data class ItemBo(
    val name: String,
    val resourceURI: String
)

data class ItemXXXBo(
    val name: String,
    val resourceURI: String,
    val type: String
)

data class ResultBo(
    val comics: ComicsBo,
    val description: String,
    val events: EventsBo,
    val id: Int,
    val modified: String,
    val name: String,
    val resourceURI: String,
    val series: SeriesBo,
    val stories: StoriesBo,
    val thumbnail: ThumbnailBo,
    val urls: List<UrlBo>
)

data class SeriesBo(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemBo>,
    val returned: Int
)

data class StoriesBo(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemXXXBo>,
    val returned: Int
)

data class ThumbnailBo(
    val extension: String,
    val path: String
)

data class UrlBo(
    val type: String,
    val url: String
)