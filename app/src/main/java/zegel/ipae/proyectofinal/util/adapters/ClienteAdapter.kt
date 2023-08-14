package zegel.ipae.proyectofinal.util.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import zegel.ipae.proyectofinal.R
import zegel.ipae.proyectofinal.data.Cliente

class ClienteAdapter: BaseAdapter() {
    private var clientes: List<Cliente> = emptyList()

    fun actualizarClientes(nuevosClientes: List<Cliente>) {
        clientes = nuevosClientes
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return clientes.size
    }

    override fun getItem(position: Int): Any {
        return clientes[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val cliente = getItem(position) as Cliente
        val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.cliente_item, parent, false)

        return itemView
    }
}