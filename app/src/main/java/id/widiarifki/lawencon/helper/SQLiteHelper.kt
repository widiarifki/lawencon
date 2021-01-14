package id.widiarifki.lawencon.helper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import id.widiarifki.lawencon.model.Barang

class SQLiteHelper(context: Context, factory: SQLiteDatabase.CursorFactory?)
    : SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TBL_BARANG = ("CREATE TABLE " +
                TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_NAME
                + " TEXT" + ")")
        db.execSQL(CREATE_TBL_BARANG)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun addBarang(barang: Barang) {
        val values = ContentValues()
        values.put(COLUMN_NAME, barang.nama_barang)
        values.put(COLUMN_QTY, barang.qty)
        values.put(COLUMN_EXP, barang.exp_date)
        values.put(COLUMN_PRICE, barang.harga)
        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getAllBarang(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }

    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "id.widiarifki.lawencon.db"
        val TABLE_NAME = "barang"
        val COLUMN_ID = "_id"
        val COLUMN_NAME = "nama_barang"
        val COLUMN_QTY = "qty"
        val COLUMN_EXP = "exp_date"
        val COLUMN_PRICE = "harga"
    }
}