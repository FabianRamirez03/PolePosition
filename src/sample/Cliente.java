package sample;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//clase cliente
public class Cliente  {

    Car myCar;
    Car rivalCar;
    String name;
    String lastAnswer = "";

    public Cliente() {
    }

    public boolean checkBothCars(){
        boolean result = false;
        update();
        if(lastAnswer != "" && lastAnswer != "wait"){
            result = true;
        }
        return result;
    }

    //metodo que inicia el cliente
    public void update()   {
        try{

            //string que indica la direccion ip local
            String ip = "192.168.100.22";

            //numero de puerto en donde se realiza la conexion cliente-servidor
            int port = 25557;

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
            pw.write(this.CarInfo()+"\0");
            pw.flush();
            //Lee la informacion enviada por el servidor
            String msg = reader.readLine();
            lastAnswer = msg;
            //imprime la informacion enviada por el servidor
            System.out.println(msg);

            //cierra el socket del cliente
            client.close();

            //cierra la salida de informacion
            salida.close();


        }
        catch(Exception e){
            System.out.println(e.getMessage());

        }
    }

    private void updateRivalsCar(String info){
        List<String> strList = new ArrayList<String>(Arrays.asList(info.split(",")));
        List<Double> numberList = new ArrayList<Double>();
        for (int i = 1; i < 6; i++){
            numberList.set(i, Double.parseDouble(strList.get(i)));
        }

    }

    public String CarInfo(){
        double[] carData =  getCarData();
        return getStringFromArray(carData);

    }

    private double[] getCarData(){
        double[] carData = new double[6];
        carData[0] = myCar.carImageView.getX();
        carData[1] = myCar.carImageView.getY();
        carData[2] = myCar.carImageView.getFitWidth();
        carData[3] = myCar.carImageView.getFitHeight();
        carData[4] = myCar.velocity;
        return carData;
    }

    private String getStringFromArray(double[] array){
        String string = name+",";
        int length = array.length-1;
        for (int i = 0; i<length; i++){
            string = string+array[i]+",";
        }
        string = string+array[length-1];
        return string;
    }

    public void setMyCar(Car car, String name){
        this.myCar = car;
        this.name = name;
    }

    public void setRivalCar(Car rivalCar) {
        this.rivalCar = rivalCar;
    }
}