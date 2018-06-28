import java.io.FilePermission;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;

/**
 * Created by USER on 28.06.2018.
 */

public class Main {
  public static void main(String[] args) throws IOException {
    Path testFilePath = Paths.get(".\\uploads\\1.txt");
    System.out.println("The file name is: " + testFilePath.getFileName());
    System.out.println("It's URI is: " + testFilePath.toUri());
    System.out.println("It's absolute path is: "
      + testFilePath.toAbsolutePath());
    System.out.println("It's normalized path is: "
      + testFilePath.normalize());

    //Получение другого объекта строки по нормализованному относительному пути
    Path testPathNormalized = Paths
      .get(testFilePath.normalize().toString());
    System.out.println("It's normalized absolute path is: "
      + testPathNormalized.toAbsolutePath());
    System.out.println("It's normalized real path is: "
      + testFilePath.toRealPath(LinkOption.NOFOLLOW_LINKS));

    ArrayList dirlist = new ArrayList();
    Path dir =  Paths.get("uploads");
    try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
      for (Path file: stream) {
        dirlist.add(file.getFileName().toString());
        System.out.println(file.getFileName());
      }
    } catch (IOException | DirectoryIteratorException x) {
      // IOException can never be thrown by the iteration.
      // In this snippet, it can only be thrown by newDirectoryStream.
      System.err.println(x);
    }

    UploadFileResponse uploadFileResponse = new UploadFileResponse(testFilePath.getFileName().toString(), testFilePath.normalize().toString());
    System.out.println(uploadFileResponse);
  }

}
