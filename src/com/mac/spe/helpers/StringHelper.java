package com.mac.spe.helpers;

import java.util.ArrayList;
import java.util.List;

public class StringHelper {

    public static List<String> stringToMultiline(String input, int widthInChars, boolean spaceOnNewLine){

        if(input == null || input.trim().length() == 0) throw new IllegalArgumentException("Cannot parse a null or empty string.");
        if(widthInChars <= 0) throw new IllegalArgumentException("Width in characters must be greater than 0.");

        List<String> result = new ArrayList<>();
        StringBuilder currentLine = new StringBuilder();
        String[] words = input.trim().split(" ");

        int currentLength;
        for(int i = 0; i < words.length; i++){
            currentLine.append(words[i] + " ");
            currentLength = currentLine.length();

            int nextWordLength = 0;
            if(i + i < words.length) nextWordLength = words[i + 1].length();
            if(currentLength + nextWordLength >= widthInChars + 2 || i + 1 >= words.length){
                result.add(currentLine.substring(0, currentLength - 1));
                currentLine = new StringBuilder();
                if(spaceOnNewLine) currentLine.append(" ");
            }
        }

        return result;
    }
}
