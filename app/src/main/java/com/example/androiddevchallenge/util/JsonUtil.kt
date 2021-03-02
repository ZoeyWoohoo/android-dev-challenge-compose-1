package com.example.androiddevchallenge.util

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlin.reflect.KClass

object JsonUtil {

    private val mCore: Moshi by lazy { createCore() }

    fun <T : Any> adapter(kClass: KClass<T>): JsonAdapter<T> = mCore.adapter(kClass.java)

    fun <T : Any> adapterList(kClass: KClass<T>): JsonAdapter<List<T>> = mCore.adapter(
        Types.newParameterizedType(List::class.java, kClass.java)
    )

    fun <T> adapter(clazz: Class<T>): JsonAdapter<T> = mCore.adapter(clazz)

    fun <T> adapterList(clazz: Class<T>): JsonAdapter<List<T>> = mCore.adapter(
        Types.newParameterizedType(List::class.java, clazz)
    )

    private fun createCore(): Moshi {
        return Moshi.Builder().build()
    }
}