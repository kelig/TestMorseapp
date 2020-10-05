package com.example.morsehorde.models;

import java.util.HashMap;

public class Translator {

    static String[] ALPHANUMERIC = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
            "s", "t", "u", "v", "w", "x", "y", "z", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "!", ",", "?",
            ".", "'"," " };
    static String[] MORSE = { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..",
            "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", ".----",
            "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.", "-----", "-.-.--", "--..--",
            "..--..", ".-.-.-", ".----."," " };

    public static HashMap<String, String> ALPHANUMERIC_TO_MORSE = new HashMap<>();
    public static HashMap<String, String> MORSE_TO_ALPHANUMERIC = new HashMap<>();


    static {
        for (int i = 0; i < ALPHANUMERIC.length  &&  i < MORSE.length; i++) {
            ALPHANUMERIC_TO_MORSE.put(ALPHANUMERIC[i], MORSE[i]);
            MORSE_TO_ALPHANUMERIC.put(MORSE[i], ALPHANUMERIC[i]);
        }
    }

    public static String morseToAlpha(String morse) {
        StringBuilder builder = new StringBuilder();
        String[] wordsmorse = morse.trim().split("   ");

        for (String morseLetter : wordsmorse) {
            for (String letter : morseLetter.split(" ")) {
                String alpha = MORSE_TO_ALPHANUMERIC.get(letter);
                builder.append(alpha);
            }

        }

        return builder.toString().toLowerCase();
    }


    public static String alphaToMorse(String input) {
        StringBuilder builder = new StringBuilder();
        String[] wordsinput = input.trim().split(" ");

        for (String letter : wordsinput) {
            for (int i = 0; i < letter.length(); i++) {
                String morse = ALPHANUMERIC_TO_MORSE.get(letter.substring(i, i + 1).toLowerCase());
                builder.append(morse).append(" ");
            }
        }

        return builder.toString();
    }



}
