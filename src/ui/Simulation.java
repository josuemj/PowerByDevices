package ui;

import model.Device;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class Simulation extends JFrame {

    private JPanel SimulationFrame;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel panel5;
    private JPanel panel6;
    private JPanel panel7;
    private JPanel panel8;
    private JPanel panel9;
    private JPanel panel10;
    private JLabel imageLabel1;
    private JLabel imageLabel2;
    private JLabel imageLabel3;
    private JLabel imageLabel4;
    private JLabel imageLabel5;
    private JLabel imageLabel6;
    private JLabel imageLabel7;
    private JLabel imageLabel8;
    private JLabel imageLabel9;
    private JLabel imageLabel10;
    private JButton button1;

    //Elements
    private int size;
    private ArrayList<JPanel> panels;
    private ArrayList<JLabel> imageLabels;

    public Simulation(HashMap<Integer, Device> devicesMap){
        setContentPane(SimulationFrame);
        setSize(135*devicesMap.size(),160);
        setResizable(false);

        /**
         Lists settings
         */

        panels = new ArrayList<JPanel>();
        panels = setPanels(panels);

        imageLabels = new ArrayList<JLabel>();
        imageLabels = setImageLabels(imageLabels);


        /**
         *
         */

        System.out.println("Simulatioin Received "+devicesMap.size()+" devices");
        System.out.println("Showing devices\n============================");
        for (int i = 0; i < devicesMap.size(); i++) {
            System.out.println(devicesMap.get(i).getDeviceName());
            panels.get(i).setVisible(true);

            //Image settings
            URL imageUrl = getClass().getResource("/ui/devicesImages/"+devicesMap.get(i).getDeviceName()+".png");
            ImageIcon imageIcon = new ImageIcon(imageUrl);
            imageLabels.get(i).setIcon(imageIcon);

        }

//        button1.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent actionEvent) {
//
//                URL imageUrl = getClass().getResource("/ui/devicesImages/ps.png");
//                if (imageUrl == null) {
//                    System.out.println("Image resource not found!");
//                }
//                //ImageIcon imageIcon = new ImageIcon(imageUrl);
//                //mageLabel.setIcon(imageIcon);
//
//            }
//        });




    }

    public ArrayList<JPanel> setPanels(ArrayList<JPanel> emptyPanelsArray){
        emptyPanelsArray.add(panel1);
        emptyPanelsArray.add(panel2);
        emptyPanelsArray.add(panel3);
        emptyPanelsArray.add(panel4);
        emptyPanelsArray.add(panel5);
        emptyPanelsArray.add(panel6);
        emptyPanelsArray.add(panel7);
        emptyPanelsArray.add(panel8);
        emptyPanelsArray.add(panel9);
        emptyPanelsArray.add(panel10);
        return emptyPanelsArray;
    }

    public ArrayList<JLabel> setImageLabels(ArrayList<JLabel> emptyLabelsArray){
        emptyLabelsArray.add(imageLabel1);
        emptyLabelsArray.add(imageLabel2);
        emptyLabelsArray.add(imageLabel3);
        emptyLabelsArray.add(imageLabel4);
        emptyLabelsArray.add(imageLabel5);
        emptyLabelsArray.add(imageLabel6);
        emptyLabelsArray.add(imageLabel7);
        emptyLabelsArray.add(imageLabel8);
        emptyLabelsArray.add(imageLabel9);
        emptyLabelsArray.add(imageLabel10);
        return emptyLabelsArray;
    }

}
