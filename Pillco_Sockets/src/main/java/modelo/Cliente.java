/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USUARIO
 */
public class Cliente {
private Socket cliente;

    public Cliente() {
        cliente=new Socket();
    }

    public Socket getCliente() {
        return cliente;
    }

    public void setCliente(Socket cliente) {
        this.cliente = cliente;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////7
    
    public static void main(String[] args) {
     Cliente cx=new Cliente();
        DataInputStream inp_var1;
        DataOutputStream out_enviar_respuesta;
         String lenguaje;
            Scanner leer=new Scanner(System.in);
    try {
        cx.setCliente(new Socket("127.0.0.1", 6881));
        inp_var1=new DataInputStream(cx.getCliente().getInputStream());
        //inp_var1.readUTF();
        System.out.println(inp_var1.readUTF());
        
        
        boolean repite=true;
        while(repite){
            
             System.out.println("INTRODUZCA SU RESPUESTA");
            lenguaje=leer.next();
            
            out_enviar_respuesta=new DataOutputStream(cx.getCliente().getOutputStream());
            out_enviar_respuesta.writeUTF(lenguaje);
            
            String llega=inp_var1.readUTF();
            if (lenguaje.equals("adios")) {
                repite=false;
                System.out.println("EL PRORAMA FINALIZO");
                
                break;
            }
            if (llega.equals("")) {
                System.out.println("NO ENTENDI TU SOLICITUD PORFAVOR, EXPLICAME MEJOR EN QUE TE PUEDO AYUDAR :)");
            }else{
                
            System.out.println(llega);
             }
        }
        /////////////////////////////////////
       
        //lenjuage enviado
    } catch (IOException ex) {
        Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
    }
     
    }
    
            
            
 ////////////////////////////////////////////////////////////////////////////////////////////////////////           
}
