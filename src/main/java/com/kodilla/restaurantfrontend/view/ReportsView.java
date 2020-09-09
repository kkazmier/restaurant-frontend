package com.kodilla.restaurantfrontend.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("reports")
public class ReportsView extends VerticalLayout {
    private Button backBtn = new Button("Powrót");

    public ReportsView() {
        addClickListeners();
        add(
                backBtn
        );
    }

    public void addClickListeners() {
        backBtn.addClickListener(
                e -> backBtn.getUI().ifPresent(
                        ui -> ui.navigate("main")
                ));
    }
}
