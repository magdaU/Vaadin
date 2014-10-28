package pl.alium.vaadin;

import javax.servlet.annotation.WebServlet;

import pl.alium.vaadin.model.Pomiar;
import pl.alium.vaadin.services.PomiarManager;


import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.ListSet;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

@Title("Aplikacja do rejestracji pomiarów ciśnienia")
@Theme("mytheme")
@SuppressWarnings("serial")
public class PomiarUI extends UI {

	private static final long serialVersionUID = 1L;

	@WebServlet(value = "/cisnienie/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = PomiarUI.class)
	public static class Servlet extends VaadinServlet {
	}

	private PomiarManager pomiarManager = new PomiarManager();
	private Pomiar pomiar1 = new Pomiar(90, 120, 100, "2014-10-24");
	private BeanItem<Pomiar> pomiarItem = new BeanItem<Pomiar>(pomiar1);
	private BeanItemContainer<Pomiar> pomiary = new BeanItemContainer<Pomiar>(
			Pomiar.class);

	enum Action {
		ADD, EDIT;
	}

	@Override
	protected void init(VaadinRequest request) {
		Button addButon = new Button("Dodaj");
		Button deleteButon = new Button("Usuń");
		Button editButon = new Button("Edytuj");

		VerticalLayout vl = new VerticalLayout();
		setContent(vl);
		vl.setMargin(true);

		HorizontalLayout hl = new HorizontalLayout();
		hl.addComponent(addButon);
		hl.addComponent(editButon);
		hl.addComponent(deleteButon);
		vl.addComponent(hl);

		final Table table = new Table("Pomiary cisnienia", pomiary);
	
		table.setColumnHeader("skurcz", " Etykieta-Skurczowe");
		table.setColumnHeader("rozkurcz", "Etykieta-Rozkurczowe");
		table.setColumnHeader("czasPomiaru", "Etykieta-pomiar");
		vl.addComponent(table);

		addButon.addClickListener(new ClickListener() {
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
			setCaption("Okno formularza");

			FormLayout form = new FormLayout();
			final FieldGroup binder = new FieldGroup(pomiarItem);

			Button saveButton1 = new Button("Zapisz");
			Button cancelButton1 = new Button("Anuluj");

			form.addComponent(binder.buildAndBind("Tetno", "tetno"));
			form.addComponent(binder.buildAndBind("Skurcz", "skurcz"));
			form.addComponent(binder.buildAndBind("Rozkurcz", "rozkurcz"));
			form.addComponent(binder.buildAndBind("Data", "czasPomiaru"));

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
