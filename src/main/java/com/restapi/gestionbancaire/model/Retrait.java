package com.restapi.gestionbancaire.model;

import java.sql.Date;

public class Retrait {

	private int id_ret;
	private int ncompte_ret;
	private String cheque_ret;
	private int montant_ret;
	private Date date_ret;
	
	
	public Retrait() {
		super();
	}
	public Retrait(int id_ret, int ncompte_ret, String cheque_ret, int montant_ret, Date date_ret) {
		super();
		this.id_ret = id_ret;
		this.ncompte_ret = ncompte_ret;
		this.cheque_ret = cheque_ret;
		this.montant_ret = montant_ret;
		this.date_ret = date_ret;
	}
	public int getId_ret() {
		return id_ret;
	}
	public void setId_ret(int id_ret) {
		this.id_ret = id_ret;
	}
	public int getNcompte_ret() {
		return ncompte_ret;
	}
	public void setNcompte_ret(int ncompte_ret) {
		this.ncompte_ret = ncompte_ret;
	}
	public String getCheque_ret() {
		return cheque_ret;
	}
	public void setCheque_ret(String cheque_ret) {
		this.cheque_ret = cheque_ret;
	}
	public int getMontant_ret() {
		return montant_ret;
	}
	public void setMontant_ret(int montant_ret) {
		this.montant_ret = montant_ret;
	}
	public Date getDate_ret() {
		return date_ret;
	}
	public void setDate_ret(Date date_ret) {
		this.date_ret = date_ret;
	}
	
	
	
	
}
