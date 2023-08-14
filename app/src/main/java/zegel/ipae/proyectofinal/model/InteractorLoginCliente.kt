package zegel.ipae.proyectofinal.model

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import zegel.ipae.proyectofinal.contract.ContratoLoginCliente
import zegel.ipae.proyectofinal.view.menuAdmin.MenuAdminActivity
import zegel.ipae.proyectofinal.view.menuCliente.MenuClienteActivity
import zegel.ipae.proyectofinal.view.register.RegisterActivity
import javax.inject.Inject

class InteractorLoginCliente @Inject constructor(private val complete: ContratoLoginCliente.CompleteLoginCliente): ContratoLoginCliente.InteractorLoginCliente {

    override fun performLogin(
        activity: Activity,
        email: String,
        pass: String,
        context: Context
        ) {
        val auth = FirebaseAuth.getInstance()

        auth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    val uid = auth.currentUser?.uid
                    if (uid != null) {
                        fetchUserRoleAndRedirect(uid, context)
                    } else {
                        complete.onFailure("Error al obtener UID del usuario")
                    }
                } else {
                    complete.onFailure("Credenciales incorrectas o usuario no encontrado")
                }
            }
            .addOnFailureListener { exception ->
                complete.onFailure("Error al autenticar usuario: $exception")
            }
    }

    private fun fetchUserRoleAndRedirect(it: String, context: Context) {
        val db = FirebaseFirestore.getInstance()
        val usuariosCollection = db.collection("usuarios")

        usuariosCollection.document(it)
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val rol = document.getString("rol")
                    if (rol != null) {
                        redirectBasedOnUserRole(rol, context)
                    } else {
                        complete.onFailure("Rol no encontrado para el usuario")
                    }
                } else {
                    complete.onFailure("Usuario no encontrado en Firestore")
                }
            }
            .addOnFailureListener { exception ->
                complete.onFailure("Error al obtener información del usuario: $exception")
            }
    }

    private fun redirectBasedOnUserRole(rol: String, context: Context) {
        when (rol) {
            "cliente" -> {
                complete.onSuccess("Inicio de sesión exitosa")
                context.startActivity(Intent(context, MenuClienteActivity::class.java))
            }
            "trabajador" -> {
                complete.onSuccess("Inicio de sesión exitosa")
                context.startActivity(Intent(context, MenuAdminActivity::class.java))
            }
            else -> {
                complete.onFailure("Rol no reconocido")
            }
        }
    }
}