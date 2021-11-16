package com.contenedor;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Containers {
    private JPanel panel1;
    private JPanel clientsPanel;
    private JPanel containersPanel;
    private JPanel ProducersPanel;
    private JPanel producer1;
    private JPanel producer2;
    private JPanel producer3;
    private JPanel producer4;
    private JPanel container1;
    private JPanel container2;
    private JPanel container3;
    private JPanel client1;
    private JPanel client2;
    private JPanel client4;
    private JList<String> consoleList;
    private JPanel consolePanel;
    private JLabel frijolLabel;
    private JLabel maizLabel;
    private JLabel trigoLabel;
    private JLabel valdoLabel;
    private JLabel hiramLabel;
    private JLabel juanpaLabel;
    private JPanel client3;

    Producer p1;
    Producer p2;
    Producer p3;
    Producer p4;

    Client c1;
    Client c2;
    Client c3;
    Client c4;

    DefaultListModel<String> listModel;

    public Containers(){
        listModel = new DefaultListModel<String>();
        consoleList.setModel(listModel);

        java.util.List<Container> containers = new ArrayList<>();
        containers.add(new Container("Frijol", 15,listModel));
        containers.add(new Container("Maíz", 30,listModel));
        containers.add(new Container("Trigo", 25,listModel));

        p1 = new Producer("1", "Frijol", 3, "Maíz", 2, containers, producer1);
        p2 = new Producer("2", "Frijol", 4, "Trigo", 2, containers, producer2);
        p3 = new Producer("3", "Maíz", 3, "Trigo", 3, containers, producer3);
        p4 = new Producer("4", "Frijol", 2, "Maíz", 4, containers, producer4);

        java.util.List<Seed> seedsClient1 = new ArrayList<>();
        seedsClient1.add(new Seed("Frijol", 5));

        java.util.List<Seed> seedsClient2 = new ArrayList<>();
        seedsClient2.add(new Seed("Frijol", 5));
        seedsClient2.add(new Seed("Maíz", 20));

        java.util.List<Seed> seedsClient3 = new ArrayList<>();
        seedsClient2.add(new Seed("Frijol", 15));
        seedsClient2.add(new Seed("Maíz", 10));

        List<Seed> seedsClient4 = new ArrayList<>();
        seedsClient3.add(new Seed("Frijol", 10));
        seedsClient3.add(new Seed("Maíz", 20));
        seedsClient3.add(new Seed("Trigo", 10));

        c1 = new Client("Valdo", seedsClient1, containers, client1);
        c2 = new Client("Hiram", seedsClient2, containers, client2);
        c3 = new Client("Juanpa", seedsClient3, containers, client3);
        c4 = new Client("Kath", seedsClient4, containers, client4);

        listModel.add(listModel.size(),"Los productores comienzan el día");
        //("Los productores comienzan el día");

        p1.start();
        p2.start();
        p3.start();
        p4.start();

        c1.start();
        c2.start();
        c3.start();
        c4.start();

        initComponents();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Contenedores");
        frame.setContentPane(new Containers().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setMinimumSize(new Dimension(800, 480));
        frame.setVisible(true);

    }

    private void initComponents() {
    }
}