package pl.alium.vaadin;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import pl.alium.vaadin.model.PomiarCukru;
import pl.alium.vaadin.services.PomiarCukruManager;
import com.packtpub.vaadin.FlotChart;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Property;
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
	private PomiarCukru pomiarC1 = new PomiarCukru(70, "2014-11-06", "obiad",
			false, false, "Medocalm", "wysiłek fizyczny-biegi");
	private BeanItem<PomiarCukru> pomiarItem = new BeanItem<PomiarCukru>(
			pomiarC1);
	//stworzenie liste obiektow pommiar
	private List<PomiarCukru> pC;
	
	private BeanItemContainer<PomiarCukru> pomiary = new BeanItemContainer<PomiarCukru>(
			PomiarCukru.class);
	final Table tableC = new Table("Pomiary poziomu cukru", pomiary);

	enum Action {
		ADD, EDIT;
	}

	final Button deleteButonCukier = new Button("Usuń");
	final Button editButonCukier = new Button("Edytuj");
	Button wykresButon = new Button("Wykres Poziomu Cukru");
	final TextArea area1 = new TextArea("Uwaga");
	
	protected void init(VaadinRequest request) {
		Button addButonCukier = new Button("Dodaj");

		VerticalLayout vlC = new VerticalLayout();
		setContent(vlC);
		vlC.setMargin(true);

		HorizontalLayout hlC = new HorizontalLayout();
		hlC.addComponent(addButonCukier);
		hlC.addComponent(editButonCukier);
		hlC.addComponent(deleteButonCukier);
		hlC.addComponent(wykresButon);
		vlC.addComponent(hlC);

		
		area1.setWordwrap(true); // The default
		area1.setValue("Wypełnij formularz z danymi, żeby otrzymać wykres");
		area1.setWidth("800px");
		area1.setRows(2);
		
		// atrybut , a pozniej etykieta
		tableC.setColumnHeader("poziomCukru", "Poziom cukru");
		tableC.setColumnHeader("czasPomiaruCukru", "Data pomiaru cukru");
		tableC.setColumnHeader("leki", "Zażywane leki");
		tableC.setColumnHeader("cwiczenia", "Cwiczenia");
		tableC.setColumnHeader("pokarm", "Przyjmowany pokarm");
		tableC.setColumnHeader("stres", "Ekspozycja na stres");
		tableC.setColumnHeader("inne", "Inne czynniki");

		tableC.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(
					com.vaadin.data.Property.ValueChangeEvent event) {
				// TODO Auto-generated method stub
				PomiarCukru selectedPomiarCukru = (PomiarCukru) tableC
						.getValue();
				if (selectedPomiarCukru == null) {
					pomiarC1.setCwiczenia(false);
					pomiarC1.setCzasPomiaruCukru("");
					pomiarC1.setId(null);
					pomiarC1.setInne("");
					pomiarC1.setLeki("");
					pomiarC1.setPokarm("");
					pomiarC1.setStres(false);
				} else {
					pomiarC1.setCwiczenia(selectedPomiarCukru.getCwiczenia());
					pomiarC1.setCzasPomiaruCukru(selectedPomiarCukru
							.getCzasPomiaruCukru());
					pomiarC1.setInne(selectedPomiarCukru.getInne());
					pomiarC1.setLeki(selectedPomiarCukru.getLeki());
					pomiarC1.setPokarm(selectedPomiarCukru.getPokarm());
					pomiarC1.setStres(selectedPomiarCukru.getStres());
					pomiarC1.setId(selectedPomiarCukru.getId());
				}
				setModificationEnabled(event.getProperty().getValue() != null);
			}
		});

		tableC.setSelectable(true);
		vlC.addComponent(tableC);
		vlC.addComponent(area1);

		addButonCukier.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				addWindow(new Formularz());
			}
		});
		
		wykresButon.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				addWindow(new WykresCukru());
			}
		});

		editButonCukier.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				addWindow(new Formularz2());

			}
		});
		editButonCukier.setEnabled(false);
		deleteButonCukier.setEnabled(false);

		deleteButonCukier.addClickListener(new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				if (!pomiarC1.getLeki().isEmpty()) {
					System.out.println("Button Clicke!!!!!1"
							+ pomiarC1.getLeki().isEmpty());
					pomcukierManager.deletePomiarCukru(pomiarC1);
					pomiary.removeAllItems();
					pomiary.addAll(pomcukierManager.getAll());
					setModificationEnabled(false);
				}
			}
		});
	}

	public void setModificationEnabled(boolean b) {
		editButonCukier.setEnabled(b);
		deleteButonCukier.setEnabled(b);
	}

	private class Formularz2 extends Window {
		private static final long serialVersionUID = 1L;

		public Formularz2() {
			center();
			setModal(true);
			setCaption("Okno formularza");

			FormLayout form = new FormLayout();
			final FieldGroup binder = new FieldGroup(pomiarItem);

			Button saveButton1 = new Button("Zapisz");
			Button cancelButton1 = new Button("Anuluj");

			// Nazwa, atrybut
			form.addComponent(binder.buildAndBind("poziomCukru", "poziomCukru"));
			form.addComponent(binder.buildAndBind("Data", "czasPomiaruCukru"));
			form.addComponent(binder.buildAndBind("pokarm", "pokarm"));
			form.addComponent(binder.buildAndBind("cwiczenia", "cwiczenia"));
			form.addComponent(binder.buildAndBind("stres", "stres"));
			form.addComponent(binder.buildAndBind("leki", "leki"));
			form.addComponent(binder.buildAndBind("inne", "inne"));
			binder.setBuffered(false);

			VerticalLayout vl = new VerticalLayout();
			vl.addComponent(form);
			HorizontalLayout hl = new HorizontalLayout();
			hl.addComponent(saveButton1);
			hl.addComponent(cancelButton1);
			vl.addComponent(hl);
			setContent(vl);

			saveButton1.addClickListener(new ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
					try {
						binder.commit();
						pomcukierManager.editPomiarC(pomiarC1);
						pomiary.addAll(pomcukierManager.getAll());
						tableC.refreshRowCache();
						close();
					} catch (CommitException e) {
						e.printStackTrace();
					}
				}
			});

			cancelButton1.addClickListener(new ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
					close();
				}
			});
		
		}
	}

	// wykres
	private class WykresCukru extends Window {

		public WykresCukru() {
			center();
			setModal(true);
			setCaption("Wykres pomiarów cukru");

			final VerticalLayout layout = new VerticalLayout();
			layout.setMargin(true);
			setContent(layout);

			FlotChart flot = new FlotChart();
			flot.setWidth("300px");
			flot.setHeight("300px");

			// lista pomiarów ciśnienia [ kolekcja]
			pC = pomcukierManager.getAll();

			String daneWykresu = "[["; // inicjalizacja
			for (int i = 0; i < pC.size(); i++) {
				if (i == pC.size() - 1) { // jezeli jest to ostatni wpis
					daneWykresu = daneWykresu + "[" + i + ","
							+ pC.get(i).getPoziomCukru() + "]]]";
				} else {
					daneWykresu = daneWykresu + "[" + i + ","
							+ pC.get(i).getPoziomCukru() + "],";
				}
				System.out.println(daneWykresu);
			}

			// String options =
			// "{ grid: { backgroundColor: { colors: [\"#fef\", \"#eee\"] } } }";
			String options = "{" + "grid:{" + "backgroundColor:{" + "colors:["
					+ "\"#fef\"," + "\"#eee\"" + "]" + "}" + "}" + "}";

			flot.setData(daneWykresu);
			flot.setOptions(options);
			layout.addComponent(flot);
		}

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
			form.addComponent(binder.buildAndBind("stres", "stres"));
			form.addComponent(binder.buildAndBind("leki", "leki"));
			form.addComponent(binder.buildAndBind("inne", "inne"));
			binder.setBuffered(false);

			VerticalLayout vlC2 = new VerticalLayout();
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
