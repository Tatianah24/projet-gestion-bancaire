package com.restapi.gestionbancaire.model;

import java.sql.Date;

public class Versement {

	private int id_vers;
	private int ncompte_vers;
	private int montant_vers;
	private Date date_vers;
	
	
	public Versement() {
		super();
	}
	public Versement(int id_vers, int ncompte_vers, int montant_vers, Date date_vers) {
		super();
		this.id_vers = id_vers;
		this.ncompte_vers = ncompte_vers;
		this.montant_vers = montant_vers;
		this.date_vers = date_vers;
	}
	public int getId_vers() {
		return id_vers;
	}
	public void setId_vers(int id_vers) {
		this.id_vers = id_vers;
	}
	public int getNcompte_vers() {
		return ncompte_vers;
	}
	public void setNcompte_vers(int ncompte_vers) {
		this.ncompte_vers = ncompte_vers;
	}
	public int getMontant_vers() {
		return montant_vers;
	}
	public void setMontant_vers(int montant_vers) {
		this.montant_vers = montant_vers;
	}
	public Date getDate_vers() {
		return date_vers;
	}
	public void setDate_vers(Date date_vers) {
		this.date_vers = date_vers;
	}
	
	
}
