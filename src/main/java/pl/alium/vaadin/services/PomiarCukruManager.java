package pl.alium.vaadin.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import pl.alium.vaadin.model.PomiarCukru;

public class PomiarCukruManager {

	private List<PomiarCukru> db = new ArrayList<PomiarCukru>();

	public void addPomiarC(PomiarCukru pomiarc) {
		PomiarCukru pC = new PomiarCukru(pomiarc.getPoziomCukru(),
				pomiarc.getCzasPomiaruCukru(), pomiarc.getCwiczenia(),
				pomiarc.getLeki(), pomiarc.getStres(), pomiarc.getPokarm(),
				pomiarc.getInne());
		
		pC.setId(UUID.randomUUID());
		db.add(pC);

	}
	
	//public void deletePomiarC(PomiarCukru pomiarC) {
	//		}

	public List<PomiarCukru> getAll() {
		return db;
	}

}
