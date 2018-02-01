/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package microservicio;

import Thrift.Servidor;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.thrift.TException;
import twitter4j.Twitter;
import twitter4j.TwitterException;






/**
 *
 * @author RICHARD
 */
public class Microservicio implements Servidor.Iface {

    /**
     * @param args the command line arguments
     */
    
    private static LinkedList<Sismo> noticias;
    public static void main(String[] args)  {
        
        HiloServidor hs = new HiloServidor();
        Thread t = new Thread(hs);
        t.start();
    }

    @Override
    public String top10(String ho) {
       Date date = new Date();   //Creación de objeto date de tipo Date 
       DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");    //Con este objeto se setea el formato de la hora deseada
       
       
        Conexion con =new Conexion(); 
        noticias=con.obtener();
        String top="";
        int acum=0;
        for(Sismo noticia: noticias){
            if(noticia.getHora().getMinutes()==date.getMinutes()){
                 try {      //Se aplica un try catch para validar credenciales
                    TwitterE twitter =new TwitterE() {};  //Crea un objeto tipo Twitter
                    twitter.Tweet(date+"");      //Llama a la funcion Tweet y pasa el parámetro date
                    Email email =new Email();    //Crea un objeto tipo Email
                    email.enviarConGMail();      //Llama al metodo envirConGmail
                } catch (TwitterException ex) {
                     System.out.println("No se puede hacer el tweet");
                }  
            }
            acum=acum+1;
            top=top+acum+noticia+";"; //Ingresa los registros de la base de datos
        }
        return top;    // Retorno de la lista enlazada   
    }

    @Override
    public String optenerLista() throws TException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
