package zegel.ipae.proyectofinal.contract

import android.app.Activity
import android.content.Context

interface ContratoLoginCliente {
    interface ViewLoginCliente {
        fun onLoginSuccess(message: String)
        fun onLoginFailure(message: String)
    }

    interface PresenterLoginCliente {
        fun login(
            activity: Activity,
            email: String,
            pass: String,
            context: Context
        )
    }

    interface InteractorLoginCliente {
        fun performLogin(   
            activity: Activity,
            email: String,
            pass: String,
            context: Context
        )
    }

    interface CompleteLoginCliente {
        fun onSuccess(message: String)
        fun onFailure(message: String)
    }
}