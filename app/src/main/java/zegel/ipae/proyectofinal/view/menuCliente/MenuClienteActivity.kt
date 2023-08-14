package zegel.ipae.proyectofinal.view.menuCliente

import android.content.Intent
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
import zegel.ipae.proyectofinal.contract.ContratoGetData
import zegel.ipae.proyectofinal.data.Cliente
import zegel.ipae.proyectofinal.model.InteractorGetData
import zegel.ipae.proyectofinal.presenter.PresenterGetData
import zegel.ipae.proyectofinal.view.login.LoginActivity
import zegel.ipae.proyectofinal.view.perfil.PerfilActivity

class MenuClienteActivity : AppCompatActivity(), View.OnClickListener, ContratoGetData.View {

    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var presenter: PresenterGetData
    private lateinit var textView: TextView
    private lateinit var button: CardView
    private lateinit var button2: CardView
    private lateinit var button3: CardView
    private lateinit var button4: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        // Ocultar el Action Bar
        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_cliente)

        initViews()
    }

    private fun initViews() {
        textView = findViewById(R.id.fullNameWork)
        button = findViewById(R.id.cardEscanear)
        button2 = findViewById(R.id.cardListado)
        button3 = findViewById(R.id.cardCuenta)
        button4 = findViewById(R.id.cardCerrar)

        button.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
        button4.setOnClickListener(this)

        presenter = PresenterGetData(this, InteractorGetData())
        presenter.loadData()

        // Inicializar googleSignInClient
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.cardEscanear -> {
                redirectToPerfil()
            }
            R.id.cardListado -> {
                redirectToPerfil()
            }
            R.id.cardCuenta -> {
                redirectToPerfil()
            }
            R.id.cardCerrar -> {
                signOut()
            }
        }
    }

    private fun signOut() {
        // cerrar sesion con firebase authentication
        FirebaseAuth.getInstance().signOut()
        // cerrar sesion con google
        googleSignInClient.signOut()

        redirectToLogin()
    }

    private fun redirectToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        finish()
    }

    override fun showUserData(userData: Cliente) {
        textView.text = userData.username
    }

    override fun showErrorMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun redirectToPerfil() {
        startActivity(Intent(this, PerfilActivity::class.java))
    }
}