package pl.alium.vaadin.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import pl.alium.vaadin.model.Pomiar;


public class PomiarManager {
	
	private List<Pomiar> db = new ArrayList<Pomiar>();

	public void addPomiar(Pomiar pomiar){
	Pomiar p1= new Pomiar(pomiar.getTetno(),
			pomiar.getRozkurcz(),
			pomiar.getSkurcz(),
			pomiar.getCzasPomiaru(),
			pomiar.getInne(),
			pomiar.getCwiczenia(), 
			pomiar.getPokarm(),
			pomiar.getStres(),
			pomiar.getLeki());
	p1.setId(UUID.randomUUID());
	db.add(p1);
	
	}
	public List<Pomiar> getAll(){
		return db;
	}
	public void editPomiar (Pomiar pomiar ){
		for(Pomiar p: db){
			if(p.getId().equals(pomiar.getId())){
					p.setCwiczenia(pomiar.getCwiczenia());
					p.setCzasPomiaru(pomiar.getCzasPomiaru());
					p.setInne(pomiar.getInne());
					p.setLeki(pomiar.getLeki());
					p.setPokarm(pomiar.getPokarm());
					p.setRozkurcz(pomiar.getRozkurcz());
					p.setSkurcz(pomiar.getSkurcz());
					p.setStres(pomiar.getStres());
					p.setTetno(pomiar.getTetno());
			}
		}
	}
	public void deletePomiar(Pomiar pomiar){
		//stworzenie refencji
		Pomiar pomiarToRemove=null;
		//klasa, p -po nich sie odniosimy , db-> kolekcja, tablica
		for(Pomiar p: db){
			if (p.getId().compareTo(pomiar.getId())==0){
				pomiarToRemove=p;
				break;
			}
		}
		db.remove(pomiarToRemove);
	}
	public void sort(Object[] propertyId, boolean[] ascending){
		System.out.println("DUpa");
	}	
}
