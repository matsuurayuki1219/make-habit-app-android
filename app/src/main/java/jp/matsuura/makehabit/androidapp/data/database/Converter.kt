package jp.matsuura.makehabit.androidapp.data.database

import androidx.room.TypeConverter
import java.time.Instant

object Converter {

    @TypeConverter
    fun fromInstant(value: Instant): Long {
        return value.toEpochMilli()
    }

    @TypeConverter
    fun toInstant(value: Long): Instant {
        return Instant.ofEpochMilli(value)
    }

}