package lesson_2_tic_tac_toe;

import java.io.FileWriter;
import java.io.IOException;

public class CustomIO {
    /**
     * append true - добавляет в конец файла
     * @param text то, что хотим записать в файл
     */

    public static void writeRates(String text) {
        try (FileWriter writer = new FileWriter("src/lesson_2_tic_tac_toe/rating.txt", true)) {
            writer.write(text);
            writer.append("\n");
            writer.flush();
        } catch (IOException ex) {
            ex.getMessage();
        }
    }
}
