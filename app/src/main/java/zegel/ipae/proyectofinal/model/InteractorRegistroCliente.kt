package zegel.ipae.proyectofinal.model

import android.app.Activity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import zegel.ipae.proyectofinal.contract.ContratoRegistroCliente
import zegel.ipae.proyectofinal.data.Cliente
import zegel.ipae.proyectofinal.util.Constantes
import javax.inject.Inject

class InteractorRegistroCliente @Inject constructor(
    private val complete: ContratoRegistroCliente.CompleteListener) : ContratoRegistroCliente.Interactor {

    override fun performSigUp(
        activity: Activity,
        correo: String,
        contrasena: String,
        username: String
    ) {
        val rolCliente = Constantes.DEFAULT_ROL_CLIENTE

        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(correo, contrasena)
            .addOnCompleteListener { task: Task<AuthResult> ->
                if (task.isSuccessful) {
                    val firebaseUser = task.result?.user!!
                    val cliente = Cliente(
                        uid = firebaseUser.uid,
                        username = username,
                        correo = correo,
                        rol = rolCliente
                    )

                    val db = FirebaseFirestore.getInstance()
                    val clientesCollection = db.collection("usuarios")

                    clientesCollection.document(firebaseUser.uid)
                        .set(cliente)
                        .addOnSuccessListener {
                            complete.onSuccess(firebaseUser)
                        }
                        .addOnFailureListener { exception ->
                            val errorMessage = exception.message ?: "Error al registrar los datos."
                            complete.onFailure(errorMessage)
                        }
                } else {
                    val errorMessage = task.exception?.message ?: "Registro fallido"
                    complete.onFailure(errorMessage)
                }
            }

    }
}