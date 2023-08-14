package zegel.ipae.proyectofinal.view.gestionTrabajador

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import zegel.ipae.proyectofinal.R
import zegel.ipae.proyectofinal.contract.ContratoGestionTrabajador
import zegel.ipae.proyectofinal.data.Trabajador
import zegel.ipae.proyectofinal.model.InteractorGestionTrabajador
import zegel.ipae.proyectofinal.presenter.PresenterGestionTrabajador
import zegel.ipae.proyectofinal.util.adapters.TrabajadorAdapter
import zegel.ipae.proyectofinal.view.menuAdmin.MenuAdminActivity

class GestionTrabajadorActivity : AppCompatActivity(), ContratoGestionTrabajador.ViewList {

    private lateinit var presenter: PresenterGestionTrabajador
    private lateinit var trabajadorAdapter: TrabajadorAdapter
    private lateinit var floatingActionButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gestion_trabajador)

        // aparezca el icono de regresar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        presenter = PresenterGestionTrabajador(this, InteractorGestionTrabajador(applicationContext), applicationContext)
        trabajadorAdapter = TrabajadorAdapter()

        trabajadorAdapter.onEditButtonClickListener = { trabajador ->
            presenter.editarTrabajador(trabajador)
        }

        presenter = PresenterGestionTrabajador(this, InteractorGestionTrabajador())

        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewTrabajador)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = trabajadorAdapter

        presenter.obtenerTrabajadores()

        floatingActionButton = findViewById(R.id.fabAgregar)
        floatingActionButton.setOnClickListener {
            navigateToAdd()
        }
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            startActivity(Intent(this, MenuAdminActivity::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showTrabajadores(trabajadores: List<Trabajador>) {
        trabajadorAdapter.setTrabajadores(trabajadores)
    }

    private fun navigateToAdd() {
        startActivity(Intent(this, AddTrabjadorActivity::class.java))
    }
}