package com.restapi.gestionbancaire.resource;

import java.sql.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.restapi.gestionbancaire.dao.TransfertDAO;
import com.restapi.gestionbancaire.model.Transfert;


@Path("/transfert")
public class TransfertResource {

	//create transfert
	 	@POST
	 	@Path("/create_transfert")
	    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	 	@Produces(MediaType.APPLICATION_JSON)
	    public int createTransfert(@FormParam("ncompte_trans") int ncompte_trans,@FormParam("ncompte_transb") int ncompte_transb,@FormParam("montant_trans") int montant_trans,@FormParam("date_trans") Date date_trans)      
	        {
	        Transfert t = new Transfert();
	        t.setNcompte_trans(ncompte_trans);
	        t.setNcompte_transb(ncompte_transb);
	        t.setMontant_trans(montant_trans);
	        t.setDate_trans(date_trans);
	        int transfertResponse = TransfertDAO.createTransfert(t);
	        return transfertResponse;
	    }
	 	
	 	//get all transfert
	    @GET
	    @Produces(MediaType.APPLICATION_JSON)
	    public List<Transfert> getAllTransfert() {
	    	List<Transfert> list = TransfertDAO.getAllTransfert();
	    	return list;  
	    }
	    
	    //delete transfert
	 	 @DELETE
	 	 @Path("/delete_transfert")
	     @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	 	 @Produces(MediaType.APPLICATION_JSON)
	     public int deleteTransfert(@FormParam("id_trans") int id_trans) {
	     	Transfert t = TransfertDAO.getTransfertById(id_trans);
	         int userResponse = TransfertDAO.deleteTransfert(t);
	         return userResponse;
	     }
}
