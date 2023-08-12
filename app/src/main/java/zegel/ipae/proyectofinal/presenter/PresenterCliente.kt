package zegel.ipae.proyectofinal.presenter

import android.app.Activity
import android.util.Log
import com.google.firebase.auth.FirebaseUser
import zegel.ipae.proyectofinal.contract.ContratoRegistroCliente
import zegel.ipae.proyectofinal.model.InteractorRegistroCliente
import zegel.ipae.proyectofinal.util.validation.Validaciones
import javax.inject.Inject

class PresenterCliente @Inject constructor(
    private val view: ContratoRegistroCliente.ViewRegistroCliente,
    private val interactor: ContratoRegistroCliente.Interactor): ContratoRegistroCliente.PresenterRegistroCliente, ContratoRegistroCliente.CompleteListener{

    override fun registerWithEmailAndPassword(
        activity: Activity,
        correo: String,
        contrasena: String,
        username: String
    ) {
        interactor.performSigUp(activity, correo, contrasena, username)
    }

    override fun onSuccess(firebaseUser: FirebaseUser) {
        view.onRegistrationSuccess(firebaseUser)
    }

    override fun onFailure(message: String) {
        view.onRegistrationFailure(message)
    }

}
