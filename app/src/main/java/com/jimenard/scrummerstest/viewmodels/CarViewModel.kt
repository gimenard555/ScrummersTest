package com.jimenard.scrummerstest.viewmodels

import android.content.Context
import androidx.databinding.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.jimenard.scrummerstest.persistence.entities.Car
import com.jimenard.scrummerstest.persistence.entities.Category
import com.jimenard.scrummerstest.repository.CarRepository
import com.jimenard.scrummerstest.utils.Resource
import javax.inject.Inject

class CarViewModel @Inject constructor(context: Context) : ViewModel() {

    private val carRepository = CarRepository(context)
    var selectedCategory = ObservableField("")
    var seatsUp = ObservableField("")
    var price = ObservableField("")
    var isNew = ObservableBoolean(false)
    var model = ObservableField("")
    var dateReleased = ObservableField("")
    var extraData = ObservableField("")
    var currentCar = Car()
    val isNewText: String
        get() {
            return if (this.currentCar.isNew!!) "IS NEW" else "IS USED"
        }

    fun saveNewCar(): LiveData<Resource<Long>> {
        return this.carRepository.saveNewCar(
            Car(
                seats = seatsUp.get()!!.toInt(),
                price = price.get()!!.toFloat(),
                isNew = isNew.get(),
                model = model.get()?.toUpperCase(),
                releasedDate = dateReleased.get()?.toUpperCase(),
                category = selectedCategory.get()?.toUpperCase(),
                spaceCapacity = if (selectedCategory.get() == "COMMERCIAL") this.extraData.get() else null,
                availablePayload = if (selectedCategory.get() == "TRUCK") this.extraData.get() else null,
                batteryCapacity = if (selectedCategory.get() == "ELECTRONIC") this.extraData.get() else null
            )
        )

    }

    fun saveCategory(categoryName: String) =
        this.carRepository.saveNewCategory(Category(nameCategory = categoryName))

    fun getAllCars() = this.carRepository.getAllCars()

    fun getAllCategories() = this.carRepository.getAllCategories()

    fun cleanForm() {
        this.selectedCategory.set("")
        this.seatsUp.set("")
        this.price.set("")
        this.isNew.set(false)
        this.model.set("")
        this.dateReleased.set("")
    }

    fun updateCarInformation() {
        this.selectedCategory.set(this.currentCar.category)
        this.seatsUp.set(this.currentCar.seats.toString())
        this.price.set(this.currentCar.price.toString())
        this.isNew.set(this.currentCar.isNew!!)
        this.model.set(this.currentCar.model)
        this.dateReleased.set(this.currentCar.releasedDate)
    }

    fun updateCar() = this.carRepository.updateCar(
        Car(
            idCar = currentCar.idCar,
            seats = seatsUp.get()!!.toInt(),
            price = price.get()!!.toFloat(),
            isNew = isNew.get(),
            model = model.get()?.toUpperCase(),
            releasedDate = dateReleased.get()?.toUpperCase(),
            category = selectedCategory.get()?.toUpperCase()
        )
    )

    fun deleteCar() = this.carRepository.deleteCar(this.currentCar.idCar)
}
