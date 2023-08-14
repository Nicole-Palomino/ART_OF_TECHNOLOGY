package zegel.ipae.proyectofinal.model

import com.google.firebase.firestore.FirebaseFirestore
import zegel.ipae.proyectofinal.contract.ContratoGestionCliente
import zegel.ipae.proyectofinal.data.Cliente
import javax.inject.Inject

class InteractorGestionCliente @Inject constructor(
    private val firestore: FirebaseFirestore): ContratoGestionCliente.Interactor{

    override fun obtenerClientes(callback: (List<Cliente>) -> Unit) {
        val clientes = mutableListOf<Cliente>()

        firestore.collection("clientes")
            .whereEqualTo("rol", "cliente")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val cliente = document.toObject(Cliente::class.java)
                    clientes.add(cliente)
                }
                callback(clientes)
            }
            .addOnFailureListener { exception ->
                // Manejar error
            }
    }

    override fun editarClientes(cliente: Cliente, callback: (Boolean) -> Unit) {
        firestore.collection("clientes")
            .document(cliente.uid)
            .set(cliente)
            .addOnSuccessListener {
                callback(true)
            }
            .addOnFailureListener { exception ->
                callback(false)
                // Manejar error
            }
    }

    override fun eliminarClientes(cliente: Cliente, callback: (Boolean) -> Unit) {
        firestore.collection("clientes")
            .document(cliente.uid)
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