import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Deque;
import java.util.LinkedList;

public class AntrianQurban {
    private Deque<Penerima> queue;
    private int nomorAntrian;

    public AntrianQurban() {
        queue = new LinkedList<>();
        nomorAntrian = 0;
    }

    public void tambahPenerima(Penerima penerima) {
        queue.addLast(penerima);
        nomorAntrian++;
    }

    public Penerima panggilPenerima() {
        return queue.pollFirst();
    }

    public Deque<Penerima> getAntrian() {
        return queue;
    }

    public void simpanDataAntrian(Penerima penerima) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data_antrian.txt", true))) {
            writer.write("Nomor Antrian: " + nomorAntrian);
            writer.write(", Nama: " + penerima.getNama());
            writer.write(", No. Telp: " + penerima.getNoTelp());
            writer.write(", Alamat: " + penerima.getAlamat());
            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            writer.write(", Tanggal: " + timestamp);
            writer.newLine();
        }
    }

    public void hapusSemuaAntrian() {
        queue.clear();
        nomorAntrian = 0;
    }
}
