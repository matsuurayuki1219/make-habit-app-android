package jp.matsuura.studytimerandroidapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import jp.matsuura.studytimerandroidapp.data.database.entity.TransactionDbEntity

@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(transaction: TransactionDbEntity): Long

    @Query("SELECT * FROM `transaction` WHERE category_id = :categoryId")
    fun getById(categoryId: Int): TransactionDbEntity

}