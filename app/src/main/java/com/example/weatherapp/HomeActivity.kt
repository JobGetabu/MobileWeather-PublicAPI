package com.jephter.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.ListView
import com.jephter.weatherapp.Adapter.LocationAdapter
import com.jephter.weatherapp.Model.Location
import com.jephter.weatherapp.RetrofitClient.weatherApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {
    private lateinit var apiKey: String
    private lateinit var locationListView: ListView

    private lateinit var locationAdapter: ArrayAdapter<String> // ArrayAdapter for location names
    private val locationNames: MutableList<String> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        apiKey = "bb6caea155f648f68b391337231804"
        val listView = findViewById<ListView>(R.id.listView)
        val searchbutton = findViewById<ImageView>(R.id.searchbutton)
        val searchView = findViewById<EditText>(R.id.searchView)


        val locationAdapter = LocationAdapter(this, locationNames)
        locationListView.adapter = locationAdapter
        searchbutton.setOnClickListener{
            val searchView = findViewById<EditText>(R.id.searchView)
            searchView.visibility=  View.GONE
        }

        searchView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                searchLocations(s.toString())

                if (locationNames.isEmpty()) {
                    locationListView.visibility = View.GONE // Hide ListView when location names are empty
                } else {
                    locationListView.visibility = View.VISIBLE // Show ListView when location names are not empty
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun searchLocations(query: String) {
        weatherApiService.searchLocations(query).enqueue(object : Callback<List<Location>> {
            override fun onResponse(call: Call<List<Location>>, response: Response<List<Location>>) {

                if (response.isSuccessful) {
                    locationNames.clear()
                    locationNames.addAll((response.body() ?: emptyList()) as Collection<String>)
                    (locationListView.adapter as LocationAdapter).notifyDataSetChanged()
                } else {
                    // Handle error
                }
            }

            override fun onFailure(call: Call<List<Location>>, t: Throwable) {
                // Handle error
            }
        })
    }




}