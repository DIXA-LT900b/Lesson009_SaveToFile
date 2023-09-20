import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class Main {

    public static final String INSTALL_PATH = "C:" + File.separator + "GameZz" + File.separator;
    public static final Path SAVEGAME_PATH = Path.of(INSTALL_PATH + File.separator + "savegames");
    public static final String ZIP_PATH = SAVEGAME_PATH + File.separator + "zip.zip";
    public static void main(String[] args) {

        List<GameProgress> progresses = new ArrayList<>();
        progresses.add(new GameProgress(10,10,10,10));
        progresses.add(new GameProgress(20,20,20,20.5f));
        progresses.add(new GameProgress(30,30,30, 0.05846));


        for (GameProgress progress : progresses){
            FileSaver.saveToFile(progress);
            try {
                TimeUnit.MILLISECONDS.sleep(1100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


        FileSaver.zipFiles();
    }

}