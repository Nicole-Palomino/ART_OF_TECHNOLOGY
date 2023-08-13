package zegel.ipae.proyectofinal.contract

import android.app.Activity

interface ContratoLoginCliente {
    interface ViewLoginCliente {
        fun onLoginSuccess(message: String)
        fun onLoginFailure(message: String)
    }

    interface PresenterLoginCliente {
        fun login(
            activity: Activity,
            email: String,
            pass: String
        )
    }

    interface InteractorLoginCliente {
        fun performLogin(   
            activity: Activity,
            email: String,
            pass: String
        )
    }

    interface CompleteLoginCliente {
        fun onSuccess(message: String)
        fun onFailure(message: String)
    }
}