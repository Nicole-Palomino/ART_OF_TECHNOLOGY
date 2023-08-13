package zegel.ipae.proyectofinal.model

import com.google.firebase.auth.FirebaseAuth
import zegel.ipae.proyectofinal.contract.ContratoForgotPassword
import javax.inject.Inject

class InteractorForgotPassword @Inject constructor(): ContratoForgotPassword.Interactor {

    override fun resetPassword(
        email: String,
        callback: ContratoForgotPassword.Interactor.InteractorCallback
    ) {
        FirebaseAuth.getInstance().sendPasswordResetEmail(email)
            .addOnSuccessListener {
                callback.onResetSuccess()
            }
            .addOnFailureListener { exception ->
                callback.onResetFailure(exception.message ?: "Error al restablecer la contraseña")
            }
    }
}