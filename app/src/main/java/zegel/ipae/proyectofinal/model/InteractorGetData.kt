package zegel.ipae.proyectofinal.model

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import zegel.ipae.proyectofinal.contract.ContratoGetData
import zegel.ipae.proyectofinal.data.Cliente
import javax.inject.Inject

class InteractorGetData @Inject constructor(): ContratoGetData.Interactor {

    override fun getUserData(callback: (Cliente?, String?,String?, String?) -> Unit) {
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            val isGoogleSignIn = user.providerData.any { it.providerId == GoogleAuthProvider.PROVIDER_ID }

            if (isGoogleSignIn) {
                val userEmail = user.email
                val userName = user.displayName

                callback(null, userEmail, userName, "Error al mostrar datos")
            } else {
                val userId = user.uid

                val userDocRef = FirebaseFirestore.getInstance().collection("usuarios").document(userId)
                userDocRef.get()
                    .addOnSuccessListener { document ->
                        if (document.exists()) {
                            val userData = document.toObject(Cliente::class.java)
                            // Verificar el rol del usuario
                            if (userData?.rol == "cliente") {
                                callback(userData, null, null, null)
                            } else {
                                callback(null, null, null, "No tienes el rol de cliente.")
                            }
                        } else {
                            callback(null, null, null, "No se encontraron datos para el usuario.")
                        }
                    }
                    .addOnFailureListener { exception ->
                        callback(null, null, null, "Error al obtener los datos del usuario: ${exception.message}")
                    }
            }
        } else {
            callback(null, null, null, "No se ha iniciado sesión.")
        }
    }
}