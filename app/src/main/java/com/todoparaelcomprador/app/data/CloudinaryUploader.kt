package com.todoparaelcomprador.app.data

import android.util.Base64
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.util.concurrent.TimeUnit

object CloudinaryUploader {
    // ⚠️ REEMPLAZA ESTOS DATOS CON LOS TUYOS DE CLOUDINARY
    private const val CLOUD_NAME = "fuhzuh4j"
    private const val API_KEY = "123413332362734"
    private const val API_SECRET = "uHbggrMIRYYGlEQeZwKLQXcfCvw"

    suspend fun subirImagen(imageBytes: ByteArray): String? = withContext(Dispatchers.IO) {
        try {
            val client = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()

            val base64Image = Base64.encodeToString(imageBytes, Base64.NO_WRAP)
            val url = "https://api.cloudinary.com/v1_1/$CLOUD_NAME/image/upload"
            
            // Cloudinary requiere autenticación básica con API Key y Secret
            val credential = Credentials.basic(API_KEY, API_SECRET)
            val formBody = FormBody.Builder()
                .add("file", "data:image/jpeg;base64,$base64Image")
                .build()

            val request = Request.Builder()
                .url(url)
                .post(formBody)
                .header("Authorization", credential)
                .build()

            val response = client.newCall(request).execute()
            val responseBody = response.body?.string()

            if (response.isSuccessful && responseBody != null) {
                val json = JSONObject(responseBody)
                return@withContext json.getString("secure_url")
            }
            return@withContext null
        } catch (e: Exception) {
            e.printStackTrace()
            return@withContext null
        }
    }
}
