package zegel.ipae.proyectofinal.view.menuAdmin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.google.firebase.auth.FirebaseAuth
import zegel.ipae.proyectofinal.R
import zegel.ipae.proyectofinal.contract.ContratoGetData
import zegel.ipae.proyectofinal.data.Trabajador
import zegel.ipae.proyectofinal.model.InteractorGetDataWork
import zegel.ipae.proyectofinal.presenter.PresenterGetDataWork
import zegel.ipae.proyectofinal.view.gestionCliente.GestionClienteActivity
import zegel.ipae.proyectofinal.view.gestionProductos.GestionProductosActivity
import zegel.ipae.proyectofinal.view.gestionTrabajador.GestionTrabajadorActivity
import zegel.ipae.proyectofinal.view.login.LoginActivity
import zegel.ipae.proyectofinal.view.perfil.PerfilActivity
import zegel.ipae.proyectofinal.view.perfil.PerfilTrabajadorActivity

class MenuAdminActivity : AppCompatActivity(), View.OnClickListener, ContratoGetData.ViewTrabajador {

    private lateinit var presenter: PresenterGetDataWork
    private lateinit var textView: TextView
    private lateinit var button: CardView
    private lateinit var button2: CardView
    private lateinit var button3: CardView
    private lateinit var button4: CardView
    private lateinit var button5: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        // Ocultar el Action Bar
        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_admin)

        initViews()
    }

    private fun initViews() {
        textView = findViewById(R.id.fullName)
        button = findViewById(R.id.cardUser)
        button2 = findViewById(R.id.cardProduct)
        button3 = findViewById(R.id.cardCliente)
        button4 = findViewById(R.id.cardPerfil)
        button5 = findViewById(R.id.cardSession)

        button.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
        button4.setOnClickListener(this)
        button5.setOnClickListener(this)

        presenter = PresenterGetDataWork(this, InteractorGetDataWork())
        presenter.loadData()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.cardUser -> {
                redirectToTrabajadores()
            }
            R.id.cardProduct -> {
                redirectToProduct()
            }
            R.id.cardCliente -> {
                redirectToClient()
            }
            R.id.cardPerfil -> {
                redirectToPerfil()
            }
            R.id.cardSession -> {
                signOut()
            }
        }
    }

    private fun signOut() {
        // cerrar sesion con firebase authentication
        FirebaseAuth.getInstance().signOut()

        redirectToLogin()
    }

    private fun redirectToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        finish()
    }

    private fun redirectToTrabajadores() {
        startActivity(Intent(this, GestionTrabajadorActivity::class.java))
    }

    private fun redirectToProduct() {
        startActivity(Intent(this, GestionProductosActivity::class.java))
    }

    private fun redirectToClient() {
        startActivity(Intent(this, GestionClienteActivity::class.java))
    }

    private fun redirectToPerfil() {
        startActivity(Intent(this, PerfilTrabajadorActivity::class.java))
    }

    // mostrar datos
    override fun showUserData(userData: Trabajador) {
        val name = userData.nombre
        val last = userData.apellido
        val fullName = "$name $last"
        textView.text = fullName
    }

    override fun showErrorMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}