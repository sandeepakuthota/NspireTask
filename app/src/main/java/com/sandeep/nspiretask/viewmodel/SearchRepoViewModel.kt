package com.sandeep.nspiretask.viewmodel

import androidx.lifecycle.*
import com.sandeep.nspiretask.common.UiState
import com.sandeep.nspiretask.entities.RepoData
import com.sandeep.nspiretask.repository.RepoRoomRepository
import com.sandeep.nspiretask.repository.SearchRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchRepoViewModel @Inject constructor(
    val searchRepository: SearchRepository, val db: RepoRoomRepository
) : ViewModel() {
    private val _datModel: MutableLiveData<UiState> by lazy { MutableLiveData<UiState>(UiState.Idle) }
    val data_model get(): LiveData<UiState> = _datModel
    var pageNumber = 1
    private var queryText = "test"
    internal fun getData() {
        if (pageNumber == 1)
            _datModel.postValue(UiState.Loading)
        val exceptionHandler = CoroutineExceptionHandler { _, _ ->
            _datModel.postValue(UiState.Error(1, "Execption"))
        }
        viewModelScope.launch(exceptionHandler) {
            searchRepository.getRepoData(queryText, pageNumber)?.let {
                _datModel.postValue(it.value)
                if (pageNumber == 1)
                    if (it.value is UiState.Success<*>) {
                        db.insertAll((it.value as UiState.Success<*>).result as List<RepoData>)
                    }
            }
        }
    }

    fun updateQueryString(queryText: String) {
        pageNumber = 1
        this.queryText = queryText
        getData()
    }

    fun loadMore() {
        pageNumber += 1
        getData()
    }

    fun getOfflineData() {
        _datModel.postValue(UiState.Loading)
        val exceptionHandler = CoroutineExceptionHandler { _, _ ->
            _datModel.postValue(UiState.Error(1, "Execption"))
        }
        viewModelScope.launch(exceptionHandler) {
            db.getRepos().let {
                    _datModel.postValue(UiState.Success(it))
                }
            }
    }
}