package org.hbrs.se1.ws23.uebung1.test;

import org.hbrs.se1.ws23.uebung1.control.*;
import org.hbrs.se1.ws23.uebung1.view.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GermanTranslatorTest {
    private Translator translator = null;

    @BeforeEach
    void setUp() {

        translator = new GermanTranslator();

    }

    @Test
    void aPositiveTest() {
        GermanTranslator translator = new GermanTranslator();
        String value = translator.translateNumber(1);
        assertEquals(value, "eins");
    }
    @Test
    void aNegativeTest() {

        String value = translator.translateNumber(11);
        assertEquals(value, "Übersetzung der Zahl 11 nicht möglich (" + Translator.version + ")");

        String value2 = translator.translateNumber(-11);
        assertEquals(value2, "Übersetzung der Zahl -11 nicht möglich (" + Translator.version + ")");

        String value3 = translator.translateNumber(0);
        assertEquals(value3, "Übersetzung der Zahl 0 nicht möglich (" + Translator.version + ")");

    }
    @Test
    void clientTest() {
        Client client = new Client();
        client.display(6);
    }


}