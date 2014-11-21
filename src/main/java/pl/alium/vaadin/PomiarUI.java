package pl.alium.vaadin;

import java.io.File;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import pl.alium.vaadin.model.Pomiar;
import pl.alium.vaadin.services.PomiarManager;
import com.packtpub.vaadin.FlotChart;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

@Title("Aplikacja do rejestracji pomiarów ciśnienia")
@Theme("runo")
@SuppressWarnings("serial")
public class PomiarUI extends UI {

	private static final long serialVersionUID = 1L;

	@WebServlet(value = "/cisnienie/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = PomiarUI.class)
	
	
	public static class Servlet extends VaadinServlet {
	}

	private PomiarManager pomiarManager = new PomiarManager();
	private Pomiar pomiar1 = new Pomiar(90, 120, 100, "2014-10-24", "inne",
			false, false, false, "leki");

	// stworzenie liste obiektow pommiar
	private List<Pomiar> pm;

	private BeanItem<Pomiar> pomiarItem = new BeanItem<Pomiar>(pomiar1);
	private BeanItemContainer<Pomiar> pomiary = new BeanItemContainer<Pomiar>(
			Pomiar.class);
	final Table table = new Table("Pomiary cisnienia", pomiary);
	final Link link = new Link("Więcej informacji!", new ExternalResource(
			"http://nadcisnienie.mp.pl/"));
	final Link linkCukier= new Link("PRZEJDZ DO POMIARU CUKRU===>", new ExternalResource(
			"http://localhost:8080/cukier"));
	Label CISNLabel = new Label("<b><h2><em>BADANIE POZIOMU CIŚNIENIA KRWI</em></h2></b>",ContentMode.HTML);

	Button addButon = new Button("Dodaj");
	final Button deleteButon = new Button("Usuń");
	final Button editButon = new Button("Edytuj");
	Button wykresButon = new Button("Wykres pomiaru ciśnienia");

	
	enum Action {
		ADD, EDIT;
	}

	
	
	
	@Override
	protected void init(VaadinRequest request) {
		
		// Find the application directory
		String basepath = VaadinService.getCurrent().getBaseDirectory()
				.getAbsolutePath();
		String basepath2 = VaadinService.getCurrent().getBaseDirectory()
				.getAbsolutePath();

		System.out.println(basepath);
		System.out.println(basepath2);

		// Image as a file resource
		FileResource resource = new FileResource(new File(basepath
				+ "/image/image2.jpg"));
		FileResource resource2 = new FileResource(new File(basepath2
				+ "/image/image3.png"));

		// Show the image in the application
		Image image = new Image("", resource);
		Image image2 = new Image("Interpretacja wyników", resource2);

		VerticalLayout vl = new VerticalLayout();
		vl.addComponent(CISNLabel);
		setContent(vl);
		vl.setMargin(true);
		vl.addComponent(linkCukier);
		vl.setComponentAlignment(linkCukier, Alignment.MIDDLE_RIGHT);

		HorizontalLayout hl = new HorizontalLayout();
		hl.setSpacing(true);
		hl.setMargin(true);
		vl.addComponent(hl);

		HorizontalLayout hl2 = new HorizontalLayout();
		hl2.setSpacing(true);
		hl2.addComponent(image);
		
		VerticalLayout toolbar = new VerticalLayout();
		hl2.addComponent(toolbar);
		toolbar.addComponent(addButon);
		toolbar.addComponent(editButon);
		toolbar.addComponent(deleteButon);
		toolbar.addComponent(wykresButon);
		hl2.setComponentAlignment(toolbar, Alignment.MIDDLE_LEFT);
		
		

		// atrybut-identyfikacja kolumny, etykieta
		table.setColumnHeader("tetno", "Tętno");
		table.setColumnHeader("skurcz", "Skurcz");
		table.setColumnHeader("rozkurcz", "Rozkurcz");
		table.setColumnHeader("czasPomiaru", "Czas pomiaru");
		table.setColumnHeader("inne", "Inne");
		table.setColumnHeader("cwiczenia", "Cwiczenia");
		table.setColumnHeader("pokarm", "Pokarm");
		table.setColumnHeader("stres", "Stres");
		table.setColumnHeader("leki", "Leki");

		table.addValueChangeListener(new Property.ValueChangeListener() {

			@Override
			public void valueChange(
					com.vaadin.data.Property.ValueChangeEvent event) {
				// TODO Auto-generated method stub
				Pomiar selectedPomiar = (Pomiar) table.getValue();
				if (selectedPomiar == null) {
					pomiar1.setId(null);
					pomiar1.setSkurcz(0);
					pomiar1.setRozkurcz(0);
					pomiar1.setTetno(0);
					pomiar1.setCzasPomiaru("");
					pomiar1.setStres(false);
					pomiar1.setCwiczenia(false);
					pomiar1.setLeki("");
					pomiar1.setPokarm(false);
					pomiar1.setInne("");
				} else {
					pomiar1.setId(selectedPomiar.getId());
					pomiar1.setSkurcz(selectedPomiar.getSkurcz());
					pomiar1.setRozkurcz(selectedPomiar.getRozkurcz());
					pomiar1.setTetno(selectedPomiar.getTetno());
					pomiar1.setCzasPomiaru(selectedPomiar.getCzasPomiaru());
					pomiar1.setCwiczenia(selectedPomiar.getCwiczenia());
					pomiar1.setStres(selectedPomiar.getStres());
					pomiar1.setLeki(selectedPomiar.getLeki());
					pomiar1.setPokarm(selectedPomiar.getPokarm());
					pomiar1.setInne(selectedPomiar.getInne());
				}
				setModificationEnabled(event.getProperty().getValue() != null);
			}
		});
		// widzialnosc tabel i ich kolejnosc
		table.setVisibleColumns(new Object[] { "tetno", "skurcz", "rozkurcz",
				"czasPomiaru", "stres", "leki","cwiczenia", "pokarm", "inne"});

		// mozna wybrac dany wiersz z kolumny
		table.setSelectable(true);
		vl.addComponent(hl2);
		vl.addComponent(table);
		vl.addComponent(image2);
		vl.addComponent(link);
		
		
		//link - Przejdz do strony Pomiar Cukru
		linkCukier.setTargetName("_blank");
		linkCukier.setTargetBorder(Link.TARGET_BORDER_NONE);
		linkCukier.setTargetHeight(600);
		linkCukier.setTargetWidth(700);
		
		//link wiecej informacji
		link.setTargetName("_blank");
		link.setTargetBorder(Link.TARGET_BORDER_NONE);
		link.setTargetHeight(600);
		link.setTargetWidth(700);
				
		// szerokośc i wysokosc tabeli, oraz max 10 rekodrów widoczne w tabeli
		table.setWidth("100%");
		table.setHeight("80%");
		table.setPageLength(8);

		addButon.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				addWindow(new Formularz());
			}
		});
		wykresButon.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				addWindow(new Wykres());
			}
		});

		editButon.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				addWindow(new Formularz2());
			}
		});

		editButon.setEnabled(false);
		deleteButon.setEnabled(false);

		deleteButon.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				if (!pomiar1.getLeki().isEmpty()) {
					pomiarManager.deletePomiar(pomiar1);
					pomiary.removeAllItems();
					pomiary.addAll(pomiarManager.getAll());
					setModificationEnabled(false);
				}
			}
		});
	}

	public void setModificationEnabled(boolean b) {
		editButon.setEnabled(b);
		deleteButon.setEnabled(b);
	}

	private class Formularz2 extends Window {
		private static final long serialVersionUID = 1L;

		public Formularz2() {
			center();
			setModal(true); // nie mozna modyfikowac jak jest otwarte okienko
			setCaption("Okno formularza"); // tytul okna

			FormLayout form = new FormLayout();
			final FieldGroup binder = new FieldGroup(pomiarItem);

			Button saveButton1 = new Button("Zapisz");
			Button cancelButton1 = new Button("Anuluj");

			// Nazwa, atrybut

			form.addComponent(binder.buildAndBind("Tetno", "tetno"));
			form.addComponent(binder.buildAndBind("Skurcz", "skurcz"));
			form.addComponent(binder.buildAndBind("Rozkurcz", "rozkurcz"));
			form.addComponent(binder.buildAndBind("Data", "czasPomiaru"));
			form.addComponent(binder.buildAndBind("Inne", "inne"));
			form.addComponent(binder.buildAndBind("Ćwiczenia", "cwiczenia"));
			form.addComponent(binder.buildAndBind("Pokarm", "pokarm"));
			form.addComponent(binder.buildAndBind("Stres", "stres"));
			form.addComponent(binder.buildAndBind("Leki", "leki"));
			binder.setBuffered(false);

			VerticalLayout vl = new VerticalLayout();
			vl.setMargin(true);
			vl.addComponent(form);
			HorizontalLayout hl = new HorizontalLayout();
			hl.addComponent(saveButton1);
			hl.addComponent(cancelButton1);
			hl.setMargin(true);
			vl.addComponent(hl);
			setContent(vl);

			saveButton1.addClickListener(new ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
					try {
						binder.commit();
						pomiarManager.editPomiar(pomiar1);
						pomiary.addAll(pomiarManager.getAll());
						table.refreshRowCache();
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

	// wykres pomiaru cisnienia [ raport]-
	private class Wykres extends Window {

		public Wykres() {
			center();
			setModal(true);
			setCaption("Wykres pomiarów ciśnienia");

			final VerticalLayout layout = new VerticalLayout();
			layout.setMargin(true);
			setContent(layout);

			String labelSkurcz = "Skurczowe";
			String labelRozkurcz = "Rozkurczowe";
			String labelTetno = "Tetno";

			FlotChart flot = new FlotChart();
			flot.setWidth("500px");
			flot.setHeight("500px");

			// lista pomiarów ciśnienia [ kolekcja]
			pm = pomiarManager.getAll();

			String daneWykresu = "[["; // inicjalizacja
			for (int i = 0; i < pm.size(); i++) {
				if (i == pm.size() - 1) { // jezeli jest to ostatni wpis
					daneWykresu = daneWykresu + "[" + i + ","
							+ pm.get(i).getSkurcz() + "]]]";
				} else {
					daneWykresu = daneWykresu + "[" + i + ","
							+ pm.get(i).getSkurcz() + "],";
				}
				System.out.println(daneWykresu);
			}

			String daneWykresuSkurcz = "[";
			for (int i = 0; i < pm.size(); i++) {
				if (i == pm.size() - 1) {
					daneWykresuSkurcz += "[" + i + "," + pm.get(i).getSkurcz()
							+ "]]";
				} else {
					daneWykresuSkurcz += "[" + i + "," + pm.get(i).getSkurcz()
							+ "],";
				}
			}

			String daneWykresuRozkurcz = "[";
			for (int i = 0; i < pm.size(); i++) {
				if (i == pm.size() - 1) {
					daneWykresuRozkurcz += "[" + i + ","
							+ pm.get(i).getRozkurcz() + "]]";
				} else {
					daneWykresuRozkurcz += "[" + i + ","
							+ pm.get(i).getRozkurcz() + "],";
				}
			}

			String daneWykresuTetno = "[";
			for (int i = 0; i < pm.size(); i++) {
				if (i == pm.size() - 1) {
					daneWykresuTetno += "[" + i + "," + pm.get(i).getTetno()
							+ "]]";
				} else {
					daneWykresuTetno += "[" + i + "," + pm.get(i).getTetno()
							+ "],";
				}
			}

			String options = "{" + "grid:{" + "backgroundColor:{" + "colors:["
					+ "\"#fef\"," + "\"#eee\"" + "]" + "}" + "}" + "}";
			String daneWykresuS = "[{label: " + labelSkurcz + ", data: "
					+ daneWykresuSkurcz + " },{label: " + labelRozkurcz
					+ ", data: " + daneWykresuRozkurcz + "},{label: "
					+ labelTetno + ", data: " + daneWykresuTetno + "}]";

			flot.setData(daneWykresuS);
			flot.setOptions(options);
			layout.addComponent(flot);
		}
	}

	private class Formularz extends Window {
		private static final long serialVersionUID = 1L;

		public Formularz() {
			center();
			setModal(true);
			setCaption("Okno formularza");

			FormLayout form = new FormLayout();
			form.setMargin(true);
			final FieldGroup binder = new FieldGroup(pomiarItem);

			Button saveButton1 = new Button("Zapisz");
			Button cancelButton1 = new Button("Anuluj");

			// Nazwa, atrybut

			form.addComponent(binder.buildAndBind("Tetno", "tetno"));
			form.addComponent(binder.buildAndBind("Rozkurcz", "rozkurcz"));
			form.addComponent(binder.buildAndBind("Skurcz", "skurcz"));
			form.addComponent(binder.buildAndBind("Data", "czasPomiaru"));
			form.addComponent(binder.buildAndBind("Inne", "inne"));
			form.addComponent(binder.buildAndBind("Ćwiczenia", "cwiczenia"));
			form.addComponent(binder.buildAndBind("Pokarm", "pokarm"));
			form.addComponent(binder.buildAndBind("Stres", "stres"));
			form.addComponent(binder.buildAndBind("Leki", "leki"));
			binder.setBuffered(false);

			VerticalLayout vl = new VerticalLayout();
			vl.addComponent(form);
			vl.setMargin(true);
			HorizontalLayout hl = new HorizontalLayout();
			hl.addComponent(saveButton1);
			hl.addComponent(cancelButton1);
			hl.setMargin(true);
			vl.addComponent(hl);
			setContent(vl);

			saveButton1.addClickListener(new ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
					try {
						binder.commit();
						pomiarManager.addPomiar(pomiar1);
						pomiary.addAll(pomiarManager.getAll());
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
}
