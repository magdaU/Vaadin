package pl.alium.vaadin.model;

import java.util.Date;
import java.util.UUID;

public class Pomiar {
	private UUID id; // unikalny UUID
	private int tetno;
	private int skurcz;
	private int rozkurcz;
	private String czasPomiaru;
	
	public Pomiar (int tetno, int rozkurcz, int skurcz, String czasPomiaru){
		this.tetno=tetno;
		this.rozkurcz=rozkurcz;
		this.skurcz=skurcz;
		this.czasPomiaru=czasPomiaru;
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
