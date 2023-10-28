package org.hbrs.se1.ws23.uebung2.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.hbrs.se1.ws23.uebung2.*;
class ContainerTest {

        private Container container = new Container();

        @BeforeEach
        void setUp() throws ContainerException {
                Member m1 = new MemberObj(1);
                Member m2 =new MemberObj(2);
        }

        @org.junit.jupiter.api.Test
        void testAddMember()  {

                assertEquals(2, container.size());
        }



        @org.junit.jupiter.api.Test
        void testDeleteMember() {
                assertEquals("Member mit der ID 1 wurde gelöscht.", container.deleteMember(1));

            }


        @org.junit.jupiter.api.Test
        void testDump() {
                container.dump();

        }

        @org.junit.jupiter.api.Test
        void size() {
                assertEquals(2, container.size());
        }
}