package com.kodilla.restaurantfrontend.views;

import com.kodilla.restaurantfrontend.domain.Ingredient;
import com.kodilla.restaurantfrontend.service.DishService;
import com.kodilla.restaurantfrontend.service.IngredientService;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Route("editDish")
public class EditDishView extends VerticalLayout {
    private Logger logger = LoggerFactory.getLogger(EditDishView.class);
    private Button saveBtn = new Button("Zapisz");
    private Button cancelBtn = new Button("Anuluj");
    private HorizontalLayout buttons = new HorizontalLayout();
    private Grid<Ingredient> notDependIngredientsGrid = new Grid<>(Ingredient.class);
    private Grid<Ingredient> dishIngredientsGrid = new Grid<>(Ingredient.class);
    private DishService dishService = new DishService();
    private IngredientService ingredientService = new IngredientService();
    private Long dishId;

    //private ComponentUtil componentUtil;

    public EditDishView() {
        //dishId = VaadinSession.getCurrent().getSession()
        addClickListeners();
        setGridProperties();
        buttons.add(
                saveBtn,
                cancelBtn
        );
        add(
                buttons,
                notDependIngredientsGrid,
                dishIngredientsGrid
        );
    }

    public void addClickListeners(){
        saveBtn.addClickListener(
                e -> saveBtn.getUI().ifPresent(
                        ui -> ui.navigate("dishes")
                ));
        cancelBtn.addClickListener(
                e -> cancelBtn.getUI().ifPresent(
                        ui -> ui.navigate("dishes")
                ));
    }

    public void setGridProperties(){
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
