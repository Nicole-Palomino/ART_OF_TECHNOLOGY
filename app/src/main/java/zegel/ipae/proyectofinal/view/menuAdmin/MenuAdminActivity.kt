package zegel.ipae.proyectofinal.view.menuAdmin

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import zegel.ipae.proyectofinal.R
import zegel.ipae.proyectofinal.contract.ContratoPerfilCliente
import zegel.ipae.proyectofinal.model.InteractorPerfilCliente
import zegel.ipae.proyectofinal.presenter.PresenterPerfilCliente
import zegel.ipae.proyectofinal.util.Constantes
import zegel.ipae.proyectofinal.view.comprobante.ComprobanteActivity
import zegel.ipae.proyectofinal.view.login.LoginActivity

class MenuAdminActivity : AppCompatActivity(), View.OnClickListener, ContratoPerfilCliente.ViewPerfilCliente {

    private lateinit var presenter: PresenterPerfilCliente
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var fullName: TextView
    private lateinit var button: CardView
    private lateinit var button6: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        // Ocultar el Action Bar
        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_admin)

        presenter = PresenterPerfilCliente(this, InteractorPerfilCliente())
        sharedPreferences = getSharedPreferences(Constantes.SHARED_PREFS_NAME, Context.MODE_PRIVATE)
        initViews()
    }

    private fun initViews() {
        fullName = findViewById(R.id.fullName)
        button = findViewById(R.id.cardSales)
        button6 = findViewById(R.id.cardSession)
        val uid = FirebaseAuth.getInstance().currentUser?.uid ?: ""

        presenter.cargarDatosUsuario(uid)
        button.setOnClickListener(this)
        button6.setOnClickListener(this)

        // Inicializar googleSignInClient
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.cardSales -> {
                startActivity(Intent(this@MenuAdminActivity, ComprobanteActivity::class.java))
                finish()
            }
            R.id.cardSession -> {
                signOut()
            }
        }
    }

    private fun signOut() {
        // cerrar sesion con firebase authentication
        FirebaseAuth.getInstance().signOut()
        // cerrar sesion con google
        googleSignInClient.signOut()

        val editor = sharedPreferences.edit()
        editor.putBoolean(Constantes.KEY_IS_LOGGED_IN, true)
        editor.apply()

        redirectToLogin()
    }

    private fun redirectToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        finish()
    }

    override fun mostrarDatosUsuario(username: String, correo: String, rol: String) {
        fullName.text = username
    }

    override fun mostrarError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}