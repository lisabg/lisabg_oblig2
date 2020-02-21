package com.firstproject.lisabg_oblig2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface AlpacaApi {

    @GET("alpakka0")
    suspend fun fetch0Alpacas(): Response<MutableList<Alpaca>>

    @GET("alpakka2")
    suspend fun fetch2Alpacas(): Response<MutableList<Alpaca>>

    @GET("alpakka20")
    suspend fun fetch20Alpacas(): Response<MutableList<Alpaca>>

    @GET("alpakka200")
    suspend fun fetch200Alpacas(): Response<MutableList<Alpaca>>

    @GET("alpakka2000")
    suspend fun fetch2000Alpacas(): Response<MutableList<Alpaca>>

}

class MainActivity : AppCompatActivity() {

    private val baseUrl = "https://in2000-alpakkaproxy.ifi.uio.no/"
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private var alpacas : MutableList<Alpaca>? = null

    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception: ${throwable.localizedMessage}")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = getAlpacaService().fetch200Alpacas()

            delay(2000L) // for å se progressbar

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    alpacas = response.body()

                    if (alpacas.isNullOrEmpty()) {
                        onError("Error: could not load alpacas")
                    } else {

                        viewAdapter = ListAdapter(alpacas!!)
                        viewManager = LinearLayoutManager(this@MainActivity)

                        alpaca_list.apply {
                            layoutManager = viewManager
                            adapter = viewAdapter
                        }
                    }

                    loading_view.visibility = View.INVISIBLE
                } else {
                    onError("Error: ${response.message()}")
                }
            }
        }
    }


    private fun onError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        //loading_view.visibility = View.INVISIBLE
    }

    private fun getAlpacaService(): AlpacaApi {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(AlpacaApi::class.java)
    }
}


