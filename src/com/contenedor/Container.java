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

    public void setQuantity(int quantity,String name){
        busy = true;
        synchronized (this) {
            if (this.quantity + quantity >= amount) {
                System.out.println("El productor " + name + " agregó " + (amount - this.quantity) + "T de " + seed + " y se regresó con " + (amount - quantity) + "T");
                System.out.println ("\033[33mEl contendedor de "+seed + " se llenó (" + amount + "T)\u001B[0m");
                this.quantity = amount;

            } else {
                this.quantity += quantity;
                System.out.println("El productor " + name + " agregó " + quantity + "T de " + seed + " (" + this.quantity + "/" + amount + ")");
            }
            notify();
        }
        busy = false;
    }
}
