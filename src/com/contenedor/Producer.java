package com.contenedor;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Producer extends Thread {

    List<Seed> seeds = new ArrayList<>();
    final List<Container> containers;
    String name;
    JPanel panel;

    public Producer(String name, String seed1, int amount1, String seed2, int amount2, List<Container> containers, JPanel panel) {
        this.name = name;
        seeds.add(new Seed(seed1, amount1));
        seeds.add(new Seed(seed2, amount2));
        this.containers = containers;
        this.panel = panel;
    }

    public void putSeed() throws InterruptedException {
        panel.setBackground(Color.yellow);
        Thread.sleep(2000);
        for (Container container : containers)
            synchronized (containers) {
                if(Container.busy) {
                    panel.setBackground(Color.orange);
                    containers.wait();
                }
                Container.busy = true;
                for (Seed seed : seeds) {
                    if (seed.name.equals(container.seed))
                        if (!(container.amount == container.quantity)) {
                            container.setQuantity(seed.amount, name);
                            panel.setBackground(Color.green);
                            Thread.sleep(500);
                        }
                        panel.setBackground(Color.blue);
                }
                Container.busy = false;
                containers.notify();
            }
    }

    @Override
    public void run() {
        while(true) {
            if(Container.verifyContainerEmpty(containers)) {
                while (!Container.VerifyContainer(containers)) {
                    Container.open = false;
                    try {
                        putSeed();
                        Thread.sleep(500);
                        panel.setBackground(Color.blue);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Container.open = true;
            }
        }

    }

}
