package zegel.ipae.proyectofinal.util.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import zegel.ipae.proyectofinal.R
import zegel.ipae.proyectofinal.data.Trabajador

class TrabajadorAdapter: BaseAdapter() {
    private var trabajadores: List<Trabajador> = emptyList()

    fun actualizarTrabajadores(nuevosTrabajadores: List<Trabajador>) {
        trabajadores = nuevosTrabajadores
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return trabajadores.size
    }

    override fun getItem(position: Int): Any {
        return trabajadores[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val trabajador = getItem(position) as Trabajador
        val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.trabajador_item, parent, false)

        return itemView
    }
}