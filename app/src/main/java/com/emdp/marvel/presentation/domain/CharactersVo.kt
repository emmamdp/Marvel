package com.emdp.marvel.presentation.domain


data class CharacterVo(
    val id: Int,
    val name: String,
    val description: String,
    val comics: ElementsVo,
    val events: ElementsVo,
    val series: ElementsVo,
    val stories: ElementsVo,
    val thumbnail: String,
    val urls: List<UrlVo>
)

data class ElementsVo(
    val available: Int,
    val items: List<ItemVo>?
)

data class ItemVo(
    val name: String,
    val resourceURI: String,
    val type: String?
)

data class UrlVo(
    val type: String,
    val url: String
)
