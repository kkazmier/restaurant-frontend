package com.kodilla.restaurantfrontend.form;

import com.kodilla.restaurantfrontend.context.OwnAppContext;
import com.kodilla.restaurantfrontend.domain.Dish;
import com.kodilla.restaurantfrontend.service.DishService;
import com.kodilla.restaurantfrontend.view.DishesView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DishForm extends FormLayout {
    private final Logger logger = LoggerFactory.getLogger(DishForm.class);

    private DishesView dishesView;
    private TextField name = new TextField("Nazwa");
    private TextField type = new TextField("Rodzaj");
    private TextField price = new TextField("Cena");
    private TextField description = new TextField("Opis");
    private Button newBtn = new Button("Nowy");
    private Button editBtn = new Button("Edytuj składniki");
    private Button deleteBtn = new Button("Usuń");
    private Binder<Dish> binder = new Binder<>(Dish.class);
    private DishService service = new DishService();


    public DishForm(DishesView dishesView){
        this.dishesView = dishesView;
        HorizontalLayout buttons = new HorizontalLayout(newBtn, editBtn, deleteBtn);
        add(name, type, price, description, buttons);
        binder.bindInstanceFields(this);
        Dish dish = new Dish();
        dish.setId("0");
        dish.setName("nazwa");
        dish.setType("rodzaj");
        dish.setPrice("0.00");
        dish.setDescription("opis");
        binder.setBean(dish);
        addClickListeners();
    }

    public void addClickListeners(){
        editBtn.addClickListener(e -> editBtn.getUI().ifPresent(
                ui -> {
                    if(OwnAppContext.getInstance().getSelectedDishInDishViewId() != null) {
                        ui.navigate("editDish");
                    } else {
                        Notification.show("Danie nie zostało wybrane!");
                    }
                }
        ));
        newBtn.addClickListener(e -> newDish());
        deleteBtn.addClickListener(e -> delete());
    }

    public void newDish(){
        Dish dish = binder.getBean();
        logger.info(dish.toString());
        if(dish != null){
            service.createDish(dish);
            logger.info("Create: " + dish.toString());
            dishesView.refresh();
        }
    }

    public void delete(){
        service.deleteDish(dishesView.getIdSelectedDish());
        dishesView.refresh();
    }

    public Binder<Dish> getBinder(){
        return binder;
    }
}
