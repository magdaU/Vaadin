package pl.alium.vaadin.model;

import java.util.Date;
import java.util.UUID;

public class Pomiar {
	private UUID id; // unikalny UUID
	private int tetno;
	private int skurcz;
	private int rozkurcz;
	private String czasPomiaru;
	private String pokarm;
	private boolean cwiczenia;
	private boolean stres;
	private String leki;
	private String inne ="brak snu";
	
	public Pomiar (int tetno, int skurcz, int rozkurcz, String czasPomiaru,
			String inne, boolean cwiczenia, String pokarm,
			boolean stres, String leki){
		this.tetno=tetno;
		this.skurcz=skurcz;
		this.rozkurcz=rozkurcz;
		this.czasPomiaru=czasPomiaru;
		this.inne=inne;
		this.cwiczenia=cwiczenia;
		this.pokarm=pokarm;
		this.stres=stres;
		this.leki=leki;
		
	}
	public String getPokarm() {
		return pokarm;
	}
	public void setPokarm(String pokarm) {
		this.pokarm = pokarm;
	}
	public boolean getCwiczenia() {
		return cwiczenia;
	}
	public void setCwiczenia (boolean string) {
		this.cwiczenia = string;
	}
	public boolean getStres() {
		return stres;
	}
	public void setStres(boolean stres) {
		this.stres = stres;
	}
	public String getLeki() {
		return leki;
	}
	public void setLeki(String leki) {
		this.leki = leki;
	}
	public String getInne() {
		return inne;
	}
	public void setInne(String inne) {
		this.inne = inne;
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public int getTetno() {
		return tetno;
	}
	public void setTetno(int tetno) {
		this.tetno = tetno;
	}
	public int getSkurcz() {
		return skurcz;
	}
	public void setSkurcz(int skurcz) {
		this.skurcz = skurcz;
	}
	public int getRozkurcz() {
		return rozkurcz;
	}
	public void setRozkurcz(int rozkurcz) {
		this.rozkurcz = rozkurcz;
	}
	public String getCzasPomiaru() {
		return czasPomiaru;
	}
	public void setCzasPomiaru(String czasPomiaru) {
		this.czasPomiaru = czasPomiaru;
	}
	
	
}
