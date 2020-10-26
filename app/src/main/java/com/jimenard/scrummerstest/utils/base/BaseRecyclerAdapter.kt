package com.jimenard.scrummerstest.utils.base

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jimenard.scrummerstest.utils.adapters.AdapterObject

/**
 * Adaptador generico para todos los recicler view utlizados en la aplicacion
 */
class BaseRecyclerAdapter<T>(
    var items: List<T>,
    private val adapterObject: AdapterObject<T>,
    private val notifier: (View, T, Int) -> Unit
) : RecyclerView.Adapter<BaseRecyclerAdapter<T>.BaseRecyclerViewHolder>() {

    private var holders: ArrayList<BaseRecyclerViewHolder> = ArrayList()
    var selectedPosition: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): BaseRecyclerViewHolder {
        val holder = BaseRecyclerViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(this.adapterObject.layoutToCharge, parent, false)
        )
        this.holders.add(holder)
        return holder
    }

    override fun getItemCount() = this.items.size

    override fun onBindViewHolder(holder: BaseRecyclerViewHolder, position: Int) {
        holder.setParams(this.items[position])
    }

    /**
     * Se valida posicion seleccionada
     * @param position posicion seleccionada que llega desde interfaz
     */
    fun setCurrentPosition(position: Int) {
        this.holders[position].changeState(position)
    }

    inner class BaseRecyclerViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        /**
         * Método encargado de recibir los parámetros cuando se renderiza la vista
         * @param itemRecyclerView item con la informacion para actualizar la vista
         */
        fun setParams(itemRecyclerView: T) {
            adapterObject.chargeView(itemRecyclerView, this.view, this.adapterPosition)
                .setOnClickListener {
                    notifier.invoke(view, itemRecyclerView, adapterPosition)
                }
        }

        /**
         * Se notifica para que el item cambie de estado
         * @param position posicion del item que se quiere cambiar de estado
         */
        fun changeState(position: Int) {
            adapterObject.changeViewState(holders[selectedPosition].view, holders[position].view)
            selectedPosition = position
        }
    }
}