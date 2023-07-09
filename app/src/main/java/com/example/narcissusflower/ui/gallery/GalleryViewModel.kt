package com.example.narcissusflower.ui.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.narcissusflower.data.remote.dtos.UnSplashPhoto
import com.example.narcissusflower.data.repos.UnSplashRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val repository: UnSplashRepository
) : ViewModel() {

    fun search(query: String): SharedFlow<PagingData<UnSplashPhoto>> {
        return repository.getSearchResultStream(query).cachedIn(viewModelScope).shareIn(
            scope = viewModelScope, started = SharingStarted.WhileSubscribed(), replay = 1
        )
    }
}