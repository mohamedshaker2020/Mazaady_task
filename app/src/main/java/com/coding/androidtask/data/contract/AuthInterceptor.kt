package com.coding.androidtask.data.contract

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("private-key", "3%o8i}_;3D4bF]G5@22r2)Et1&mLJ4?$@+16")

            .build()
        return chain.proceed(request)
    }
}