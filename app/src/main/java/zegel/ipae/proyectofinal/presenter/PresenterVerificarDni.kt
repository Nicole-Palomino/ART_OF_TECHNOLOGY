package zegel.ipae.proyectofinal.presenter

import zegel.ipae.proyectofinal.contract.ContratoVerificarDni
import javax.inject.Inject

class PresenterVerificarDni @Inject constructor(
    private val view: ContratoVerificarDni.View,
    private val interactor: ContratoVerificarDni.Interactor) : ContratoVerificarDni.Presenter {

    override fun verifyDni(dni: String) {
        interactor.verifyDni(dni, object : ContratoVerificarDni.Interactor.OnVerificationFinishedListener {
            override fun onVerificationSuccess(name: String, lastName: String) {
                view.showVerificationResult(name, lastName)
            }

            override fun onVerificationError(error: String) {
                view.showVerificationError(error)
            }
        })
    }
}