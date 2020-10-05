package com.example.morsehorde;

import com.example.morsehorde.models.Translator;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    //testing translation functionality word
    @Test
    public void TestTranslatorWord() {

        Translator translator = new Translator();
        String t = "test";


        String translateString = translator.alphaToMorse(t);
        String morseTestString = translator.morseToAlpha(translateString);


        assertEquals(t, morseTestString);

    }

    //testing translation functionality morse
    @Test
    public void TestTranslatorMorse() {

        Translator translator = new Translator();
        String t = ".- ";


        String translateString = translator.morseToAlpha(t);
        String morseTestString = translator.alphaToMorse(translateString);

        assertEquals(t, morseTestString);

    }






}