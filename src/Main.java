import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {

    public static final Path INSTALL_PATH = Path.of("C:" + File.separator + "GameZz");
    public static final Path SAVEGAME_PATH = INSTALL_PATH.resolve("savegames");
    public static final Path ZIP_PATH = SAVEGAME_PATH .resolve("zip.zip");
    public static void main(String[] args) {

        try {
            Files.createDirectories(SAVEGAME_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<GameProgress> progresses = new ArrayList<>();
        progresses.add(new GameProgress(10,10,10,10));
        progresses.add(new GameProgress(20,20,20,20.5f));
        progresses.add(new GameProgress(30,30,30, 0.05846));

        System.out.println("Подождите, выполняется сохранение...");
        for (GameProgress progress : progresses){
            FileSaver.saveToFile(progress);
            try {
                TimeUnit.MILLISECONDS.sleep(1100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Сохранено...");

        FileSaver.zipFiles();
    }

}