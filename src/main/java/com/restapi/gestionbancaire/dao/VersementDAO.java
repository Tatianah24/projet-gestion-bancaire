package com.restapi.gestionbancaire.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.restapi.gestionbancaire.database.Connexion;
import com.restapi.gestionbancaire.model.Client;
import com.restapi.gestionbancaire.model.Versement;

public class VersementDAO {

	//create versement
		public static int createVersement(Versement v) {
			int status = 0;
			try {
				Connection con = Connexion.getConnection();
				PreparedStatement ps = con.prepareStatement("insert into versement (ncompte_vers,montant_vers,date_vers) values (?,?,?);");
				ps.setInt(1, v.getNcompte_vers());
				ps.setInt(2, v.getMontant_vers());
				ps.setDate(3, v.getDate_vers());
				status = ps.executeUpdate();
				
				PreparedStatement ps1 = con.prepareStatement("select * from client where ncompte=?");
				ps1.setInt(1, v.getNcompte_vers());
				Client c = new Client();
				ResultSet rs = ps1.executeQuery();
				while(rs.next()) {
					c.setNcompte(rs.getInt("ncompte"));
					c.setNom(rs.getString("nom"));
					c.setSolde(rs.getInt("solde"));
				}
				
				int solde = c.getSolde();
				int nouv_solde = solde + v.getMontant_vers();
				
				PreparedStatement ps2 = con.prepareStatement("update client set solde=? where ncompte=?");
				ps2.setFloat(1, nouv_solde);
				ps2.setInt(2, c.getNcompte());
				status=ps2.executeUpdate();
			}catch(Exception e) {
				System.out.println(e);
			}
			return status;
		}
		
		//get versement by id
		 	public static Versement getVersementById(int id_vers) {
				Versement v=null;
				try {
					Connection con = Connexion.getConnection();
					PreparedStatement ps = con.prepareStatement("select * from versement where id_vers=?");
					ps.setInt(1, id_vers);
					ResultSet rs = ps.executeQuery();
					while(rs.next()) {
						v = new Versement();
						v.setId_vers(rs.getInt("id_vers"));
						v.setNcompte_vers(rs.getInt("ncompte_vers"));
						v.setMontant_vers(rs.getInt("montant_vers"));
						v.setDate_vers(rs.getDate("date_vers"));
					}
					System.out.println("GET BY ID"+ v);
				}catch(Exception e) {
					System.out.println("GET BY ID VERSEMENT"+ e);
				}
				return v;
			}
		
		//delete versement
		public static int deleteVersement( Versement v){  
		    int status=0;  
		    try{  
		        Connection con=Connexion.getConnection();  
		        PreparedStatement ps=con.prepareStatement("delete from versement where id_vers=?");  
		        ps.setInt(1,v.getId_vers());  
		        status=ps.executeUpdate();  
		    }catch(Exception e){
		    	System.out.println("DELETE VERSEMENT"+ e);
		    	}  
		    return status;  
		} 
		
		//get all versement
		public static List<Versement> getAllVersement(){
			List<Versement> list=new ArrayList<Versement>();  
	        try{  
	            Connection con=Connexion.getConnection();  
	            PreparedStatement ps=con.prepareStatement("select * from versement");  
	            ResultSet rs=ps.executeQuery();  
	            while(rs.next()){  
	                Versement v=new Versement();
	                v.setNcompte_vers(rs.getInt("ncompte_vers"));
	                v.setMontant_vers(rs.getInt("montant_vers"));  
	                v.setDate_vers(rs.getDate("date_vers"));
	                list.add(v);  
	            }  
	        }catch(Exception e){
	        	System.out.println("Error read database Versement"+ e);
	        	}  
	        return list; 
		}
}
