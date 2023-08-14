package zegel.ipae.proyectofinal.util.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import zegel.ipae.proyectofinal.R
import zegel.ipae.proyectofinal.data.Producto

class ProductoAdapter: RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder>()  {
    private val productos : MutableList<Producto> = mutableListOf()
    fun actualizarProductos(productos: List<Producto>) {
        this.productos.clear()
        this.productos.addAll(productos)
        notifyDataSetChanged()
    }

    //COMIENZO DE PRODUCTOVIEWHOLDER
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ):  ProductoAdapter.ProductoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.producto_item, parent, false)
        return ProductoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductoAdapter.ProductoViewHolder, position: Int) {
        val productos = productos[position]
        holder.bind(productos)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int = productos.size

    inner class ProductoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(producto: Producto) {
            itemView.findViewById<TextView>(R.id.txt_uidprod).text = producto.uid
            itemView.findViewById<TextView>(R.id.txt_nombreprod).text = producto.nombre
            itemView.findViewById<TextView>(R.id.txt_precioprod).text = producto.precio.toString()
            itemView.findViewById<TextView>(R.id.txt_stockprod).text = producto.stock.toString()
            // Bind other user data here
        }
    }
}