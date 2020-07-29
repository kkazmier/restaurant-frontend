package com.kodilla.restaurantfrontend.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout {
    Button ingredientsViewBtn = new Button("Składniki");
    Button dishesViewBtn = new Button("Dania");
    Button tableOrdersBtn = new Button("Zamówienia");

    public MainView() {
        addClickListeners();
        add(
                ingredientsViewBtn,
                dishesViewBtn,
                tableOrdersBtn
        );
    }

    public void addClickListeners(){
        ingredientsViewBtn.addClickListener(
                e -> ingredientsViewBtn.getUI().ifPresent(
                        ui -> ui.navigate("ingredients")
                ));
        dishesViewBtn.addClickListener(
                e -> dishesViewBtn.getUI().ifPresent(
                        ui -> ui.navigate("dishes")
                ));
        tableOrdersBtn.addClickListener(
                e -> tableOrdersBtn.getUI().ifPresent(
                        ui -> ui.navigate("tableOrders")
                ));
    }
}
