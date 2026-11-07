package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import com.toedter.calendar.JDateChooser;

public class WorkOrderView extends JFrame {
    
    // Deklarasi Komponen UI
    public JTextField txtIdWo, txtNama, txtTelepon, txtAlamat, txtIdBarang, txtNamaBarang, txtSpesifikasi;
    public JDateChooser txtTanggal; 
    public JButton btnSimpan, btnUbah, btnHapus, btnBatal;
    public JTable tabelWO;
    public DefaultTableModel tableModel;

    public WorkOrderView() {
        // Setup Frame Utama
        setTitle("Kelola Work Order - Tubes ADPL");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        setLayout(new BorderLayout(10, 10));

        // 1. Panel Form Input
        JPanel panelForm = new JPanel(new GridLayout(8, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        txtIdWo = new JTextField();
        txtNama = new JTextField();
        txtTelepon = new JTextField();
        txtAlamat = new JTextField();
        txtIdBarang = new JTextField();
        txtNamaBarang = new JTextField();
        txtSpesifikasi = new JTextField();
        txtTanggal = new JDateChooser();
        txtTanggal.setDateFormatString("yyyy-MM-dd"); 

        // Validasi input angka
        setNumberOnly(txtIdWo);
        setNumberOnly(txtTelepon);
        setNumberOnly(txtIdBarang);

        panelForm.add(new JLabel("ID Work Order (Angka):")); panelForm.add(txtIdWo);
        panelForm.add(new JLabel("Tanggal WO:")); panelForm.add(txtTanggal);
        panelForm.add(new JLabel("Nama Pelanggan:")); panelForm.add(txtNama);
        panelForm.add(new JLabel("Nomor Telepon (Angka):")); panelForm.add(txtTelepon);
        panelForm.add(new JLabel("Alamat:")); panelForm.add(txtAlamat);
        panelForm.add(new JLabel("ID Barang (Angka):")); panelForm.add(txtIdBarang);
        panelForm.add(new JLabel("Nama Barang:")); panelForm.add(txtNamaBarang);
        panelForm.add(new JLabel("Spesifikasi:")); panelForm.add(txtSpesifikasi);

        // 2. Panel Tombol Aksi
        JPanel panelTombol = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        btnSimpan = new JButton("Simpan");
        btnUbah = new JButton("Ubah");
        btnHapus = new JButton("Hapus");
        btnBatal = new JButton("Batal");

        panelTombol.add(btnSimpan);
        panelTombol.add(btnUbah);
        panelTombol.add(btnHapus);
        panelTombol.add(btnBatal);

        JPanel panelAtas = new JPanel(new BorderLayout());
        panelAtas.add(panelForm, BorderLayout.CENTER);
        panelAtas.add(panelTombol, BorderLayout.SOUTH);

        // 3. Panel Tabel Data
        String[] kolomTabel = {"ID WO", "Tanggal", "Nama", "Telepon", "Alamat", "ID Barang", "Nama Barang", "Spesifikasi"};
        tableModel = new DefaultTableModel(kolomTabel, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelWO = new JTable(tableModel);
        
        // Perapian Tabel (Ukuran Baris & Font)
        tabelWO.setRowHeight(30); 
        tabelWO.setFont(new Font("SansSerif", Font.PLAIN, 14)); 
        tabelWO.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14)); 
        
        JScrollPane scrollPane = new JScrollPane(tabelWO);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));

        add(panelAtas, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    // Fungsi mencegah user mengetik huruf
    private void setNumberOnly(JTextField textField) {
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!Character.isDigit(c)) {
                    evt.consume(); 
                }
            }
        });
    }

    // Entry point GUI
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            WorkOrderView view = new WorkOrderView();
            new controller.WorkOrderController(view); 
            view.setVisible(true);
        });
    }
}