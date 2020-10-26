package com.jimenard.scrummerstest.persistence.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

/**
 * Dao generico para las operaciones basicas sobre la base de datos
 */
interface GenericDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRow(entity: T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRows(entity: List<T>): Array<Long>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateRow(entity: T)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateRows(entity: List<T>)
}