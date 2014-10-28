package pl.alium.vaadin.model;

import java.util.Collection;
import java.util.UUID;

public class PomiarCukru {
		private UUID id;
		private int poziomCukru;
		private String czasPomiaruCukru;

		public PomiarCukru (int poziomCukru, String czasPomiaruCukru){
			this.poziomCukru=poziomCukru;
			this.czasPomiaruCukru=czasPomiaruCukru;		
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

		
}
