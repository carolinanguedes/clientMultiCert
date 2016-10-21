package com.clientMultiCert;

import java.io.Serializable;
import java.math.BigInteger;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "client")
public class Client implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String address;
	private BigInteger nif;
	private String phone;

	public Client(){}

	public Client(int id, String name, String address, BigInteger nif, String phone){
		this.id=id;
		this.name = name;
		this.address = address;
		this.nif = nif;
		this.phone = phone;
	}

	@XmlElement
	public int getId() {
		return id;
	}
	@XmlElement
	public void setId(int id) {
		this.id = id;
	}
	@XmlElement
	public String getName() {
		return name;
	}
	@XmlElement
	public void setName(String name) {
		this.name = name;
	}
	@XmlElement
	public String getAddress() {
		return address;
	}
	@XmlElement
	public void setAddress(String address) {
		this.address = address;
	}
	@XmlElement
	public BigInteger getNif() {
		return nif;
	}
	@XmlElement
	public void setNif(BigInteger nif) {
		this.nif = nif;
	}
	@XmlElement
	public String getPhone() {
		return phone;
	}
	@XmlElement
	public void setPhone(String phone) {
		this.phone = phone;
	}



}