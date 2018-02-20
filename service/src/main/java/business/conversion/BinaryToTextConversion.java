package business.conversion;

import java.io.*;

public class BinaryToTextConversion {

    public static void binaryToText(String pathToBinaryFile, String pathToTextFile) throws IOException {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(pathToTextFile, "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        DataInputStream input = null;
        try {
            input = new DataInputStream(new FileInputStream(pathToBinaryFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert input != null;
        while (input.available() > 0) {
            try {
                double nr = input.readDouble();
                assert writer != null;
                writer.println(nr);
            } catch (EOFException e) {
                break;
            }
        }
        assert writer != null;
        writer.close();
    }

    public static String changeFileExtension(String file) {
        if (file.contains(".")) {
            int index = file.indexOf(".");
            String filename = file.substring(0, index);
            return filename + ".txt";
        } else {
            return file;
        }
    }
}
