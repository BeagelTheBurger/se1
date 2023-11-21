package org.hbrs.se1.ws23.uebung4.prototype;

import java.util.ArrayList;
import java.util.List;

public class UserStoryView {

    public <userStory> void dump(ArrayList liste) {

        for (Object userStory : liste) {
            System.out.println(userStory.toString());
        }

    }
}
