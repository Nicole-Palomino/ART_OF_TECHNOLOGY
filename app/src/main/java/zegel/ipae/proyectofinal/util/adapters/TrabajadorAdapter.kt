package zegel.ipae.proyectofinal.util.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.RecyclerView
import zegel.ipae.proyectofinal.R
import zegel.ipae.proyectofinal.data.Trabajador

class TrabajadorAdapter: RecyclerView.Adapter<TrabajadorAdapter.TrabajadorViewHolder>() {

    private val trabajadores : MutableList<Trabajador> = mutableListOf()

    fun setTrabajadores(trabajadores: List<Trabajador>) {
        this.trabajadores.clear()
        this.trabajadores.addAll(trabajadores)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TrabajadorAdapter.TrabajadorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.trabajador_item, parent, false)
        return TrabajadorViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrabajadorAdapter.TrabajadorViewHolder, position: Int) {
        val trabajador = trabajadores[position]
        holder.bind(trabajador)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int = trabajadores.size

    var onEditButtonClickListener: ((Trabajador) -> Unit)? = null

    inner class TrabajadorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val editButton: AppCompatImageButton = itemView.findViewById(R.id.btnEdit)
        fun bind(user: Trabajador) {
            val nombreCompleto = "${user.nombre} ${user.apellido}"
            itemView.findViewById<TextView>(R.id.txt_nombre).text = nombreCompleto
            itemView.findViewById<TextView>(R.id.txt_email).text = user.correo
            itemView.findViewById<TextView>(R.id.txt_number).text = user.dni
            itemView.findViewById<TextView>(R.id.txt_tel).text = user.tel
            itemView.findViewById<TextView>(R.id.txt_rol).text = user.rol
            itemView.findViewById<TextView>(R.id.txt_estado).text = user.estado

            editButton.setOnClickListener {
                onEditButtonClickListener?.invoke(user)
            }
        }
    }
}