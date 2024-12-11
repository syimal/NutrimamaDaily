package com.example.nutrimamadaily

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class JumlahMakananActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jumlah_makanan)

        // Referensi elemen UI
        val tvProgressText = findViewById<TextView>(R.id.tvProgressText)
        val rbSatu = findViewById<RadioButton>(R.id.rbSatu)
        val rbDua = findViewById<RadioButton>(R.id.rbDua)
        val rbTiga = findViewById<RadioButton>(R.id.rbTiga)
        val btnSubmit = findViewById<Button>(R.id.BtnSubmit)

        // Dapatkan progres sebelumnya dari Intent
        val previousProgress = intent.getIntExtra("progress", 0)
        Log.d("JumlahMakananActivity", "Previous Progress: $previousProgress") // Debugging

        // Tampilkan progres awal di TextView
        tvProgressText.text = "Progres: $previousProgress%"

        // Set listener untuk tombol submit
        btnSubmit.setOnClickListener {
            // Cek apakah pengguna memilih salah satu porsi
            if (!rbSatu.isChecked && !rbDua.isChecked && !rbTiga.isChecked) {
                Toast.makeText(this, "Pilih jumlah porsi terlebih dahulu!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Tambahkan 25% ke progres sebelumnya
            val totalProgress = previousProgress + 25
            Log.d("JumlahMakananActivity", "Total Progress: $totalProgress") // Debugging

            // Tampilkan progres terbaru
            tvProgressText.text = "Progres: $totalProgress%"

            // Tampilkan Toast berdasarkan pilihan porsi
            val selectedPorsi = when {
                rbSatu.isChecked -> "Anda memilih Satu Porsi"
                rbDua.isChecked -> "Anda memilih Setengah Porsi"
                rbTiga.isChecked -> "Anda memilih Seperempat Porsi"
                else -> ""
            }
            Toast.makeText(this, selectedPorsi, Toast.LENGTH_SHORT).show()

            // Kirim hasil ke halaman berikutnya
            val intent = Intent(this, WonPage::class.java)
            intent.putExtra("progress", totalProgress)
            startActivity(intent)
        }
    }
}
