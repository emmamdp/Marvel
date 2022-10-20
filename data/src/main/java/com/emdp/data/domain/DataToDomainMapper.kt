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

fun CharactersDto.dtoToBo() = CharactersBo(code, `data`.dtoToBo(), status)

fun DataDto.dtoToBo() = DataBo(results.dtoToBoResultList())

fun List<ResultDto>.dtoToBoResultList(): List<ResultBo> = map { it.dtoToBo() }

fun ResultDto.dtoToBo() = ResultBo(
    id,
    name,
    description,
    comics.dtoToBo(),
    events.dtoToBo(),
    series.dtoToBo(),
    stories.dtoToBo(),
    thumbnail.dtoToStr(),
    urls.dtoToBoUrlList()
)

fun ElementsDto.dtoToBo(): ElementsBo = ElementsBo(available, items?.dtoToBoItemList())

fun ThumbnailDto.dtoToStr(): String = "${this.path}.${this.extension}"

fun List<ItemDto>.dtoToBoItemList(): List<ItemBo> = map { it.dtoToBo() }

fun ItemDto.dtoToBo(): ItemBo = ItemBo(name, resourceURI, type)

fun List<UrlDto>.dtoToBoUrlList(): List<UrlBo> = map { it.dtoToBo() }

fun UrlDto.dtoToBo(): UrlBo = UrlBo(type, url)