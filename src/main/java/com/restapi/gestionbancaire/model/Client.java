package com.restapi.gestionbancaire.model;

public class Client {

	private int ncompte;
	private String nom;
	private int solde;
	
	
	public Client() {
		super();
	}
	public Client(int ncompte, String nom, int solde) {
		super();
		this.ncompte = ncompte;
		this.nom = nom;
		this.solde = solde;
	}
	public int getNcompte() {
		return ncompte;
	}
	public void setNcompte(int ncompte) {
		this.ncompte = ncompte;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getSolde() {
		return solde;
	}
	public void setSolde(int solde) {
		this.solde = solde;
	}
	
	
}
