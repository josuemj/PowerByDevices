package ui;

import model.Device;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
    private JLabel invoiceTotal;
    private JLabel tipoTarifa;
    private JPanel totalPanel;
    private JLabel Energy;
    private JLabel Calibre;
    private JLabel Label;
    private JLabel powerLabel;
    private JButton button1;

    //Elements
    private int size;
    private ArrayList<JPanel> panels;
    private ArrayList<JLabel> imageLabels;
    private int data;

    public Simulation(HashMap<Integer, Device> devicesMap,double cableLenght){
        setContentPane(SimulationFrame);

        if(devicesMap.size()==1){
            setSize(200*devicesMap.size(),280);
            totalPanel.setSize(200*devicesMap.size(),200);
        }else{
            setSize(150*devicesMap.size(),280);
            totalPanel.setSize(150*devicesMap.size(),200);
        }
        setResizable(false);



        /**
         Lists settings
         */

        panels = new ArrayList<JPanel>();
        panels = setPanels(panels);
        setInvisiblePanels(panels);

        imageLabels = new ArrayList<JLabel>();
        imageLabels = setImageLabels(imageLabels);


        /**
         *
         */

        System.out.println("Simulatioin Received "+devicesMap.size()+" devices");
        System.out.println("Showing devices\n============================");
        double maxPower = getMaxPower(devicesMap);
        System.out.println("Max Power: "+maxPower);
        double maxCurrent = getMaxCurrent(maxPower,devicesMap);
        System.out.println("Max current: "+maxCurrent);
        System.out.println("Min diameter: "+getDiameter(cableLenght,maxCurrent,maxPower));


        //Calibre minimo
        data = getMinDiameter(getDiameter(cableLenght,maxCurrent,maxPower)); //intento de calculo de calibre

        getInvoice(devicesMap);

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

    public void setInvisiblePanels(ArrayList<JPanel> panels){
        for (int i = 0; i <panels.size() ; i++) {
            panels.get(i).setVisible(false);
        }
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

    public double getMaxPower(HashMap<Integer,Device> devicesSelected){

        ArrayList<Double> powerList = new ArrayList<Double>();

        for (int i = 0; i < devicesSelected.size(); i++) {
            double power = devicesSelected.get(i).getPower();
            powerList.add(power);
        }

        double maxPower = powerList.get(0); // Initialize maxPower with the first element

        for (int i = 1; i < powerList.size(); i++) {
            double currentPower = powerList.get(i);
            if (currentPower > maxPower) {
                maxPower = currentPower; // Update maxPower if a higher power is found
            }
        }

        return maxPower;
    }

    public double getMaxCurrent(double maxPower,HashMap<Integer,Device> deviceMap){

        double current = 0;
        for (int i = 0; i < deviceMap.size(); i++) {
            if(deviceMap.get(i).getPower() == maxPower){
                current = deviceMap.get(i).getCurrent();
            }
        }
        return current;
    }

    public double getDiameter(double cableLenght,double maxCurrent,double maxPower){
        double CuResis = 1.72e-8;
        double diameter = 2*maxCurrent*Math.sqrt((CuResis*cableLenght)/(maxPower*Math.PI));
        System.out.println("Diametro minimo: "+diameter);
        return diameter;
    }

    public void getInvoice(HashMap<Integer,Device> deviceHashMap) {
        double kW = 0;
        for (int i = 0; i < deviceHashMap.size(); i++) {
            double devicePower = deviceHashMap.get(i).getPower();
            double deviceUseTime = deviceHashMap.get(i).getTime();
            double powerPerDay = devicePower * deviceUseTime; // Total watts per day
            double monthlyDevicePower = (powerPerDay * 30) / 1000; // Total wat per month (kW)

            kW = monthlyDevicePower + kW; //Adding power per device
        }
        double total = kW*2.2;
        System.out.println("total: "+total);

        //Redondeo
        BigDecimal bigDecimal = new BigDecimal(total).setScale(2, RoundingMode.HALF_UP);
        double roundedNumber = bigDecimal.doubleValue();

        invoiceTotal.setText(roundedNumber+" Q");
        System.out.println("rounded total: "+roundedNumber);

        BigDecimal bd = new BigDecimal(kW).setScale(2, RoundingMode.HALF_UP);
        double rn = bd.doubleValue();
        Energy.setText(String.valueOf(rn)+ " kW");
        Calibre.setText(String.valueOf(data));
        System.out.println("total power: "+kW+" kW");

        //estableciendo tarifa
        setTarifa(kW);
    }

    /**
     * BAJA TENSIÓN SIMPLE SOCIAL -BTSS- = 1 a 300 kwh
     * BAJA TENSIÓN SIMPLE -BTS- = menor o igual a 11 kw
     * BAJA TENSIÓN SIMPLE HORARIA -BTSH- = menor o igual a 11 kw
     * BAJA TENSIÓN SIMPLE AUTOPRODUCTORES -BTSA- = mas de 300 kwh
     * BAJA TENSIÓN HORARIA CON DEMANDA -BTHD- = mayor a 11 kw
     * MEDIA TENSIÓN HORARIA CON DEMANDA -MTHD- = mayor a 11 kw
     * BAJA TENSIÓN CON DEMANDA AUTOPRODUCTORES -BTDA- = mayor a 11 kw
     * MEDIA TENSIÓN CON DEMANDA AUTOPRODUCTORES -MTDA- = mayor a 11 kw
     * @param kW
     */
    public void setTarifa(double kW){
        if(kW<=11){
            tipoTarifa.setText("BTS");
        }else if (kW>=1 && kW<=300){
            tipoTarifa.setText("BTSS");
        }else if(kW>300){
            tipoTarifa.setText("BTSA");
        }
    }

    /**
     * attempt TODO
     * @param minDiameter
     * @return
     */

    public int getMinDiameter(double minDiameter){
        HashMap<Integer,Double> calibres = new HashMap<>();
        calibres.put(1, 0.007348);
        calibres.put(2, 0.006544);
        calibres.put(3, 0.005827);
        calibres.put(4, 0.005189);
        calibres.put(5, 0.004621);
        calibres.put(6, 0.004115);
        calibres.put(7, 0.003665);
        calibres.put(8, 0.003264);
        calibres.put(9, 0.002906);
        calibres.put(10, 0.002588);
        calibres.put(11, 0.002305);
        calibres.put(12, 0.002053);
        calibres.put(13, 0.001828);
        calibres.put(14, 0.001628);
        calibres.put(15, 0.001450);
        calibres.put(16, 0.001291);
        calibres.put(17, 0.001150);
        calibres.put(18, 0.001024);
        calibres.put(19, 0.000912);
        calibres.put(20, 0.000812);
        calibres.put(21, 0.009723);
        calibres.put(22, 0.000644);
        calibres.put(23, 0.000573);
        calibres.put(24, 0.000511);
        calibres.put(25, 0.000455);
        calibres.put(26, 0.000405);
        calibres.put(27, 0.000361);
        calibres.put(28, 0.000321);
        calibres.put(29, 0.000286);
        calibres.put(30, 0.000255);
        calibres.put(31, 0.000227);
        calibres.put(32, 0.000202);
        calibres.put(33, 0.000180);
        calibres.put(34, 0.000160);
        calibres.put(35, 0.000143);
        calibres.put(36, 0.000127);
        calibres.put(37, 0.000113);
        calibres.put(38, 0.000101);
        calibres.put(39, 0.000090);
        calibres.put(40, 0.000080);

        double[] data = new double[2];
        int minCalibre = 0;
        for (Integer key: calibres.keySet()) {
            System.out.print("////////////////////////////////");
            System.out.println("Calibre: " + key + ", Diameter: " + calibres.get(key));
            if(calibres.get(key)>minDiameter){
                minCalibre = key;
                System.out.println("Diametro minimo (calculo): "+minDiameter);
                System.out.println("Calibre minimo recomendado: "+ minCalibre);
                break;

            }else{
                minCalibre = key;
                System.out.println("Diametro minimo (calculo): "+minDiameter);
                System.out.println("Calibre minimo recomendado: "+ key);
            }
        }

        return minCalibre;

    }

}
