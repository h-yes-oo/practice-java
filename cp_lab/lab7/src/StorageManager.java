import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class StorageManager {
    /*
     * Read string lines of files in a specified directory.
     * The files are sorted by their names.
     */

    //해당 디렉토리의 파일들을 순서대로 읽어들여서 nested list로 저장
    //디렉토리[파일1[라인1, 라인2, ... ], 파일2[라인1, 라인2, ...], 파일3[라인1, 라인2, ...]]
    public static List<List<String>> directoryChildrenLines(String directoryName) {
        List<List<String>> childrenLines = new LinkedList<>();
        for (File childFile : nameSortedDirectoryFiles(directoryName)) {
            List<String> lines = readLines(childFile);
            childrenLines.add(lines);
        }
        return childrenLines;
    }

    /* Save string lines into as a file */
    public static void writeLines(String fileName, List<String> strings) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            for (String string : strings) {
                fileWriter.write(string + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* Delete a file */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("file not exists");
        }

        boolean res = file.delete();
        if (!res) {
            System.out.println("failed to delete");
        }

        return res;
    }

    /* Read a file */
    private static List<String> readLines(File file) {
        List<String> strings = new LinkedList<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                strings.add(line);
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strings;
    }

    //이름 순으로 디렉토리의 파일들을 정렬한 리스트 리턴
    private static File[] nameSortedDirectoryFiles(String directoryName) {
        File[] directoryFiles = directoryFiles(directoryName);
        Arrays.sort(directoryFiles, Comparator.comparing(File::getName));
        return directoryFiles;
    }

    //디렉토리 경로에 있는 파일들을 배열로 리턴
    private static File[] directoryFiles(String directoryName) {
        File directory = new File(directoryName);
        return directory.listFiles();
    }
}
