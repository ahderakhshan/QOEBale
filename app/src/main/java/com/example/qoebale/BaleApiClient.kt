package com.example.qoebale

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.HttpTimeout
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.Serializable
import java.io.File
import kotlin.system.measureTimeMillis
import io.ktor.http.Parameters

@Serializable
data class SendMessageRequest(
    val chat_id: String,
    val text: String,
    val reply_markup: ReplyMarkup? = null,
    val reply_to_message_id: Int? = null
)

@Serializable
data class ReplyMarkup(
    val keyboard: List<List<KeyboardButton>>,
    val resize_keyboard: Boolean = true,
    val one_time_keyboard: Boolean = true,
    val selective: Boolean = true
)

@Serializable
data class KeyboardButton(
    val text: String,
    val request_contact: Boolean? = null,
    val request_location: Boolean? = null
)

class BaleApiClient {
    private val client = HttpClient(OkHttp) {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }
    val clientphoto = HttpClient(CIO)



    suspend fun sendMessage(chatId: String, text: String): Float {
        val requestData = SendMessageRequest(
            chat_id = chatId,
            text = text
        )

        val time = measureTimeMillis {
            for(i in 1..10) {
                client.post<String>("https://tapi.bale.ai/bot1418026098:BwdEO97xAdUaIb86XyNDigImRVhQM16lFoo8M8Al/sendMessage") {
                    contentType(ContentType.Application.Json)
                    body = requestData
                }
            }
        }
        val average_time = 10*5*1000 / time
        return average_time.toFloat()

    }
    suspend fun SendPhoto(){

        val r: HttpResponse = clientphoto.submitForm(
            url = "https://tapi.bale.ai/bot1418026098:BwdEO97xAdUaIb86XyNDigImRVhQM16lFoo8M8Al/SendPhoto",
            formParameters = Parameters.build{
                append("chat_id","@mabahesv2")
                append("photo","https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png")
            }

        )
    }
}