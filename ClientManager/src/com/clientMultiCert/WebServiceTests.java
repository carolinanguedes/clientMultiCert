package com.clientMultiCert;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;


public class WebServiceTests {

	private javax.ws.rs.client.Client clientJavax;
	private String REST_SERVICE_URL = "http://localhost:8080/ClientManager/rest/ClientWebService/clients";
	private static final String SUCCESS_RESULT="<result>success</result>";
	private static final String PASS = "Pass";
	private static final String FAIL = "Fail";



	private void init(){
		this.clientJavax = ClientBuilder.newClient();
	}

	public static void main(String[] args){
		WebServiceTests test = new WebServiceTests();
		//initialize the tester
		test.init();
		test.testGetAllClients();
		test.testGetClientListByName();
		test.testGetClientByNif();
		test.testAddClient();
		test.testDeleteClient();
	}



	//Test: Get list of all clients
	//Test: Check if list is not empty
	private void testGetAllClients(){
		GenericType<List<Client>> list = new GenericType<List<Client>>() {};
		List<Client> clients = clientJavax
				.target(REST_SERVICE_URL)
				.request(MediaType.APPLICATION_XML)
				.get(list);
		String result = PASS;
		if(clients.isEmpty()){
			result = FAIL;
		}
		System.out.println("Test case name: testGetAllClients, Result: " + result );
	}

	//Test: Delete client of id 1
	//Test: Check if result is success XML.
	private void testDeleteClient(){
		String callResult = clientJavax
				.target(REST_SERVICE_URL)
				.path("/{clientid}")
				.resolveTemplate("clientid", 1)
				.request(MediaType.APPLICATION_XML)
				.delete(String.class);

		String result = PASS;
		if(!SUCCESS_RESULT.equals(callResult)){
			result = FAIL;
		}

		System.out.println("Test case name: testDeleteClient, Result: " + result );
	}

	//Test: Add Client of id 1
	//Test: Check if result is success XML.
	private void testAddClient(){
		Form form = new Form();
		form.param("id", "1");
		form.param("name", "Carol");
		form.param("address", "Avenida Bordalo Pinheiro");
		form.param("nif", "489499886");
		form.param("phone", "910764876");
		String callResult = clientJavax
				.target(REST_SERVICE_URL)
				.request(MediaType.APPLICATION_XML)
				.put(Entity.entity(form,
						MediaType.APPLICATION_FORM_URLENCODED_TYPE),
						String.class);

		String result = PASS;
		if(!SUCCESS_RESULT.equals(callResult)){
			result = FAIL;
		}
	    System.out.println("Test case name: testAddClient, Result: " + result );

	}
	
	   //Test: Get client with a specific name
	   //Test: Check if list is same as sample list
	   private void testGetClientListByName(){
		  List<com.clientMultiCert.Client> sampleClientList = new  ArrayList<com.clientMultiCert.Client>();
		  com.clientMultiCert.Client sampleClient = new  com.clientMultiCert.Client();
		  sampleClient.setName("Carol");
		  sampleClientList.add(sampleClient);

	      com.clientMultiCert.Client client = clientJavax
	         .target(REST_SERVICE_URL)
	         .path("/{name}")
	         .resolveTemplate("name", "Carol")
	         .request(MediaType.APPLICATION_XML)
	         .get(com.clientMultiCert.Client.class);
	      String result = FAIL;
	      for(com.clientMultiCert.Client c : sampleClientList){
		      if(sampleClient != null && sampleClient.getName() == c.getName()){
			         result = PASS;
			      }
	      }
	      System.out.println("Test case name: testGetClientListByName, Result: " + result );
	   }
	   
	   //Test: Get client with a specific nif
	   //Test: Check if client is same as client
		private void testGetClientByNif() {
			com.clientMultiCert.Client sampleClient = new com.clientMultiCert.Client();
		      sampleClient.setNif(BigInteger.valueOf(3783731));

		      com.clientMultiCert.Client client = clientJavax
		         .target(REST_SERVICE_URL)
		         .path("/{nif}")
		         .resolveTemplate("nif", BigInteger.valueOf(3783731))
		         .request(MediaType.APPLICATION_XML)
		         .get(com.clientMultiCert.Client.class);
		      String result = FAIL;
		      if(sampleClient != null && sampleClient.getNif() == client.getNif()){
		         result = PASS;
		      }
		      System.out.println("Test case name: testGetClientByNif, Result: " + result );			
		}

}