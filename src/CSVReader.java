import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CSVReader {

    //Метод чтения файла по ссылке
    public String readCSV(String url) {
        try {
            if (Files.exists(Path.of(url))) {
                return Files.readString(Path.of(url));
            } else return null;

        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. " +
                    "Возможно, файл не находится в нужной директории.");
            return null;
        }
    }


}
