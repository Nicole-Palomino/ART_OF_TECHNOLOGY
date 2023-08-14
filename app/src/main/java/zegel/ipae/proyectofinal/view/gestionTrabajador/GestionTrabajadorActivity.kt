package zegel.ipae.proyectofinal.view.gestionTrabajador

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
    private lateinit var search: TextInputEditText

    private val listaCompleta: MutableList<Trabajador> = mutableListOf()
    private val listaFiltrada: MutableList<Trabajador> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gestion_trabajador)

        // aparezca el icono de regresar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        search = findViewById(R.id.TextInputBuscarTrabajador)
        search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No se utiliza en este caso
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // No se utiliza en este caso
            }

            override fun afterTextChanged(s: Editable?) {
                val query = s.toString()
                filterList(query)
            }
        })

        presenter = PresenterGestionTrabajador(this, InteractorGestionTrabajador(applicationContext), applicationContext)
        trabajadorAdapter = TrabajadorAdapter()

        trabajadorAdapter.onEditButtonClickListener = { trabajador ->
            presenter.editarTrabajador(trabajador)
        }

        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewTrabajador)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = trabajadorAdapter

        presenter.obtenerTrabajadores()

        floatingActionButton = findViewById(R.id.fabAgregar)
        floatingActionButton.setOnClickListener {
            navigateToAdd()
        }
    }

    private fun filterList(query: String) {
        listaFiltrada.clear()
        if (query.isEmpty()) {
            listaFiltrada.addAll(listaCompleta)
        } else {
            val filteredList = listaCompleta.filter { trabajador ->
                trabajador.nombre.contains(query, true) || trabajador.apellido.contains(query, true)
            }
            listaFiltrada.addAll(filteredList)
        }
        trabajadorAdapter.setTrabajadores(listaFiltrada)
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