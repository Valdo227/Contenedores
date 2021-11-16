package com.contenedor;

import javax.swing.*;
import java.util.List;

public class Container {

    String seed;
    int amount;
    int quantity;
    public static boolean full = false;
    public static boolean busy = false;
    public static boolean open = false;

    DefaultListModel listModel;

    public Container(String seed, int amount, DefaultListModel listModel) {
        this.seed = seed;
        this.amount = amount;
        this.quantity = 0;
        this.listModel = listModel;
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

    public static boolean verifyContainerEmpty(List<Container> containers) {
        return containers.stream().anyMatch(container -> container.quantity == 0);
    }

    public void setQuantity(int quantity, String name) {
        //listModel.add(listModel.size(),"Entró productor " + name + " " + this.seed);

        if (this.quantity + quantity >= amount) {
            listModel.add(0,"El productor " + name + " agregó " + (amount - this.quantity) + "T de " + seed + " y se regresó con " + (quantity - (amount - this.quantity)) + "T");
            //listModel.add(listModel.size(),"\033[33mEl contendedor de " + seed + " se llenó (" + amount + "T)\u001B[0m");
            this.quantity = amount;

        } else {
            this.quantity += quantity;
            listModel.add(0,"El productor " + name + " agregó " + quantity + "T de " + seed + " (" + this.quantity + "/" + amount + ")");
        }
        //listModel.add(listModel.size(),"salio productor " + name + " " + this.seed);
    }

    public synchronized void getQuantity(Seed seed, String name) throws InterruptedException {
        listModel.add(0,"Entró cliente " + name + " a comprar " + this.seed);

        if (this.quantity - seed.amount == 0) {
            listModel.add(0,"El cliente " + name + " compró " + (seed.amount) + "T de " + seed.name);
            listModel.add(0,"El contendedor de " + seed.name + " se vació");
            this.quantity = 0;
            seed.amount = 0;
        } else if (this.quantity - seed.amount < 0) {
            listModel.add(0,"El cliente " + name + " compró " + (seed.amount + (this.quantity - seed.amount)) + "T de " + seed.name + " y le faltaron " + (seed.amount - this.quantity) + "T");
            listModel.add(0,"El contendedor de " + seed.name + " se vació");
            seed.amount -= this.quantity;
            this.quantity = 0;

        } else {
            this.quantity -= seed.amount;
            listModel.add(0,"El cliente " + name + " compró " + seed.amount + "T de " + seed.name + " (" + this.quantity + "/" + amount + ")");
            seed.amount = 0;
        }
        listModel.add(0,"salió el cliente " + name + " " + this.seed);
    }
}
