package zegel.ipae.proyectofinal.presenter

import zegel.ipae.proyectofinal.contract.ContratoGetData
import zegel.ipae.proyectofinal.data.Cliente
import javax.inject.Inject

class PresenterGetDataWork @Inject constructor(
    private val view: ContratoGetData.ViewTrabajador,
    private val interactor: ContratoGetData.InteractorTrabajador
) : ContratoGetData.PresenterTrabajador {

    override fun loadData() {
        interactor.getUserData { userData, message ->
            if (userData != null) {
                view.showUserData(userData)
            } else {
                view.showErrorMessage(message ?: "Error desconocido")
            }
        }
    }
}