package common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by rage on 24.05.15.
 */
public class ReadKeys {


    public static PrivateKey readPrivateKey(File selectedFile) {
        return null;
    }

    public static PubKey readPubKey(File selectedFile) {
        String keyInString = readFile(selectedFile);
        String[] split = keyInString.split("\n");
        List<String> listBasicNum = listBasicNum(split[0]);
        String controlSum = split[1];
        return new PubKey(listBasicNum, controlSum);
    }

    private static List<String> listBasicNum(String tipaList) {

        tipaList = tipaList.replace("[", "").replace("]", "").replace(" ", "");
        List<String> result = new ArrayList<String>();

        String[] split = tipaList.split(",");
        Collections.addAll(result, split);
        return result;
    }


    private static String readFile(File selectedFile) {
        try {
            BufferedReader in = null;
            StringBuilder stringBuilder = new StringBuilder();
            try {
                in = new BufferedReader(new FileReader(selectedFile));
                String line = in.readLine();
                while (line != null) {
                    stringBuilder.append(line).append("\n");
                    line = in.readLine();
                }
                in.readLine();
                return stringBuilder.toString();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (in != null) {
                    in.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;


    }
}
