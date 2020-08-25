package com.kodilla.restaurantfrontend.view;

import com.kodilla.restaurantfrontend.context.OwnAppContext;
import com.kodilla.restaurantfrontend.domain.Ingredient;
import com.kodilla.restaurantfrontend.service.DishService;
import com.kodilla.restaurantfrontend.service.IngredientService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Route("editDish")
public class EditDishView extends VerticalLayout {
    private Logger logger = LoggerFactory.getLogger(EditDishView.class);

    private Button backBtn = new Button("Powrót");
    private Label containIngredientsLabel = new Label("Składniki wchodzące w skład dania:");
    private Label notDependIngredientsLabel = new Label("Składniki nie przypisane do dania:");
    private Grid<Ingredient> notDependIngredientsGrid = new Grid<>(Ingredient.class);
    private Grid<Ingredient> dishIngredientsGrid = new Grid<>(Ingredient.class);
    private DishService dishService = new DishService();
    private IngredientService ingredientService = new IngredientService();
    private Long dishId;

    public EditDishView() {
        dishId = OwnAppContext.getInstance().getSelectedDishInDishViewId();
        logger.info("Id selected dish: " + dishId);
        addClickListeners();
        setGridsProperties();
        setLabelsProperties();
        add(
                backBtn,
                notDependIngredientsLabel,
                notDependIngredientsGrid,
                containIngredientsLabel,
                dishIngredientsGrid
        );
        refresh();
    }

    public void addClickListeners(){
        backBtn.addClickListener(
                e -> backBtn.getUI().ifPresent(
                        ui -> ui.navigate("dishes")
                ));
        notDependIngredientsGrid.addItemDoubleClickListener(
                e -> {
                    Long ingredientId = Long.parseLong(e.getItem().getId());
                    dishService.addIngredient(dishId, ingredientId);
                    refresh();
                });
        dishIngredientsGrid.addItemDoubleClickListener(
                e -> {
                    Long ingredientId = Long.parseLong(e.getItem().getId());
                    dishService.removeIngredient(dishId, ingredientId);
                    refresh();
                });
    }

    public void setLabelsProperties(){
        containIngredientsLabel.getStyle().set("fontWeight", "bold");
        notDependIngredientsLabel.getStyle().set("fontWeight", "bold");
    }

    public void setGridsProperties(){
        notDependIngredientsGrid.setColumns("id", "name", "type", "quantity", "measureUnit", "price", "description");
        notDependIngredientsGrid.getColumnByKey("name").setHeader("Nazwa");
        notDependIngredientsGrid.getColumnByKey("type").setHeader("Rodzaj");
        notDependIngredientsGrid.getColumnByKey("quantity").setHeader("Ilość");
        notDependIngredientsGrid.getColumnByKey("measureUnit").setHeader("Jednostka miary");
        notDependIngredientsGrid.getColumnByKey("price").setHeader("Cena");
        notDependIngredientsGrid.getColumnByKey("description").setHeader("Opis");

        dishIngredientsGrid.setColumns("id", "name", "type", "quantity", "measureUnit", "price", "description");
        dishIngredientsGrid.getColumnByKey("name").setHeader("Nazwa");
        dishIngredientsGrid.getColumnByKey("type").setHeader("Rodzaj");
        dishIngredientsGrid.getColumnByKey("quantity").setHeader("Ilość");
        dishIngredientsGrid.getColumnByKey("measureUnit").setHeader("Jednostka miary");
        dishIngredientsGrid.getColumnByKey("price").setHeader("Cena");
        dishIngredientsGrid.getColumnByKey("description").setHeader("Opis");
    }

    public void refresh(){
        dishIngredientsGrid.setItems(dishService.getIngredients(dishId));
        notDependIngredientsGrid.setItems(ingredientService.getIngredientsNotDependToDish());
    }
}
