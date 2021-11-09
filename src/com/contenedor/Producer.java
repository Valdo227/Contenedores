package com.contenedor;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Producer extends Thread {

    List<Seed> seeds = new ArrayList<>();
    final List<Container> containers;
    String name;

    public Producer(String name, String seed1, int amount1, String seed2, int amount2, List<Container> containers) {
        this.name = name;
        seeds.add(new Seed(seed1, amount1));
        seeds.add(new Seed(seed2, amount2));
        this.containers = containers;
    }

    public void putSeed() throws InterruptedException {
        System.out.println("Intentó " + name);
        for (Container container : containers)
            synchronized (containers) {
                for (Seed seed : seeds)
                    if (seed.name.equals(container.seed))
                        if (!(container.amount == container.quantity))
                            container.setQuantity(seed.amount, name);
            }
    }

    @Override
    public void run() {
        while (!Container.VerifyContainer(containers)) {
            try {
                putSeed();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(name + " ha terminado de surtir");
    }

}
