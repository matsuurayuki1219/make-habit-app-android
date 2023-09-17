package jp.matsuura.makehabit.androidapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import jp.matsuura.makehabit.androidapp.data.database.entity.TransactionDbEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Query("SELECT * FROM `transaction`")
    fun getAll(): Flow<List<TransactionDbEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(transaction: TransactionDbEntity): Long

    @Query("SELECT * FROM `transaction` WHERE id = :transactionId AND category_id = :categoryId")
    fun getById(transactionId: Int, categoryId: Int): TransactionDbEntity

}