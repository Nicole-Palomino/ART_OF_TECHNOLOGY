package zegel.ipae.proyectofinal.presenter

import android.app.Activity
import zegel.ipae.proyectofinal.contract.ContratoLoginCliente
import javax.inject.Inject

class PresenterLoginCliente @Inject constructor(
    private val view: ContratoLoginCliente.ViewLoginCliente,
    private val interactor: ContratoLoginCliente.InteractorLoginCliente): ContratoLoginCliente.PresenterLoginCliente, ContratoLoginCliente.CompleteLoginCliente {

    override fun login(
        activity: Activity,
        email: String,
        pass: String) {
        interactor.performLogin(activity,email,pass)
    }

    override fun onSuccess(message: String) {
        view.onLoginSuccess(message)
    }

    override fun onFailure(message: String) {
        view.onLoginFailure(message)
    }
}