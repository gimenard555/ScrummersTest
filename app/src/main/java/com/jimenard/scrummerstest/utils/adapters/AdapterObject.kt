package com.jimenard.scrummerstest.utils.adapters

import android.view.View

abstract class AdapterObject<T>(var layoutToCharge: Int) {

    /**
     * Se carga vista en el holder del recyclerView
     * @param itemRecyclerView item con informacion que se carga en vista
     */
    abstract fun chargeView(
        itemRecyclerView: T,
        view: View,
        position: Int
    ): View

    /**
     * Metodo para cambiar estados de las vistas si estas son seleccionadas y se le quita la seleccion a la anterior
     * @param currentView vista actual seleccionada vista a la que se le pone seleccion
     * @param lastView ultima vista seleccionada vista a la que se quita seleccion
     */
    abstract fun changeViewState(lastView: View, currentView: View)

}
