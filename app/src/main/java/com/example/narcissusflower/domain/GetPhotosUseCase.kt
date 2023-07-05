package com.example.narcissusflower.domain

import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.narcissusflower.data.remote.dtos.UnSplashPhoto
import com.example.narcissusflower.data.repos.UnSplashRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(
    private val repository: UnSplashRepository
) {

    private fun items(query: String): Flow<PagingData<UnSplashPhoto>> {
        return repository.getSearchResultStream(query)
    }

    fun items(
        coroutineScope: CoroutineScope,
        query: String,
        op: (Throwable) -> Unit
    ): SharedFlow<PagingData<UnSplashPhoto>> {
        return items(query = query)
            .cachedIn(coroutineScope)
            .catch { op(it) }
            .shareIn(
                scope = coroutineScope, started = SharingStarted.WhileSubscribed(), replay = 1
            )
    }
}