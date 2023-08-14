package zegel.ipae.proyectofinal.presenter

import android.app.Activity
import com.google.firebase.auth.FirebaseUser
import zegel.ipae.proyectofinal.contract.ContratoRegistroCliente

class PresenterRegistroWork constructor(
    private val view: ContratoRegistroCliente.ViewRegistroTrabajador,
    private val interactor: ContratoRegistroCliente.InteractorTrabajador): ContratoRegistroCliente.PresenterRegistroTrabajador, ContratoRegistroCliente.CompleteListenerTrabajador {

    override fun registerWithEmailAndPassword(
        activity: Activity,
        correo: String,
        contrasena: String,
        dni: String,
        tel: String,
        nombre: String,
        apellido: String
    ) {
        interactor.performSigUp(activity,correo,contrasena,dni,tel,nombre,apellido)
    }

    override fun onSuccess(firebaseUser: FirebaseUser) {
        view.onRegistrationSuccess(firebaseUser)
    }

    override fun onFailure(message: String) {
        view.onRegistrationFailure(message)
    }
}