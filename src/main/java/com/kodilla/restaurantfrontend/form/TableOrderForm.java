package com.kodilla.restaurantfrontend.form;

import com.kodilla.restaurantfrontend.context.OwnAppContext;
import com.kodilla.restaurantfrontend.domain.TableOrder;
import com.kodilla.restaurantfrontend.service.TableOrderService;
import com.kodilla.restaurantfrontend.view.TableOrdersView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.binder.Binder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TableOrderForm extends FormLayout {
    private final Logger logger = LoggerFactory.getLogger(TableOrderForm.class);
    private TableOrdersView tableOrdersView;
    private Button newBtn = new Button("Nowe");
    private Button editBtn = new Button("Edytuj");
    private Button deleteBtn = new Button("Usuń");
    private Dialog notChoseOrderMessage = new Dialog();
    private TableOrderService tableOrderService = new TableOrderService();

    public TableOrderForm(TableOrdersView tableOrdersView) {
        this.tableOrdersView = tableOrdersView;
        HorizontalLayout buttons = new HorizontalLayout(newBtn, editBtn, deleteBtn);
        notChoseOrderMessage.add("Zamówienie nie zostało wybrane!");
        add(
                buttons,
                notChoseOrderMessage
        );
        addClickListeners();
    }

    public void addClickListeners(){
        editBtn.addClickListener(e -> editBtn.getUI().ifPresent(
                ui -> {
                    if(OwnAppContext.getInstance().getSelectedTableOrderInTableOrdersViewId() != null) {
                        ui.navigate("editTableOrder");
                    } else {
                        notChoseOrderMessage.open();
                    }
                }
        ));
        newBtn.addClickListener(e -> newOrder());
        deleteBtn.addClickListener(e -> deleteOrder());
    }

    public void newOrder(){
        Long empId = OwnAppContext.getInstance().getActuallyActiveUserId();
        logger.info("User id: " + empId);
        if(empId != null){
            tableOrderService.createTableOrder(new TableOrder(), empId);
            tableOrdersView.refresh();
        } else {
            logger.info("Emp id is null.");
        }
    }

    public void deleteOrder(){
        Long empId = OwnAppContext.getInstance().getActuallyActiveUserId();
        Long orderId = tableOrdersView.getSelectedOrderId();
        if(orderId == null){
            logger.info("Selected order id is null!");
        } else {
            logger.info("Delete order has id: " + orderId);
            tableOrderService.deleteTableOrder(orderId, empId);
            tableOrdersView.refresh();
        }
    }
}
