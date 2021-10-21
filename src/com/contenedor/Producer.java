package com.contenedor;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Producer extends Thread {

    List<Seed> seeds = new ArrayList<>();
    List<Container> containers;
    String name;

    public Producer(String name,String seed1,int amount1,String seed2,int amount2, List<Container> containers){
        this.name = name;
        seeds.add(new Seed(seed1,amount1));
        seeds.add(new Seed(seed2,amount2));
        this.containers = containers;
    }

    public void putSeed(int random) throws InterruptedException {
        Seed seed = seeds.get(random);
        System.out.println(name+ "Tratando de agregar...");
        for(Container container: containers){
            if(seed.name.equals(container.seed)){
                if(!(container.amount == container.quantity)){
                    if(container.busy) {
                        System.out.println(name+ "Esperando...");
                        wait();
                    }
                    container.setQuantity(seed.amount,name);
                    System.out.println(name+  " agregó "+seed.amount+"T de "+ seed.name+" en el contenedor");
                }
                break;
            }
        }


    }

    @Override
    public void run() {
        Random random = new Random();
        while(!Container.VerifyContainer(containers)){
            System.out.println("Entré al while...");
            try {
                int rand = random.nextInt(2);
                putSeed(rand);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
