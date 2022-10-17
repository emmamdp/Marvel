package com.emdp.data.domain

import com.emdp.domain.domain.*

private const val DEFAULT_STRING_VALUE = ""

fun FailureDto.dtoToBo(): FailureBo = when (this) {
    FailureDto.NoConnection -> FailureBo.NoConnection
    FailureDto.NoData -> FailureBo.NoData
    FailureDto.Unknown -> FailureBo.Unknown
    is FailureDto.RequestError -> FailureBo.RequestError(msg = msg ?: DEFAULT_STRING_VALUE)
    is FailureDto.ServerError -> FailureBo.ServerError(msg = msg ?: DEFAULT_STRING_VALUE)
    is FailureDto.Error -> FailureBo.Error(msg = msg ?: DEFAULT_STRING_VALUE)
}

fun CharactersDto.dtoToBo() =
    CharactersBo(attributionHTML, attributionText, code, copyright, `data`.dtoToBo(), etag, status)

fun DataDto.dtoToBo() =
    DataBo(count, limit, offset, results.dtoToBoResultList(), total)

fun List<ResultDto>.dtoToBoResultList(): List<ResultBo> = map { it.dtoToBo() }

fun ResultDto.dtoToBo() = ResultBo(
    comics.dtoToBo(),
    description,
    events.dtoToBo(),
    id,
    modified,
    name,
    resourceURI,
    series.dtoToBo(),
    stories.dtoToBo(),
    thumbnail.dtoToBo(),
    urls.dtoToBoUrlList()
)

fun ComicsDto.dtoToBo(): ComicsBo =
    ComicsBo(available, collectionURI, items.dtoToBoItemList(), returned)

fun EventsDto.dtoToBo(): EventsBo =
    EventsBo(available, collectionURI, items.dtoToBoItemList(), returned)

fun SeriesDto.dtoToBo(): SeriesBo =
    SeriesBo(available, collectionURI, items.dtoToBoItemList(), returned)

fun StoriesDto.dtoToBo(): StoriesBo =
    StoriesBo(available, collectionURI, items.dtoToBoItemXXXList(), returned)

fun ThumbnailDto.dtoToBo(): ThumbnailBo = ThumbnailBo(extension, path)

fun List<UrlDto>.dtoToBoUrlList(): List<UrlBo> = map { it.dtoToBo() }

fun UrlDto.dtoToBo(): UrlBo = UrlBo(type, url)

fun List<ItemDto>.dtoToBoItemList(): List<ItemBo> = map { it.dtoToBo() }

fun ItemDto.dtoToBo(): ItemBo = ItemBo(name, resourceURI)

fun List<ItemXXXDto>.dtoToBoItemXXXList(): List<ItemXXXBo> = map { it.dtoToBo() }

fun ItemXXXDto.dtoToBo(): ItemXXXBo = ItemXXXBo(name, resourceURI, type)