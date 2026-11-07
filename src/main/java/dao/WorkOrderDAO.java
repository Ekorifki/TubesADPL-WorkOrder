package dao;

import database.DatabaseConnection;
import model.WorkOrder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class WorkOrderDAO {
    
    // 1. READ: Mengambil semua data dari database (Sudah kita buat sebelumnya)
    public List<WorkOrder> getAllWorkOrders() {
        List<WorkOrder> listWO = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM work_order";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                WorkOrder wo = new WorkOrder();
                wo.setIdWorkOrder(rs.getString("id_work_order"));
                wo.setTanggalWo(rs.getString("tanggal_wo"));
                wo.setNamaPelanggan(rs.getString("nama_pelanggan"));
                wo.setNomorTelepon(rs.getString("nomor_telepon"));
                wo.setAlamat(rs.getString("alamat"));
                wo.setIdBarang(rs.getString("id_barang"));
                wo.setNamaBarang(rs.getString("nama_barang"));
                wo.setSpesifikasi(rs.getString("spesifikasi"));
                listWO.add(wo);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error di DAO Read: " + e.getMessage());
        }
        return listWO;
    }

    // 2. CREATE: Menyimpan data baru ke database MySQL
    public boolean tambahData(WorkOrder wo) {
        String sql = "INSERT INTO work_order (id_work_order, tanggal_wo, nama_pelanggan, nomor_telepon, alamat, id_barang, nama_barang, spesifikasi) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, wo.getIdWorkOrder());
            ps.setString(2, wo.getTanggalWo());
            ps.setString(3, wo.getNamaPelanggan());
            ps.setString(4, wo.getNomorTelepon());
            ps.setString(5, wo.getAlamat());
            ps.setString(6, wo.getIdBarang());
            ps.setString(7, wo.getNamaBarang());
            ps.setString(8, wo.getSpesifikasi());
            
            ps.executeUpdate();
            ps.close();
            return true; // Berhasil
        } catch (Exception e) {
            System.out.println("Error di DAO Create: " + e.getMessage());
            return false; // Gagal
        }
    }

    // 3. UPDATE: Mengubah data yang sudah ada di database
    public boolean ubahData(WorkOrder wo) {
        String sql = "UPDATE work_order SET tanggal_wo=?, nama_pelanggan=?, nomor_telepon=?, alamat=?, id_barang=?, nama_barang=?, spesifikasi=? WHERE id_work_order=?";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            // Urutan diisi sesuai tanda tanya (?) pada query UPDATE di atas
            ps.setString(1, wo.getTanggalWo());
            ps.setString(2, wo.getNamaPelanggan());
            ps.setString(3, wo.getNomorTelepon());
            ps.setString(4, wo.getAlamat());
            ps.setString(5, wo.getIdBarang());
            ps.setString(6, wo.getNamaBarang());
            ps.setString(7, wo.getSpesifikasi());
            ps.setString(8, wo.getIdWorkOrder()); // WHERE id_work_order=?
            
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error di DAO Update: " + e.getMessage());
            return false;
        }
    }

    // 4. DELETE: Menghapus data dari database berdasarkan ID
    public boolean hapusData(String idWorkOrder) {
        String sql = "DELETE FROM work_order WHERE id_work_order=?";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, idWorkOrder);
            
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error di DAO Delete: " + e.getMessage());
            return false;
        }
    }
}