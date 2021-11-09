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
        return seeds.stream().anyMatch(seed -> seed.amount == 0);
    }

    public void buy() {
        System.out.println("Intentó comprar" + name);
        for (Container container : containers)
            synchronized (containers) {
                for (Seed seed : seeds)
                    if (seed.name.equals(container.seed))
                        if (seed.amount != 0 )
                            container.getQuantity(seed.amount, name);
            }
    }

    @Override
    public void run() {
        while (checkList()) {
            try {

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(name + " ha terminado de comprar");
    }
}
