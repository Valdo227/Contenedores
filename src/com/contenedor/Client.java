package com.contenedor;

import java.io.Console;
import java.util.List;

public class Client extends Thread {
    String name;
    List<Seed> seeds;
    final List<Container> containers;

    public Client(String name, List<Seed> seeds, List<Container> containers) {
        this.name = name;
        this.seeds = seeds;
        this.containers = containers;
    }

    public boolean checkList() {
        return seeds.stream().anyMatch(seed -> seed.amount > 0);
    }

    public void buy() throws InterruptedException {
        System.out.println("Intentó comprar " + name);
        for (Container container : containers)
            synchronized (containers) {
                for (Seed seed : seeds)
                    if (seed.name.equals(container.seed)) {
                        if (seed.amount == 0) {
                            this.getThreadGroup().wait();
                        }
                        container.getQuantity(seed, name);
                    }
            }
    }

    @Override
    public void run() {
        while (checkList()) {
            try {
                System.out.println(this.getThreadGroup().getName());
                buy();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Cliente " + name + " ha terminado de comprar");
    }
}
