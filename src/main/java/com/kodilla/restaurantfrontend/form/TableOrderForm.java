package com.kodilla.restaurantfrontend.form;

import com.kodilla.restaurantfrontend.context.OwnAppContext;
import com.kodilla.restaurantfrontend.domain.TableOrder;
import com.kodilla.restaurantfrontend.service.TableOrderService;
import com.kodilla.restaurantfrontend.view.TableOrdersView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;


public class TableOrderForm extends FormLayout {
    private final Logger logger = LoggerFactory.getLogger(TableOrderForm.class);
    private TableOrdersView tableOrdersView;
    private TextField status = new TextField("Status");
    private TextField description = new TextField("Uwagi");
    private Button newBtn = new Button("Nowe");
    private Button editBtn = new Button("Edytuj");
    private Button deleteBtn = new Button("Usuń");
    private Dialog notChoseOrderMessage = new Dialog();
    private TableOrderService tableOrderService = new TableOrderService();
    private Binder<TableOrder> binder = new Binder<>(TableOrder.class);

    public TableOrderForm(TableOrdersView tableOrdersView) {
        this.tableOrdersView = tableOrdersView;
        HorizontalLayout buttons = new HorizontalLayout(newBtn, editBtn, deleteBtn);
        notChoseOrderMessage.add("Zamówienie nie zostało wybrane!");
        add(
                status,
                description,
                buttons,
                notChoseOrderMessage
        );
        addClickListeners();
        binder.bindInstanceFields(this);
        TableOrder order = new TableOrder();
        order.setId("0");
        order.setStatus("Otwarte");
        order.setCreatedTime(LocalDateTime.now());
        order.setClosedTime(LocalDateTime.now());
        order.setTotalCost("0.00");
        order.setDescription("...");
        order.setName("...");
        binder.setBean(order);
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
        TableOrder order = binder.getBean();
        Long empId = OwnAppContext.getInstance().getActuallyActiveUserId();
        logger.info(order.toString());
        logger.info("User id: " + empId);
        if(order != null && empId != null){
            tableOrderService.createTableOrder(order, empId);
            tableOrdersView.refresh();
        } else {
            logger.info("Order or/and empId is/are null.");
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

    public Binder<TableOrder> getBinder(){
        return binder;
    }
}
