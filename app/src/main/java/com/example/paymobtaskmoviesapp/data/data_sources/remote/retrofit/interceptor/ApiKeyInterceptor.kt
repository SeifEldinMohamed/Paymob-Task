package com.example.paymobtaskmoviesapp.data.data_sources.remote.retrofit.interceptor

import com.example.paymobtaskmoviesapp.data.Constants.Companion.API_KEY
import com.example.paymobtaskmoviesapp.data.Constants.Companion.API_KEY_Value
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url

        // Add the API key to the URL query parameters
        val urlWithApiKey = originalUrl.newBuilder()
            .addQueryParameter(API_KEY, API_KEY_Value)
            .build()

        // Build a new request with the modified URL
        val requestWithApiKey = originalRequest.newBuilder()
            .url(urlWithApiKey)
            .build()

        return chain.proceed(requestWithApiKey)
    }
}