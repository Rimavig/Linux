/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package microservicio;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.spy.memcached.MemcachedClient;
import org.apache.thrift.TException;
import twitter4j.TwitterException;
/**
 *
 * @author RICHARD
 */

public class Conexion {
   
   
   private  Connection cnx = null;
   InetSocketAddress[] servers = new InetSocketAddress[]{ new InetSocketAddress("127.0.0.1", 11211)};
   MemcachedClient mc;

    public Conexion() {
       try {
           this.mc = new MemcachedClient(servers);
       } catch (IOException ex) {
           System.out.println("");;
       }
    }


   public  LinkedList<Sismo> obtener()  {
     
       try {
            Class.forName("com.mysql.jdbc.Driver");
            //Conexion con la base de datos, usuario y clave
            cnx = DriverManager.getConnection("jdbc:mysql://13.58.55.118/prueba", "root", "centos");
            //se establece la conexion
            Statement st= cnx.createStatement();
            System.out.println("Se establecio con exito la conexion");
            //Parametros para consulta de la base de datos 
            PreparedStatement consulta = cnx.prepareStatement("SELECT id,sensor,fecha,hora FROM sismo" );
            //contiene todo el resultado de la consulta 
            ResultSet resultado = consulta.executeQuery();
             LinkedList<Sismo> lista =new  LinkedList<Sismo>();
            Sismo not;
           
            //recorremos el resultado de la consulta de la base de datos
            while(resultado.next()){
                not=new Sismo();
                //guardamos en una Noticia cada parametro 
                not.setId(resultado.getInt("id"));
                not.setSensor(resultado.getString("sensor"));
                not.setFecha(resultado.getDate("fecha"));
                not.setHora(resultado.getTime("hora"));
                lista.add(not);
            }
            LinkedList<Sismo> lista1 =new  LinkedList<Sismo>();
            LinkedList<Sismo> lista2 =new  LinkedList<Sismo>();
            Map<String,LinkedList<Sismo>> mapa =new HashMap();
//             mc.delete("lista");
//             mc.delete("mapa");
           if (mc.get("lista")==(null)){
               mc.set("lista",36999, lista);
                for(Sismo sismo: lista){

                    if (mapa.isEmpty()){
                        lista1.add(sismo);
                        mapa.put(sismo.getHora()+"", lista1);
                    }else{
                       if(mapa.containsKey(sismo.getHora()+"")){
                           lista1=mapa.get(sismo.getHora()+"");
                           lista1.add(sismo);
                           mapa.remove(sismo.getHora()+"");
                           mapa.put(sismo.getHora()+"", lista1);
                       } else{
                         lista1 =new  LinkedList<Sismo>();
                         lista1.add(sismo);  
                         mapa.put(sismo.getHora()+"", lista1); 
                       }
                    }
                } 
                mc.set("mapa", 36999, mapa);
                Iterator it = mapa.keySet().iterator();      
                while(it.hasNext()){
                  String key = (String) it.next();
                  if(mapa.get(key).size()==3){

                      lista2.add(mapa.get(key).get(0));
                  }
                }
                mc.shutdown();
                return lista2;
           }else{
               lista1 = (LinkedList<Sismo>)mc.get("lista");
               System.out.println(lista.size());
               System.out.println(lista1.size());
               if(lista.size()==(lista1.size())){
                   mapa=(Map<String, LinkedList<Sismo>>) mc.get("mapa");
                   System.out.println(mapa);
                   Iterator it = mapa.keySet().iterator();
                    while(it.hasNext()){
                      String key = (String) it.next();
                      if(mapa.get(key).size()==3){
                          lista2.add(mapa.get(key).get(0));
                      }
                    }
                    mc.shutdown();
                    return lista2;
               }else if(lista.size()<(lista1.size())){
                   mc.set("lista", 36999, lista);
                   lista1 =new  LinkedList<Sismo>();
                   mapa =new HashMap();
                   for(Sismo sismo: lista){
                    if (mapa.isEmpty()){
                        lista1.add(sismo);
                        mapa.put(sismo.getHora()+"", lista1);
                    }else{
                       if(mapa.containsKey(sismo.getHora()+"")){
                           lista1=mapa.get(sismo.getHora()+"");
                           lista1.add(sismo);
                           mapa.remove(sismo.getHora()+"");
                           mapa.put(sismo.getHora()+"", lista1);
                       } else{
                         lista1 =new  LinkedList<Sismo>();
                         lista1.add(sismo);  
                         mapa.put(sismo.getHora()+"", lista1); 
                       }
                        }
                    } 
                    mc.set("mapa", 36999, mapa);
                    Iterator it = mapa.keySet().iterator();      
                    while(it.hasNext()){
                      String key = (String) it.next();
                      if(mapa.get(key).size()==3){

                          lista2.add(mapa.get(key).get(0));
                      }
                    }
                    mc.shutdown();
                  return lista2;
               }else { 
                   lista1 = (LinkedList<Sismo>)mc.get("lista");
                   mapa=(Map<String, LinkedList<Sismo>>) mc.get("mapa");
                   
                   for(Sismo sismo: lista){
                        if(lista1.contains(sismo)){ 
                            System.out.println("ssadas");
                        }else{
           
                            if (mapa.isEmpty()){
                                lista1 =new  LinkedList<Sismo>();
                                lista1.add(sismo);
                                mapa.put(sismo.getHora()+"", lista1);
                            }else{                                
                               if(mapa.containsKey(sismo.getHora()+"")){
                                   lista1=mapa.get(sismo.getHora()+"");
                                    lista1.add(sismo);
                                    mapa.remove(sismo.getHora()+"");
                                    mapa.put(sismo.getHora()+"", lista1);
                               } else{
                                 lista1 =new  LinkedList<Sismo>();
                                 lista1.add(sismo);  
                                 mapa.put(sismo.getHora()+"", lista1); 
                               }
                            } 
                        }
                        
                    } 
                   
                   mc.set("lista", 36999, lista);
                   mc.set("mapa", 36999, mapa);
                    Iterator it = mapa.keySet().iterator();
                    while(it.hasNext()){
                      String key = (String) it.next();
                      if(mapa.get(key).size()==3){
                          lista2.add(mapa.get(key).get(0));
                      }
                    }
                    mc.shutdown();
                     return lista2;
               }
               
               
           }
            
            
            
         } catch (Exception ex) {
             System.out.println("Error");
         }
       return null;
   }
}
