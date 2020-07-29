package com.kodilla.restaurantfrontend.views;

import com.kodilla.restaurantfrontend.domain.TableOrder;
import com.kodilla.restaurantfrontend.forms.TableOrderForm;
import com.kodilla.restaurantfrontend.service.TableOrderService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.selection.SingleSelect;
import com.vaadin.flow.router.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Route("tableOrders")
public class TableOrdersView extends VerticalLayout {
    private Logger logger = LoggerFactory.getLogger(TableOrdersView.class);
    private Button mainViewBtn = new Button("Strona główna");
    private TableOrderService tableOrderService = new TableOrderService();
    private TableOrderForm form = new TableOrderForm(this);
    private Grid<TableOrder> tableOrderGrid = new Grid<>(TableOrder.class);
    private SingleSelect<Grid<TableOrder>, TableOrder> selectedRow = tableOrderGrid.asSingleSelect();
    private TableOrder selectedTableOrder;

    public TableOrdersView() {
        add(
                mainViewBtn,
                form,
                tableOrderGrid
        );
    }

    public void setGridProperties(){
        tableOrderGrid.setColumns("id", "status", "createdTime", "closedTime", "description");
        tableOrderGrid.getColumnByKey("status").setHeader("Status");
        tableOrderGrid.getColumnByKey("createdTime").setHeader("Czas utworzenia");
        tableOrderGrid.getColumnByKey("closedTime").setHeader("Czas zakończenia");
        tableOrderGrid.getColumnByKey("totalCost").setHeader("Cena całkowita");
        tableOrderGrid.getColumnByKey("description").setHeader("Opis");
    }

    public void addClickListeners(){

    }

    public void refresh(){

    }
}