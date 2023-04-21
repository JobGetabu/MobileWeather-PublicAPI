package com.jephter.weatherapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.jephter.weatherapp.Model.Location

class LocationAdapter(private val context: Context, private val locations: MutableList<String>) : ArrayAdapter<String>(context, 0, locations) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false)
        val textView = view.findViewById<TextView>(android.R.id.text1)
        textView.text = locations[position].toString()
        return view
    }

    override fun getCount(): Int {
        return locations.size
    }

}
