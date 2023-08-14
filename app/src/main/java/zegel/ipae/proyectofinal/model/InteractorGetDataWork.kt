package zegel.ipae.proyectofinal.model

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import zegel.ipae.proyectofinal.contract.ContratoGetData
import zegel.ipae.proyectofinal.data.Cliente
import zegel.ipae.proyectofinal.data.Trabajador
import javax.inject.Inject

class InteractorGetDataWork @Inject constructor(): ContratoGetData.InteractorTrabajador {

    override fun getUserData(callback: (Trabajador?, String?) -> Unit) {
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            val userId = user.uid

            val userDocRef = FirebaseFirestore.getInstance().collection("usuarios").document(userId)
            userDocRef.get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        val userData = document.toObject(Trabajador::class.java)
                        // Verificar el rol del usuario
                        if (userData?.rol == "trabajador") {
                            callback(userData, null)
                        } else {
                            callback(null, "No tienes el rol de trabajador.")
                        }
                    } else {
                        callback(null, "No se encontraron datos para el trabajador.")
                    }
                }
                .addOnFailureListener { exception ->
                    callback(null, "Error al obtener los datos del usuario: ${exception.message}")
                }
        } else {
            callback(null, "No se ha iniciado sesión.")
        }
    }
}