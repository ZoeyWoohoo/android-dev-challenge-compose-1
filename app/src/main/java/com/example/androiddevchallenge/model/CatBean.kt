package com.example.androiddevchallenge.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class CatBean(
    val avatar: String,
    val name: String,
    val desc: String,
) : Parcelable