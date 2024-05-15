public class Latihan {

    public static void main(String[] args) {
        int num1 = 10;
        int num2 = 0;
        int result;

        try {
            result = num1 / num2;
            System.out.println("Hasil: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Terjadi kesalahan: Pembagian dengan nol tidak diperbolehkan.");
        }

        System.out.println("Program selesai.");
    }
}
