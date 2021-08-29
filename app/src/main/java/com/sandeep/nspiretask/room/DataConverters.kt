package com.sandeep.nspiretask.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.sandeep.nspiretask.entities.RepoDataUser


class DataConverters {
    @TypeConverter
    fun StringToRepoDataUser(value: String?): RepoDataUser? {
        return Gson().fromJson(value, RepoDataUser::class.java)
    }

    @TypeConverter
    fun RepoDataUserToString(data: RepoDataUser?): String? {
        return Gson().toJson(data)
    }
}