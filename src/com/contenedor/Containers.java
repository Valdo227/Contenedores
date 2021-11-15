package com.contenedor;

import javax.swing.*;
import java.awt.*;

public class Containers {
    private JPanel panel1;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Contenedores");
        frame.setContentPane(new Containers().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setMinimumSize(new Dimension(480, 480));
        frame.setVisible(true);
    }
}