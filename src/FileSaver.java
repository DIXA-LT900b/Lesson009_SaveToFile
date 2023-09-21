import java.io.*;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileSaver {

    private static String fileNaming(){
        return "save" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".dat";
    }
    public static void saveToFile(GameProgress progress) {

        File file = new File(Main.SAVEGAME_PATH + File.separator + fileNaming());

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

    public static List<String> getFilesInDirectory(String directory){
         File fileName = new File(directory);
         File[] fileList = fileName.listFiles();
         List<String> allSavedFiles = Arrays
                .stream(fileList)
                .map(File::getAbsolutePath)
                .toList();
         return allSavedFiles;
    }
    public static void zipFiles(){

     List<String> filesToZip = getFilesInDirectory(String.valueOf(Main.SAVEGAME_PATH));

        try {
            ZipOutputStream zipFileStream = new ZipOutputStream(new FileOutputStream(String.valueOf(Main.ZIP_PATH)));

            for (String fileToZip : filesToZip){

                ZipEntry zipEntry = new ZipEntry(String.valueOf(Path.of(fileToZip).getFileName()));
                FileInputStream inputStream = new FileInputStream(fileToZip);
                zipFileStream.putNextEntry(zipEntry);

                byte[] buffer = new byte[inputStream.available()];
                inputStream.read(buffer);

                zipFileStream.write(buffer);
                zipFileStream.closeEntry();
                inputStream.close();
            }
            zipFileStream.finish();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}