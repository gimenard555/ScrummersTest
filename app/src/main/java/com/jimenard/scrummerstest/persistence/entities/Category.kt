package com.jimenard.scrummerstest.persistence.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jimenard.scrummerstest.persistence.entities.Category.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
class Category(
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID_CATEGORY)
    var idCategory: Int = 0,

    @ColumnInfo(name = NAME_CATEGORY)
    var nameCategory: String
) {
    companion object {
        const val TABLE_NAME = "category"
        const val ID_CATEGORY = "id_category"
        const val NAME_CATEGORY = "name_category"
    }
}