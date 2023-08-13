package zegel.ipae.proyectofinal.presenter

import zegel.ipae.proyectofinal.contract.ContratoGetData
import zegel.ipae.proyectofinal.data.Cliente
import javax.inject.Inject

class PresenterGetData @Inject constructor(
    private val view: ContratoGetData.View,
    private val interactor: ContratoGetData.Interactor
) : ContratoGetData.Presenter {

    override fun loadData() {
        interactor.getUserData { userData, dataEmail, dataUser, message ->
            if (userData != null) {
                view.showUserData(userData)
            } else if (dataEmail != null && dataUser != null) {
                val cliente = Cliente("", dataUser, dataEmail, "")
                view.showUserData(cliente)
            } else {
                view.showErrorMessage(message ?: "Error desconocido")
            }
        }
    }
}