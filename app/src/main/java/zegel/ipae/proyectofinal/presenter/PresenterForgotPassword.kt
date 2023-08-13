package zegel.ipae.proyectofinal.presenter

import zegel.ipae.proyectofinal.contract.ContratoForgotPassword
import javax.inject.Inject

class PresenterForgotPassword @Inject constructor(
    private val view: ContratoForgotPassword.View,
    private val interactor: ContratoForgotPassword.Interactor
    ): ContratoForgotPassword.Presenter {

    override fun resetPassword(email: String) {
        interactor.resetPassword(email, object : ContratoForgotPassword.Interactor.InteractorCallback {
            override fun onResetSuccess() {
                view.showEmailSentMessage()
            }

            override fun onResetFailure(message: String) {
                view.showErrorMessage(message)
            }
        })
    }
}