package com.emdp.domain.domain


data class CharactersBo(
    val code: Int,
    val `data`: DataBo,
    val status: String
)

data class DataBo(
    val results: List<ResultBo>
)

data class ResultBo(
    val id: Int,
    val name: String,
    val description: String,
    val comics: ElementsBo,
    val events: ElementsBo,
    val series: ElementsBo,
    val stories: ElementsBo,
    val thumbnail: String,
    val urls: List<UrlBo>
)

data class ElementsBo(
    val available: Int,
    val items: List<ItemBo>?
)

data class ItemBo(
    val name: String,
    val resourceURI: String,
    val type: String?
)

data class UrlBo(
    val type: String,
    val url: String
)