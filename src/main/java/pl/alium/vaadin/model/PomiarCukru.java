package pl.alium.vaadin.model;

import java.util.UUID;

public class PomiarCukru {
		private UUID id;
		private int poziomCukru;
		private String czasPomiaruCukru;
		private String pokarm;
		private String cwiczenia;
		private String stres;
		private String leki;
		private String inne="nałogi";

		public String getLeki() {
			return leki;
		}

		public void setLeki(String leki) {
			this.leki = leki;
		}

		public String getPokarm() {
			return pokarm;
		}

		public void setPokarm(String pokarm) {
			this.pokarm = pokarm;
		}

		public String getCwiczenia() {
			return cwiczenia;
		}

		public void setCwiczenia(String cwiczenia) {
			this.cwiczenia = cwiczenia;
		}

		public String getStres() {
			return stres;
		}

		public void setStres(String stres) {
			this.stres = stres;
		}

		public PomiarCukru (int poziomCukru, String czasPomiaruCukru,
				String pokarm, String cwiczenia, String stres, String leki,
				String inne){
			this.poziomCukru=poziomCukru;
			this.czasPomiaruCukru=czasPomiaruCukru;
			this.pokarm=pokarm;
			this.cwiczenia=cwiczenia;
			this.stres=stres;
			this.leki=leki;
			this.inne=inne;
		}

		public UUID getId() {
			return id;
		}

		public void setId(UUID id) {
			this.id = id;
		}

		public int getPoziomCukru() {
			return poziomCukru;
		}

		public void setPoziomCukru(int poziomCukru) {
			this.poziomCukru = poziomCukru;
		}

		public String getCzasPomiaruCukru() {
			return czasPomiaruCukru;
		}

		public void setCzasPomiaruCukru(String czasPomiaruCukru) {
			this.czasPomiaruCukru = czasPomiaruCukru;
		}

		public String getInne() {
			return inne;
		}

		public void setInne(String inne) {
			this.inne = inne;
		}

		
}
