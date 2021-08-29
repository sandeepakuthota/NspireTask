package com.sandeep.nspiretask.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sandeep.nspiretask.entities.RepoData


@Database(entities = [RepoData::class], version = AppDatabase.VERSION,exportSchema = false)
@TypeConverters(DataConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun repoDao(): RepoDao

    companion object {
        const val VERSION = 1
    }


}