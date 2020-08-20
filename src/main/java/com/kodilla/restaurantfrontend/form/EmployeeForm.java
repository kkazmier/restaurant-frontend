package com.kodilla.restaurantfrontend.form;

import com.kodilla.restaurantfrontend.domain.Employee;
import com.kodilla.restaurantfrontend.service.EmployeeService;
import com.kodilla.restaurantfrontend.view.EmployeesView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmployeeForm extends FormLayout {
    private final Logger logger = LoggerFactory.getLogger(Employee.class);
    private EmployeesView employeesView;
    private TextField firstName = new TextField("Imię");
    private TextField lastName = new TextField("Nazwisko");
    private TextField email = new TextField("Email");
    private TextField phone = new TextField("Telefon");
    private TextField PIN = new TextField("PIN");
    private Button newBtn = new Button("Nowy");
    private Button editBtn = new Button("Edytuj");
    private Button deleteBtn = new Button("Usuń");
    private HorizontalLayout buttons = new HorizontalLayout(newBtn, editBtn, deleteBtn);
    private Binder<Employee> binder = new Binder<>(Employee.class);
    private Dialog notChoseEmpMessage = new Dialog();
    private EmployeeService employeeService = new EmployeeService();

    public EmployeeForm(EmployeesView employeesView) {
        this.employeesView = employeesView;
        addClickListeners();
        notChoseEmpMessage.add("Pracownik nie został wybrany!");
        add(
                firstName,
                lastName,
                email,
                phone,
                PIN,
                buttons,
                notChoseEmpMessage
        );
        Employee employee = new Employee();
        binder.setBean(employee);
        binder.bindInstanceFields(this);
    }

    public void addClickListeners(){
        editBtn.addClickListener(e -> editBtn.getUI().ifPresent(
                ui -> {
                }
        ));
        newBtn.addClickListener(e -> createEmployee());
        deleteBtn.addClickListener(e -> removeEmployee());
    }

    public void createEmployee(){

    }

    public void removeEmployee(){

    }
}
