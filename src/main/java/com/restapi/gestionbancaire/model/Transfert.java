package com.restapi.gestionbancaire.model;

import java.sql.Date;

public class Transfert {

	private int id_trans;
	private int ncompte_trans;
	private int ncompte_transb;
	private int montant_trans;
	private Date date_trans;
	
	
	public Transfert() {
		super();
	}
	
	
	public Transfert(int id_trans, int ncompte_trans, int ncompte_transb, int montant_trans, Date date_trans) {
		super();
		this.id_trans = id_trans;
		this.ncompte_trans = ncompte_trans;
		this.ncompte_transb = ncompte_transb;
		this.montant_trans = montant_trans;
		this.date_trans = date_trans;
	}


	public int getNcompte_transb() {
		return ncompte_transb;
	}
	public void setNcompte_transb(int ncompte_transb) {
		this.ncompte_transb = ncompte_transb;
	}

	public int getId_trans() {
		return id_trans;
	}
	public void setId_trans(int id_trans) {
		this.id_trans = id_trans;
	}
	public int getNcompte_trans() {
		return ncompte_trans;
	}
	public void setNcompte_trans(int ncompte_trans) {
		this.ncompte_trans = ncompte_trans;
	}
	
	public int getMontant_trans() {
		return montant_trans;
	}
	public void setMontant_trans(int montant_trans) {
		this.montant_trans = montant_trans;
	}
	public Date getDate_trans() {
		return date_trans;
	}
	public void setDate_trans(Date date_trans) {
		this.date_trans = date_trans;
	}
	
	
}
