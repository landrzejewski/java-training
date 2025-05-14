package pl.training.module07;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Application {

    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());

    public static void main(String[] args) {
        // zapis do pliku

        /*try {
            var fileWriter = new FileWriter("output.txt");
            fileWriter.write("Ala ma kota");
            fileWriter.close();
        } catch (IOException ioException) {
            LOGGER.log(Level.SEVERE, "Failed to open file", ioException);
        }*/

        // zapis do pliku + auto-zamykanie

        try (var fileWriter = new FileWriter("output.txt")) { // auto-closable
            fileWriter.write("Ala ma kota");
        } catch (IOException ioException) {
            LOGGER.log(Level.SEVERE, "Failed to open file", ioException);
        }

        // czytanie pliku z buforowaniem

        try (var fileReader = new FileReader("output.txt");
             var bufferedReader = new BufferedReader(fileReader)) { // auto-closable
            /*var text = bufferedReader.readLine();
            System.out.println(text);*/
            bufferedReader.lines()
                    .forEach(System.out::println);
        } catch (IOException ioException) {
            LOGGER.log(Level.SEVERE, "Failed to read file", ioException);
        }

        // odczyt wszystkich lini jako strumień

        /*try {
            Files.readAllLines(Path.of("output.txt"))
                    .forEach(System.out::println);
        } catch (IOException ioException) {
            LOGGER.log(Level.SEVERE, "Failed to read file", ioException);
        }*/

        // odczyt z konsoli

        /*try (var consoleReader = new InputStreamReader(System.in);
             var bufferedReader = new BufferedReader(consoleReader)) {
            System.out.println(bufferedReader.readLine());
        } catch (IOException ioException) {
            LOGGER.log(Level.SEVERE, "Failed to read console", ioException);
        }*/

        /*try (var scanner = new Scanner(System.in)) {
            if (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        }*/

        // Serializacja

        try (var fileWriter = new FileOutputStream("output.ser");
             var oos = new ObjectOutputStream(fileWriter)) {
            oos.writeObject(new User("Jan", "Kowalski"));
        } catch (IOException ioException) {
            LOGGER.log(Level.SEVERE, "Failed to write file", ioException);
        }

        try (var fileReader = new FileInputStream("output.ser");
             var ois = new ObjectInputStream(fileReader)) {
            var object = ois.readObject();
            if (object instanceof User user) {
                System.out.println(user);
            }
        } catch (IOException | ClassNotFoundException exception) {
            LOGGER.log(Level.SEVERE, "Failed to read file", exception);
        }
    }

    // https://github.com/landrzejewski/java-performance

}

record User(String firstName, String lastName) implements Serializable {
}
