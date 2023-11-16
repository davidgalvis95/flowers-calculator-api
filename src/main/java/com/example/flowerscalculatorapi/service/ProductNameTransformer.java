package com.example.flowerscalculatorapi.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductNameTransformer {
    public static String transformFlowerName(String input) {

        if(input == null || input.isEmpty()) {
            return "";
        }

        final String[] words = input.split(" ");
        final List<String> codifiedWords = Arrays.stream(words)
                .map(ProductNameTransformer::calculateCodifiedWord)
                .toList();

        final StringBuilder finalCodifiedName = new StringBuilder();
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
        char firstChar = word.charAt(0);
        char lastChar = word.charAt(word.length() - 1);
        StringBuilder result = new StringBuilder();
        int distinctChars = getDistinctCharacterCountInBetween(word);
        result.append(firstChar);
        result.append(distinctChars);
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
        final Set<Character> distinctCharacters = new HashSet<>();
        for (int i = 1; i < input.length() - 1; i++) {
            char currentChar = input.charAt(i);
            distinctCharacters.add(currentChar);
        }
        return distinctCharacters.size();
    }
}
