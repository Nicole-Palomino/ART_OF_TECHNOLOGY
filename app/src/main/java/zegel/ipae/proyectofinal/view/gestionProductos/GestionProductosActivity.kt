package zegel.ipae.proyectofinal.view.gestionProductos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import zegel.ipae.proyectofinal.R
import zegel.ipae.proyectofinal.contract.ContratoGestionProducto
import zegel.ipae.proyectofinal.data.Producto
import zegel.ipae.proyectofinal.model.InteractorGestionProducto
import zegel.ipae.proyectofinal.presenter.PresenterGestionProducto
import zegel.ipae.proyectofinal.util.adapters.ProductoAdapter
import zegel.ipae.proyectofinal.view.menuAdmin.MenuAdminActivity

class GestionProductosActivity : AppCompatActivity(), ContratoGestionProducto.View {

    private lateinit var presenter: PresenterGestionProducto

    private val productoAdapter = ProductoAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gestion_productos)

        // aparezca el icono de regresar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        presenter = PresenterGestionProducto(this, InteractorGestionProducto())

        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewProducto)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = productoAdapter

        presenter.obtenerProductos()
    }

    override fun showProductos(productos: List<Producto>) {
        productoAdapter.actualizarProductos(productos)
    }

    override fun showEditSuccess() {
        Toast.makeText(this, "Edición exitosa", Toast.LENGTH_SHORT).show()
    }

    override fun showEditFailure() {
        Toast.makeText(this, "Error en la edición", Toast.LENGTH_SHORT).show()
    }

    override fun showDeleteSuccess() {
        Toast.makeText(this, "Eliminación exitosa", Toast.LENGTH_SHORT).show()
    }

    override fun showDeleteFailure() {
        Toast.makeText(this, "Error en la eliminación", Toast.LENGTH_SHORT).show()
    }
}