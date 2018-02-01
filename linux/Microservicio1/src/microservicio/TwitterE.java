/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package microservicio;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Twitter;


/**
 *
 * @author RICHARD
 */
public class TwitterE  {
/**
 *Conexion a twitter con usuario y contraseña
 *Twitteando mensaje de alerta sismica 
 * 
 */  
    public void Tweet(String hora) throws TwitterException{
       Twitter twitter;
        ConfigurationBuilder cb = new ConfigurationBuilder();

        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("5i8LHKhtr7Dbw96JCj")
                .setOAuthConsumerSecret("6a6ZDhQUlZztTCGQOBaJXjenWRtj8BXvklFJIRh1Z")
                .setOAuthAccessToken("234858708-qddkuAlF0msbquZDBoKpWi6")
                .setOAuthAccessTokenSecret("Hh7wltPaiao5jHPcHO2OWTTyI");
        
        TwitterFactory tf = new TwitterFactory(cb.build());
         twitter=(Twitter) tf.getInstance();
        Paging pagina = new Paging();

        Status tweetEscrito = twitter.updateStatus("ALERTA SISMO!!!  MEJORAMIENTO ES TODO\nPERO AHORA SALVA TU VIDA\n"+hora);      
 
    }
}
