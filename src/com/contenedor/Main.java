package com.contenedor;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Container> containers = new ArrayList<>();
        containers.add(new Container("Frijol", 15));
        containers.add(new Container("Maíz", 30));
        containers.add(new Container("Trigo", 25));

        Producer producer1 = new Producer("1", "Frijol", 3, "Maíz", 2, containers);
        Producer producer2 = new Producer("2", "Frijol", 4, "Trigo", 2, containers);
        Producer producer3 = new Producer("3", "Maíz", 3, "Trigo", 3, containers);
        Producer producer4 = new Producer("4", "Frijol", 2, "Maíz", 4, containers);

        List<Seed> seedsClient1 = new ArrayList<>();
        seedsClient1.add(new Seed("Frijol", 5));

        List<Seed> seedsClient2 = new ArrayList<>();
        seedsClient2.add(new Seed("Frijol", 5));
        seedsClient2.add(new Seed("Maíz", 20));

        List<Seed> seedsClient3 = new ArrayList<>();
        seedsClient3.add(new Seed("Frijol", 10));
        seedsClient3.add(new Seed("Maíz", 20));
        seedsClient3.add(new Seed("Trigo", 10));

        ThreadGroup clients = new ThreadGroup("clients");

        Client client1 = new Client("Valdo", seedsClient1, containers);
        Client client2 = new Client("Hiram", seedsClient2, containers);
        Client client3 = new Client("Juanpa", seedsClient3, containers);

        Thread c1 = new Thread(clients,client1,"Valdo");
        Thread c2 = new Thread(clients,client2, "Hiram");
        Thread c3 = new Thread(clients,client3, "Juanpa");

        System.out.println("Los productores comienzan el día");

        producer1.start();
        producer2.start();
        producer3.start();
        producer4.start();

        c1.start();
        c2.start();
        c3.start();

    }
}
