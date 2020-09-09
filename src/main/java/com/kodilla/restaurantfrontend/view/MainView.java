package com.kodilla.restaurantfrontend.view;

import com.kodilla.restaurantfrontend.context.OwnAppContext;
import com.kodilla.restaurantfrontend.domain.Employee;
import com.kodilla.restaurantfrontend.domain.Role;
import com.kodilla.restaurantfrontend.service.EmployeeService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.router.Route;

@Route("main")
public class MainView extends VerticalLayout {
    private Button tableOrdersBtn = new Button("Zamówienia");
    private Button reportBtn = new Button("Raporty");
    private Button managementBtn = new Button("Zarządzanie");
    private Button logoutBtn = new Button("Wyloguj");
    private Label loggedLabel = new Label();
    private Label loggedUserNameLabel = new Label();
    private HorizontalLayout labels = new HorizontalLayout();
    private Dialog dontHavePrivilegesMessage = new Dialog();
    private EmployeeService employeeService = new EmployeeService();
    private Employee loggedEmployee;

    public MainView() {
        loggedEmployee = employeeService.getEmployee(OwnAppContext.getInstance().getActuallyActiveUserId());
        addClickListeners();
        setLabels();
        labels.add(loggedLabel, loggedUserNameLabel);
        dontHavePrivilegesMessage.add(new Label("Nie masz wystarczających uprawnień!"));
        add(
                labels,
                tableOrdersBtn,
                reportBtn,
                managementBtn,
                logoutBtn,
                dontHavePrivilegesMessage
        );
    }

    public void addClickListeners(){
        tableOrdersBtn.addClickListener(
                e -> tableOrdersBtn.getUI().ifPresent(
                        ui -> ui.navigate("tableOrders")
                ));
        reportBtn.addClickListener(
                e -> reportBtn.getUI().ifPresent(
                        ui -> ui.navigate("reports")
                ));
        managementBtn.addClickListener(
                e -> managementBtn.getUI().ifPresent(
                        ui -> {
                            if(employeeService.getRole(OwnAppContext.getInstance().getActuallyActiveUserId()) == Role.manager){
                                ui.navigate("management");
                            } else {
                                dontHavePrivilegesMessage.open();
                            }
                        }
                )
        );
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
