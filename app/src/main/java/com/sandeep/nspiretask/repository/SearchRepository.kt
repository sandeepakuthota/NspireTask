package com.sandeep.nspiretask.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sandeep.nspiretask.api.APIService
import com.sandeep.nspiretask.common.APIResponce
import com.sandeep.nspiretask.common.UiState
import com.sandeep.nspiretask.entities.RepoData

interface  searchRepo {
    suspend fun getRepoData(qearyString : String, pageNumber : Int): LiveData<UiState>?
}
class SearchRepository(val api: APIService) : searchRepo {
    private var data_model: MutableLiveData<UiState>? = null
    override suspend fun getRepoData(qearyString: String, pageNumber: Int): MutableLiveData<UiState>? {
      val result =   api.searchRepos(qearyString,"10",pageNumber.toString())
        if(result.isSuccessful) {
            data_model = MutableLiveData<UiState>(UiState.Success(result.body()?.items))
        } else {
            data_model = MutableLiveData<UiState>(UiState.Error(result.code(),result.message()))
        }
        return data_model
    }
}