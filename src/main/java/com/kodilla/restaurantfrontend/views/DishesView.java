package com.kodilla.restaurantfrontend.views;

import com.kodilla.restaurantfrontend.context.ViewsContext;
import com.kodilla.restaurantfrontend.domain.Dish;
import com.kodilla.restaurantfrontend.domain.Ingredient;
import com.kodilla.restaurantfrontend.forms.DishForm;
import com.kodilla.restaurantfrontend.service.DishService;
import com.kodilla.restaurantfrontend.service.IngredientService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.selection.SingleSelect;
import com.vaadin.flow.router.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Route("dishes")
public class DishesView extends VerticalLayout {
    private Logger logger = LoggerFactory.getLogger(DishesView.class);

    private DishService dishService = new DishService();
    private Button mainViewBtn = new Button("Strona główna");
    private DishForm form = new DishForm(this);
    private Grid<Dish> dishGrid = new Grid<>(Dish.class);
    private SingleSelect<Grid<Dish>, Dish> selectedRow = dishGrid.asSingleSelect();
    private Dish selectedDish;

    public DishesView() {
        addClickListeners();
        add(
                mainViewBtn,
                form,
                dishGrid
        );
        setGridProperties();
        refresh();
    }

    public void setGridProperties(){
        dishGrid.setColumns("id", "name", "type", "price", "description");
        dishGrid.getColumnByKey("name").setHeader("Nazwa");
        dishGrid.getColumnByKey("type").setHeader("Rodzaj");
        dishGrid.getColumnByKey("price").setHeader("Cena");
        dishGrid.getColumnByKey("description").setHeader("Opis");
    }

    public void addClickListeners(){
        mainViewBtn.addClickListener(
                e -> mainViewBtn.getUI().ifPresent(
                        ui -> ui.navigate("")
                ));
        selectedRow.addValueChangeListener(e -> {
            selectedDish = e.getValue();
            form.getBinder().setBean(selectedDish);
            ViewsContext.getInstance().setSelectedDishInDishView(selectedDish);
            logger.info("select: " + selectedDish.toString());
        });
    }

    public void refresh(){
        dishGrid.setItems(dishService.getDishes());
        logger.info("load dishes");
    }

    public Long getIdSelectedDish(){
        if(selectedDish != null){
            return Long.parseLong(selectedDish.getId());
        }
        else {
            return -1L;
        }
    }

    public Dish getSelectedDish() {
        return selectedDish;
    }
}
