package zegel.ipae.proyectofinal.presenter

import zegel.ipae.proyectofinal.contract.ContratoPerfilCliente
import javax.inject.Inject

class PresenterPerfilCliente @Inject constructor(
        private val view: ContratoPerfilCliente.ViewPerfilCliente,
        private val interactor: ContratoPerfilCliente.Interactor
    ) : ContratoPerfilCliente.PresenterPerfilCliente {

    override fun cargarDatosUsuario(uid: String) {
        interactor.obtenerDatosUsuario(uid) { cliente ->
            if (cliente != null) {
                view.mostrarDatosUsuario(cliente.username, cliente.correo, cliente.rol)
            } else {
                view.mostrarError("Error al cargar los datos del cliente")
            }
        }
    }
}