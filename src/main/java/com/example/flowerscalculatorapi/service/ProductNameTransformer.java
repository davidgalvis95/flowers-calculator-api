package com.example.flowerscalculatorapi.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductNameTransformer {
    public static String transformFlowerName(final String input) {

        //If no string then no calculation is needed, returning empty
        if(input == null || input.isEmpty()) {
            return "";
        }

        //Get each word from the name and perform separate calculations
        final String[] words = input.split(" ");
        final List<String> codifiedWords = Arrays.stream(words)
                .map(ProductNameTransformer::calculateCodifiedWord)
                .toList();

        final StringBuilder finalCodifiedName = new StringBuilder();

        //Each word in the list except the last one will be joined by "-" which is like replacing the space
        for (int i = 0; i < codifiedWords.size(); i++) {
            String word = codifiedWords.get(i);
            if (i == codifiedWords.size() - 1) {
                finalCodifiedName.append(word);
            } else {
                finalCodifiedName.append(word).append("-");
            }
        }

        return finalCodifiedName.toString();
    }

    private static String calculateCodifiedWord(final String word) {
        //Gets the first character, the count, the special ones and the last one
        char firstChar = word.charAt(0);
        char lastChar = word.charAt(word.length() - 1);
        StringBuilder result = new StringBuilder();
        int distinctChars = getDistinctCharacterCountInBetween(word);
        result.append(firstChar);
        result.append(distinctChars);
        //The special characters are only counted or added if they are not the first character
        for (int i = 1; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            if (!Character.isLetterOrDigit(currentChar)) {
                result.append(currentChar);
            }
        }
        result.append(lastChar);
        return result.toString();
    }

    protected static int getDistinctCharacterCountInBetween(final String input) {
        //Get only the unique characters in between the first one and the last one, e.g. in Baa%i we get 2
        final Set<Character> distinctCharacters = new HashSet<>();
        for (int i = 1; i < input.length() - 1; i++) {
            char currentChar = input.charAt(i);
            distinctCharacters.add(currentChar);
        }
        return distinctCharacters.size();
    }
}
