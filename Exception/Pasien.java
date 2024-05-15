import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;



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

public class Antrian {
    Deque<Pasien> queue;

    public Antrian() {
        queue = new LinkedList<>();
    }

    public void enqueue(Pasien pasien) {
        queue.addLast(pasien);
    }

    public void inputPasien() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Masukkan nama pasien:");
        String name = scanner.nextLine();
        System.out.println("Masukkan usia pasien:");
        int age = scanner.nextInt();
        scanner.nextLine();
        Pasien newPasien = new Pasien(name, age);
        enqueue(newPasien);
        System.out.println("Pasien ditambahkan dalam antrian");
    }

    public void displayQueue() {
        if (queue.isEmpty()) {
            System.out.println("Antrian kosong");
            return;
        }
        System.out.println("Antrian Rumah Sakit:");
        for (Pasien pasien : queue) {
            System.out.println(pasien);
        }
    }
}


public class Main {
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
                scanner.nextLine(); 
            }
        }
    }
}