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
}