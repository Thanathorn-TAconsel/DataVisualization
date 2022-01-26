import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    Main() throws FileNotFoundException {
        FileInputStream fin = new FileInputStream("Superstore.csv");
        Scanner scan = new Scanner(fin);
        while (scan.hasNextLine()) {
            System.out.println(scan.nextLine());
        }
    }
    public static void main(String[] args) throws FileNotFoundException {
        new Main();
    }
}
