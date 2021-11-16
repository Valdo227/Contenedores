package com.contenedor;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Client extends Thread {
    String name;
    List<Seed> seeds;
    final List<Container> containers;

    JPanel panel;

    public Client(String name, List<Seed> seeds, List<Container> containers, JPanel panel) {
        this.name = name;
        this.seeds = seeds;
        this.containers = containers;
        this.panel = panel;
    }

    public boolean checkList() {
        return seeds.stream().anyMatch(seed -> seed.amount > 0);
    }

    public void buy() throws InterruptedException {

        System.out.println("Intentó comprar " + name);
        panel.setBackground(Color.yellow);
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
                            Thread.sleep(500);
                        }
                        else
                            panel.setBackground(Color.blue);

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
            if(Container.open) {
                try {
                    buy();
                    if(!checkList()){
                        System.out.println("\033[33mCliente " + name + " ha terminado de comprar\u001B[0m");
                    }
                    Thread.sleep(500);
                    panel.setBackground(Color.blue);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
