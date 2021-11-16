package com.contenedor;

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
        for (Container container : containers) {
            synchronized (containers) {
                if(Container.busy)
                    containers.wait();
                Container.busy = true;
                for (Seed seed : seeds) {
                    if (seed.name.equals(container.seed)) {
                        if (seed.amount != 0)
                            container.getQuantity(seed, name);
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
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("\033[33mCliente " + name + " ha terminado de comprar\u001B[0m");
    }
}
