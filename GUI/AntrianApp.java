import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Deque;
import java.util.LinkedList;

// Class Pasien
class Pasien {
    private String name;
    private int age;

    public Pasien(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Nama: " + name + ", Usia: " + age;
    }
}

// Class Antrian
class Antrian {
    private Deque<Pasien> queue;
    private int nomorAntrian;

    public Antrian() {
        queue = new LinkedList<>();
        nomorAntrian = 0;
    }

    public void enqueue(Pasien pasien) {
        queue.addLast(pasien);
        nomorAntrian++;
    }

    public Deque<Pasien> getQueue() {
        return queue;
    }

    public int getNomorAntrian() {
        return nomorAntrian;
    }

    public void simpanDataAntrian(Pasien pasien) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data_antrian.txt", true))) {
            writer.write("Nomor Antrian: " + nomorAntrian);
            writer.write(", Nama: " + pasien.getName());
            writer.write(", Usia: " + pasien.getAge());
            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            writer.write(", Tanggal: " + timestamp);
            writer.newLine();
        }
    }
}

// Class AntrianApp (GUI)
public class AntrianApp extends JFrame {
    private Antrian antrian;
    private JTextArea displayArea;
    private JTextField nameField, ageField;

    public AntrianApp() {
        antrian = new Antrian();

        setTitle("Aplikasi Antrian Rumah Sakit");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Tambah Pasien"));

        inputPanel.add(new JLabel("Nama:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Usia:"));
        ageField = new JTextField();
        inputPanel.add(ageField);

        JButton tambahButton = new JButton("Tambah");
        inputPanel.add(tambahButton);

        JButton lihatButton = new JButton("Lihat Antrian");
        inputPanel.add(lihatButton);

        add(inputPanel, BorderLayout.NORTH);

        displayArea = new JTextArea();
        displayArea.setBorder(BorderFactory.createTitledBorder("Daftar Antrian"));
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        tambahButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tambahPasien();
            }
        });

        lihatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayQueue();
            }
        });
    }

    private void tambahPasien() {
        String name = nameField.getText().trim();
        int age;

        try {
            age = Integer.parseInt(ageField.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Usia harus berupa angka", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (name.isEmpty() || age < 0) {
            JOptionPane.showMessageDialog(this, "Nama tidak boleh kosong dan usia tidak boleh negatif.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Pasien pasien = new Pasien(name, age);
        antrian.enqueue(pasien);
        nameField.setText("");
        ageField.setText("");

        try {
            antrian.simpanDataAntrian(pasien);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat menyimpan data ke file", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayQueue() {
        Deque<Pasien> queue = antrian.getQueue();
        displayArea.setText("");

        if (queue.isEmpty()) {
            displayArea.append("Antrian kosong\n");
        } else {
            displayArea.append("Antrian Rumah Sakit:\n");
            for (Pasien pasien : queue) {
                displayArea.append(pasien.toString() + "\n");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AntrianApp().setVisible(true);
            }
        });
    }
}
