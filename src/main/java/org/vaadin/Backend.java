package org.vaadin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Backend {

    public List<String> getStrings() {
        List<String> res = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < random.nextInt(20); i++) {
            try {
                Thread.sleep(250);
            } catch (final InterruptedException e) {
                e.printStackTrace();
            }
            res.add(random.nextGaussian() + "");
        }
        return res;
    }
}
