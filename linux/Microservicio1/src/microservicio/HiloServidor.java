/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package microservicio;

import Thrift.Servidor;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;

/**
 *
 * @author Richard
 */
public class HiloServidor implements Runnable{
    @Override
    public void run() {
        while (true) {            
            try {
            TServerSocket serverTransport = new TServerSocket(7911); //Conexion al puerto 7911 del servidor
            Servidor.Processor processor = new Servidor.Processor (new Microservicio()); //Procesos levantados para generar registros usando hilos
            TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor)); 
            System.err.println("Servidor en escucha puerto 7915...");
            server.serve();
            } catch (Exception e) {
               
            }
        }
    }
    
}
