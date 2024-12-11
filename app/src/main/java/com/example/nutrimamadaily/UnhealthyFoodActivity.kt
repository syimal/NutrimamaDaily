package com.example.nutrimamadaily

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class UnhealthyFoodActivity : AppCompatActivity() {
    private var selectedUnhealthyCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_unhealthy_food)

        val checkBoxes = ArrayList<CheckBox>()
        for (i in 1..20) {
            val checkBoxId = resources.getIdentifier("cbUnhealthy$i", "id", packageName)
            val checkBox = findViewById<CheckBox>(checkBoxId)
            checkBoxes.add(checkBox)
        }

        val btnOke2 = findViewById<Button>(R.id.BtnOke2)

        btnOke2.setOnClickListener {
            selectedUnhealthyCount = 0
            val selectedItems = StringBuilder("You selected:\n")

            for (i in 0 until checkBoxes.size) {
                if (checkBoxes[i].isChecked) {
                    selectedItems.append("${getUnhealthyFoodName(i + 1)}\n")
                    selectedUnhealthyCount++
                }
            }

            // Menampilkan Toast
            Toast.makeText(this@UnhealthyFoodActivity, selectedItems.toString(), Toast.LENGTH_LONG).show()

            // Hitung progress tidak sehat berdasarkan jumlah makanan yang dipilih
            val unhealthyProgress = selectedUnhealthyCount * 25 // 25% untuk setiap makanan tidak sehat yang dipilih

            // Menambahkan log untuk pengecekan
            Log.d("UnhealthyFoodActivity", "Progress Tidak Sehat: $unhealthyProgress")

            // Kirim ke activity berikutnya untuk menampilkan jumlah porsi makanan
            val intent = Intent(this, JumlahMakananActivity::class.java)
            intent.putExtra("unhealthyProgress", unhealthyProgress) // Kirim unhealthyProgress ke JumlahMakananActivity
            startActivity(intent)  // Memulai activity JumlahMakananActivity
        }
    }

    private fun getUnhealthyFoodName(index: Int): String {
        return when (index) {
            1 -> "Seblak"
            2 -> "Mie Instan"
            3 -> "Kentang Goreng"
            4 -> "Burger"
            5 -> "Pizza"
            6 -> "Hotdog"
            7 -> "Nugget Ayam"
            8 -> "Cake"
            9 -> "Donat"
            10 -> "Pop Corn"
            11 -> "Ikan Sarden"
            12 -> "Eskrim"
            13 -> "Cireng"
            14 -> "Gorengan"
            15 -> "Jeroan"
            16 -> "Cokelat"
            17 -> "Bakso Bakar"
            18 -> "Mie Ayam"
            19 -> "Bakso"
            20 -> "Basreng"
            else -> "Unknown Food"
        }
    }
}
