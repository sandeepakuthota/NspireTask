package com.sandeep.nspiretask.room

import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sandeep.nspiretask.entities.RepoData

@Dao
interface RepoDao {
    @Query("select * from repos")
    suspend fun getall_items(): List<RepoData>

    @Query("select * from repos")
    fun getall_items_n(): List<RepoData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(value:List<RepoData>)
}