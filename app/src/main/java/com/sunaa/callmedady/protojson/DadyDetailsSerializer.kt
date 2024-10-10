package com.sunaa.callmedady.protojson

import androidx.datastore.core.Serializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

object DadyDetailsSerializer : Serializer<DadyDetails> {
    override val defaultValue: DadyDetails
        get() = DadyDetails(
            profession = "000",
            nameOfProfessional = "***",
            liking = true
        )

    override suspend fun readFrom(input: InputStream): DadyDetails {
        // Define how we read data from InputStream here we parse json from Input to our DadyDetails Object
        return try {
            Json.decodeFromString(
                deserializer = DadyDetails.serializer(),
                string = input.readBytes().decodeToString()
            )
        } catch (e: SerializationException) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: DadyDetails, output: OutputStream) {
        output.write(
            Json.encodeToString(
                serializer = DadyDetails.serializer(),
                value = t
            ).encodeToByteArray()
        )
    }
}
