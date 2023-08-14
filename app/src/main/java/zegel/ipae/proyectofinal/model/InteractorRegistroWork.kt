package zegel.ipae.proyectofinal.model

import android.app.Activity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import zegel.ipae.proyectofinal.contract.ContratoRegistroCliente
import zegel.ipae.proyectofinal.data.Trabajador
import zegel.ipae.proyectofinal.util.Constantes
import javax.inject.Inject

class InteractorRegistroWork @Inject constructor(
    private val complete: ContratoRegistroCliente.CompleteListenerTrabajador
) : ContratoRegistroCliente.InteractorTrabajador {

    override fun performSigUp(
        activity: Activity,
        correo: String,
        contrasena: String,
        dni: String,
        tel: String,
        nombre: String,
        apellido: String
    ) {
        val rolTrabajador = Constantes.DEFAULT_ROL_TRABAJADOR
        val estado = Constantes.DEFAULT_ESTADO_TRABAJADOR

        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(correo, contrasena)
            .addOnCompleteListener { task: Task<AuthResult> ->
                if (task.isSuccessful) {
                    val firebaseUser = task.result?.user!!
                    val user = Trabajador(
                        uid = firebaseUser.uid,
                        nombre = nombre,
                        apellido = apellido,
                        correo = correo,
                        rol = rolTrabajador,
                        dni = dni,
                        tel = tel,
                        estado = estado
                    )

                    val db = FirebaseFirestore.getInstance()
                    val clientesCollection = db.collection("usuarios")

                    clientesCollection.document(firebaseUser.uid)
                        .set(user)
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