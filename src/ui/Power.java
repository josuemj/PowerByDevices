package ui;

import model.Device;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class Power extends JFrame {

    private JPanel powerForm;
    private JButton button1;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JComboBox devicesComboBox;
    private JButton simularButton;
    private JLabel label6;
    private JLabel label7;
    private JLabel label8;
    private JLabel label9;
    private JLabel label10;
    private JLabel adviceLabel;
    private JTextField voltage1;
    private JTextField current1;
    private JTextField voltage2;
    private JTextField current2;
    private JTextField voltage3;
    private JTextField current3;
    private JTextField voltage4;
    private JTextField current4;
    private JTextField voltage5;
    private JTextField current5;
    private JTextField voltage6;
    private JTextField current6;
    private JTextField voltage7;
    private JTextField current7;
    private JTextField voltage8;
    private JTextField current8;
    private JTextField voltage9;
    private JTextField current9;
    private JTextField voltage10;
    private JTextField current10;
    private ArrayList<JLabel> labelList;
    private ArrayList<String> devicesList;
    private int currentDevices;
    private ArrayList<JTextField> voltageFields;
    private ArrayList<JTextField> currentFields;

    private ArrayList<String> devicesSelected;

    private HashMap<Integer, Device> DevicesMap;


    public Power(

    ){
        /**
         * Setting lists
         */
        devicesList = new ArrayList<String>();
        labelList = new ArrayList<JLabel>();
        voltageFields = new ArrayList<JTextField>();
        currentFields = new ArrayList<JTextField>();
        devicesSelected = new ArrayList<String>();
        DevicesMap = new HashMap<Integer, Device>();

        devicesList = setDevices(devicesList);
        labelList = setLabels(labelList);
        voltageFields = setVoltageFields(voltageFields);
        currentFields = setCurrentFields(currentFields);

        setComboBox1Elemets(devicesComboBox,devicesList);
        setLabels(labelList);
        setInvisibleLabels(labelList);
        setInvisibleFields(voltageFields);
        setInvisibleFields(currentFields);

        adviceLabel.setVisible(false);

        currentDevices = 0;

        setContentPane(powerForm);
        setSize(500,529);
        setResizable(false);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                addDevice(labelList);
            }
        });


        simularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Total devices: "+currentDevices);

                System.out.println(voltageFields.size());

                for (int i = 0; i < devicesSelected.size(); i++) {
                    try{

                        String deviceSelected = devicesSelected.get(i);
                        System.out.println(deviceSelected);

                        double voltage = Double.parseDouble(voltageFields.get(i).getText());
                        System.out.println("Voltage: "+voltage);

                        double current = Double.parseDouble(currentFields.get(i).getText());
                        System.out.println("Current: "+current);

                        Device newDevice = new Device(deviceSelected,deviceSelected,voltage,current);
                        DevicesMap.put(i,newDevice);


                    }catch (Exception e){
                        adviceLabel.setText("Error en parametros");
                        System.out.println("Error");
                    }
                }
                Simulation simulation = new Simulation(DevicesMap);
                simulation.setVisible(true);
            }
        });
    }



    public void setComboBox1Elemets(JComboBox ComboBox1,ArrayList<String> devices){
        for (int i = 0; i < devices.size(); i++) {
            ComboBox1.addItem((devices.get(i)));
        }
    }

    public void addDevice(ArrayList<JLabel> Labels){

        if(available(currentDevices)){
            System.out.println(currentDevices);
            System.out.println("XD");
            System.out.println("FULL");
            adviceLabel.setVisible(true);
            adviceLabel.setText("Dispositivos maximos alcanzados");
            System.out.println(getSize());
        }else{
            Labels.get(currentDevices).setText(devicesComboBox.getSelectedItem().toString());
            Labels.get(currentDevices).setVisible(true);
            voltageFields.get(currentDevices).setVisible(true);
            currentFields.get(currentDevices).setVisible(true);
            devicesSelected.add(devicesComboBox.getSelectedItem().toString());

            currentDevices++;
        }


    }

    public boolean available(int currentDevices){
        if(currentDevices<10){
            return false;
        }
        return true;
    }

    public void setInvisibleLabels(ArrayList<JLabel> labelsList){
        for (int i = 0; i < labelsList.size(); i++) {
            labelsList.get(i).setVisible(false);
        }
    }

    public void setInvisibleFields(ArrayList<JTextField> fieldsList){
        for (int i = 0; i < fieldsList.size(); i++) {
            fieldsList.get(i).setVisible(false);
        }
    }
    //Functions for default configuration

    public ArrayList<String> setDevices(ArrayList<String> emptyDevicesList){
        emptyDevicesList.add("Licuadora");
        emptyDevicesList.add("Computadora");
        emptyDevicesList.add("Play4");
        emptyDevicesList.add("Router");
        emptyDevicesList.add("TV");
        emptyDevicesList.add("Refrigerador");
        emptyDevicesList.add("XBOX");
        emptyDevicesList.add("Switch");
        emptyDevicesList.add("Microondas");
        emptyDevicesList.add("Tostadora");
        emptyDevicesList.add("Tesla Model X");
        emptyDevicesList.add("Camara");
        emptyDevicesList.add("Cafetera");
        emptyDevicesList.add("Arbol-Navidad");
        emptyDevicesList.add("Reloj");
        emptyDevicesList.add("Lavadora");
        emptyDevicesList.add("Batidora");
        emptyDevicesList.add("Mesa DJ");
        emptyDevicesList.add("Foco");
        emptyDevicesList.add("Cargador");
        return emptyDevicesList;

    }



    public ArrayList<JLabel> setLabels(ArrayList<JLabel> labelsList){
        labelList.add(label1);
        labelList.add(label2);
        labelList.add(label3);
        labelList.add(label4);
        labelList.add(label5);
        labelList.add(label6);
        labelList.add(label7);
        labelList.add(label8);
        labelList.add(label9);
        labelList.add(label10);
        return labelsList;
    }

    public ArrayList<JTextField> setVoltageFields(ArrayList<JTextField> voltageFields){
        voltageFields.add(voltage1);
        voltageFields.add(voltage2);
        voltageFields.add(voltage3);
        voltageFields.add(voltage4);
        voltageFields.add(voltage5);
        voltageFields.add(voltage6);
        voltageFields.add(voltage7);
        voltageFields.add(voltage8);
        voltageFields.add(voltage9);
        voltageFields.add(voltage10);
        return voltageFields;
    }

    public ArrayList<JTextField> setCurrentFields(ArrayList<JTextField> currentFields){
        currentFields.add(current1);
        currentFields.add(current2);
        currentFields.add(current3);
        currentFields.add(current4);
        currentFields.add(current5);
        currentFields.add(current6);
        currentFields.add(current7);
        currentFields.add(current8);
        currentFields.add(current9);
        currentFields.add(current10);
        return currentFields;
    }



}
