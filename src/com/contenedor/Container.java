package com.contenedor;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Container {

    String seed;
    int amount;
    int quantity;
    public static boolean full = false;
    public static boolean busy = false;
    public static boolean open = false;

    DefaultListModel listModel;
    JLabel label;
    JPanel panel;

    public Container(String seed, int amount, DefaultListModel listModel,JPanel panel, JLabel label) {
        this.seed = seed;
        this.amount = amount;
        this.quantity = 0;
        this.listModel = listModel;
        this.label = label;
        this.panel = panel;
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

    public void setQuantity(int quantity, String name) throws InterruptedException  {
        panel.setBackground(Color.green);
        label.setForeground(Color.black);
        if (this.quantity + quantity >= amount) {
            listModel.add(0,"El productor " + name + " agregó " + (amount - this.quantity) + "T de " + seed + " y se regresó con " + (quantity - (amount - this.quantity)) + "T");
            this.quantity = amount;

        } else {
            this.quantity += quantity;
            listModel.add(0,"El productor " + name + " agregó " + quantity + "T de " + seed + " (" + this.quantity + "/" + amount + ")");
        }
        label.setText(this.quantity + "/" + amount);
        Thread.sleep(500);
        panel.setBackground(Color.blue);
        label.setForeground(Color.white);
    }

    public synchronized void getQuantity(Seed seed, String name) throws InterruptedException {
        panel.setBackground(Color.green);
        label.setForeground(Color.black);

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
        label.setText(this.quantity + "/" + amount);
        Thread.sleep(1000);
        panel.setBackground(Color.blue);
        label.setForeground(Color.white);
    }
}
