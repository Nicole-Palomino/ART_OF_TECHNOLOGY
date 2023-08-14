package zegel.ipae.proyectofinal.model

import fuel.Fuel
import fuel.get
import org.json.JSONException
import org.json.JSONObject
import zegel.ipae.proyectofinal.contract.ContratoVerificarDni
import java.net.URL
import javax.inject.Inject

class InteractorVerificarDni @Inject constructor() : ContratoVerificarDni.Interactor {
    
    override fun verifyDni(
        dni: String,
        listener: ContratoVerificarDni.Interactor.OnVerificationFinishedListener
    ) {
        val apiUrl = "https://dniruc.apisperu.com/api/v1/dni/$dni?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Im5pY29sZXBhbG9taW5vYWx2YXJhZG9AZ21haWwuY29tIn0.wGeapRib86ZMQfNZtf17U_jvYHFU8Omonk62WO5-Kow"

        val response = URL(apiUrl).readText()

        try {
            val jsonObject = JSONObject(response)
            val name = jsonObject.getString("nombres")
            val last = jsonObject.getString("apellidoPaterno")
            val last2 = jsonObject.getString("apellidoMaterno")
            val lastName = "$last $last2"

            listener.onVerificationSuccess(name, lastName)
        } catch (e: JSONException) {
            listener.onVerificationError("Error en la verificación")
        }
    }
}