import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicLong;

public class Main {

    public static void main(String[] args) {
        
        final boolean recursive = (args.length != 2) ? false : true;
        
        if (args.length < 1 || args.length > 2){
            System.out.println("Error: Invalid number of parameters.");
            System.exit(0);
        }

        String directoryName = args[0];
        Path path = Paths.get(directoryName);
        AtomicLong size = new AtomicLong(0);

        try{
            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {

                @Override public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

                    /*
                    String[] path = (file+"").split("/");
                    String[] rootDir = directoryName.split("/");
                    String dirName = path[path.length-2];       // ex: "/home/" -> ["","home",""] (home no index 1)
                    String fileName = path[path.length-1];      // o que vem a seguir ao dirName em path[]

                    if (!dirName.equals(rootDir[rootDir.length-1]))
                        if (recursive) {
                            fileName = dirName + "/" + fileName;
                            System.out.println("DIR - " + fileName + " | Total size = " + attrs.size());
                        }
                        else
                            return FileVisitResult.CONTINUE;

                    */

                    if (recursive) {
                        if (Files.isDirectory(file)) {
                            System.out.println("DIR -> " + file + " - " + attrs.size() + " bytes");
                            size.addAndGet(attrs.size());
                        } else {
                            System.out.println("FILE -> " + file + " - " + attrs.size());
                            size.addAndGet(attrs.size());
                        }
                    } else {
                        if (Files.isRegularFile(file)) {
                            System.out.println("FILE -> " + file + " - " + attrs.size());
                            size.addAndGet(attrs.size());
                        }
                    }

                    return FileVisitResult.CONTINUE;
                }
            });

        }catch(Exception e){
            System.out.println("Error: can't walk though the tree..");
            System.exit(0);
        }
    }

}