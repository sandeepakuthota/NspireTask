package com.sandeep.nspiretask.api

import com.sandeep.nspiretask.entities.ContributeDataN
import com.sandeep.nspiretask.entities.DataModel
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url
import java.util.ArrayList

interface APIService {
    @GET("search/repositories?")
    suspend fun searchRepos(
        @Query("q") searchTerm: String,
        @Query("per_page") per_page: String,
        @Query("page") page: String
    ): Response<DataModel>

    @GET
    suspend fun getContributersData(@Url url: String): Response<ArrayList<ContributeDataN>>

}