package com.sandeep.nspiretask.di

import android.content.Context
import androidx.room.Room
import com.sandeep.nspiretask.repository.RepoRoomRepository
import com.sandeep.nspiretask.room.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RoomDatabaseModule {

    @Singleton
    @Provides
    fun providesAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "nspire_db").build()
    }

    @Singleton
    @Provides
    fun provideDao(database: AppDatabase) = database.repoDao()

    @Singleton
    @Provides
    fun providesRepoDao(demoDatabase: AppDatabase): RepoRoomRepository{
        return RepoRoomRepository(demoDatabase)
    }
}