package com.sandeep.nspiretask.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataModel(@SerializedName("items") val items: List<RepoData>) : Parcelable