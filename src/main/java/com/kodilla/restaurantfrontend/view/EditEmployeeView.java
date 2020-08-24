package com.kodilla.restaurantfrontend.view;

import com.kodilla.restaurantfrontend.context.OwnAppContext;
import com.kodilla.restaurantfrontend.domain.Employee;
import com.kodilla.restaurantfrontend.service.EmployeeService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.time.LocalDateTime;

@Route("editEmployee")
public class EditEmployeeView extends VerticalLayout {
    private TextField firstName = new TextField("Imię");
    private TextField lastName = new TextField("Nazwisko");
    private TextField email = new TextField("Email");
    private TextField phone = new TextField("Telefon");
    private TextField PIN = new TextField("PIN");
    private Button saveBtn = new Button("Zapisz");
    private Employee employee;
    private EmployeeService service = new EmployeeService();

    public EditEmployeeView() {
        initFields();
        add(
                firstName,
                lastName,
                email,
                phone,
                PIN,
                saveBtn
        );
        saveBtn.addClickListener(e -> saveBtn.getUI().ifPresent(
                ui -> {
                    setEmployee();
                    service.updateEmployee(employee);
                    ui.navigate("employees");
                }
        ));
    }

    public void initFields(){
        employee = service.getEmployee(OwnAppContext.getInstance().getSelectedEmployeeInEmployeeViewId());
        firstName.setValue(employee.getFirstName());
        lastName.setValue(employee.getLastName());
        phone.setValue(employee.getPhone());
        email.setValue(employee.getEmail());
        PIN.setValue(employee.getPIN());
    }

    public void setEmployee(){
        employee.setFirstName(firstName.getValue());
        employee.setLastName(lastName.getValue());
        employee.setPhone(phone.getValue());
        employee.setEmail(email.getValue());
        employee.setHireDate(LocalDateTime.now());
        employee.setPIN(PIN.getValue());
    }
}
