package com.example.narcissusflower.data.repos

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.narcissusflower.data.datasource.UnSplashPagingSource
import com.example.narcissusflower.data.remote.UnSplashPhoto
import com.example.narcissusflower.data.remote.UnSplashService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UnSplashRepository @Inject constructor(
    private val service: UnSplashService
) {

    fun getSearchResultStream(query: String): Flow<PagingData<UnSplashPhoto>> {
        val pagingConfig = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false)
        return Pager(config = pagingConfig) {
            UnSplashPagingSource(service, query)
        }.flow
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 25
    }
}