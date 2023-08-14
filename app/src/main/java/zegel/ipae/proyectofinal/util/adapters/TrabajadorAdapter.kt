package zegel.ipae.proyectofinal.util.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import zegel.ipae.proyectofinal.R
import zegel.ipae.proyectofinal.data.Trabajador

class TrabajadorAdapter: RecyclerView.Adapter<TrabajadorAdapter.TrabajadorViewHolder>() {
    private val trabajadores : MutableList<Trabajador> = mutableListOf()
    fun actualizarTrabajadores(trabajadores: List<Trabajador>) {
        this.trabajadores.clear()
        this.trabajadores.addAll(trabajadores)
        notifyDataSetChanged()
    }

    //COMIENZO DE TRABAJADORVIEWHOLDER
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TrabajadorAdapter.TrabajadorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.trabajador_item, parent, false)
        return TrabajadorViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrabajadorAdapter.TrabajadorViewHolder, position: Int) {
        val trabajadores = trabajadores[position]
        holder.bind(trabajadores)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int = trabajadores.size

    inner class TrabajadorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(trabajador: Trabajador) {
            itemView.findViewById<TextView>(R.id.txt_dni).text = trabajador.dni
            itemView.findViewById<TextView>(R.id.txt_tel).text = trabajador.tel
            itemView.findViewById<TextView>(R.id.txt_rol).text = trabajador.rol
            itemView.findViewById<TextView>(R.id.txt_nombre).text = trabajador.nombre
            itemView.findViewById<TextView>(R.id.txt_apellido).text = trabajador.apellido
            itemView.findViewById<TextView>(R.id.txt_correo).text = trabajador.correo
            itemView.findViewById<TextView>(R.id.txt_uid).text = trabajador.uid
            // Bind other user data here
        }
    }
}