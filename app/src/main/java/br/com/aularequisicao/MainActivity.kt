package br.com.aularequisicao

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.tv_resultado)
        // ...

        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "https://viacep.com.br/ws/01001000/json/"

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                // Display the first 500 characters of the response string.
                textView.text = "Response is: ${response}"
                var gson = Gson()
                var endereco = gson?.fromJson(response,Endereco.Data::class.java)
                textView.text = endereco.localidade
            },
            Response.ErrorListener { textView.text = "That didn't work!" })

        // Add the request to the RequestQueue.
        queue.add(stringRequest)
    }
}