package com.clientMultiCert;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/ClientWebService")
public class ClientWebService {

   ClientDao clientDao = new ClientDao();
   
   @GET
   @Path("/clients")
   @Produces(MediaType.APPLICATION_XML)
   public List<Client> getClients(){
      return clientDao.getAllClients();
   }	
   
   @GET
   @Path("/clients/{clientName}")
   @Produces(MediaType.APPLICATION_XML)
   public List<Client> getAllClientsByName(@FormParam("clientName") String name){
      return clientDao.getAllClientsByName(name);
   }	
   
   @GET
   @Path("/clients/{nif}")
   @Produces(MediaType.APPLICATION_XML)
   public Client getUser(@PathParam("nif") BigInteger nif){
      return clientDao.getClientByNIF(nif);
   }
   
   @PUT
   @Path("/clients")
   @Produces(MediaType.APPLICATION_XML)
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
   public void createUser(@FormParam("id") int id,
      @FormParam("name") String name,
      @FormParam("address") String address,
      @FormParam("nif") BigInteger nif,
      @FormParam("phone") String phone,     
      @Context HttpServletResponse servletResponse) throws IOException{
      Client newClient = new Client(id, address, phone, nif, phone);
      clientDao.addClient(newClient);

   }
   
   @DELETE
   @Path("/clients/{clientid}")
   @Produces(MediaType.APPLICATION_XML)
   public void deleteUser(@PathParam("clientid") int clientid){
      clientDao.deleteClient(clientid);
   }
}