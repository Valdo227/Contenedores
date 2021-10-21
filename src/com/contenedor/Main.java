package com.contenedor;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Container> containers = new ArrayList<>();
        containers.add(new Container("Frijol",10));
        containers.add(new Container("Maíz",20));
        containers.add(new Container("Trigo",15));

        Producer producer1 = new Producer("1","Frijol",3,"Maíz",2,containers);
        Producer producer2 = new Producer("2","Frijol",5,"Trigo",2,containers);
        Producer producer3 = new Producer("3","Maíz",3,"Trigo",3,containers);
        Producer producer4 = new Producer("4","Frijol",2,"Maíz",5,containers);

        producer1.start();
        producer2.start();
        producer3.start();
        producer4.start();

    }
}
