package com.emdp.marvel.presentation.domain

import com.emdp.domain.domain.*

fun FailureBo.boToVo(): FailureVo = when (this) {
    FailureBo.NoConnection -> FailureVo.NoConnection
    FailureBo.NoData -> FailureVo.NoData
    FailureBo.Unknown -> FailureVo.Unknown
    is FailureBo.Error -> FailureVo.Error(msg = msg)
    else -> FailureVo.Error(msg = msg)
}

fun List<ResultBo>.boToVoResultList(): List<CharacterVo> = map { it.boToVo() }

fun ResultBo.boToVo() = CharacterVo(
    id,
    name,
    description,
    comics.boToVo(),
    events.boToVo(),
    series.boToVo(),
    stories.boToVo(),
    thumbnail,
    urls.boToVoUrlList()
)

fun ElementsBo.boToVo(): ElementsVo = ElementsVo(available, items?.boToVoItemList())

fun List<ItemBo>.boToVoItemList(): List<ItemVo> = map { it.boToVo() }

fun ItemBo.boToVo(): ItemVo = ItemVo(name, resourceURI, type)

fun List<UrlBo>.boToVoUrlList(): List<UrlVo> = map { it.boToVo() }

fun UrlBo.boToVo(): UrlVo = UrlVo(type, url)