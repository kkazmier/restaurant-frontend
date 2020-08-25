package com.kodilla.restaurantfrontend.view;

import com.kodilla.restaurantfrontend.context.OwnAppContext;
import com.kodilla.restaurantfrontend.service.EmployeeService;
import com.kodilla.restaurantfrontend.service.LoginService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Route("login")
public class LoginView extends VerticalLayout {
    Logger logger = LoggerFactory.getLogger(LoginView.class);
    private Label loginLabel = new Label("PIN: ");
    private Label loginFieldValue = new Label();
    private HorizontalLayout pinLabels = new HorizontalLayout();
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
    private Dialog wrongPinMessage = new Dialog();
    private LoginService loginService = new LoginService();
    private EmployeeService employeeService = new EmployeeService();
    private String pin;
    private Long userId;

    public LoginView() {
        loginLabel.getStyle().set("fontWeight", "bold");
        wrongPinMessage.add(new Label("Nie istnieje użytkownik o podanym PINie!"));
        pinLabels.add(loginLabel, loginFieldValue);
        addClickListeners();
        add(
                firstRow,
                secondRow,
                thirdRow,
                pinLabels,
                applyBtn,
                wrongPinMessage
        );
        pin = "";
    }

    public void addClickListeners(){
        one.addClickListener(
                e -> one.getUI().ifPresent(
                        ui -> {
                            pin += "1";
                            refresh();
                        }
        ));
        two.addClickListener(
                e -> two.getUI().ifPresent(
                        ui -> {
                            pin += "2";
                            refresh();
                        }
                ));
        three.addClickListener(
                e -> three.getUI().ifPresent(
                        ui -> {
                            pin += "3";
                            refresh();
                        }
                ));
        four.addClickListener(
                e -> four.getUI().ifPresent(
                        ui -> {
                            pin += "4";
                            refresh();
                        }
                ));
        five.addClickListener(
                e -> five.getUI().ifPresent(
                        ui -> {
                            pin += "5";
                            refresh();
                        }
                ));
        six.addClickListener(
                e -> six.getUI().ifPresent(
                        ui -> {
                            pin += "6";
                            refresh();
                        }
                ));
        seven.addClickListener(
                e -> seven.getUI().ifPresent(
                        ui -> {
                            pin += "7";
                            refresh();
                        }
                ));
        eight.addClickListener(
                e -> eight.getUI().ifPresent(
                        ui -> {
                            pin += "8";
                            refresh();
                        }
                ));
        nine.addClickListener(
                e -> nine.getUI().ifPresent(
                        ui -> {
                            pin += "9";
                            refresh();
                        }
                ));
        applyBtn.addClickListener(
                e -> applyBtn.getUI().ifPresent(
                        ui -> {
                            userId = loginService.getActuallyActiveUserId(pin);
                            if(userId < 0){
                                wrongPinMessage.open();
                            } else {
                                logger.info("Logged user: " +
                                        employeeService.getEmployee(userId).getFirstName() +
                                        employeeService.getEmployee(userId).getLastName());
                                OwnAppContext.getInstance().setActuallyActiveUserId(userId);
                                ui.navigate("main");
                            }
                            pin = "";
                            refresh();
                        }
        ));
    }

    public void refresh(){
        loginFieldValue.setText(pin);
    }
}
