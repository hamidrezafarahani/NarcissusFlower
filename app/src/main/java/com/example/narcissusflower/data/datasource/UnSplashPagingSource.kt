package com.example.narcissusflower.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.narcissusflower.data.remote.dtos.UnSplashPhoto
import com.example.narcissusflower.data.remote.UnSplashService

private const val UNSPLASH_STARTING_PAGE_INDEX = 1

class UnSplashPagingSource(
    private val service: UnSplashService,
    private val query: String
) : PagingSource<Int, UnSplashPhoto>() {

    override fun getRefreshKey(state: PagingState<Int, UnSplashPhoto>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnSplashPhoto> {
        val page = params.key ?: UNSPLASH_STARTING_PAGE_INDEX
        return try {
            val response = service.searchPhotos(query, page, params.loadSize)
            val photos = response.results
            LoadResult.Page(
                data = photos,
                prevKey = if (page == UNSPLASH_STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (page == response.totalPage) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}