package zegel.ipae.proyectofinal.presenter

import zegel.ipae.proyectofinal.contract.ContratoGestionCliente
import zegel.ipae.proyectofinal.data.Cliente
import javax.inject.Inject

class PresenterGestionCliente @Inject constructor(
    private val view: ContratoGestionCliente.View,
    private val interactor: ContratoGestionCliente.Interactor) : ContratoGestionCliente.Presenter{

    override fun obtenerClientes() {
        interactor.obtenerClientes { clientes ->
            view.showClientes(clientes)
        }
    }

    override fun editarClientes(cliente: Cliente) {
        interactor.editarClientes(cliente) { success ->
            if (success) {
                // Notificar a la vista que la edición fue exitosa
                view.showEditSuccess()
            } else {
                // Notificar a la vista que la edición falló
                view.showEditFailure()
            }
        }
    }

    override fun eliminarClientes(cliente: Cliente) {
        interactor.eliminarClientes(cliente) { success ->
            if (success) {
                // Notificar a la vista que la eliminación fue exitosa
                view.showDeleteSuccess()
            } else {
                // Notificar a la vista que la eliminación falló
                view.showDeleteFailure()
            }
        }
    }
}