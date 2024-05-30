package com.skydoves.pokedex.upgrader.utils

import com.google.firebase.Timestamp
import com.google.gson.*
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.Date

class TimestampDeserializer : JsonDeserializer<Timestamp> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Timestamp {
        return if (json != null && json.isJsonPrimitive && json.asJsonPrimitive.isString) {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
            try {
                val date = dateFormat.parse(json.asString)
                Timestamp(date)
            } catch (e: Exception) {
                Timestamp.now()
            }
        } else {
            Timestamp.now()
        }
    }
}
