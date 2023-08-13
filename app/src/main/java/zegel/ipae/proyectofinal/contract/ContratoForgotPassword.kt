package zegel.ipae.proyectofinal.contract

interface ContratoForgotPassword {
    interface View {
        fun showEmailSentMessage()
        fun showErrorMessage(message: String)
    }

    interface Presenter {
        fun resetPassword(email: String)
    }

    interface Interactor {
        fun resetPassword(email: String, callback: InteractorCallback)
        interface InteractorCallback {
            fun onResetSuccess()
            fun onResetFailure(message: String)
        }
    }
}