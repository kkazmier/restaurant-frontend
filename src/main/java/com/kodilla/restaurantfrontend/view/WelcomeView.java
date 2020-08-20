package com.kodilla.restaurantfrontend.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class WelcomeView extends VerticalLayout {
    private Button loginBtn = new Button("Logownie");

    public WelcomeView() {
        addClickListeners();
        add(
                loginBtn
        );
    }

    public void addClickListeners(){
        loginBtn.addClickListener(
                e -> loginBtn.getUI().ifPresent(
                        ui -> ui.navigate("login")
                )
        );
    }
}
