package com.example.muuvdemo.main_screen.data.local.entities

data class PaginatedDataState<T>(
    val items: List<T> = emptyList(),
    val loading: Boolean = true,
    val hasNextPage: Boolean = true,
    val nextPageToLoad: Int = 1,
    val errorLoadingLastPage: Throwable? = null,
    val pageSize: Int = 1
) {

    fun onLoading() = copy(errorLoadingLastPage = null, loading = true)

    fun onPageLoaded(page: DataPage<T>): PaginatedDataState<T> {
        var nextPageToLoad = nextPageToLoad
        val newItemsList = if (items.isNotEmpty() && items.size.rem(pageSize) == 0) {
            // We are currently bucket full in all pages loaded so next time we need to load a new page
            nextPageToLoad++
            items.plus(page.data)
        } else {
            // We have loaded the last know page so we need to insert from its start index
            val lastFullPageIndex = pageSize * (nextPageToLoad - 1)
            items.subList(0, lastFullPageIndex).plus(page.data)
        }
        return PaginatedDataState(
            newItemsList,
            false,
            newItemsList.size < page.total,
            nextPageToLoad,
            null,
            pageSize
        )
    }

    fun onPageError(error: Throwable) = copy(errorLoadingLastPage = error, loading = false)
}
