package zegel.ipae.proyectofinal.model

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import zegel.ipae.proyectofinal.contract.ContratoGestionCliente
import zegel.ipae.proyectofinal.data.Cliente
import javax.inject.Inject


class InteractorGestionCliente @Inject constructor(): ContratoGestionCliente.Interactor{

    private val firestore = FirebaseFirestore.getInstance()

    override fun obtenerClientes(callback: (List<Cliente>) -> Unit) {
        val clientes = mutableListOf<Cliente>()

        firestore.collection("usuarios")
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