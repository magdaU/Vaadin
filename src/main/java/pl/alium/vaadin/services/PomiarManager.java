package pl.alium.vaadin.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import pl.alium.vaadin.model.Pomiar;


public class PomiarManager {
	
	private List<Pomiar> db = new ArrayList<Pomiar>();

	public void addPomiar(Pomiar pomiar){
	Pomiar p1= new Pomiar(pomiar.getTetno(),pomiar.getRozkurcz(),
			pomiar.getSkurcz(), pomiar.getCzasPomiaru(),pomiar.getInne(),
			pomiar.getCwiczenia(), pomiar.getPokarm(), pomiar.getStres(),
			pomiar.getLeki());
	p1.setId(UUID.randomUUID());
	db.add(p1);
	
	}
	public List<Pomiar> getAll(){
		return db;
	}
	
	
}
