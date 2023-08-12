package zegel.ipae.proyectofinal.contract

import zegel.ipae.proyectofinal.data.Cliente

interface ContratoPerfilCliente {
    interface ViewPerfilCliente {
        fun mostrarDatosUsuario(username: String, correo: String, rol: String)
        fun mostrarError(message: String)
    }

    interface PresenterPerfilCliente {
        fun cargarDatosUsuario(uid: String)
    }

    interface Interactor {
        fun obtenerDatosUsuario(uid: String, onComplete: (cliente: Cliente?) -> Unit)
    }
}