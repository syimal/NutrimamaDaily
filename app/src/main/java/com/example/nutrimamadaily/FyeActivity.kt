package com.example.nutrimamadaily

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

class FyeActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var dataList: ArrayList<DataClass>
    lateinit var imageList: Array<Int>
    lateinit var titleList: Array<String>
    lateinit var descList: Array<String>
    lateinit var detailImage: Array<Int>
    private lateinit var myAdapter: AdapterClass
    private lateinit var searchView: SearchView
    private lateinit var searchList: ArrayList<DataClass>

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Memastikan layout yang benar
        setContentView(R.layout.activity_fye)

        // Menyesuaikan padding dengan system bars (edge to edge)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Data untuk RecyclerView
        imageList = arrayOf(
            R.drawable.ic_ayam,
            R.drawable.ic_bayam,
            R.drawable.ic_brokoli,
            R.drawable.ic_daging,
            R.drawable.ic_delima,
            R.drawable.ic_hatisapi,
            R.drawable.ic_ikan,
            R.drawable.ic_kacangedamame,
            R.drawable.ic_kacangpanjang,
            R.drawable.ic_telur
        )

        titleList = arrayOf(
            "Ayam", "Bayam", "Brokoli", "Daging", "Delima", "Hati Sapi", "Ikan", "Kacang Edamame", "Kacang Panjang", "Telur"
        )

        descList = arrayOf(
            getString(R.string.Ayam),
            getString(R.string.Bayam),
            getString(R.string.Brokoli),
            getString(R.string.Daging),
            getString(R.string.Delima),
            getString(R.string.HatiSapi),
            getString(R.string.Ikan),
            getString(R.string.KacangEdamame),
            getString(R.string.KacangPanjang),
            getString(R.string.Telur)
        )

        detailImage = arrayOf(
            R.drawable.ic_ayam,
            R.drawable.ic_bayam,
            R.drawable.ic_brokoli,
            R.drawable.ic_daging,
            R.drawable.ic_delima,
            R.drawable.ic_hatisapi,
            R.drawable.ic_ikan,
            R.drawable.ic_kacangedamame,
            R.drawable.ic_kacangpanjang,
            R.drawable.ic_telur
        )

        // Inisialisasi RecyclerView dan SearchView
        recyclerView = findViewById(R.id.recyclerView)
        searchView = findViewById(R.id.search)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        dataList = arrayListOf()
        searchList = arrayListOf()

        // Mengambil data untuk ditampilkan
        getData()

        // Setup fungsionalitas pencarian
        searchView.clearFocus()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchList.clear()
                val searchText = newText!!.toLowerCase(Locale.getDefault())
                if (searchText.isNotEmpty()) {
                    dataList.forEach {
                        if (it.dataTitle.toLowerCase(Locale.getDefault()).contains(searchText)) {
                            searchList.add(it)
                        }
                    }
                    recyclerView.adapter!!.notifyDataSetChanged()
                } else {
                    searchList.clear()
                    searchList.addAll(dataList)
                    recyclerView.adapter!!.notifyDataSetChanged()
                }
                return false
            }
        })

        // Inisialisasi adapter dan menerapkannya pada RecyclerView
        myAdapter = AdapterClass(searchList)
        recyclerView.adapter = myAdapter

        // Fungsionalitas ketika item di RecyclerView diklik
        myAdapter.onItemClick = {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("android", it)  // Mengirim data ke DetailActivity
            startActivity(intent)
        }
    }

    // Mengambil data dan menambahkannya ke dataList
    private fun getData() {
        for (i in imageList.indices) {
            val dataClass = DataClass(imageList[i], titleList[i], descList[i], detailImage[i])
            dataList.add(dataClass)
        }
        searchList.addAll(dataList)
        recyclerView.adapter = AdapterClass(searchList)
    }
}
