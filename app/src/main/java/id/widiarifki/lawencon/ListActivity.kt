package id.widiarifki.lawencon

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import id.widiarifki.lawencon.helper.SQLiteHelper
import id.widiarifki.lawencon.model.Barang

class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        setupClick()
        showData()
    }

    private var listBarang = ArrayList<Barang>()

    private fun showData() {
        val dbHandler = SQLiteHelper(this, null)
        val cursor = dbHandler.getAllBarang()

        listBarang.clear()
        cursor?.let {
            if (it.moveToFirst()) {
                do {
                    val name = it.getString(it.getColumnIndex(SQLiteHelper.COLUMN_NAME))
                    val qty = it.getString(it.getColumnIndex(SQLiteHelper.COLUMN_QTY))
                    val exp = it.getString(it.getColumnIndex(SQLiteHelper.COLUMN_EXP))
                    val price = it.getString(it.getColumnIndex(SQLiteHelper.COLUMN_PRICE))
                    listBarang.add(Barang(name, qty, exp, price))
                } while (it.moveToNext())
            }
        }

        val notesAdapter = ListAdapter(this, listBarang)
        val list = findViewById<ListView>(R.id.list)
        list.adapter = notesAdapter
    }

    private fun setupClick() {
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        btnAdd.setOnClickListener {
            addBarang()
        }
    }

    private fun addBarang() {
        val intent = Intent(this, FormActivity::class.java)
        startActivityForResult(intent, 1)
    }

    private fun updateBarang(barang: Barang) {
        val intent = Intent(this, FormActivity::class.java)
        intent.putExtra("id", barang.id)
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            1 -> {
                if (resultCode == Activity.RESULT_OK) {
                    showData()
                }
            }
        }
    }

    inner class ListAdapter : BaseAdapter {
        private var listBarang = ArrayList<Barang>()
        private var context: Context? = null

        constructor(context: Context, notesList: ArrayList<Barang>) : super() {
            this.listBarang = notesList
            this.context = context
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
            val view: View?
            val vh: BarangViewHolder

            if (convertView == null) {
                view = layoutInflater.inflate(R.layout.item_barang, parent, false)
                vh = BarangViewHolder(view)
                view.tag = vh
            } else {
                view = convertView
                vh = view.tag as BarangViewHolder
            }

            var mBarang = listBarang[position]

            vh.tvName?.text = mBarang.nama_barang
            vh.btnEdit?.setOnClickListener {
                updateBarang(mBarang)
            }
            vh.btnDelete?.setOnClickListener {

            }

            return view
        }

        override fun getItem(position: Int): Any {
            return listBarang[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return listBarang.size
        }
    }


    private class BarangViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvName: TextView? = null
        var btnEdit: Button? = null
        var btnDelete: Button? = null

        init {
            this.tvName = view?.findViewById(R.id.tvName) as TextView
            this.btnEdit = view?.findViewById(R.id.btnEdit) as Button
            this.btnDelete = view?.findViewById(R.id.btnDelete) as Button
        }
    }
}