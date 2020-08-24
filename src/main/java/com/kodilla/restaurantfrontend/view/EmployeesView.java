package com.kodilla.restaurantfrontend.view;

import com.kodilla.restaurantfrontend.context.OwnAppContext;
import com.kodilla.restaurantfrontend.domain.Employee;
import com.kodilla.restaurantfrontend.service.EmployeeService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.selection.SingleSelect;
import com.vaadin.flow.router.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Route("employees")
public class EmployeesView extends VerticalLayout {
    private Logger logger = LoggerFactory.getLogger(EmployeesView.class);
    private Button mainViewBtn = new Button("Strona główna");
    private Button newViewBtn = new Button("Nowy");
    private Button editViewBtn = new Button("Edytuj");
    private EmployeeService employeeService = new EmployeeService();
    private Grid<Employee> employeeGrid = new Grid<>(Employee.class);
    private SingleSelect<Grid<Employee>, Employee> selectedRow = employeeGrid.asSingleSelect();
    private Dialog notChoseEmpMessage = new Dialog();
    private Employee selectedEmployee;

    public EmployeesView() {
        addClickListeners();
        setGridProperties();
        notChoseEmpMessage.add("Pracownik nie został wybrany!");
        add(
                mainViewBtn,
                newViewBtn,
                editViewBtn,
                employeeGrid,
                notChoseEmpMessage
        );
        refresh();
    }

    public void setGridProperties(){
        employeeGrid.setColumns(
                "id",
                "firstName",
                "lastName",
                "email",
                "phone",
                "PIN"
        );
        employeeGrid.getColumnByKey("id").setHeader("id");
        employeeGrid.getColumnByKey("firstName").setHeader("imię");
        employeeGrid.getColumnByKey("lastName").setHeader("nazwisko");
        employeeGrid.getColumnByKey("email").setHeader("email");
        employeeGrid.getColumnByKey("phone").setHeader("telefon");
        employeeGrid.getColumnByKey("PIN").setHeader("PIN");
        //employeeGrid.getColumnByKey("").setHeader("");
    }

    public void addClickListeners(){
        mainViewBtn.addClickListener(
                e -> mainViewBtn.getUI().ifPresent(
                        ui -> ui.navigate("main")
                ));
        editViewBtn.addClickListener(
                e -> editViewBtn.getUI().ifPresent(
                        ui -> {
                            if(selectedRow != null) {
                                logger.info(selectedRow.toString());
                                ui.navigate("editEmployee");
                            } else {
                                notChoseEmpMessage.open();
                            }
                        }
                ));
        newViewBtn.addClickListener(
                e -> newViewBtn.getUI().ifPresent(
                        ui -> ui.navigate("newEmployee")
                ));
        selectedRow.addValueChangeListener(
                e -> {
                    selectedEmployee = e.getValue();
                    OwnAppContext.getInstance()
                            .setSelectedEmployeeInEmployeeViewId(Long.parseLong(selectedEmployee.getId()));
                });
    }

    public void refresh(){
        employeeGrid.setItems(employeeService.getEmployees());
    }

    public Long getSelectedEmployeeId(){
        return Long.parseLong(selectedEmployee.getId());
    }
}
