import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;

public class Latihan {

    public static void main(String[] args) {
      try {
        File f = new File("\"D:\\teks.txt\"");
        FileReader reader = new FileReader(f);
        BufferedReader buff = new BufferedReader(reader);
        String x = buff.readLine();
        while (x != null) {
        System.out.println(x);
        x = buff.readLine();
       }
       buff.close();
       reader.close();
      } catch (Exception e) {
        System.out.println("File tidak ada");
        }
    }
}