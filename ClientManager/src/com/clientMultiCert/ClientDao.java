package com.clientMultiCert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class ClientDao {

	/**
	 * Add a new Client.                           
	 *
	 * @param  newClient the client to add to the client list.          
	 */
	public void addClient(Client newClient){
		List<Client> clientList = getAllClients();
		if(!clientAlreadyExists(newClient)){
			clientList.add(newClient);
			saveClientList(clientList);
		}
	} 
	
	/**
	 * Delete a Client.                           
	 *
	 * @param  clientId the client id to delete from the client list.          
	 */
	public void deleteClient(int clientId){
		List<Client> clientList = getAllClients();
		for(Client c: clientList){
			if(clientId==c.getId()){
				clientList.remove(c);
				saveClientList(clientList);
				break;
			}
		}	
	} 

	/**
	 * Very if a client already exists.                          
	 *
	 * @param  client the client to verify if exists.          
	 * @return true if this client already exists in the client list.
	 */
	public boolean clientAlreadyExists(Client client){
		List<Client> clientList = getAllClients();
		boolean clientAlreadyExists = false;
		for(Client c: clientList){
			if(c.getId() == client.getId()){
				clientAlreadyExists = true;
				break;
			}
		}		
		return clientAlreadyExists;
	}

	
	
	/**
	 * Save a new client List.                         
	 *
	 * @param  clientList the new client list to save.          
	 */
	private void saveClientList(List<Client> clientList){
		try {
			File file = new File("Clients.dat");
			FileOutputStream fos;

			fos = new FileOutputStream(file);

			ObjectOutputStream oos = new ObjectOutputStream(fos);		
			oos.writeObject(clientList);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get all the clients.                         
	 *
	 * @return the client list.
	 */
	public List<Client> getAllClients(){
		List<Client> clientList = null;
		try {
			File file = new File("Clients.dat");
			if (!file.exists()) {
				Client client = new Client();
				clientList = new ArrayList<Client>();
				clientList.add(client);
				saveClientList(clientList);		
			}
			else{
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);
				clientList = (List<Client>) ois.readObject();
				ois.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}		
		return clientList;
	}
	
	/**
	 * Get all the clients with a specified name.  
	 *                        
     * @param  name the specified name.          
	 * @return the client list with a specified name.
	 */
	public List<Client> getAllClientsByName(String name){
		List<Client> auxClientList = null;
		List<Client> clientListByName = null;

		try {
			File file = new File("Clients.dat");
			if (!file.exists()) {
				Client client = new Client();
				auxClientList = new ArrayList<Client>();
				auxClientList.add(client);
				saveClientList(auxClientList);		
			}
			else{
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);
				auxClientList = (List<Client>) ois.readObject();
				ois.close();
				
				for (Client c : auxClientList) {
					if (name.equals(c.getName())) {
						clientListByName.add(c);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}		
		return clientListByName;
	}
	
	/**
	 * Get the client with a specified nif.  
	 *                        
     * @param  nif the nif client.          
	 * @return the client with a specified nif.
	 */
	public Client getClientByNIF(BigInteger nif){
		List<Client> clientList = getAllClients();
		for(Client c: clientList){
			if(nif.equals(c.getNif())){
				return c;
			}
		}		
		return null;
	}
}