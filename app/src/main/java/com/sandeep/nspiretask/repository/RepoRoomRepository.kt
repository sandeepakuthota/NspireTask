package com.sandeep.nspiretask.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sandeep.nspiretask.common.UiState
import com.sandeep.nspiretask.entities.RepoData
import com.sandeep.nspiretask.room.AppDatabase
import com.sandeep.nspiretask.room.RepoDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class RepoRoomRepository @Inject constructor(demoDatabase: AppDatabase)   {
    private var repoDao: RepoDao = demoDatabase.repoDao()
    suspend fun  getRepos(): List<RepoData> {
        return repoDao.getall_items()
    }

    fun insertAll(repos: List<RepoData>) {
        CoroutineScope(Dispatchers.IO).launch {
            repoDao.insertAll(repos)
        }
    }

}