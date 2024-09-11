package com.xcheko51x.googlesheetapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.xcheko51x.googlesheetapp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PersonalAdapter

    private var listaPersonal: List<Personal> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        obtenerData()

        binding.btnAgregar.setOnClickListener {
            agregarData()
        }
    }

    fun setupRecyclerView() {
        adapter = PersonalAdapter(listaPersonal)
        binding.rvPersonal.adapter = adapter
    }

    fun obtenerData() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = RetrofitClient.webService(BaseUrl.base_url_get).obtenerTodoPersonal()

            if (response.isSuccessful) {
                listaPersonal = response.body()?.personal ?: emptyList()

                withContext(Dispatchers.Main) {
                    setupRecyclerView()
                }
            }
        }
    }

    fun agregarData() {
        CoroutineScope(Dispatchers.IO).launch {
            val personalData = PersonalData(
                spreadsheet_id = Constantes.google_sheet_id,
                sheet = Constantes.sheet,
                rows = listOf(
                    listOf(
                        binding.etId.text.toString(),
                        binding.etNombre.text.toString(),
                        binding.etCorreo.text.toString()
                    )
                )
            )

            val response = RetrofitClient.webService(BaseUrl.base_url_post).agregarPersonal(personalData)

            if (response.isSuccessful) {
                obtenerData()
            }
        }
    }
}