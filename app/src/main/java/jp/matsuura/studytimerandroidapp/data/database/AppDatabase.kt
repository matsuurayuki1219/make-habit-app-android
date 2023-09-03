package jp.matsuura.studytimerandroidapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import jp.matsuura.studytimerandroidapp.data.database.dao.CategoryDao
import jp.matsuura.studytimerandroidapp.data.database.entity.CategoryDbEntity

@Database(entities = [CategoryDbEntity::class], version = 1)
@TypeConverters(Converter::class,)
abstract class AppDatabase : RoomDatabase() {

    abstract fun categoryDao() : CategoryDao

}