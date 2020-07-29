package com.kodilla.restaurantfrontend.forms;

import com.kodilla.restaurantfrontend.domain.TableOrder;
import com.kodilla.restaurantfrontend.service.TableOrderService;
import com.kodilla.restaurantfrontend.views.TableOrdersView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
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
        binder.bindInstanceFields(this);
    }
}
