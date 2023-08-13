package zegel.ipae.proyectofinal.view.menuAdmin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.cardview.widget.CardView
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import zegel.ipae.proyectofinal.R
import zegel.ipae.proyectofinal.view.login.LoginActivity
import zegel.ipae.proyectofinal.view.perfil.PerfilActivity

class MenuAdminActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var googleSignInClient: GoogleSignInClient
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

        // Inicializar googleSignInClient
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

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

    private fun redirectToTrabajadores() {
        startActivity(Intent(this, PerfilActivity::class.java))
    }

    private fun redirectToProduct() {
        startActivity(Intent(this, MenuAdminActivity::class.java))
    }

    private fun redirectToClient() {
        startActivity(Intent(this, PerfilActivity::class.java))
    }

    private fun redirectToPerfil() {
        startActivity(Intent(this, PerfilActivity::class.java))
    }
}