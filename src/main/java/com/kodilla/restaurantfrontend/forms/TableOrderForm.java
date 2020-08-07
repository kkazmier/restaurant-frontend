package com.kodilla.restaurantfrontend.forms;

import com.kodilla.restaurantfrontend.context.OwnAppContext;
import com.kodilla.restaurantfrontend.domain.TableOrder;
import com.kodilla.restaurantfrontend.service.TableOrderService;
import com.kodilla.restaurantfrontend.views.TableOrdersView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TableOrderForm extends FormLayout {
    private final Logger logger = LoggerFactory.getLogger(TableOrderForm.class);
    private TableOrdersView tableOrdersView;
    private TextField status = new TextField("Status");
    private TextField description = new TextField("Opis");
    private Button newBtn = new Button("Nowy");
    private Button editBtn = new Button("Edytuj zamówienie");
    private Button deleteBtn = new Button("Usuń");
    private TableOrderService tableOrderService = new TableOrderService();
    private Binder<TableOrder> binder = new Binder<>(TableOrder.class);

    public TableOrderForm(TableOrdersView tableOrdersView) {
        this.tableOrdersView = tableOrdersView;
        HorizontalLayout buttons = new HorizontalLayout(newBtn, editBtn, deleteBtn);
        add(status, description, buttons);
        addClickListeners();
        binder.bindInstanceFields(this);
        TableOrder order = new TableOrder();
        binder.setBean(order);
    }

    public void addClickListeners(){
        editBtn.addClickListener(e -> editBtn.getUI().ifPresent(
                ui -> {
                    if(OwnAppContext.getInstance().getSelectedTableOrderInTableOrdersView() != null) {
                        ui.navigate("editTableOrder");
                    } else {
                        Notification.show("Zamówienie nie zostało wybrane!");
                    }
                }
        ));
        newBtn.addClickListener(e -> newOrder());
        deleteBtn.addClickListener(e -> delete());
    }

    public void newOrder(){

    }

    public void delete(){

    }

    public Binder<TableOrder> getBinder(){
        return binder;
    }
}
