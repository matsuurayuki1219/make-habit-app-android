package jp.matsuura.makehabit.androidapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import jp.matsuura.makehabit.androidapp.data.database.entity.CategoryDbEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Query("SELECT * FROM category")
    fun getAll(): Flow<List<CategoryDbEntity>>

    @Query("SELECT * FROM category WHERE id = :categoryId")
    fun getById(categoryId: Int): CategoryDbEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategory(category: CategoryDbEntity)

    @Query("DELETE FROM category WHERE id = :id")
    fun deleteCategory(id: Int)

}