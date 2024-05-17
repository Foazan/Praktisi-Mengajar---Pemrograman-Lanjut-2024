import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Deque;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

// Class Pasien
public class Pasien {
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
    }

    public void inputPasien() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Masukkan nama pasien:");
            String name = scanner.nextLine();
            System.out.println("Masukkan usia pasien:");
            int age = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (name.trim().isEmpty() || age < 0) {
                throw new IllegalArgumentException("Nama tidak boleh kosong dan usia tidak boleh negatif.");
            }

            Pasien newPasien = new Pasien(name, age);
            enqueue(newPasien);
            nomorAntrian++;
            System.out.println("Pasien ditambahkan dalam antrian");

            simpanDataAntrian(newPasien);
        } catch (InputMismatchException e) {
            System.out.println("Input tidak valid. Usia harus berupa angka.");
            scanner.nextLine(); // Clear the invalid input
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menyimpan data ke file: " + e.getMessage());
        }
    }

    public void displayQueue() {
        if (queue.isEmpty()) {
            System.out.println("Antrian kosong");
        } else {
            System.out.println("Antrian Rumah Sakit:");
            for (Pasien pasien : queue) {
                System.out.println(pasien);
            }
        }
    }

    public void simpanDataAntrian(Pasien pasien) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data_antrian.txt", true))) {
            writer.write("Nomor Antrian: " + nomorAntrian);
            writer.write(", Nama: " + pasien.getName());
            writer.write(", Usia: " + pasien.getAge());
            writer.newLine();
        }
    }
}

// Main class
class Main {
    public static void main(String[] args) {
        Antrian antrian = new Antrian();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Tambah Pasien");
            System.out.println("2. Lihat Antrian");
            System.out.println("3. Keluar");

            try {
                System.out.print("Pilih menu: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        antrian.inputPasien();
                        break;
                    case 2:
                        antrian.displayQueue();
                        break;
                    case 3:
                        System.out.println("Keluar...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Pilihan tidak valid.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input tidak valid. Pilihan harus berupa angka.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }
}
