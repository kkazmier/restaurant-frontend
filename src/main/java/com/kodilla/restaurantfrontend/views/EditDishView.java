package com.kodilla.restaurantfrontend.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("editDish")
public class EditDishView extends VerticalLayout {
    Button saveBtn = new Button("Zapisz");
    Button cancelBtn = new Button("Anuluj");
    HorizontalLayout buttons = new HorizontalLayout();


    public EditDishView() {
        addClickListeners();
        buttons.add(
                saveBtn,
                cancelBtn
        );
        add(
                buttons
        );
    }

    public void addClickListeners(){
        saveBtn.addClickListener(
                e -> saveBtn.getUI().ifPresent(
                        ui -> ui.navigate("dishes")
                ));
        cancelBtn.addClickListener(
                e -> cancelBtn.getUI().ifPresent(
                        ui -> ui.navigate("dishes")
                ));
    }
}
