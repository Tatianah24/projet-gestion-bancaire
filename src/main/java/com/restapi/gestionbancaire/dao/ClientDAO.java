package com.restapi.gestionbancaire.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.restapi.gestionbancaire.database.Connexion;
import com.restapi.gestionbancaire.model.Client;


public class ClientDAO {

	//get all client
	public static List<Client> getAllClient(){
		List<Client> list=new ArrayList<Client>();  
        
        try{  
            Connection con=Connexion.getConnection();  
            PreparedStatement ps=con.prepareStatement("select * from client");  
            ResultSet rs=ps.executeQuery();  
            while(rs.next()){  
                Client u=new Client();
                u.setNcompte(rs.getInt("ncompte"));
                u.setNom(rs.getString("nom"));  
                u.setSolde(rs.getInt("solde"));
                list.add(u);  
            }  
        }catch(Exception e){System.out.println("Error read database"+e);}  
        return list; 
	}
	
	//create client
	public static int createClient(Client c) {
		int status = 0;
		try {
			Connection con = Connexion.getConnection();
			PreparedStatement ps = con.prepareStatement("insert into client (nom,solde) values (?,?);");
			ps.setString(1, c.getNom());
			ps.setInt(2, c.getSolde());
			status = ps.executeUpdate();
		}catch(Exception e) {
			System.out.println("CREATE CLIENT" + e);
		}
		return status;
	}
	
	//update client
 	public static int updateClient(Client c) {
		int status=0;
		try {
			Connection con = Connexion.getConnection();
			PreparedStatement ps = con.prepareStatement("update client set nom=?, solde=? where ncompte=?");
			ps.setString(1, c.getNom());
			ps.setFloat(2, c.getSolde());
			ps.setInt(3, c.getNcompte());
			status=ps.executeUpdate();
		}catch(Exception e){
			System.out.println("UPDATE CLIENT"+ e);
		}
		return status;
	}
 	
 	//get client by id
 	public static Client getClientById(int ncompte) {
		Client c=null;
		try {
			Connection con = Connexion.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from client where ncompte=?");
			ps.setInt(1, ncompte);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				c = new Client();
				c.setNcompte(rs.getInt("ncompte"));
				c.setNom(rs.getString("nom"));
				c.setSolde(rs.getInt("solde"));	
			}
		}catch(Exception e) {
			System.out.println("GET BY ID CLIENT"+ e);
		}
		return c;
	}
 	
 	//recherche client par nom
 	public static List<Client> getClientByNom(String nom){
		List<Client> list=new ArrayList<Client>();  
        
        try{  
            Connection con=Connexion.getConnection();  
            String query = "%"+nom+"%";
            PreparedStatement ps=con.prepareStatement("select * from client where nom like ?");  
            ps.setString(1, query);
            ResultSet rs=ps.executeQuery();  
            while(rs.next()){  
                Client u=new Client();
                u.setNcompte(rs.getInt("ncompte"));
                u.setNom(rs.getString("nom"));  
                u.setSolde(rs.getInt("solde"));
                list.add(u);  
            }  
        }catch(Exception e){System.out.println("Error RECHERCHE "+ e);}  
        return list; 
	}
 	
 	//delete client
 	public static int deleteClient(Client c){  
	    int status=0;  
	    try{  
	        Connection con=Connexion.getConnection();  
	        PreparedStatement ps=con.prepareStatement("delete from client where ncompte=?");  
	        ps.setInt(1,c.getNcompte());  
	        status=ps.executeUpdate();  
	    }catch(Exception e){
	    	System.out.println("DELETE CLIENT"+ e);
	    	}  
	  
	    return status;  
	} 
}
