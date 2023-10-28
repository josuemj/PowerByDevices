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
    private JTextField potencia1;
    private JTextField tiempo1;
    private JTextField potencia2;
    private JTextField potencia3;
    private JTextField potencia4;
    private JTextField potencia5;
    private JTextField potencia6;
    private JTextField potencia7;
    private JTextField potencia8;
    private JTextField potencia9;
    private JTextField potencia10;
    private JTextField tiempo2;
    private JTextField tiempo3;
    private JTextField tiempo4;
    private JTextField tiempo5;
    private JTextField tiempo6;
    private JTextField tiempo7;
    private JTextField tiempo8;
    private JTextField tiempo9;
    private JTextField tiempo10;
    private JTextField lengthField;
    private ArrayList<JLabel> labelList;
    private ArrayList<String> devicesList;
    private int currentDevices;
    private ArrayList<JTextField> voltageFields;
    private ArrayList<JTextField> currentFields;
    private ArrayList<JTextField> powerFields;
    private ArrayList<JTextField> timeFields;


    private ArrayList<String> devicesSelected;

    private HashMap<Integer, Device> DevicesMap;


    public Power(

    ){
        /**
         * Setting lists
         */
        devicesList = new ArrayList<String>();
        labelList = new ArrayList<JLabel>();
        powerFields = new ArrayList<JTextField>();
        timeFields = new ArrayList<JTextField>();


        voltageFields = new ArrayList<JTextField>();
        currentFields = new ArrayList<JTextField>();
        devicesSelected = new ArrayList<String>();
        DevicesMap = new HashMap<Integer, Device>();

        devicesList = setDevices(devicesList);
        labelList = setLabels(labelList);
        voltageFields = setVoltageFields(voltageFields);
        currentFields = setCurrentFields(currentFields);
        powerFields = setPowerFields(powerFields);
        timeFields = setTimeFields(timeFields);


        setComboBox1Elemets(devicesComboBox,devicesList);
        setLabels(labelList);
        setInvisibleLabels(labelList);
        setInvisibleFields(voltageFields);
        setInvisibleFields(currentFields);
        setInvisibleFields(powerFields);
        setInvisibleFields(timeFields);

        adviceLabel.setVisible(false);

        currentDevices = 0;

        setContentPane(powerForm);
        setSize(750,529);
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

                        double power = Double.parseDouble(powerFields.get(i).getText());

                        double time = Double.parseDouble(timeFields.get(i).getText());

                        if (power == voltage*current){
                            //Power mathches with I*V = P
                            System.out.println("Power: "+power);
                            Device newDevice = new Device(deviceSelected,deviceSelected,voltage,current,power,time);
                            DevicesMap.put(i,newDevice); //All set to add to map
                            System.out.println("Device added");
                            adviceLabel.setText("Dispositivo agregado");
                            adviceLabel.setVisible(true);
                        }else{
                            System.out.println("Dismatch");
                            adviceLabel.setVisible(true);
                            adviceLabel.setText("Incongruencia en valores!");
                        }

                    }catch (Exception e){
                        adviceLabel.setVisible(true);
                        adviceLabel.setText("Error en parametros");
                        System.out.println("Error");
                    }
                }
                double cableLenght = Double.parseDouble(lengthField.getText());
                Simulation simulation = new Simulation(DevicesMap,cableLenght);
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

            //TextFields
            voltageFields.get(currentDevices).setVisible(true);
            currentFields.get(currentDevices).setVisible(true);
            powerFields.get(currentDevices).setVisible(true);
            timeFields.get(currentDevices).setVisible(true);

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

    public ArrayList<JTextField> setPowerFields(ArrayList<JTextField> powerFields){
        powerFields.add(potencia1);
        powerFields.add(potencia2);
        powerFields.add(potencia3);
        powerFields.add(potencia4);
        powerFields.add(potencia5);
        powerFields.add(potencia6);
        powerFields.add(potencia7);
        powerFields.add(potencia8);
        powerFields.add(potencia9);
        powerFields.add(potencia10);
        return powerFields;
    }

    public ArrayList<JTextField> setTimeFields(ArrayList<JTextField> timeFields){
        timeFields.add(tiempo1);
        timeFields.add(tiempo2);
        timeFields.add(tiempo3);
        timeFields.add(tiempo4);
        timeFields.add(tiempo5);
        timeFields.add(tiempo6);
        timeFields.add(tiempo7);
        timeFields.add(tiempo8);
        timeFields.add(tiempo9);
        timeFields.add(tiempo10);
        return timeFields;
    }


}
