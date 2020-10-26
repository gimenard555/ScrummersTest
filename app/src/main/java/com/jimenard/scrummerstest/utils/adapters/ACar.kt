package com.jimenard.scrummerstest.utils.adapters

import android.view.View
import com.jimenard.scrummerstest.R
import com.jimenard.scrummerstest.persistence.entities.Car
import kotlinx.android.synthetic.main.car_list_item_view.view.*

class ACar(layout: Int = R.layout.car_list_item_view) :
    AdapterObject<Car>(layout) {

    override fun chargeView(
        itemRecyclerView: Car,
        view: View,
        position: Int
    ): View {
        view.category_car.text = itemRecyclerView.category
        view.model_car.text = itemRecyclerView.model
        return view.item_container
    }

    override fun changeViewState(lastView: View, currentView: View) {
    }
}
