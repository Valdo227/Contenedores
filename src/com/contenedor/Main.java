package com.contenedor;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Container> containers = new ArrayList<>();
        containers.add(new Container("Frijol",15));
        containers.add(new Container("Maíz",30));
        containers.add(new Container("Trigo",25));

        Producer producer1 = new Producer("1","Frijol",3,"Maíz",2,containers);
        Producer producer2 = new Producer("2","Frijol",4,"Trigo",2,containers);
        Producer producer3 = new Producer("3","Maíz",3,"Trigo",3,containers);
        Producer producer4 = new Producer("4","Frijol",2,"Maíz",4,containers);

        List<Seed> seedsClient1 = new ArrayList<>();
        seedsClient1.add(new Seed("Frijol",5));

        List<Seed> seedsClient2 = new ArrayList<>();
        seedsClient1.add(new Seed("Frijol",5));
        seedsClient1.add(new Seed("Maíz",20));

        List<Seed> seedsClient3 = new ArrayList<>();
        seedsClient1.add(new Seed("Frijol",10));
        seedsClient1.add(new Seed("Maíz",20));
        seedsClient1.add(new Seed("Trigo",10));

        Client client1 = new Client("Valdo", seedsClient1, containers);
        Client client2 = new Client("Valdo", seedsClient2, containers);
        Client client3 = new Client("Valdo", seedsClient3, containers);

        System.out.println("Los productores comienzan el día");

        producer1.start();
        producer2.start();
        producer3.start();
        producer4.start();

        client1.start();
        client2.start();
        client3.start();

        while(client1.isAlive() || client2.isAlive() || client3.isAlive()){

            while(producer1.isAlive() || producer2.isAlive() || producer3.isAlive() || producer4.isAlive()){ }
            System.out.println("\033[33mTodos los contenedores se han llenado");
        }



    }
}
