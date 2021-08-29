package com.sandeep.nspiretask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sandeep.nspiretask.common.UiState
import com.sandeep.nspiretask.repository.RepoDetailsRepositary
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class RepoDetailsViewModel @Inject constructor(
    val repodetailsRepository: RepoDetailsRepositary
) : ViewModel() {
    private val _datModel : MutableLiveData<UiState> by lazy {  MutableLiveData<UiState>(UiState.Idle) }
    val data_model get(): LiveData<UiState> = _datModel

    internal  fun getData(queryText: String) {
        _datModel.postValue(UiState.Loading)
        val exceptionHandler = CoroutineExceptionHandler {_, _, ->
            _datModel.postValue(UiState.Error(1,"Execption"))
        }

        viewModelScope.launch (exceptionHandler)  {
            repodetailsRepository.getRepoData(queryText)?.let {
                _datModel.postValue(it.value)
            }
        }
    }


}