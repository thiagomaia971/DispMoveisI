package com.example.sqlliteapplication

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DB_HELPER = DatabaseHelper(this)
    }

    fun pesquisar(view: View) {
        val proprietarioId = (findViewById<EditText>(R.id.editText)).text.toString()

        popularTextView(proprietarioId)
        popularListView(proprietarioId)
    }

    private fun popularTextView(proprietarioId: String) {
        val proprietarioNome = DB_HELPER?.getProprietarioNome(proprietarioId)
        findViewById<TextView>(R.id.nomeProprietario_Value).text = proprietarioNome
    }

    fun popularListView(proprietarioId: String) {
        val carros = DB_HELPER?.getCarrosDoProprietario(proprietarioId)
        val listItems = arrayOfNulls<String>(carros!!.size)
        for (i in carros.indices)
            listItems[i] = "[Id: ${carros[i].id}] [Modelo: ${carros[i].modelo}] [Estilo: ${carros[i].estilo}] [Ano: ${carros[i].ano}]"
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)
        findViewById<ListView>(R.id.list_carro)?.adapter = adapter
    }

    companion object {
        var DB_HELPER: DatabaseHelper? = null
    }
}
