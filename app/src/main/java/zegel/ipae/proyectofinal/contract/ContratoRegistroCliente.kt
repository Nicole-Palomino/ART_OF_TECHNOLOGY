package zegel.ipae.proyectofinal.contract

import android.app.Activity
import com.google.firebase.auth.FirebaseUser

interface ContratoRegistroCliente {
    interface ViewRegistroCliente {
        fun onRegistrationSuccess(firebaseUser: FirebaseUser)
        fun onRegistrationFailure(message: String)
    }

    interface PresenterRegistroCliente {
        fun registerWithEmailAndPassword(
            activity: Activity,
            correo: String,
            contrasena: String,
            username: String
        )
    }

    interface Interactor {
        fun performSigUp(
            activity: Activity,
            correo: String,
            contrasena: String,
            username: String
        )
    }

    interface CompleteListener{
        fun onSuccess(firebaseUser: FirebaseUser)
        fun onFailure(message: String)
    }
}