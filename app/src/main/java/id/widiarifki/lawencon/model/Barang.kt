package id.widiarifki.lawencon.model

class Barang {

    var id: Int = 0
    var nama_barang: String? = null
    var qty: Int = 0
    var exp_date: String? = null
    var harga: Int = 0

    constructor(id: Int, nama: String, qty: Int, exp_date: String, harga: Int) {
        this.id = id
        this.nama_barang = nama
        this.qty = qty
        this.exp_date = exp_date
        this.harga = harga
    }

    constructor(nama: String, qty: String, exp_date: String, harga: String) {
        this.nama_barang = nama
        this.qty = qty.toInt()
        this.exp_date = exp_date
        this.harga = harga.toInt()
    }

}