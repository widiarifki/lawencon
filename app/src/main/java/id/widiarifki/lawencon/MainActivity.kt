package id.widiarifki.lawencon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupClick()
    }

    private fun setupClick() {
        val btn = findViewById<Button>(R.id.btnSubmit)
        btn.setOnClickListener {
            if (validated()) {
                gotoForm()
            } else {
                Toast.makeText(this, "username & password salah", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun validated() : Boolean {
        val username = findViewById<TextInputEditText>(R.id.etUsername)
        val pwd = findViewById<TextInputEditText>(R.id.etPassword)

        return username.text.toString() == "test" && pwd.text.toString() == "1234"
    }

    private fun gotoForm() {
        val intent = Intent(this, ListActivity::class.java)
        startActivity(intent)
    }


}