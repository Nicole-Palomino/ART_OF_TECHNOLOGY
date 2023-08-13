package zegel.ipae.proyectofinal.contract

import zegel.ipae.proyectofinal.data.Cliente

interface ContratoGetData {
    interface View {
        fun showUserData(userData: Cliente)
        fun showErrorMessage(message: String)
    }

    interface Presenter {
        fun loadData()
    }

    interface Interactor {
        fun getUserData(callback: (Cliente?, String?, String?, String?) -> Unit)
    }
}