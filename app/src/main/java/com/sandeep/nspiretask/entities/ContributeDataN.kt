package com.sandeep.nspiretask.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ContributeDataN(@SerializedName("login") val login: String,
                           @SerializedName("avatar_url") val avatar_url: String,
                           @SerializedName("html_url") val html_url: String,
                           @SerializedName("type")val type: String,
                           @SerializedName("contributions")val contributions: String):Parcelable