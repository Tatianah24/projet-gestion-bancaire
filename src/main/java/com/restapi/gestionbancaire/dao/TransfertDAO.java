package com.restapi.gestionbancaire.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.restapi.gestionbancaire.database.Connexion;
import com.restapi.gestionbancaire.model.Client;
import com.restapi.gestionbancaire.model.Transfert;


public class TransfertDAO {

	//create transfert
			public static int createTransfert(Transfert t) {
				int status = 0;
				try {
					Connection con = Connexion.getConnection();
					PreparedStatement ps = con.prepareStatement("insert into transfert (ncompte_trans,ncompte_transb,montant_trans,date_trans) values (?,?,?,?);");
					ps.setInt(1, t.getNcompte_trans());
					ps.setInt(2, t.getNcompte_transb());
					ps.setInt(3, t.getMontant_trans());
					ps.setDate(4, t.getDate_trans());
					status = ps.executeUpdate();
					
					PreparedStatement ps1 = con.prepareStatement("select * from client where ncompte=?");
					ps1.setInt(1, t.getNcompte_trans());
					Client c = new Client();
					ResultSet rs = ps1.executeQuery();
					while(rs.next()) {
						c.setNcompte(rs.getInt("ncompte"));
						c.setNom(rs.getString("nom"));
						c.setSolde(rs.getInt("solde"));
					}
					
					//mise à jour du solde du client qui transfère 
					int solde = c.getSolde();
					int nouv_solde = solde - t.getMontant_trans();
					
					PreparedStatement ps2 = con.prepareStatement("update client set solde=? where ncompte=?");
					ps2.setFloat(1, nouv_solde);
					ps2.setInt(2, c.getNcompte());
					status=ps2.executeUpdate();
					
					//mise à jour du solde de l'autre client
					PreparedStatement ps3 = con.prepareStatement("select * from client where ncompte=?");
					ps3.setInt(1, t.getNcompte_transb());
					Client c2 = new Client();
					ResultSet rs1 = ps3.executeQuery();
					while(rs1.next()) {
						c2.setNcompte(rs1.getInt("ncompte"));
						c2.setNom(rs1.getString("nom"));
						c2.setSolde(rs1.getInt("solde"));
					}
					
					int solde_transB = c2.getSolde();
					int nouv_solde_transB = solde_transB + t.getMontant_trans();
					
					PreparedStatement ps4 = con.prepareStatement("update client set solde=? where ncompte=?");
					ps4.setFloat(1, nouv_solde_transB);
					ps4.setInt(2, c2.getNcompte());
					status=ps4.executeUpdate();
					
					}catch(Exception e) {
						System.out.println("CREATE TRANSFERT " + e);
					}	
				return status;
			}
			
			//get transfert by id
		 	public static Transfert getTransfertById(int id_trans) {
				Transfert t = null;
				try {
					Connection con = Connexion.getConnection();
					PreparedStatement ps = con.prepareStatement("select * from transfert where id_trans=?");
					ps.setInt(1, id_trans);
					ResultSet rs = ps.executeQuery();
					while(rs.next()) {
						t = new Transfert();
						t.setId_trans(rs.getInt("id_trans"));
						t.setNcompte_trans(rs.getInt("ncompte_trans"));
						t.setNcompte_transb(rs.getInt("ncompte_transb"));
						t.setMontant_trans(rs.getInt("montant_trans"));
						t.setDate_trans(rs.getDate("date_trans"));
					}
					System.out.println("GET BY ID"+ t);
				}catch(Exception e) {
					System.out.println("GET BY ID TRANSFERT"+ e);
				}
				return t;
			}
		
		//delete transfert
		public static int deleteTransfert( Transfert t){  
		    int status=0;  
		    try{  
		        Connection con=Connexion.getConnection();  
		        PreparedStatement ps=con.prepareStatement("delete from transfert where id_trans=?");  
		        ps.setInt(1,t.getId_trans());  
		        status=ps.executeUpdate();  
		    }catch(Exception e){
		    	System.out.println("DELETE TRANSFERT"+ e);
		    	}  
		    return status;  
		} 
		
		//get all transfert
		public static List<Transfert> getAllTransfert(){
			List<Transfert> list=new ArrayList<Transfert>();  
	        try{  
	            Connection con=Connexion.getConnection();  
	            PreparedStatement ps=con.prepareStatement("select * from transfert");  
	            ResultSet rs=ps.executeQuery();  
	            while(rs.next()){  
	                Transfert t = new Transfert();
	                t.setNcompte_trans(rs.getInt("ncompte_trans"));
	                t.setNcompte_transb(rs.getInt("ncompte_transb"));
	                t.setMontant_trans(rs.getInt("montant_trans"));  
	                t.setDate_trans(rs.getDate("date_trans"));
	                list.add(t);  
	            }  
	        }catch(Exception e){
	        	System.out.println("Error read database Transfert"+ e);
	        	}  
	        return list; 
		}
}
