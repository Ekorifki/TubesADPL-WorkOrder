package com.mycompany.tubesadpl;

import view.WorkOrderView;
import controller.WorkOrderController;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class TubesADPL {

    public static void main(String[] args) {
        // 1. Mengatur tampilan agar menyatu dengan tema sistem operasi (opsional tapi bagus)
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // 2. Membuka form dan menyambungkannya ke Controller
        SwingUtilities.invokeLater(() -> {
            WorkOrderView view = new WorkOrderView(); // Buat UI
            new WorkOrderController(view);            // Sambungkan ke Controller
            view.setVisible(true);                    // Tampilkan ke layar
        });
    }
}