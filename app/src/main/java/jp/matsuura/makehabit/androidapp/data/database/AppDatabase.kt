package jp.matsuura.makehabit.androidapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import jp.matsuura.makehabit.androidapp.data.database.dao.CategoryDao
import jp.matsuura.makehabit.androidapp.data.database.dao.TransactionDao
import jp.matsuura.makehabit.androidapp.data.database.entity.CategoryDbEntity
import jp.matsuura.makehabit.androidapp.data.database.entity.TransactionDbEntity

@Database(
    entities = [CategoryDbEntity::class, TransactionDbEntity::class],
    version = 1,
)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao

    abstract fun transactionDao(): TransactionDao

}