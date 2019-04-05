package com.example.pelicudex

import android.net.Uri
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.*

class NetworkUtils {
    companion object Factory {
        val MOVIES_API_BASE_URL = "http://www.omdbapi.com/"
        val TOKEN_API = "abec766c"

        fun buildUrl(movieName: String): URL {
            val builtUri = Uri.parse(MOVIES_API_BASE_URL)
                    .buildUpon()
                    .appendQueryParameter("apikey", TOKEN_API)
                    .appendQueryParameter("t", movieName)
                    .build()

            return try {
                URL(builtUri.toString())
            } catch (e: MalformedURLException) {
                URL("")
            }

        }

        @Throws(IOException::class)
        fun getResponseFromHttpUrl(url: URL): String? {
            val urlConnection = url.openConnection() as HttpURLConnection
            try {
                val `in` = urlConnection.inputStream

                val scanner = Scanner(`in`)
                scanner.useDelimiter("\\A")

                val hasInput = scanner.hasNext()
                return if (hasInput) {
                    scanner.next()
                } else {
                    ""
                }
            } finally {
                urlConnection.disconnect()
            }
        }

    }
}