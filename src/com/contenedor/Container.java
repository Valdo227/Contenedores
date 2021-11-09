package com.contenedor;

import java.util.List;

public class Container {

    String seed;
    int amount;
    int quantity;
    public static boolean full = false;

    public Container(String seed, int amount) {
        this.seed = seed;
        this.amount = amount;
        this.quantity = 0;
    }

    public static boolean VerifyContainer(List<Container> containers) {
        full = true;
        for (Container container : containers) {
            if (!(container.amount == container.quantity)) {
                full = false;
                break;
            }
        }
        return full;
    }

    public synchronized void setQuantity(int quantity, String name) {
        System.out.println("entró " + name + " " + this.seed);

        if (this.quantity + quantity >= amount) {
            System.out.println("El productor " + name + " agregó " + (amount - this.quantity) + "T de " + seed + " y se regresó con " + (quantity - (amount - this.quantity)) + "T");
            System.out.println("\033[33mEl contendedor de " + seed + " se llenó (" + amount + "T)\u001B[0m");
            this.quantity = amount;

        } else {
            this.quantity += quantity;
            System.out.println("El productor " + name + " agregó " + quantity + "T de " + seed + " (" + this.quantity + "/" + amount + ")");
        }
        System.out.println("salio " + name + " " + this.seed);
        notifyAll();
    }

    public synchronized void getQuantity(Seed seed, String name) {
        System.out.println("entró a comprar" + name + " " + this.seed);

        if (this.quantity - seed.amount <= 0) {
            System.out.println("El cliente " + name + " compró " + (seed.amount + (this.quantity - seed.amount)) + "T de " + seed + " y le faltaron " + (seed.amount - this.quantity) + "T");
            System.out.println("\033[33mEl contendedor de " + seed + " se vació");
            this.quantity = 0;
            seed.amount -= seed.amount;
        } else {
            this.quantity -= seed.amount;
            System.out.println("El cliente " + name + " compró " + seed.amount + "T de " + seed + " (" + this.quantity + "/" + amount + ")");
            seed.amount = 0;
        }
        System.out.println("salió el cliente " + name + " " + this.seed);
        notifyAll();
    }
}
