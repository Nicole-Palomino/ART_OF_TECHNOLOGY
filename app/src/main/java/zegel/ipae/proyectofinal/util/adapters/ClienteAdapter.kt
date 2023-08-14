package zegel.ipae.proyectofinal.util.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import zegel.ipae.proyectofinal.R
import zegel.ipae.proyectofinal.data.Cliente

class ClienteAdapter : RecyclerView.Adapter<ClienteAdapter.ClienteViewHolder>() {

    private val clientes : MutableList<Cliente> = mutableListOf()

    fun setClientes(clientes: List<Cliente>) {
        this.clientes.clear()
        this.clientes.addAll(clientes)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ClienteAdapter.ClienteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cliente_item, parent, false)
        return ClienteViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClienteAdapter.ClienteViewHolder, position: Int) {
        val cliente = clientes[position]
        holder.bind(cliente)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int = clientes.size

    inner class ClienteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(cliente: Cliente) {
            itemView.findViewById<TextView>(R.id.txt_userName).text = cliente.username
            itemView.findViewById<TextView>(R.id.txt_correo).text = cliente.correo
            itemView.findViewById<TextView>(R.id.txt_rol).text = cliente.rol
        }
    }
}
