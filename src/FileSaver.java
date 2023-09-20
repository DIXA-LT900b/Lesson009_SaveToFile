import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;


public class FileSaver {

    private static String fileNaming(){
        return "save" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".dat";
    }
    public static void saveToFile(GameProgress progress) {

        File file = new File(Main.SAVEGAME_PATH + fileNaming());

        if (!file.exists()) {
            try {
                file.createNewFile();
                ObjectOutputStream saveToFileStream = new ObjectOutputStream(new FileOutputStream(file));
                saveToFileStream.writeObject(progress);
                saveToFileStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void zipFiles(){

        List<String> allSavedFiles = new ArrayList<>();

        try {
            Files.list(Paths.get(String.valueOf(Main.SAVEGAME_PATH))).forEach(path -> Coll);
            System.out.println(allSavedFiles);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        //File zipFile = new File(Main.ZIP_PATH);
        try {
            ZipFile zipFile = new ZipFile(Main.ZIP_PATH);
            ZipOutputStream zipFileStream = new ZipOutputStream(new FileOutputStream(String.valueOf(zipFile)));

            for (String fileToZip : allSavedFiles){
                System.out.println(fileToZip);
                FileInputStream inputStream = new FileInputStream(fileToZip);
                ZipEntry zipEntry = new ZipEntry(fileToZip);
                zipFileStream.putNextEntry(zipEntry);

                byte[] buffer = new byte[inputStream.available()];
                inputStream.read(buffer);

                zipFileStream.write(buffer);

                zipFileStream.closeEntry();
            }

            zipFileStream.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());;
        }
    }
}