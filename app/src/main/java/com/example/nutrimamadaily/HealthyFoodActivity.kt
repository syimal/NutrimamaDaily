package com.example.nutrimamadaily

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class HealthyFoodActivity : AppCompatActivity() {

    private var selectedHealthyCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_healthy_food)

        val checkBoxes = ArrayList<CheckBox>()
        for (i in 1..36) {
            val checkBoxId = resources.getIdentifier("cbHealthy$i", "id", packageName)
            val checkBox = findViewById<CheckBox>(checkBoxId)
            checkBoxes.add(checkBox)
        }

        val btnOke1 = findViewById<Button>(R.id.BtnOke1)

        btnOke1.setOnClickListener {
            selectedHealthyCount = 0
            val selectedItems = StringBuilder("You selected:\n")

            for (i in 0 until checkBoxes.size) {
                if (checkBoxes[i].isChecked) {
                    selectedItems.append("${getFoodName(i + 1)}\n")
                    selectedHealthyCount++
                }
            }

            // Menampilkan Toast dengan jumlah yang dipilih
            Toast.makeText(
                this@HealthyFoodActivity,
                "Selected Healthy Foods: $selectedHealthyCount",
                Toast.LENGTH_LONG
            ).show()

            // Hitung progress untuk makanan sehat (25%)
            val progress = (selectedHealthyCount * 50) // Misalnya 25% untuk setiap makanan sehat yang dipilih
            val intent = Intent(this, JumlahMakananActivity::class.java)
            intent.putExtra("selected_healthy_food_count", selectedHealthyCount)
            intent.putExtra("progress_healthy", progress)
            startActivity(intent)
        }
    }

    private fun getFoodName(index: Int): String {
        return when (index) {
            1 -> "Nasi"
            2 -> "Salad Sayur"
            3 -> "Salad Buah"
            4 -> "Sayur Sop"
            5 -> "Soto Ayam"
            6 -> "Bubur"
            7 -> "Rawon"
            8 -> "Ikan Bakar"
            9 -> "Ikan Goreng"
            10 -> "Nasi Goreng"
            11 -> "Pecel"
            12 -> "Gado-Gado"
            13 -> "Pepes Ikan"
            14 -> "Sayur Bening Bayam"
            15 -> "Tempe Goreng"
            16 -> "Tahu Goreng"
            17 -> "Bubur Kacang Ijo"
            18 -> "Tumis Bayam"
            19 -> "Rujak Buah"
            20 -> "Bubur Sum-Sum"
            21 -> "Bubur Ketan Hitam"
            22 -> "Sop daging"
            23 -> "Udang Saus Padang"
            24 -> "Kepiting Saus Padang"
            25 -> "Cumi Asam Manis"
            26 -> "Cumi Saus Tiram"
            27 -> "Cumi Saus Padang"
            28 -> "Ayam Bakar"
            29 -> "Tumis Kangkung"
            30 -> "Ayam Goreng"
            31 -> "Rendang"
            32 -> "Opor Ayam"
            33 -> "Dendeng Sapi"
            34 -> "Ayam Rica-Rica"
            35 -> "Tumis Tahu Tempe"
            36 -> "Pudding Buah"
            else -> "Unknown Food"
        }
    }
}
