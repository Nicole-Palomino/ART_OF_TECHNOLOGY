package zegel.ipae.proyectofinal.model

import com.google.firebase.firestore.FirebaseFirestore
import zegel.ipae.proyectofinal.contract.ContratoGestionProducto
import zegel.ipae.proyectofinal.data.Producto
import javax.inject.Inject

class InteractorGestionProducto @Inject constructor(): ContratoGestionProducto.Interactor {
    val firestore = FirebaseFirestore.getInstance()
    override fun obtenerProductos(callback: (List<Producto>) -> Unit) {
        val productos = mutableListOf<Producto>()

        firestore.collection("productos")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val producto = document.toObject(Producto::class.java)
                    productos.add(producto)
                }
                callback(productos)
            }
            .addOnFailureListener { exception ->
                // Manejar error
            }
    }

    override fun editarProductos(producto: Producto, callback: (Boolean) -> Unit) {
        firestore.collection("productos")
            .document(producto.uid)
            .set(producto)
            .addOnSuccessListener {
                callback(true)
            }
            .addOnFailureListener { exception ->
                callback(false)
                // Manejar error
            }
    }

    override fun eliminarProductos(producto: Producto, callback: (Boolean) -> Unit) {
        firestore.collection("productos")
            .document(producto.uid)
            .delete()
            .addOnSuccessListener {
                callback(true)
            }
            .addOnFailureListener { exception ->
                callback(false)
                // Manejar error
            }
    }

}