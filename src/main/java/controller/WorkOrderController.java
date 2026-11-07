package controller;

import view.WorkOrderView;
import dao.WorkOrderDAO;
import model.WorkOrder;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;

public class WorkOrderController {
    private WorkOrderView view;
    private WorkOrderDAO dao;

    public WorkOrderController(WorkOrderView view) {
        this.view = view;
        this.dao = new WorkOrderDAO();
        initController();
        tampilData(); // Langsung ambil dari database saat dibuka
    }

    private void initController() {
        view.btnSimpan.addActionListener(e -> simpanData());
        view.btnUbah.addActionListener(e -> ubahData());
        view.btnHapus.addActionListener(e -> hapusData());
        view.btnBatal.addActionListener(e -> bersihkanForm());
        view.tabelWO.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                isiForm();
            }
        });
    }

    // 1. TAMPILKAN DATA (READ)
    private void tampilData() {
        view.tableModel.setRowCount(0);
        List<WorkOrder> listWO = dao.getAllWorkOrders(); // Minta ke DAO
        for (WorkOrder wo : listWO) {
            view.tableModel.addRow(new Object[]{
                wo.getIdWorkOrder(), wo.getTanggalWo(), wo.getNamaPelanggan(),
                wo.getNomorTelepon(), wo.getAlamat(), wo.getIdBarang(),
                wo.getNamaBarang(), wo.getSpesifikasi()
            });
        }
    }

    // 2. SIMPAN DATA (CREATE)
    private void simpanData() {
        if (view.txtIdWo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(view, "ID tidak boleh kosong!");
            return;
        }
        
        WorkOrder wo = buatObjekDariForm(); // Bungkus data form
        if (dao.tambahData(wo)) {           // Suruh DAO simpan ke MySQL
            JOptionPane.showMessageDialog(view, "Data tersimpan di Database!");
            bersihkanForm();
            tampilData(); // Refresh tabel
        } else {
            JOptionPane.showMessageDialog(view, "Gagal menyimpan data!");
        }
    }

    // 3. UBAH DATA (UPDATE)
    private void ubahData() {
        if (view.tabelWO.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(view, "Pilih data di tabel yang mau diubah!");
            return;
        }
        
        WorkOrder wo = buatObjekDariForm();
        if (dao.ubahData(wo)) { // Suruh DAO update MySQL
            JOptionPane.showMessageDialog(view, "Data berhasil diubah!");
            bersihkanForm();
            tampilData();
        } else {
            JOptionPane.showMessageDialog(view, "Gagal mengubah data!");
        }
    }

    // 4. HAPUS DATA (DELETE)
    private void hapusData() {
        int baris = view.tabelWO.getSelectedRow();
        if (baris == -1) {
            JOptionPane.showMessageDialog(view, "Pilih data yang mau dihapus!");
            return;
        }
        
        String id = view.tabelWO.getValueAt(baris, 0).toString();
        int konfirmasi = JOptionPane.showConfirmDialog(view, "Yakin hapus ID " + id + "?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        
        if (konfirmasi == JOptionPane.YES_OPTION) {
            if (dao.hapusData(id)) { // Suruh DAO hapus dari MySQL
                JOptionPane.showMessageDialog(view, "Data terhapus dari Database!");
                bersihkanForm();
                tampilData();
            }
        }
    }

    // --- FUNGSI BANTUAN AGAR KODE TETAP PENDEK ---

    private void bersihkanForm() {
        view.txtIdWo.setText("");
        view.txtTanggal.setDate(null);
        view.txtNama.setText("");
        view.txtTelepon.setText("");
        view.txtAlamat.setText("");
        view.txtIdBarang.setText("");
        view.txtNamaBarang.setText("");
        view.txtSpesifikasi.setText("");
        view.txtIdWo.requestFocus();
        view.tabelWO.clearSelection();
    }

    // Membungkus isian form menjadi satu objek WorkOrder
    private WorkOrder buatObjekDariForm() {
        WorkOrder wo = new WorkOrder();
        wo.setIdWorkOrder(view.txtIdWo.getText());
        if (view.txtTanggal.getDate() != null) {
            wo.setTanggalWo(new SimpleDateFormat("yyyy-MM-dd").format(view.txtTanggal.getDate()));
        } else {
            wo.setTanggalWo("");
        }
        wo.setNamaPelanggan(view.txtNama.getText());
        wo.setNomorTelepon(view.txtTelepon.getText());
        wo.setAlamat(view.txtAlamat.getText());
        wo.setIdBarang(view.txtIdBarang.getText());
        wo.setNamaBarang(view.txtNamaBarang.getText());
        wo.setSpesifikasi(view.txtSpesifikasi.getText());
        return wo;
    }

    // Melempar data dari tabel ke form
    private void isiForm() {
        int baris = view.tabelWO.getSelectedRow();
        if (baris != -1) {
            view.txtIdWo.setText(view.tabelWO.getValueAt(baris, 0).toString());
            try {
                String tgl = view.tabelWO.getValueAt(baris, 1).toString();
                view.txtTanggal.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(tgl));
            } catch (Exception e) { view.txtTanggal.setDate(null); }
            view.txtNama.setText(view.tabelWO.getValueAt(baris, 2).toString());
            view.txtTelepon.setText(view.tabelWO.getValueAt(baris, 3).toString());
            view.txtAlamat.setText(view.tabelWO.getValueAt(baris, 4).toString());
            view.txtIdBarang.setText(view.tabelWO.getValueAt(baris, 5).toString());
            view.txtNamaBarang.setText(view.tabelWO.getValueAt(baris, 6).toString());
            view.txtSpesifikasi.setText(view.tabelWO.getValueAt(baris, 7).toString());
        }
    }
}