package com.sandeep.nspiretask.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.sandeep.nspiretask.api.APIService
import com.sandeep.nspiretask.common.UiState
import com.sandeep.nspiretask.entities.ContributeDataN
import org.json.JSONArray
import java.util.*
import kotlin.collections.ArrayList


interface RepoDetailsRepo {
    suspend fun getRepoData(url : String): LiveData<UiState>?
}
class RepoDetailsRepositary(val api: APIService,val gson: Gson) : RepoDetailsRepo {
    private var data_model: MutableLiveData<UiState>? = null
    override suspend fun getRepoData(url: String): MutableLiveData<UiState>? {
        val result1 =   api.getContributersData(url)
        val result = result1.body()
        if(result1.isSuccessful) {
   /*         val dataList = JSONArray(result?.string())
            val arrayList = ArrayList<ContributeDataN>()
            for (i in 0 until dataList.length()) {
                val item = dataList.getJSONObject(i)
                arrayList.add(gson.fromJson(item.toString(), ContributeDataN::class.java))
            }*/
             data_model = MutableLiveData<UiState>(UiState.Success(result))
        } else {
            data_model = MutableLiveData<UiState>(UiState.Error(result1.code(),result1.message()))
        }
        return data_model
    }
}