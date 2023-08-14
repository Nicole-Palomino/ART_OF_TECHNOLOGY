package zegel.ipae.proyectofinal.contract

interface ContratoVerificarDni {
    interface View {
        fun showVerificationResult(name: String, lastName: String)
        fun showVerificationError(error: String)
    }

    interface Presenter {
        fun verifyDni(dni: String)
    }

    interface Interactor {
        interface OnVerificationFinishedListener {
            fun onVerificationSuccess(name: String, lastName: String)
            fun onVerificationError(error: String)
        }

        fun verifyDni(dni: String, listener: OnVerificationFinishedListener)
    }
}