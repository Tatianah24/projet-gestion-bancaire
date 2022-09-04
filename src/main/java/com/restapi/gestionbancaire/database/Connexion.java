package com.restapi.gestionbancaire.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connexion {

	public static Connection getConnection(){  
	    Connection con=null;  
	    try{  
	        Class.forName("org.postgresql.Driver");  
	        con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/bancaire","postgres","maharo19");  
	    }
	    catch(Exception e){
	    	System.out.println("DATABASE "+ e);
	    	}  
	    return con;  
	} 
}
