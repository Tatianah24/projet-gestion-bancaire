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

import com.restapi.gestionbancaire.dao.VersementDAO;
import com.restapi.gestionbancaire.model.Versement;

@Path("/versement")
public class VersementResource {

	//create versement
 	@POST
 	@Path("/create_versement")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
 	@Produces(MediaType.APPLICATION_JSON)
    public int createVersement(@FormParam("ncompte_vers") int ncompte_vers,@FormParam("montant_vers") int montant_vers,@FormParam("date_vers") Date date_vers)      
        {
        Versement v = new Versement();
        v.setNcompte_vers(ncompte_vers);
        v.setMontant_vers(montant_vers);
        v.setDate_vers(date_vers);
        int versementResponse = VersementDAO.createVersement(v);
        return versementResponse;
    }
 
    //get all versement
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public List<Versement> getAllVersement() {
    	List<Versement> list = VersementDAO.getAllVersement();
    	return list;  
    }
    
    //delete versement
 	 @DELETE
 	 @Path("/delete_vers")
     @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
 	 @Produces(MediaType.APPLICATION_JSON)
     public int deleteVersement(@FormParam("id_vers") int id_vers) {
     	Versement v= VersementDAO.getVersementById(id_vers);
         int userResponse = VersementDAO.deleteVersement(v);
         return userResponse;
     }
}
