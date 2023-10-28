package org.hbrs.se1.ws23.uebung1.control;



public class Factory {

    /**
     * @return Gibt eine Instanz des GermanTranslator zurück
     */
    public static Translator createGermanTranslator() {
        return new GermanTranslator();
    }

    public static Translator createEnglishTranslator() {
        return null;
    }



}
