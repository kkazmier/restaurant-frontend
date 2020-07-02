package com.kodilla.restaurantfrontend.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("dishes")
public class DishesView extends VerticalLayout {
    Button mainViewBtn = new Button("Strona główna");

    public DishesView() {
        addClickListeners();
        add(
                mainViewBtn
        );
    }

    public void addClickListeners(){
        mainViewBtn.addClickListener(
                e -> mainViewBtn.getUI().ifPresent(
                        ui -> ui.navigate("")
                ));
    }
}
