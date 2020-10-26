package com.jimenard.scrummerstest.utils.adapters

import android.view.View
import com.jimenard.scrummerstest.R
import com.jimenard.scrummerstest.persistence.entities.Category
import kotlinx.android.synthetic.main.category_list_item_view.view.*

class ACategory(layout: Int = R.layout.category_list_item_view) :
    AdapterObject<Category>(layout) {

    override fun chargeView(
        itemRecyclerView: Category,
        view: View,
        position: Int
    ): View {
        view.category_car.text = itemRecyclerView.nameCategory
        return view
    }

    override fun changeViewState(lastView: View, currentView: View) {
    }
}
