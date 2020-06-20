package sample;

/*
  Dependencias de la clase
 */

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Clase cliente que se encarga de la comunicacion con el servidor
 */
public class Cliente  {

    /**
     * El carro utilizado por este programa. Podra controlarlo
     */
    Car myCar;
    /**
     * El carro del rival por un programa remoto. Se actualiza por medio de la informacion
     * recibida por el server/
     */
    Car rivalCar;
    /**
     * Nombre de mi carro, para identificacion
     */
    String name;
    /**
     * Ultima respuesta obtenida desde el server
     */
    private String lastAnswer = "";
    /**
     * IP para conectarse con el server
     */
    private final String ip = "192.168.100.22";
    /**
     * Puerto para conectarse con el server
     */
    private final int port = 25557;
    /**
     * Distancia que debe recorrer el carro para poder ganar
     */
    private final int winDistance = 1000;

    /***
     * Constructor
     */
    public Cliente() {
    }

    /**
     * Verifica que ambos carros esten listos
     * @return true en caso de que ambos esten listos
     */
    public boolean checkBothCars(){
        return !lastAnswer.equals("wait");
    }

    /**
     * Verifica que si algun carro ya recorrio la distancia para ganar
     * @return true en caso de que alguno haya terminado
     */
    public boolean checkWinner(){
        return !FinalWindow.getInstance().getWhoWin().equals("Keep playing");
    }

    /**
     * Actualiza el servidor con la informacion de mi carro
     * Recibe la informacion del carro rival para actualizarla a nivel local
     */
    public void update()   {
        try{
            //se instancia el cliente y se le indica la ip y puerto de la conexion
            //canal por donde el cliente recibe y envia informacion
            Socket client = new Socket(ip, port);

            //crea el output por donde se envia la informacion del cliente hacia el servidor
            // la salida es la informacion que se envia al servidor y en este caso se indicia que es un objeto
            DataOutputStream salida = new DataOutputStream(client.getOutputStream());

            //crea el input por donde se recibe la informacion del servidor
            InputStream input = client.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            //Envia informacion al server
            PrintWriter pw = new PrintWriter(salida);
            pw.flush();
            pw.write(CarInfo()+"\0");
            System.out.println("I sent to Server: " + CarInfo());
            pw.flush();
            //Lee la informacion enviada por el servidor
            String msg = reader.readLine();


            //Verifica que la informacion recibida sea del formato esperado para actualizar el carro rival
            if (!msg.equals("wait")) {
                updateRivalsCar(msg);
            }

            //Verifica si el carro rival ya ha ganado
            if(rivalCar.distance >= winDistance){
                FinalWindow.getInstance().setWhoWin("Perdiste");
            }
            //Verifica si el usuario ya ha ganado
            if(myCar.distance >= winDistance){
                FinalWindow.getInstance().setWhoWin("Ganaste");
            }


            //Actualiza la ultima respuesta del server
            lastAnswer = msg;
            //imprime la informacion enviada por el servidor
            System.out.println("Server says: " + msg);

            //cierra el socket del cliente
            client.close();

            //cierra la salida de informacion
            salida.close();


        }
        catch(Exception e){
            System.out.println(e.getMessage());

        }
    }

    /**
     * Envia la instruccion que limpie los txt para que no interfiera en juegos futuros
     */
    public void cleanFiles(){
        try{
            Socket client = new Socket(ip, port);
            DataOutputStream salida = new DataOutputStream(client.getOutputStream());
            InputStream input = client.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            PrintWriter pw = new PrintWriter(salida);
            pw.flush();
            pw.write("bye,");
            pw.flush();
            client.close();
            salida.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Actualiza los parametros del carro rival
     * @param info informacion recibida por el server
     */
    private void updateRivalsCar(String info){
        //Descompone el string recibido en una lista de dobles
        List<String> strList = new ArrayList<>(Arrays.asList(info.split(",")));
        List<Double> numberList = new ArrayList<>(2);
        for (int i = 1; i < 3; i++){
            numberList.add(Double.parseDouble(strList.get(i)));
        }
        rivalCar.carImageView.setX(numberList.get(0));
        rivalCar.velocity = numberList.get(1).intValue();
        rivalCar.distance = rivalCar.distance + rivalCar.velocity;

    }

    /**
     * Consigue la informacion del carro referencia que esta utilizando para luego enviarla al server
     * @return string de la informacion para ser enviada
     */
    public String CarInfo(){
        double[] carData =  getCarData();
        return getStringFromArray(carData);

    }


    /**
     * Funcion auxilar dde CarInfo() que obtiene un array de Doubles con la informacion del carro
     * @return Double[] con la informacion del carro principal
     */
    private double[] getCarData(){
        double[] carData = new double[3];
        carData[0] = myCar.carImageView.getX();
        carData[1] = myCar.velocity.doubleValue();
        AnimatedImage.setDuration(myCar.velocity);
        myCar.distance = myCar.distance + myCar.velocity;
        carData[2] = myCar.distance.doubleValue();
        return carData;
    }

    /**
     * Convierte la lista de atributos en un String para enviar al server. Indispensable que se separa por comas
     * @param array Lista de atributos del carro
     * @return string de informacion del carro
     */
    private String getStringFromArray(double[] array){
        String string = name+",";
        int length = array.length-1;
        for (int i = 0; i<length; i++){
            string = string+array[i]+",";
        }
        string = string+array[length];
        return string;
    }

    //Getters and Setters
    public void setMyCar(Car car, String name){
        this.myCar = car;
        this.name = name;
    }

    public void setRivalCar(Car rivalCar) {
        this.rivalCar = rivalCar;
    }
}