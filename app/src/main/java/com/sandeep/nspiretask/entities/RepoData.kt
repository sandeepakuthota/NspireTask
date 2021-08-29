package com.sandeep.nspiretask.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "repos")
@Parcelize
data class RepoData(
    @PrimaryKey(autoGenerate = true)
    val idn: Int,
    @SerializedName("id") val id: String,
    @SerializedName("full_name") val full_name: String,
    @SerializedName("description") val description: String,
    @SerializedName("contributors_url") val contributors_url: String,
    @SerializedName("owner") val owner: RepoDataUser,
    @SerializedName("html_url") val html_url: String
) : Parcelable