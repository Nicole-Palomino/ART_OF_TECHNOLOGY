package zegel.ipae.proyectofinal.presenter

import zegel.ipae.proyectofinal.contract.ContratoGestionProducto
import zegel.ipae.proyectofinal.data.Producto
import javax.inject.Inject

class PresenterGestionProducto @Inject constructor(
    private val view: ContratoGestionProducto.View,
    private val interactor: ContratoGestionProducto.Interactor) : ContratoGestionProducto.Presenter{
    override fun obtenerProductos() {
        interactor.obtenerProductos { productos ->
            view.showProductos(productos)
        }
    }

    override fun editarProductos(producto: Producto) {
        interactor.editarProductos(producto) { success ->
            if (success) {
                // Notificar a la vista que la edición fue exitosa
                view.showEditSuccess()
            } else {
                // Notificar a la vista que la edición falló
                view.showEditFailure()
            }
        }
    }

    override fun eliminarProductos(producto: Producto) {
        interactor.eliminarProductos(producto) { success ->
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