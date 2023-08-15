package zegel.ipae.proyectofinal.view.escanearCodigo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.zxing.integration.android.IntentIntegrator
import zegel.ipae.proyectofinal.R
import zegel.ipae.proyectofinal.data.Producto
import zegel.ipae.proyectofinal.databinding.ActivityEscanearCodigoBinding

class EscanearCodigoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEscanearCodigoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEscanearCodigoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnScan.setOnClickListener { initScanner() }
        binding.btnConsultar.setOnClickListener { consultarProducto() }
    }

    private fun initScanner() {
        IntentIntegrator(this).initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show()
            } else {
                // Mostrar el valor escaneado en el EditText
                binding.etCodigo.setText(result.contents)
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun consultarProducto() {
        val codigoEscaneado = binding.etCodigo.text.toString().trim()
        Log.d("EscanearCodigoActivity", "Consultando producto con UID: $codigoEscaneado")

        val db = FirebaseFirestore.getInstance()
        val productosRef = db.collection("productos")

        productosRef
            .whereEqualTo("uid", codigoEscaneado)
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty) {
                    val producto = querySnapshot.documents[0].toObject(Producto::class.java)
                    if (producto != null) {
                        Log.d("EscanearCodigoActivity", "Producto encontrado: ${producto.nombre}, Precio: ${producto.precio}, Stock: ${producto.stock}")
                        binding.etNombreProd.setText(producto.nombre)
                        binding.etPrecio.setText(producto.precio.toString())
                        binding.etStock.setText(producto.stock.toString())
                    }
                } else {
                    Log.d("EscanearCodigoActivity", "Producto no encontrado en Firebase")
                    Toast.makeText(this, "Producto no encontrado", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener { exception ->
                Log.d("EscanearCodigoActivity", "Error al consultar: ${exception.message}")
                Toast.makeText(this, "Error al consultar: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
    }

}
