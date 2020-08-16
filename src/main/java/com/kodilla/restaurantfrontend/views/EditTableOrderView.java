package com.kodilla.restaurantfrontend.views;

import com.kodilla.restaurantfrontend.context.OwnAppContext;
import com.kodilla.restaurantfrontend.domain.Dish;
import com.kodilla.restaurantfrontend.domain.TableOrder;
import com.kodilla.restaurantfrontend.service.DishService;
import com.kodilla.restaurantfrontend.service.TableOrderService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Route("editTableOrder")
public class EditTableOrderView extends VerticalLayout {
    private Logger logger = LoggerFactory.getLogger(EditTableOrderView.class);
    private Button backBtn = new Button("Powrót");
    private Label availableDishesLabel = new Label("Dostępne dania");
    private Label orderDishesLabel = new Label("Dania w zamówieniu");
    private Grid<Dish> availableDishesGrid = new Grid(Dish.class);
    private Grid<Dish> orderDishesGrid = new Grid(Dish.class);
    private TableOrderService orderService = new TableOrderService();
    private DishService dishService = new DishService();
    private Long orderId;

    public EditTableOrderView() {
        orderId = Long.parseLong(OwnAppContext.getInstance().getSelectedTableOrderInTableOrdersView().getId());
        addClickListeners();
        setGridsProperties();
        setLabelsProperties();
        add(
                backBtn,
                availableDishesLabel,
                availableDishesGrid,
                orderDishesLabel,
                orderDishesGrid
        );
        refresh();
    }

    public void addClickListeners(){
        backBtn.addClickListener(
                e -> backBtn.getUI().ifPresent(
                        ui -> ui.navigate("tableOrders")
                ));
        availableDishesGrid.addItemDoubleClickListener(
                e -> {
                    orderService.addDish(
                            Long.parseLong(e.getItem().getId()),
                            orderId);
                    refresh();
                });
        orderDishesGrid.addItemDoubleClickListener(
                e -> {
                    orderService.removeDish(
                            Long.parseLong(e.getItem().getId()),
                            orderId);
                    refresh();
                });
    }

    public void setLabelsProperties(){
        availableDishesLabel.getStyle().set("fontWeight", "bold");
        orderDishesLabel.getStyle().set("fontWeight", "bold");
    }

    public void setGridsProperties(){
        availableDishesGrid.setColumns("id", "name", "type", "price", "description");
        availableDishesGrid.getColumnByKey("name").setHeader("Nazwa");
        availableDishesGrid.getColumnByKey("type").setHeader("Rodzaj");
        availableDishesGrid.getColumnByKey("price").setHeader("Cena");
        availableDishesGrid.getColumnByKey("description").setHeader("Opis");

        orderDishesGrid.setColumns("id", "name", "type", "price", "description");
        orderDishesGrid.getColumnByKey("name").setHeader("Nazwa");
        orderDishesGrid.getColumnByKey("type").setHeader("Rodzaj");
        orderDishesGrid.getColumnByKey("price").setHeader("Cena");
        orderDishesGrid.getColumnByKey("description").setHeader("Opis");
    }

    public void refresh(){
        availableDishesGrid.setItems(dishService.getDishes());
        orderDishesGrid.setItems(orderService.getDishes(orderId));
    }
}
