package com.kodilla.restaurantfrontend.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("main")
public class MainView extends VerticalLayout {
    private Button ingredientsViewBtn = new Button("Składniki");
    private Button dishesViewBtn = new Button("Dania");
    private Button tableOrdersBtn = new Button("Zamówienia");
    private Button employeesBtn = new Button("Pracownicy");

    public MainView() {
        addClickListeners();
        add(
                ingredientsViewBtn,
                dishesViewBtn,
                tableOrdersBtn,
                employeesBtn
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
        employeesBtn.addClickListener(
                e -> employeesBtn.getUI().ifPresent(
                        ui -> ui.navigate("employees")
                ));
    }
}
