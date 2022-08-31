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

import com.restapi.gestionbancaire.dao.RetraitDAO;
import com.restapi.gestionbancaire.model.Retrait;

@Path("/retrait")
public class RetraitResource {

	//create retrait
	 	@POST
	 	@Path("/create_retrait")
	    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	 	@Produces(MediaType.APPLICATION_JSON)
	    public int createRetrait(@FormParam("cheque_ret") String cheque_ret,@FormParam("ncompte_ret") int
	          ncompte_ret,@FormParam("montant_ret") int montant_ret,@FormParam("date_ret") Date date_ret)      
	        {
	        Retrait r = new Retrait();
	        r.setCheque_ret(cheque_ret);
	        r.setNcompte_ret(ncompte_ret);
	        r.setMontant_ret(montant_ret);
	        r.setDate_ret(date_ret);
	        int clientResponse = RetraitDAO.createRetrait(r);
	        return clientResponse;
	    }
	 
	    //get all retrait
	    @GET
	    @Produces(MediaType.APPLICATION_JSON)
	    public List<Retrait> getAllRetrait() {
	    	List<Retrait> retraitList = RetraitDAO.getAllRetrait();
	    	return retraitList;  
	    }
	    
	    //delete retrait
	 	 @DELETE
	 	 @Path("/delete_retrait")
	     @Produces(MediaType.APPLICATION_JSON)
	     @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	     public int deleteRetrait(@FormParam("id_ret") int id_ret) {
	     	Retrait r= RetraitDAO.getRetraitById(id_ret);
	         int userResponse = RetraitDAO.deleteRetrait(r);
	         return userResponse;
	     }
}
