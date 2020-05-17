package br.unifor.dispositivos.moveis

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val db = FirebaseFirestore.getInstance()
//        CreateNewDocument(db)
        ReadDocuments(db)
    }

    fun pesquisar(view: View) {
        val pessoaId = (findViewById<EditText>(R.id.editText)).text.toString()
        val db = FirebaseFirestore.getInstance()
        if (pessoaId == "")
            ReadDocuments(db)
        else
            ReadDocuments(db, pessoaId)
    }

    private fun CreateNewDocument(db: FirebaseFirestore) {
        val user = hashMapOf(
            "ID" to 15,
            "Nome" to "Oi"
        )

        db.collection("Pessoa")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d("FragmentActivity", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("FragmentActivity", "Error adding document", e)
            }
    }

    private fun ReadDocuments(db: FirebaseFirestore) {
        db.collection("Pessoa")
            .get()
            .addOnSuccessListener { result ->
                val pessoas = mutableListOf<Pessoa>()

                for (document in result) {
                    Log.d("FragmentActivity", "${document.id} => ${document.data}")
                    val data = document.data
                    pessoas.add(Pessoa(data["ID"].toString(), data["Nome"].toString()))
                }
                val listItems = arrayOfNulls<String>(pessoas!!.size)
                for (i in pessoas.indices)
                    listItems[i] = "[Id: ${pessoas[i].id}] [Nome: ${pessoas[i].nome}]"
                val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)
                findViewById<ListView>(R.id.list)?.adapter = adapter
            }
            .addOnFailureListener { exception ->
                Log.w("FragmentActivity", "Error getting documents.", exception)
            }
    }

    private fun ReadDocuments(db: FirebaseFirestore, id: String) {
        db.collection("Pessoa")
            .whereEqualTo("ID", id)
            .get()
            .addOnSuccessListener { result ->
                val pessoas = mutableListOf<Pessoa>()

                for (document in result) {
                    Log.d("FragmentActivity", "${document.id} => ${document.data}")
                    val data = document.data
                    pessoas.add(Pessoa(data["ID"].toString(), data["Nome"].toString()))
                }
                val listItems = arrayOfNulls<String>(pessoas!!.size)
                for (i in pessoas.indices)
                    listItems[i] = "[Id: ${pessoas[i].id}] [Nome: ${pessoas[i].nome}]"
                val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)
                findViewById<ListView>(R.id.list)?.adapter = adapter
            }
            .addOnFailureListener { exception ->
                Log.w("FragmentActivity", "Error getting documents.", exception)
            }
    }

    class Pessoa(val id: String, val nome: String) {

    }
}
