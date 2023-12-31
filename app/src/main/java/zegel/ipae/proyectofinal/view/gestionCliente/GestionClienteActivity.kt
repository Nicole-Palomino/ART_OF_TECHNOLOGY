package zegel.ipae.proyectofinal.view.gestionCliente

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import zegel.ipae.proyectofinal.R
import zegel.ipae.proyectofinal.contract.ContratoGestionCliente
import zegel.ipae.proyectofinal.data.Cliente
import zegel.ipae.proyectofinal.model.InteractorGestionCliente
import zegel.ipae.proyectofinal.presenter.PresenterGestionCliente
import zegel.ipae.proyectofinal.util.adapters.ClienteAdapter
import zegel.ipae.proyectofinal.view.menuAdmin.MenuAdminActivity

class GestionClienteActivity : AppCompatActivity(), ContratoGestionCliente.View {

    private lateinit var presenter: PresenterGestionCliente

    private lateinit var clienteAdapter: ClienteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gestion_cliente)

        // aparezca el icono de regresar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        presenter = PresenterGestionCliente(this, InteractorGestionCliente())
        clienteAdapter = ClienteAdapter()

        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewCliente)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = clienteAdapter

        presenter.obtenerClientes()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            startActivity(Intent(this, MenuAdminActivity::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showClientes(clientes: List<Cliente>) {
        clienteAdapter.setClientes(clientes)
    }
}
