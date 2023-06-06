/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.Port;

/**
 *
 * @author USUARIO
 */
public class Servidor {
    private ServerSocket servidor;
    private int puertox=6881;
    
    public Servidor(int puerto) {
        try{
        servidor=new ServerSocket(puerto);
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public ServerSocket getServidor() {
        return servidor;
    }

    public void setServidor(ServerSocket servidor) {
        this.servidor = servidor;
    }

    public int getPuertox() {
        return puertox;
    }

    public void setPuertox(int puertox) {
        this.puertox = puertox;
    }
    
////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) {
        try {
            
            DataOutputStream out_var1;
            DataOutputStream out_descripcion;
            DataInputStream inp_respuesta;
            HashMap<String,String> diccionario=new HashMap<>();
             diccionario.put("bienvenido", "Hola,Bienvenido a su local de servício técnico de confianza,cuentame que deseas saber,que marca es su celular"
                             + "\n Con gusto te atenderemos,tambien puedes consultar por nuestras sucursales");
            
            diccionario.put("samsung", " Un teléfono samsung es de una prestijiosa empresa japonesa, ahora bien el daño que tiene es de sóftware o de hardware");
            diccionario.put("hardware", " Entendido, si es de hardware quiere decir que físicamente tu teléfono esta aberiado, por lo que me puedes decir que componente esta averiado o roto");
            diccionario.put("pantalla", "si tu pantalla esta rota ,dime que modelo de teléfono tienes");
            diccionario.put("galaxya20", "esta pantalla tiene un costo de $20.00 y el cambio dura 3 horas hasta finalizar la instalación, hay algo mas en lo que te pueda ayudar");
            diccionario.put("sucursales", "tenemos 2 locales uno en la AV.CONQUISTADORES y otro en AV.ESPAÑA, telf: 0958876453, ¿algo mas en lo que te pueda ayudar?");
            
            
            
            
            
            Servidor server=new Servidor(6881);
            
           System.out.println("ESPERANDO CLIENTES EN EL PUERTO "+server.getPuertox());
           Socket st= server.getServidor().accept();
           //si entra get output y si entra get input
           out_var1=new DataOutputStream(st.getOutputStream());
           out_var1.writeUTF(diccionario.getOrDefault("bienvenido", ""));
           
            System.out.println(st.getInetAddress()+" CLIENTE CONECTADO");
            inp_respuesta =new DataInputStream(st.getInputStream());
            
            //System.out.println(inp_lenguaje.readUTF());
            boolean repite=true;
            while(repite=true){
            String key_diccionario=String.valueOf(inp_respuesta.readUTF());
            String respuesta="";
            
                for (Map.Entry<String, String> entry : diccionario.entrySet()) {
                    if (entry.getKey().equalsIgnoreCase(key_diccionario)) {
                         respuesta=entry.getValue();
                         break;
                    }
                    
                }
            
            
                if (key_diccionario.equals("adios")) {
                    System.out.println("FINALIZADO EN SERVIDOR");
                    repite=false;
                }
            System.out.println(">>>>>"+respuesta);
            out_var1.writeUTF(respuesta);
            }
            
            
            //System.out.println(descripcion);
            
            
            
            
            
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //////////////////////////////////////////////////////////////////////
}
