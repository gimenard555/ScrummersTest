package com.jimenard.scrummerstest.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.jimenard.scrummerstest.persistence.dao.CarDao
import com.jimenard.scrummerstest.persistence.dao.CategoryDao
import com.jimenard.scrummerstest.persistence.entities.Car
import com.jimenard.scrummerstest.persistence.entities.Category
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

@Database(
    entities = [Car::class,
        Category::class],
    version = ScrummersDb.VERSION,
    exportSchema = false
)
/**
 * Modelo de la base de datos
 */
abstract class ScrummersDb : RoomDatabase() {

    abstract fun getCarDao(): CarDao
    abstract fun getCategoryDao(): CategoryDao

    companion object {
        private const val DATABASE_NAME = "ScrummersDb"
        const val VERSION = 1
        var INSTANCE: ScrummersDb? = null

        fun getDatabase(context: Context): ScrummersDb =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            ScrummersDb::class.java, DATABASE_NAME
        ) // prepopulate the database after onCreate was called
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    GlobalScope.launch {
                        getDatabase(context).getCategoryDao().insertRows(
                            listOf(
                                Category(nameCategory = "ELECTRONIC"),
                                Category(nameCategory = "COMMERCIAL"),
                                Category(nameCategory = "TRUCK")
                            )
                        )
                    }
                }
            })
            .build()
    }
}