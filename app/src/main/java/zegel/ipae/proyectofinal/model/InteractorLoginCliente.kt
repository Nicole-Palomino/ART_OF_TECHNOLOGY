package zegel.ipae.proyectofinal.model

import android.app.Activity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import zegel.ipae.proyectofinal.contract.ContratoLoginCliente
import java.security.MessageDigest
import javax.inject.Inject

class InteractorLoginCliente @Inject constructor(private val complete: ContratoLoginCliente.CompleteLoginCliente): ContratoLoginCliente.InteractorLoginCliente {

    override fun performLogin(
        activity: Activity,
        email: String,
        pass: String) {
        val auth = FirebaseAuth.getInstance()
        val hashedPassword = hashSHA256(pass)

        auth.signInWithEmailAndPassword(email, hashedPassword)
            .addOnCompleteListener(activity) { task: Task<AuthResult> ->
                if (task.isSuccessful) {
                    val uid = auth.currentUser?.uid
                    uid?.let { fetchUserRoleAndRedirect(it) }
                } else {
                    complete.onFailure("Credenciales incorrectas")
                }
            }
    }

    private fun fetchUserRoleAndRedirect(it: String) {
        val db = FirebaseFirestore.getInstance()
        val userDocRef = db.collection("clientes").document(it)

        userDocRef.get()
            .addOnSuccessListener { documentSnapshot ->
                when (documentSnapshot.getString("rol")) {
                    "cliente" -> {
                        complete.onSuccess("Inicio de sesión exitoso como cliente")
                    }
                    "trabajador" -> {
                        complete.onSuccess("Inicio de sesión exitoso como trabajador")
                    }
                    else -> {
                        complete.onFailure("Rol no reconocido")
                    }
                }
            }
            .addOnFailureListener { exception ->
                complete.onFailure("Error al obtener el rol: $exception")
            }
    }

    private fun hashSHA256(input: String): String {
        val messagedigest = MessageDigest.getInstance("SHA-256")
        val byteBuffer = messagedigest.digest(input.toByteArray())
        val hexString = StringBuilder()

        for (byte in byteBuffer) {
            val hex = Integer.toHexString(0xff and byte.toInt())
            if (hex.length == 1) hexString.append('0')
            hexString.append(hex)
        }

        return hexString.toString()
    }
}