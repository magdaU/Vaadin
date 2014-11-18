package com.packtpub.vaadin;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
public class MyVaadinUI extends UI {

	private static final long serialVersionUID = 1L;

	@WebServlet(value = "/flot/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = MyVaadinUI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		System.out.println("TEST");
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		setContent(layout);

		FlotChart flot = new FlotChart();
		flot.setWidth("300px");
		flot.setHeight("300px");

		// String options =
		// "{ grid: { backgroundColor: { colors: [\"#fef\", \"#eee\"] } } }";
		String options = "{" + "grid:{" + "backgroundColor:{" + "colors:["
				+ "\"#fef\"," + "\"#eee\"" + "]" + "}" + "}" + "}";
		String data = "[" + "[" + "[0, 5]," + "[2, 7]," + "[4, 8]," + "[10, 5]"
				+ "]" + "]";

		flot.setData(data);
		flot.setOptions(options);
		layout.addComponent(flot);

	}
}
