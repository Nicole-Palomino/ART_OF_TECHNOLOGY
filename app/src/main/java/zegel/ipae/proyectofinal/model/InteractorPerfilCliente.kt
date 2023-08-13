package zegel.ipae.proyectofinal.model

import com.google.firebase.firestore.FirebaseFirestore
import zegel.ipae.proyectofinal.contract.ContratoPerfilCliente
import zegel.ipae.proyectofinal.data.Cliente
import javax.inject.Inject

class InteractorPerfilCliente @Inject constructor() : ContratoPerfilCliente.Interactor {

    override fun obtenerDatosUsuario(uid: String, onComplete: (cliente: Cliente?) -> Unit) {
        val db = FirebaseFirestore.getInstance()
        val usuariosRef = db.collection("clientes").document(uid)

        usuariosRef.get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    val id = document.getString("uid")
                    val username = document.getString("username")
                    val correo = document.getString("correo")
                    val rol = document.getString("rol")
                    val pass = document.getString("contrasena")
                    val cliente = Cliente(id ?: "", username ?: "", correo ?: "", rol ?: "", pass ?: "")
                    onComplete(cliente)
                } else {
                    onComplete(null)
                }
            }
            .addOnFailureListener { exception ->
                onComplete(null)
            }
    }
}