package com.contenedor;

import java.util.List;

public class Container {

    String seed;
    int amount;
    int quantity;
    public static boolean full = false;
    boolean busy = false;

    public Container(String seed,int amount){
        this.seed = seed;
        this.amount = amount;
        this.quantity = 0;
    }

    public static boolean VerifyContainer(List<Container> containers){
        full = true;
        for(Container container: containers) {
            if (!(container.amount == container.quantity)){
                full = false;
                break;
            }
        }
        return full;
    }

    public synchronized void setQuantity(int quantity,String name){
        busy = true;
        this.quantity += quantity;
        System.out.println(name + " libera "+ seed +"..." + this.quantity +"/"+amount);
        busy = false;
        this.notify();
    }
}
