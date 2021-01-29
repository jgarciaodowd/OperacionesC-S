/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;


/**
 *
 * @author oracle
 */
public class Cliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        try {
            
            Scanner sc = new Scanner(System.in);
            
            System.out.println("Creando socket cliente...");
            Socket clienteSocket = new Socket();
            System.out.println("Estableciendo la conexión...");

            InetSocketAddress addr = new InetSocketAddress("192.168.0.23", 6666);
            clienteSocket.connect(addr);

            InputStream is = clienteSocket.getInputStream();
            OutputStream os = clienteSocket.getOutputStream();
            
            String mensaje1 = "";
            System.out.println(" Te toca ");
            mensaje1 = sc.nextLine();
            os.write(mensaje1.getBytes());
            
            byte[] contestacion1 = new byte[25];
            System.out.println("Mensaje enviado ");
            int n = is.read(contestacion1);
            if(n > -1){
                System.out.println("La operación es: " + new String(contestacion1));
                
            }

            System.out.println("Cerrando el socket cliente...");

            clienteSocket.close();

            System.out.println("Terminado");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
