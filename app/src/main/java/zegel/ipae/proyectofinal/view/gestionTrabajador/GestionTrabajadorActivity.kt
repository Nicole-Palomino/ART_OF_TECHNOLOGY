package zegel.ipae.proyectofinal.view.gestionTrabajador

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import zegel.ipae.proyectofinal.R
import zegel.ipae.proyectofinal.contract.ContratoGestionTrabajador
import zegel.ipae.proyectofinal.data.Trabajador
import zegel.ipae.proyectofinal.model.InteractorGestionCliente
import zegel.ipae.proyectofinal.model.InteractorGestionTrabajador
import zegel.ipae.proyectofinal.presenter.PresenterGestionCliente
import zegel.ipae.proyectofinal.presenter.PresenterGestionTrabajador
import zegel.ipae.proyectofinal.util.adapters.TrabajadorAdapter

class GestionTrabajadorActivity : AppCompatActivity(), ContratoGestionTrabajador.View{

    private lateinit var presenter: PresenterGestionTrabajador

    private val trabajadorAdapter = TrabajadorAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gestion_trabajador)

        // aparezca el icono de regresar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        presenter = PresenterGestionTrabajador(this, InteractorGestionTrabajador())

        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewTrabajador)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = trabajadorAdapter

        presenter.obtenerTrabajadores()
    }

    override fun showTrabajadores(trabajadores: List<Trabajador>) {
        trabajadorAdapter.actualizarTrabajadores(trabajadores)
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