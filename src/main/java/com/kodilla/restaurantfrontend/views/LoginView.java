package com.kodilla.restaurantfrontend.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("login")
public class LoginView extends VerticalLayout {
    private Label loginLabel = new Label("Logowanie");
    private TextField loginField = new TextField("PIN");
    private Button one = new Button("1");
    private Button two = new Button("2");
    private Button three = new Button("3");
    private Button four = new Button("4");
    private Button five = new Button("5");
    private Button six = new Button("6");
    private Button seven = new Button("7");
    private Button eight = new Button("8");
    private Button nine = new Button("9");
    private Button zero = new Button("0");
    private Button applyBtn = new Button("Zatwierdź");
    private HorizontalLayout firstRow = new HorizontalLayout(one, two, three);
    private HorizontalLayout secondRow = new HorizontalLayout(four, five, six);
    private HorizontalLayout thirdRow = new HorizontalLayout(seven, eight, nine);

    public LoginView() {
        loginLabel.getStyle().set("fontWeight", "bold");
        addClickListeners();
        add(
                loginLabel,
                firstRow,
                secondRow,
                thirdRow,
                loginField,
                applyBtn
        );
    }

    public void addClickListeners(){

    }


}
