import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Deque;

public class AntrianDagingQurbanGUI extends JFrame {
    private AntrianQurban antrian;
    private JTextField namaField, noTelpField, alamatField;
    private JTable table;
    private DefaultTableModel tableModel;

    public AntrianDagingQurbanGUI() {
        antrian = new AntrianQurban();

        setTitle("Sistem Antrian Pengambilan Daging Qurban Masjid Al Huda");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel input dengan GridBagLayout
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createTitledBorder("Tambah Penerima"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("Nama:"), gbc);

        gbc.gridx = 1;
        namaField = new JTextField(20);
        inputPanel.add(namaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("No. Telp:"), gbc);

        gbc.gridx = 1;
        noTelpField = new JTextField(20);
        inputPanel.add(noTelpField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(new JLabel("Alamat:"), gbc);

        gbc.gridx = 1;
        alamatField = new JTextField(20);
        inputPanel.add(alamatField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JPanel buttonPanel = new JPanel();
        JButton tambahButton = new JButton("Tambah");
        tambahButton.setBackground(Color.GREEN);
        tambahButton.setForeground(Color.WHITE);
        buttonPanel.add(tambahButton);

        JButton panggilButton = new JButton("Panggil Penerima");
        panggilButton.setBackground(Color.BLUE);
        panggilButton.setForeground(Color.WHITE);
        buttonPanel.add(panggilButton);

        JButton hapusButton = new JButton("Hapus Semua Antrian");
        hapusButton.setBackground(Color.RED);
        hapusButton.setForeground(Color.WHITE);
        buttonPanel.add(hapusButton);
        inputPanel.add(buttonPanel, gbc);

        add(inputPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{"Nama", "No. Telp", "Alamat"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        tambahButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tambahPenerima();
            }
        });

        panggilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panggilPenerima();
            }
        });

        hapusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hapusSemuaAntrian();
            }
        });
    }

    private void tambahPenerima() {
        String nama = namaField.getText().trim();
        String noTelp = noTelpField.getText().trim();
        String alamat = alamatField.getText().trim();

        if (nama.isEmpty() || noTelp.isEmpty() || alamat.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua field harus diisi.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Penerima penerima = new Penerima(nama, noTelp, alamat);
        antrian.tambahPenerima(penerima);
        namaField.setText("");
        noTelpField.setText("");
        alamatField.setText("");

        try {
            antrian.simpanDataAntrian(penerima);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat menyimpan data antrian.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        updateTable();
    }

    private void updateTable() {
        Deque<Penerima> queue = antrian.getAntrian();
        tableModel.setRowCount(0); // Reset tabel

        for (Penerima penerima : queue) {
            tableModel.addRow(new Object[]{penerima.getNama(), penerima.getNoTelp(), penerima.getAlamat()});
        }
    }

    private void panggilPenerima() {
        Penerima penerima = antrian.panggilPenerima();
        if (penerima == null) {
            JOptionPane.showMessageDialog(this, "Tidak ada penerima dalam antrian.", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Memanggil: " + penerima.toString(), "Panggilan Penerima", JOptionPane.INFORMATION_MESSAGE);
            updateTable();
        }
    }

    private void hapusSemuaAntrian() {
        antrian.hapusSemuaAntrian();
        updateTable();
        JOptionPane.showMessageDialog(this, "Semua antrian telah dihapus.", "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AntrianDagingQurbanGUI().setVisible(true);
            }
        });
    }
}
