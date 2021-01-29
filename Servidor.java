/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.Scanner;

/**
 *
 * @author oracle
 */
public class Servidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try{
            
            Scanner sc = new Scanner(System.in);
            
            System.out.println("Creando socket servidor...");

            ServerSocket serverSocket=new ServerSocket();

            System.out.println("Realizando el bind...");

            InetSocketAddress addr=new InetSocketAddress("192.168.0.23",6666);
            serverSocket.bind(addr);

            System.out.println("Aceptando conexiones...");

            Socket newSocket= serverSocket.accept();

            System.out.println("Conexión recibida ");

            InputStream is=newSocket.getInputStream();
            OutputStream os=newSocket.getOutputStream();

            byte[] mensaje=new byte[25];
            int operacion = 0;
            int n = is.read(mensaje);
            String mensajeCon = new String(mensaje);
            String[] partes = mensajeCon.split(" ");
            
            if(n > -1){
                System.out.println("Operacion recibida: " + new String(mensaje));
            }
            int op1 = Integer.parseInt(partes[0]);
            int op2 = Integer.parseInt(partes[2]);
            
            if("+".equals(partes[1])){
                operacion = op1 + op2;
            }
            if("-".equals(partes[1])){
                operacion = op1 - op2;
            }
            if("*".equals(partes[1])){
                operacion = op1 * op2;
            }
            if("/".equals(partes[1])){
                operacion = op1 / op2;
            }

            String resultado = String.valueOf(operacion);
            os.write(resultado.getBytes());
            
            System.out.println("Cerrando el nuevo socket...");

            newSocket.close();

            System.out.println("Cerrando el socket servidor...");

            serverSocket.close();

            System.out.println("Terminado");

        }catch (IOException e) {
        }
    }  
}
