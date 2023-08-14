package zegel.ipae.proyectofinal.contract

import zegel.ipae.proyectofinal.data.Cliente

interface ContratoGestionCliente {
    interface View {
        fun showClientes(clientes: List<Cliente>)
    }

    interface Presenter {
        fun obtenerClientes()
    }

    interface Interactor {
        fun obtenerClientes(callback: (List<Cliente>) -> Unit)
    }
}
