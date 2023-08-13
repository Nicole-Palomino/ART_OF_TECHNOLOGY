package zegel.ipae.proyectofinal.presenter

import zegel.ipae.proyectofinal.contract.ContratoGestionCliente
import javax.inject.Inject

class PresenterGestionCliente @Inject constructor(
    private val view: ContratoGestionCliente.View,
    private val interactor: ContratoGestionCliente.Interactor) : ContratoGestionCliente.Presenter{

    override fun obtenerClientes() {
        interactor.obtenerClientes { clientes ->
            view.showClientes(clientes)
        }
    }
}