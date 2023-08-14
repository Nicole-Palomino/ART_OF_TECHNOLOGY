package zegel.ipae.proyectofinal.util.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
<<<<<<< HEAD
=======
import androidx.appcompat.widget.AppCompatImageButton
>>>>>>> 14684140d01f16a609f8b59c7798d13ff31e5b19
import androidx.recyclerview.widget.RecyclerView
import zegel.ipae.proyectofinal.R
import zegel.ipae.proyectofinal.data.Trabajador

class TrabajadorAdapter: RecyclerView.Adapter<TrabajadorAdapter.TrabajadorViewHolder>() {
<<<<<<< HEAD
    private val trabajadores : MutableList<Trabajador> = mutableListOf()
    fun actualizarTrabajadores(trabajadores: List<Trabajador>) {
=======

    private val trabajadores : MutableList<Trabajador> = mutableListOf()

    fun setTrabajadores(trabajadores: List<Trabajador>) {
>>>>>>> 14684140d01f16a609f8b59c7798d13ff31e5b19
        this.trabajadores.clear()
        this.trabajadores.addAll(trabajadores)
        notifyDataSetChanged()
    }

<<<<<<< HEAD
    //COMIENZO DE TRABAJADORVIEWHOLDER
=======
>>>>>>> 14684140d01f16a609f8b59c7798d13ff31e5b19
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TrabajadorAdapter.TrabajadorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.trabajador_item, parent, false)
        return TrabajadorViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrabajadorAdapter.TrabajadorViewHolder, position: Int) {
<<<<<<< HEAD
        val trabajadores = trabajadores[position]
        holder.bind(trabajadores)
=======
        val trabajador = trabajadores[position]
        holder.bind(trabajador)
>>>>>>> 14684140d01f16a609f8b59c7798d13ff31e5b19
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int = trabajadores.size

<<<<<<< HEAD
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
=======
    var onEditButtonClickListener: ((Trabajador) -> Unit)? = null

    inner class TrabajadorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val editButton: AppCompatImageButton = itemView.findViewById(R.id.btnEdit)
        fun bind(user: Trabajador) {
            val nombreCompleto = "${user.nombre} ${user.apellido}"
            itemView.findViewById<TextView>(R.id.txt_Name).text = nombreCompleto
            itemView.findViewById<TextView>(R.id.txt_email).text = user.correo
            itemView.findViewById<TextView>(R.id.txt_number).text = user.dni
            itemView.findViewById<TextView>(R.id.txt_tel).text = user.tel
            itemView.findViewById<TextView>(R.id.txt_rol).text = user.rol
            itemView.findViewById<TextView>(R.id.txt_estado).text = user.estado

            editButton.setOnClickListener {
                onEditButtonClickListener?.invoke(user)
            }
>>>>>>> 14684140d01f16a609f8b59c7798d13ff31e5b19
        }
    }
}