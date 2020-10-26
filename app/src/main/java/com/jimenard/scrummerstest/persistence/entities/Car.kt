package com.jimenard.scrummerstest.persistence.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jimenard.scrummerstest.persistence.entities.Car.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class Car(
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID_CAR)
    var idCar: Int = 0,

    @ColumnInfo(name = SEATS_COLUMN_NAME)
    var seats: Int? = null,

    @ColumnInfo(name = PRICE_COLUMN_NAME)
    var price: Float? = null,

    @ColumnInfo(name = IS_NEW_COLUMN_NAME)
    var isNew: Boolean? = null,

    @ColumnInfo(name = MODEL_COLUMN_NAME)
    var model: String? = null,

    @ColumnInfo(name = DATE_RELEASED_COLUMN_NAME)
    var releasedDate: String? = null,

    @ColumnInfo(name = CATEGORY_COLUMN_NAME)
    var category: String? = null,

    @ColumnInfo(name = BATTERY_CAPACITY_COLUMN_NAME)
    var batteryCapacity: String? = null,

    @ColumnInfo(name = AVAILABLE_PAYLOAD_COLUMN_NAME)
    var availablePayload: String? = null,

    @ColumnInfo(name = SPACE_CAPACITY_COLUMN_NAME)
    var spaceCapacity: String? = null
) {

    companion object {
        const val TABLE_NAME = "car"
        const val ID_CAR = "id_car"
        const val SEATS_COLUMN_NAME = "seats"
        const val PRICE_COLUMN_NAME = "price"
        const val IS_NEW_COLUMN_NAME = "isnew"
        const val MODEL_COLUMN_NAME = "model"
        const val DATE_RELEASED_COLUMN_NAME = "date_released"
        const val CATEGORY_COLUMN_NAME = "category"
        const val BATTERY_CAPACITY_COLUMN_NAME = "battery_capacity"
        const val AVAILABLE_PAYLOAD_COLUMN_NAME = "available_payload"
        const val SPACE_CAPACITY_COLUMN_NAME = "space_capacity"
    }
}