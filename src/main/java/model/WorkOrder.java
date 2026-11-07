package model;

public class WorkOrder {
    private String idWorkOrder;
    private String tanggalWo;
    private String namaPelanggan;
    private String nomorTelepon;
    private String alamat;
    private String idBarang;
    private String namaBarang;
    private String spesifikasi;

    public WorkOrder() {}

    public WorkOrder(String idWorkOrder, String tanggalWo, String namaPelanggan, String nomorTelepon, String alamat, String idBarang, String namaBarang, String spesifikasi) {
        this.idWorkOrder = idWorkOrder;
        this.tanggalWo = tanggalWo;
        this.namaPelanggan = namaPelanggan;
        this.nomorTelepon = nomorTelepon;
        this.alamat = alamat;
        this.idBarang = idBarang;
        this.namaBarang = namaBarang;
        this.spesifikasi = spesifikasi;
    }

    public String getIdWorkOrder() { return idWorkOrder; }
    public void setIdWorkOrder(String idWorkOrder) { this.idWorkOrder = idWorkOrder; }

    public String getTanggalWo() { return tanggalWo; }
    public void setTanggalWo(String tanggalWo) { this.tanggalWo = tanggalWo; }

    public String getNamaPelanggan() { return namaPelanggan; }
    public void setNamaPelanggan(String namaPelanggan) { this.namaPelanggan = namaPelanggan; }

    public String getNomorTelepon() { return nomorTelepon; }
    public void setNomorTelepon(String nomorTelepon) { this.nomorTelepon = nomorTelepon; }

    public String getAlamat() { return alamat; }
    public void setAlamat(String alamat) { this.alamat = alamat; }

    public String getIdBarang() { return idBarang; }
    public void setIdBarang(String idBarang) { this.idBarang = idBarang; }

    public String getNamaBarang() { return namaBarang; }
    public void setNamaBarang(String namaBarang) { this.namaBarang = namaBarang; }

    public String getSpesifikasi() { return spesifikasi; }
    public void setSpesifikasi(String spesifikasi) { this.spesifikasi = spesifikasi; }
}