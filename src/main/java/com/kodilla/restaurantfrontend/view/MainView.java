package com.kodilla.restaurantfrontend.view;

import com.kodilla.restaurantfrontend.context.OwnAppContext;
import com.kodilla.restaurantfrontend.domain.Employee;
import com.kodilla.restaurantfrontend.service.EmployeeService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.router.Route;

@Route("main")
public class MainView extends VerticalLayout {
    private Button ingredientsViewBtn = new Button("Składniki");
    private Button dishesViewBtn = new Button("Dania");
    private Button tableOrdersBtn = new Button("Zamówienia");
    private Button employeesBtn = new Button("Pracownicy");
    private Button logoutBtn = new Button("Wyloguj");
    private Label loggedLabel = new Label();
    private Label loggedUserNameLabel = new Label();
    private HorizontalLayout labels = new HorizontalLayout();
    private EmployeeService employeeService = new EmployeeService();
    private Employee loggedEmployee;

    public MainView() {
        loggedEmployee = employeeService.getEmployee(OwnAppContext.getInstance().getActuallyActiveUserId());
        addClickListeners();
        setLabels();
        labels.add(loggedLabel, loggedUserNameLabel);
        add(
                labels,
                ingredientsViewBtn,
                dishesViewBtn,
                tableOrdersBtn,
                employeesBtn,
                logoutBtn
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
        logoutBtn.addClickListener(
                e -> logoutBtn.getUI().ifPresent(
                        ui -> {
                            OwnAppContext.getInstance().setActuallyActiveUserId(-1l);
                            ui.navigate("login");
                        }
                ));
    }

    public void setLabels(){
        loggedLabel.getStyle().set("fontWeight", "bold");
        loggedLabel.getStyle().set("fontSize", "24px");
        loggedUserNameLabel.getStyle().set("fontSize", "24px");
        loggedLabel.setText("Zalogowany: ");
        loggedUserNameLabel.setText(loggedEmployee.getFirstName() + " " + loggedEmployee.getLastName());
    }
}
