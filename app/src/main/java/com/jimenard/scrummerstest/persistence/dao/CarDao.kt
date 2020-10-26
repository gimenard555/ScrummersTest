package com.jimenard.scrummerstest.persistence.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import com.jimenard.scrummerstest.persistence.entities.Car

@Dao
abstract class CarDao : GenericDao<Car> {

    @Query("select * from ${Car.TABLE_NAME}")
    abstract suspend fun getAllCars(): List<Car>

    @Query("delete from ${Car.TABLE_NAME} WHERE  ${Car.ID_CAR} = :carId")
    abstract suspend fun deleteCar(carId: Int)

}