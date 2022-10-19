package com.emdp.marvel.presentation.domain

import com.emdp.domain.domain.*

fun FailureBo.boToVo(): FailureVo = when (this) {
    FailureBo.NoConnection -> FailureVo.NoConnection
    FailureBo.NoData -> FailureVo.NoData
    FailureBo.Unknown -> FailureVo.Unknown
    is FailureBo.Error -> FailureVo.Error(msg = msg)
    else -> FailureVo.Error(msg = msg)
}

fun CharactersBo.boToVo() =
    CharactersVo(attributionHTML, attributionText, code, copyright, `data`.boToVo(), etag, status)

fun DataBo.boToVo() =
    DataVo(count, limit, offset, results.boToVoResultList(), total)

fun List<ResultBo>.boToVoResultList(): List<ResultVo> = map { it.boToVo() }

fun ResultBo.boToVo() = ResultVo(
    comics.boToVo(),
    description,
    events.boToVo(),
    id,
    modified,
    name,
    resourceURI,
    series.boToVo(),
    stories.boToVo(),
    thumbnail.boToVo(),
    urls.boToVoUrlList()
)

fun ComicsBo.boToVo(): ComicsVo =
    ComicsVo(available, collectionURI, items.boToVoItemList(), returned)

fun EventsBo.boToVo(): EventsVo =
    EventsVo(available, collectionURI, items.boToVoItemList(), returned)

fun SeriesBo.boToVo(): SeriesVo =
    SeriesVo(available, collectionURI, items.boToVoItemList(), returned)

fun StoriesBo.boToVo(): StoriesVo =
    StoriesVo(available, collectionURI, items.boToVoItemXXXList(), returned)

fun ThumbnailBo.boToVo(): ThumbnailVo = ThumbnailVo(extension, path)

fun List<UrlBo>.boToVoUrlList(): List<UrlVo> = map { it.boToVo() }

fun UrlBo.boToVo(): UrlVo = UrlVo(type, url)

fun List<ItemBo>.boToVoItemList(): List<ItemVo> = map { it.boToVo() }

fun ItemBo.boToVo(): ItemVo = ItemVo(name, resourceURI)

fun List<ItemXXXBo>.boToVoItemXXXList(): List<ItemXXXVo> = map { it.boToVo() }

fun ItemXXXBo.boToVo(): ItemXXXVo = ItemXXXVo(name, resourceURI, type)