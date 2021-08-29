package com.sandeep.nspiretask.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RepoDataUser(@SerializedName("avatar_url")val avatar_url: String): Parcelable