package pl.alium.vaadin.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import pl.alium.vaadin.model.PomiarCukru;

public class PomiarCukruManager {

	private List<PomiarCukru> db = new ArrayList<PomiarCukru>();

	// kolejnosc danych jest ważna [ inaczej blad]!!
	public void addPomiarC(PomiarCukru pomiarc) {
		PomiarCukru pC = new PomiarCukru(pomiarc.getPoziomCukru(),
				pomiarc.getCzasPomiaruCukru(), pomiarc.getPokarm(),
				pomiarc.getCwiczenia(), pomiarc.getStres(), pomiarc.getLeki(),
				pomiarc.getInne());
		pC.setId(UUID.randomUUID());
		db.add(pC);
	}

	public List<PomiarCukru> getAll() {
		return db;
	}
   // kolejnosc danych jest ważna [ inaczej jest blad]!!!
	public void editPomiarC(PomiarCukru pomiarc) {
		for (PomiarCukru pC : db) {
			if (pC.getId().equals(pomiarc.getId())) {
				pC.setPoziomCukru(pomiarc.getPoziomCukru());
				pC.setPokarm(pomiarc.getPokarm());
				pC.setCwiczenia(pomiarc.getCwiczenia());
				pC.setStres(pomiarc.getStres());
				pC.setLeki(pomiarc.getLeki());
				pC.setInne(pomiarc.getInne());
			}
		}
	}

	public void deletePomiarCukru(PomiarCukru pomiarC) {
		// stworzenie refencji
		System.out.println("!!!!!!!!!!! deletePomiarCukru !!!!!!!");
		PomiarCukru pomiarToRemove = null;
		// klasa, p -po nich sie odniosimy , db-> kolekcja, tablica
		for (PomiarCukru pC : db) {
			if (pC.getId().compareTo(pomiarC.getId()) == 0) {
				System.out.println("!!!!!!!!!!!!"+pC.getId().compareTo(pomiarC.getId()));
				pomiarToRemove = pC;
				break;
			}
		}
		db.remove(pomiarToRemove);
	}


}
