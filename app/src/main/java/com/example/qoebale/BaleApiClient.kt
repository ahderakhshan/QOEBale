package com.example.qoebale

import io.ktor.client.*
import io.ktor.client.engine.*
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
import io.ktor.utils.io.ByteChannel
import java.io.BufferedInputStream
import java.net.URL
import java.net.URLConnection
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection


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
    val clientfile = HttpClient(CIO)



    suspend fun sendMessage(chatId: String, text: String): Double {
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
        return average_time.toDouble()

    }
    suspend fun SendPhoto(): Double {
        val time = measureTimeMillis {
            val r: HttpResponse = clientfile.submitForm(
                url = "https://tapi.bale.ai/bot1418026098:BwdEO97xAdUaIb86XyNDigImRVhQM16lFoo8M8Al/SendPhoto",
                formParameters = Parameters.build{
                    append("chat_id","@mabahesv2")
                    append("photo","https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png")
                }

            )
        }
        val result = (5969 * 1000)/time // size of image is 5969 byte
        return result.toDouble()
    }
    suspend fun sendAudio(): Double {
        val time = measureTimeMillis {
            val r: HttpResponse = clientfile.submitForm(
                url = "https://tapi.bale.ai/bot1418026098:BwdEO97xAdUaIb86XyNDigImRVhQM16lFoo8M8Al/Sendaudio",
                formParameters = Parameters.build {
                    append("chat_id", "@mabahesv2")
                    append(
                        "audio",
                        "https://dls.music-fa.com/tagdl/1401/Amir%20Kermanshahi%20-%20Mashhadia%20Tehronia%20(320).mp3"
                    )
                }
            )
        }
        val result = (8.58*1000*1000000)/time //size of audio is 8.58 mb
        return result.toDouble()
    }
    suspend fun sendDoc(): Double {
        val time = measureTimeMillis {
            val r: HttpResponse = clientfile.submitForm(
                url = "https://tapi.bale.ai/bot1418026098:BwdEO97xAdUaIb86XyNDigImRVhQM16lFoo8M8Al/Senddocument",
                formParameters = Parameters.build {
                    append("chat_id", "@mabahesv2")
                    append(
                        "document",
                        "https://drive.google.com/file/d/1q0arRJkExGePWCyS0N1dz6DhmOv2Lb-5/view?usp=sharing"
                    )
                }
            )
        }
        val result = (78.46*1000*1000)/(time)
        return result.toDouble()
    }
    suspend fun sendVideo(): Double {
        val time = measureTimeMillis {
            clientfile.submitForm (
                url = "https://tapi.bale.ai/bot1418026098:BwdEO97xAdUaIb86XyNDigImRVhQM16lFoo8M8Al/Sendvideo",
                formParameters = Parameters.build {
                    append("chat_id", "@mabahesv2")
                    append("video",
                        "https://aspb1.asset.aparat.com/aparat-video/4ae9be770d355ea038af40407364d41411919097-144p.mp4?wmsAuthSign=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbiI6IjY0MDNmMmRmMjMxZmI3MzQwMGM0MTFkMmRmMjgxNzVkIiwiZXhwIjoxNjg4NjY2MjcyLCJpc3MiOiJTYWJhIElkZWEgR1NJRyJ9.7WOKbKrzlxbLnih56uu4WHHv_-PZ1p_t4Nn4IGpnlHE")
                }

            )
        }
        val result = ( 13940 * 1000 * 1000) / time // 13940 kbyte is size of video
        return result.toDouble()
    }
    suspend fun getAdminsInfo(): Double {
        val obj = URL("https://tapi.bale.ai/bot1418026098:BwdEO97xAdUaIb86XyNDigImRVhQM16lFoo8M8Al/getChatAdministrators?chat_id=@mabahesv2")
        val connection = obj.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        val time = measureTimeMillis {
            connection.responseCode
        }
        connection.disconnect()
        val result = 483 * 1000 / time
        return result.toDouble()
    }
    suspend fun getFileInfo(): Double{
        val obj = URL("https://tapi.bale.ai/bot1418026098:BwdEO97xAdUaIb86XyNDigImRVhQM16lFoo8M8Al/getfile?file_id=1418026098:559251684355350273:1:c3f6fd3a43f364514be600e821b55abe1ef92213e01ce0169c3e2e6ac98129abc4e668c45e70295222fb0d6eece7e68cd554fc4790e9387a")
        val connection = obj.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        val time = measureTimeMillis {
            connection.responseCode
        }
        connection.disconnect()
        val result = 729 * 1000 / time
        return result.toDouble()
    }
}