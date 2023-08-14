package zegel.ipae.proyectofinal.contract

import zegel.ipae.proyectofinal.data.Cliente


interface ContratoGestionCliente {
    interface View {
        fun showClientes(clientes: List<Cliente>)
        fun showEditSuccess()
        fun showEditFailure()
        fun showDeleteSuccess()
        fun showDeleteFailure()
    }

    interface Presenter {
        fun obtenerClientes()
        fun editarClientes(cliente: Cliente)
        fun eliminarClientes(cliente: Cliente)
    }

    interface Interactor {
        fun obtenerClientes(callback: (List<Cliente>) -> Unit)
        fun editarClientes(cliente: Cliente, callback: (Boolean) -> Unit)
        fun eliminarClientes(cliente: Cliente, callback: (Boolean) -> Unit)
    }

    interface ViewEditClient{
        fun showEditClient()
    }
}
