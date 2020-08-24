package com.kodilla.restaurantfrontend.view;

import com.kodilla.restaurantfrontend.domain.Employee;
import com.kodilla.restaurantfrontend.service.EmployeeService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.time.LocalDateTime;

@Route("newEmployee")
public class NewEmployeeView extends VerticalLayout {
    private TextField firstName = new TextField("Imię");
    private TextField lastName = new TextField("Nazwisko");
    private TextField email = new TextField("Email");
    private TextField phone = new TextField("Telefon");
    private TextField PIN = new TextField("PIN");
    private Button createBtn = new Button("Utwórz");
    private Employee employee = new Employee();
    private EmployeeService service = new EmployeeService();

    public NewEmployeeView() {
        add(
                firstName,
                lastName,
                email,
                phone,
                PIN,
                createBtn
        );
        createBtn.addClickListener(e -> createBtn.getUI().ifPresent(
                ui -> {
                    setEmployee();
                    service.addEmployee(employee);
                    ui.navigate("employees");
                }
        ));
    }

    public void setEmployee(){
        employee.setId("0");
        employee.setFirstName(firstName.getValue());
        employee.setLastName(lastName.getValue());
        employee.setPhone(phone.getValue());
        employee.setEmail(email.getValue());
        employee.setHireDate(LocalDateTime.now());
        employee.setPIN(PIN.getValue());
    }
}
