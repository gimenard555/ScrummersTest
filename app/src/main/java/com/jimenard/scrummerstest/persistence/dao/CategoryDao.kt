package com.jimenard.scrummerstest.persistence.dao

import androidx.room.Dao
import androidx.room.Query
import com.jimenard.scrummerstest.persistence.entities.Category

@Dao
abstract class CategoryDao : GenericDao<Category> {

    @Query("select * from ${Category.TABLE_NAME}")
    abstract suspend fun getAllCategories(): List<Category>
}