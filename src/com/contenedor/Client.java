package com.contenedor;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Client extends Thread {
    String name;
    List<Seed> seeds;
    final List<Container> containers;

    JPanel panel;
    JLabel label;

    public Client(String name, List<Seed> seeds, List<Container> containers, JPanel panel, JLabel label) {
        this.name = name;
        this.seeds = seeds;
        this.containers = containers;
        this.panel = panel;
        this.label = label;
    }

    public boolean checkList() {
        return seeds.stream().anyMatch(seed -> seed.amount > 0);
    }

    public void buy() throws InterruptedException {

        System.out.println("Intentó comprar " + name);
        panel.setBackground(Color.yellow);
        label.setForeground(Color.black);
        Thread.sleep(2000);
        for (Container container : containers) {
            synchronized (containers) {
                if(Container.busy)
                    containers.wait();
                Container.busy = true;
                for (Seed seed : seeds) {
                    if (seed.name.equals(container.seed)) {
                        if (seed.amount != 0){
                            container.getQuantity(seed, name);
                            panel.setBackground(Color.green);
                            label.setForeground(Color.black);
                            Thread.sleep(500);
                        }
                        panel.setBackground(Color.blue);
                        label.setForeground(Color.white);

                    }
                }
                Container.busy = false;
                containers.notify();
            }
        }
    }

    @Override
    public void run() {
        while (checkList()) {
            label.setText("Esperando");
            if(Container.open) {
                label.setText("Comprando");
                try {
                    buy();
                    Thread.sleep(500);
                    panel.setBackground(Color.blue);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        label.setText("Acabó");

    }
}
