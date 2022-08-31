package com.restapi.gestionbancaire.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.restapi.gestionbancaire.database.Connexion;
import com.restapi.gestionbancaire.model.Client;
import com.restapi.gestionbancaire.model.Retrait;

public class RetraitDAO {

	//create retrait
	public static int createRetrait(Retrait r) {
		int status = 0;
		try {
			Connection con = Connexion.getConnection();
			PreparedStatement ps = con.prepareStatement("insert into retrait (ncompte_ret,cheque_ret,montant_ret,date_ret) values (?,?,?,?);");
			ps.setInt(1, r.getNcompte_ret());
			ps.setString(2, r.getCheque_ret());
			ps.setInt(3, r.getMontant_ret());
			ps.setDate(4, r.getDate_ret());
			status = ps.executeUpdate();
			
			PreparedStatement ps1 = con.prepareStatement("select * from client where ncompte=?");
			ps1.setInt(1, r.getNcompte_ret());
			Client c = new Client();
			ResultSet rs = ps1.executeQuery();
			while(rs.next()) {
				c.setNcompte(rs.getInt("ncompte"));
				c.setNom(rs.getString("nom"));
				c.setSolde(rs.getInt("solde"));
			}
			
			int solde = c.getSolde();
			int nouv_solde = solde - r.getMontant_ret();
			
			PreparedStatement ps2 = con.prepareStatement("update client set solde=? where ncompte=?");
			ps2.setFloat(1, nouv_solde);
			ps2.setInt(2, c.getNcompte());
			status=ps2.executeUpdate();
		}catch(Exception e) {
			System.out.println(e);
		}
		return status;
	}
	
	//get retrait by id
	 	public static Retrait getRetraitById(int id_ret) {
			Retrait r=null;
			try {
				Connection con = Connexion.getConnection();
				PreparedStatement ps = con.prepareStatement("select * from retrait where id_ret=?");
				ps.setInt(1, id_ret);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					r = new Retrait();
					r.setId_ret(rs.getInt("id_ret"));
					r.setNcompte_ret(rs.getInt("ncompte_ret"));
					r.setCheque_ret(rs.getString("cheque_ret"));
					r.setMontant_ret(rs.getInt("montant_ret"));
					r.setDate_ret(rs.getDate("date_ret"));
				}
				System.out.println("GET BY ID"+r);
			}catch(Exception e) {
				System.out.println("GET BY ID RETRAIT"+ e);
			}
			return r;
		}
	
	//delete retrait
	public static int deleteRetrait(Retrait r){  
	    int status=0;  
	    try{  
	        Connection con=Connexion.getConnection();  
	        PreparedStatement ps=con.prepareStatement("delete from retrait where id_ret=?");  
	        ps.setInt(1,r.getId_ret());  
	        status=ps.executeUpdate();  
	    }catch(Exception e){
	    	System.out.println("DELETE RETRAIT"+ e);
	    	}  
	    return status;  
	} 
	
	//get all retrait
	public static List<Retrait> getAllRetrait(){
		List<Retrait> list=new ArrayList<Retrait>();  
        try{  
            Connection con=Connexion.getConnection();  
            PreparedStatement ps=con.prepareStatement("select * from retrait");  
            ResultSet rs=ps.executeQuery();  
            while(rs.next()){  
                Retrait r=new Retrait();
                r.setCheque_ret(rs.getString("cheque_ret"));
                r.setNcompte_ret(rs.getInt("ncompte_ret"));
                r.setMontant_ret(rs.getInt("montant_ret"));  
                r.setDate_ret(rs.getDate("date_ret"));
                list.add(r);  
            }  
        }catch(Exception e){
        	System.out.println("Error read database"+e);
        	}  
        return list; 
	}
}
