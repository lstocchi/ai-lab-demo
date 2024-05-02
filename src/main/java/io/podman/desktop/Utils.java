package io.podman.desktop;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static List<String> splitString(String inputString, int blockLength) {
        List<String> blocks = new ArrayList<>();
        if (inputString == null || inputString.length() == 0 || blockLength <= 0) {
            return blocks;
        }
    
        int tmpBlockLength = blockLength;
        for (int i = 0; i < inputString.length(); i += tmpBlockLength) {
            String subString = "";
            if (i + blockLength > inputString.length()) {
                blocks.add(inputString.substring(i, inputString.length()-1));
                break;
            } else {
                subString = inputString.substring(i, i + blockLength);
            }
            int lastNewLine = subString.lastIndexOf("\n");
            if (lastNewLine != -1) {
                tmpBlockLength = lastNewLine;
            } else {
                tmpBlockLength = blockLength;
            }
            System.out.println(i);
            System.out.println(tmpBlockLength);
            System.out.println(inputString.length());
            
            blocks.add(inputString.substring(i, i+tmpBlockLength));
        }
    
        return blocks;
    }
    
}
