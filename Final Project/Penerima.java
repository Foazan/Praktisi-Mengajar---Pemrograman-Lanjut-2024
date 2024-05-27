public class Penerima<T> {
    private T nama;
    private T noTelp;
    private T alamat;

    public Penerima(T nama, T noTelp, T alamat) {
        this.nama = nama;
        this.noTelp = noTelp;
        this.alamat = alamat;
    }

    public T getNama() {
        return nama;
    }

    public T getNoTelp() {
        return noTelp;
    }

    public T getAlamat() {
        return alamat;
    }

    @Override
    public String toString() {
        return "Nama: " + nama + ", No. Telp: " + noTelp + ", Alamat: " + alamat;
    }
}
