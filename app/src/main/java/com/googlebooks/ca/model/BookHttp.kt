package com.googlebooks.ca.model

import com.google.gson.Gson
import com.googlebooks.ca.util.Dominios
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.Exception
import java.util.concurrent.TimeUnit

object BookHttp {

    private val client = OkHttpClient.Builder()
        .readTimeout(10, TimeUnit.SECONDS)
        .connectTimeout(5, TimeUnit.SECONDS)
        .build()

    fun searchBook(q: String) : SearchResult?{
        val request = Request.Builder()
            .url(String.format(Dominios.BOOK_URL, q))
            .build()

        try{
            val response = client.newCall(request).execute()
            val json = response.body?.string()
            return Gson().fromJson(json, SearchResult::class.java)

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}