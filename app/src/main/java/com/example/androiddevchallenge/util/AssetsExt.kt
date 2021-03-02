package com.example.androiddevchallenge.util

import android.content.res.AssetManager

fun AssetManager.readAssetsJson(fileName: String): String {
    return open(fileName).bufferedReader().use { it.readText() }
}