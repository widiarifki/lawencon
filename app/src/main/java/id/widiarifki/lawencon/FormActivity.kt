package id.widiarifki.lawencon

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import id.widiarifki.lawencon.helper.SQLiteHelper
import id.widiarifki.lawencon.model.Barang

class FormActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)
        setupClick()
    }

    private fun setupClick() {
        val btnUpdate = findViewById<Button>(R.id.btnUpdate)
        btnUpdate.setOnClickListener {
            addBarang()
        }
    }

    private fun addBarang() {
        val nama = findViewById<TextInputEditText>(R.id.etNama)
        val qty = findViewById<TextInputEditText>(R.id.etQty)
        val exp = findViewById<TextInputEditText>(R.id.etExp)
        val harga = findViewById<TextInputEditText>(R.id.etHarga)

        val dbHandler = SQLiteHelper(this, null)
        val barang = Barang(
            nama.text.toString(),
            qty.text.toString(),
            exp.text.toString(),
            harga.text.toString()
        )

        dbHandler.addBarang(barang)


        Toast.makeText(this, nama.text.toString() + " sudah ditambahkan", Toast.LENGTH_LONG).show()
        finish()
    }
}