package com.kodilla.restaurantfrontend.view;

import com.kodilla.restaurantfrontend.context.OwnAppContext;
import com.kodilla.restaurantfrontend.domain.Employee;
import com.kodilla.restaurantfrontend.service.EmployeeService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("management")
public class ManagementView extends VerticalLayout {
    private Button ingredientsViewBtn = new Button("Składniki");
    private Button dishesViewBtn = new Button("Dania");
    private Button employeesBtn = new Button("Pracownicy");
    private Button backBtn = new Button("Powrót");
    private EmployeeService employeeService = new EmployeeService();
    private Employee loggedEmployee;

    public ManagementView() {
        loggedEmployee = employeeService.getEmployee(OwnAppContext.getInstance().getActuallyActiveUserId());
        addClickListeners();
        add(
                ingredientsViewBtn,
                dishesViewBtn,
                employeesBtn,
                backBtn
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
        employeesBtn.addClickListener(
                e -> employeesBtn.getUI().ifPresent(
                        ui -> ui.navigate("employees")
                ));
        backBtn.addClickListener(
                e -> backBtn.getUI().ifPresent(
                        ui -> ui.navigate("main")
                ));
    }
}
