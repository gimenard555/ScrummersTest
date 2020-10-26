package com.jimenard.scrummerstest.repository

import android.content.Context
import androidx.lifecycle.liveData
import com.jimenard.scrummerstest.persistence.ScrummersDb
import com.jimenard.scrummerstest.persistence.entities.Car
import com.jimenard.scrummerstest.persistence.entities.Category
import com.jimenard.scrummerstest.utils.Resource
import kotlinx.coroutines.Dispatchers

class CarRepository(context: Context) {

    private val categoryDao = ScrummersDb.getDatabase(context)?.getCategoryDao()!!
    private val carDao = ScrummersDb.getDatabase(context)?.getCarDao()!!

    fun getAllCars() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = carDao.getAllCars()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getAllCategories() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = categoryDao.getAllCategories()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun saveNewCar(car: Car) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = carDao.insertRow(car)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun updateCar(car: Car) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = carDao.updateRow(car)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun deleteCar(carId: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = carDao.deleteCar(carId)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun saveNewCategory(category: Category) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = categoryDao.insertRow(category)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}