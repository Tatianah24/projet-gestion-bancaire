package com.restapi.gestionbancaire.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.restapi.gestionbancaire.dao.ClientDAO;
import com.restapi.gestionbancaire.model.Client;

@Path("/client")
public class ClientResource {

	//get_all_client
	 	@GET
	    @Produces(MediaType.APPLICATION_JSON)
	    public List<Client> getAllClient(@QueryParam("nom") String nom) {
	 		List<Client> clientList = new ArrayList<Client>();
	 		if (nom != null) {
	 			clientList = ClientDAO.getClientByNom(nom);
	 		}
	 		else {
	 			clientList = ClientDAO.getAllClient();
	 		}
	    	return clientList; 
	    }
	 	
	 //create client
	 	@POST
	 	@Path ("/create_client")
	 	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	 	@Produces(MediaType.APPLICATION_JSON)
	    public int createClient(@FormParam("nom") String nom,@FormParam("solde") int solde)      
	        {
	        Client cli = new Client();
	        cli.setNom(nom);
	        cli.setSolde(solde);
	        int clientResponse = ClientDAO.createClient(cli);
	        return clientResponse;
	    }
	 	
	 //update client
	 	@PUT
	 	@Path("/update_client")
	    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	 	@Produces(MediaType.APPLICATION_JSON)
	    public int updateClient(@FormParam("ncompte") int ncompte,@FormParam("nom") String
	                                                         nom,@FormParam("solde") int solde)
	       {
	        Client client = ClientDAO.getClientById(ncompte);
	        client.setNom(nom);
	        client.setSolde(solde);
	        int userResponse = ClientDAO.updateClient(client);
	        return userResponse;
	    }
	 	
	 //delete client
	 	 @DELETE
	 	 @Path("/delete_client")
	     @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	 	 @Produces(MediaType.APPLICATION_JSON)
	     public int deleteClient(@FormParam("ncompte") int ncompte) {
	     	Client client=ClientDAO.getClientById(ncompte);
	         int userResponse = ClientDAO.deleteClient(client);
	         return userResponse;
	     }
}
