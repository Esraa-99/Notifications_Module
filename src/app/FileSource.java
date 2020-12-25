package app;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileSource extends Source {

    FileSource(String source) {
        super(source);
    }

    @Override
    public String readSource() {
        File file = new File(source);
        try {
            Scanner scanner = new Scanner(file);
            if (scanner.hasNext()) {
                data += scanner.nextLine();
                if (scanner.hasNext()) {
                    data += "\n" + scanner.nextLine();
                    String content = "";
                    while (scanner.hasNext()) {
                        content += scanner.nextLine();
                    }
                    data += "\n" + content;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            data = null;
        }
        return data;
    }

    @Override
    public boolean writeToSource(String data) {
        File file = new File(source);
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(data);
            writer.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteSource() {
        File file = new File(source);
        if (file.isAbsolute() && file.isFile()) return file.delete();
        else return false;
    }

}