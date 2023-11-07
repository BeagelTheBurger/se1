package org.hbrs.se1.ws23.uebung3;



import org.hbrs.se1.ws23.uebung3.persistence.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContainerTest {

   private Container container = null;
    MemberKonkret member1 = null;

    @BeforeEach
    void setUp() throws ContainerException {

        container = Container.getInstance();
        MemberKonkret r1 = new MemberKonkret(12);
        MemberKonkret r2 = new MemberKonkret(32);
        container.addMember(r1);
        container.addMember(r2);
            }


    @org.junit.jupiter.api.Test
    void addAndDeleteMember() {
        // Test-Objekte anlegen
        MemberKonkret r1 = new MemberKonkret(12);
        MemberKonkret r2 = new MemberKonkret(32);

        // Testfall 1 - Check auf leeren Container
        assertEquals(0, container.size(),
                "Testfall 1 - Pruefung auf leeren Container");

        // Vorbereitung für Testfall 2
        try {
            container.addMember(r1);
        } catch (ContainerException e) {
            e.printStackTrace();
        }

        // Testfall 2 - Check, ob ein Objekt hinzugefuegt wurde (Size = Zustand = 1)
        assertEquals(1, container.size(),
                "Testfall 2 - Pruefung auf Zustand 1");

        // Vorbereitung für Testfall 3
        try {
            container.addMember(r2);
        } catch (ContainerException e) {
            e.printStackTrace();
        }

        // Testfall 3 - Check, ob ein Objekt hinzugefuegt wurde (Size = Zustand = 1)
        assertEquals(2, container.size(),
                "Testfall 3 - Pruefung auf Zustand 2");

        // Testfälle 4 - 8 - Kein Zustandwechsel erlaubt, Zustand bleibt 2!
        assertThrows(ContainerException.class, () -> {
            container.addMember(r2); // Schon enthalten!
        });
        assertEquals(2, container.size(), "Testfall 4 - Pruefung auf Zustand 2");

        assertThrows(ContainerException.class, () -> {
            container.addMember(null); // Null kann nicht angenommen werden!
        });
        assertEquals(2, container.size(), "Testfall 5 - Pruefung auf Zustand 2");

        // Testfall 6 - Check, ob ein Objekt geloescht wurde (Size = Zustand = 1)
        assertEquals("Member mit der ID 12 wurde gelöscht.", container.deleteMember(12),
                "Testfall 6 - Pruefung auf Zustand 1");

        // Testfall 7 - Check, ob ein Objekt geloescht wurde (Size = Zustand = 0

    }
    @Test
    void persistentStore() throws PersistenceException{

        container.store();

    }
    @Test
    void persistentLoad() throws PersistenceException{

        container.load();

    }
}

