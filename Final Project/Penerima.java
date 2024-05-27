public class Penerima {
    private String nama;
    private String noTelp;
    private String alamat;

    public Penerima(String nama, String noTelp, String alamat) {
        this.nama = nama;
        this.noTelp = noTelp;
        this.alamat = alamat;
    }

    public String getNama() {
        return nama;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public String getAlamat() {
        return alamat;
    }

    @Override
    public String toString() {
        return "Nama: " + nama + ", No. Telp: " + noTelp + ", Alamat: " + alamat;
    }
}
