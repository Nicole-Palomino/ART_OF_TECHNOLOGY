package zegel.ipae.proyectofinal.contract

import zegel.ipae.proyectofinal.data.Producto

interface ContratoGestionProducto {
    interface View {
        fun showProductos(productos: List<Producto>)
        fun showEditSuccess()
        fun showEditFailure()
        fun showDeleteSuccess()
        fun showDeleteFailure()
    }

    interface Presenter {
        fun obtenerProductos()
        fun editarProductos(producto: Producto)
        fun eliminarProductos(producto: Producto)
    }

    interface Interactor {
        fun obtenerProductos(callback: (List<Producto>) -> Unit)
        fun editarProductos(producto: Producto, callback: (Boolean) -> Unit)
        fun eliminarProductos(producto: Producto, callback: (Boolean) -> Unit)
    }

    interface ViewEditProducto {
        fun showEditProducto()
    }
}