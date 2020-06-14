package sample;
import java.io.*;
import java.net.Socket;

//clase cliente
public class Cliente  {

    //boolean que define si es el turno de este cliente
    private boolean turno = true;

    //metodo que inicia el cliente
    public  void start()   {
        try{

            //string que indica la direccion ip local
            String ip = "192.168.1.100";

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

            while (turno) {

                //Envia informacion al server
                PrintWriter pw=new PrintWriter(salida);
                pw.flush();
                pw.write("Bye25\0");
                pw.flush();


                //Lee la informacion enviada por el servidor
                String msg = reader.readLine();
                //imprime la informacion enviada por el servidor
                System.out.println(msg);



            }



            //como ya se envio una palabra entonces el turno de este jugador es false
            this.turno = false;


            //cierra el socket del cliente
            client.close();


            //cierra la salida de informacion
            salida.close();

        }
        catch(Exception e){
            System.out.println(e.getMessage());

        }
    }


}