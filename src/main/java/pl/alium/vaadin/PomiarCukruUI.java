package pl.alium.vaadin;

import javax.servlet.annotation.WebServlet;

import pl.alium.vaadin.model.PomiarCukru;
import pl.alium.vaadin.services.PomiarCukruManager;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@Title("Aplikacja do rejestracji pomiarów cukru")
@Theme("mytheme")
@SuppressWarnings("serial")

public class PomiarCukruUI extends UI {
	private static final long serialVersionUID = 1L;

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = PomiarCukruUI.class)
	
	public static class Servlet extends VaadinServlet {
	}

	private PomiarCukruManager pomcukierManager = new PomiarCukruManager();
	private PomiarCukru pomiarC1 = new PomiarCukru(80,"2014-10-29", "śniadanie", "bieg po schodach", "nie", "Medocalm", "palenie papierosów");
	
	private BeanItem<PomiarCukru> pomiarItem = new BeanItem<PomiarCukru>(pomiarC1);
	private BeanItemContainer<PomiarCukru> pomiary = new BeanItemContainer<PomiarCukru>(
			PomiarCukru.class);

	enum Action {
		ADD, EDIT;
	}

	protected void init(VaadinRequest request) {
		Button addButonCukier = new Button("Dodaj");
		Button deleteButonCukier = new Button("Usuń");
		Button editButonCukier = new Button("Edytuj");

		VerticalLayout vlC = new VerticalLayout();
		setContent(vlC);
		vlC.setMargin(true);

		HorizontalLayout hlC = new HorizontalLayout();
		hlC.addComponent(addButonCukier);
		hlC.addComponent(editButonCukier);
		hlC.addComponent(deleteButonCukier);
		vlC.addComponent(hlC);

		final Table tableC = new Table("Pomiary cukru", pomiary);
	
		//atrybut , a pozniej etykieta
		tableC.setColumnHeader("poziomCukru", "Etykieta-cukier");
		tableC.setColumnHeader("czasPomiaruCukru", "Etykieta-czas");
		tableC.setColumnHeader("leki","Etykieta-leki");
		tableC.setColumnHeader("cwiczenia", "Etykieta-cwiczenia");
		tableC.setColumnHeader("pokarm", "Etykieta-pokarm");
		tableC.setColumnHeader("stres", "Etykieta-stres");
		tableC.setColumnHeader("inne", "Etykieta- inne");
		vlC.addComponent(tableC);

		addButonCukier.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				addWindow(new Formularz());
			}
		});
		
	}

	private class Formularz extends Window {
		private static final long serialVersionUID = 1L;

		public Formularz() {
			center();
			setModal(true);
			setCaption("OKNO FORMULARZA");

			FormLayout form = new FormLayout();
			final FieldGroup binder = new FieldGroup(pomiarItem);
			

			Button saveButtonC = new Button("Zapisz");
			Button cancelButtonC = new Button("Anuluj");
			
			// etykieta, atrybut
			form.addComponent(binder.buildAndBind("poziomCukru", "poziomCukru"));
			form.addComponent(binder.buildAndBind("Data", "czasPomiaruCukru"));
			form.addComponent(binder.buildAndBind("pokarm", "pokarm"));
			form.addComponent(binder.buildAndBind("cwiczenia", "cwiczenia"));
			form.addComponent(binder.buildAndBind ("stres", "stres"));
			form.addComponent(binder.buildAndBind("leki", "leki"));
			form.addComponent(binder.buildAndBind("inne", "inne"));
			binder.setBuffered(false);

			VerticalLayout vlC2= new VerticalLayout();
			vlC2.addComponent(form);
			HorizontalLayout hlC2 = new HorizontalLayout();
			hlC2.addComponent(saveButtonC);
			hlC2.addComponent(cancelButtonC);
			vlC2.addComponent(hlC2);
			setContent(vlC2);

			saveButtonC.addClickListener(new ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
					try {
						binder.commit();
						pomcukierManager.addPomiarC(pomiarC1);
						pomiary.addAll(pomcukierManager.getAll());
						close();
					} catch (CommitException e) {
						e.printStackTrace();
					}
				}
			});
			cancelButtonC.addClickListener(new ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
					close();
					
				}
			});
		}	
	}
}



