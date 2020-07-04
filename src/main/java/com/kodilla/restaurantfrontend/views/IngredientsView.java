package com.kodilla.restaurantfrontend.views;

import com.kodilla.restaurantfrontend.domain.Ingredient;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@Route("ingredients")
public class IngredientsView extends VerticalLayout {
    Button mainViewBtn = new Button("Strona główna");
    private Grid<Ingredient> grid = new Grid<>(Ingredient.class);

    public IngredientsView() {
        addClickListeners();
        setGridProperties();
        add(
                mainViewBtn,
                grid
        );
    }

    public void addClickListeners(){
        mainViewBtn.addClickListener(
                e -> mainViewBtn.getUI().ifPresent(
                        ui -> ui.navigate("")
                ));
    }

    public void setGridProperties(){
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient(1l, "nazwa1", "typ1", 1.0, "kg", 1.00, "opis"));
//        ingredients.add(new Ingredient("nazwa2", "typ1", 1.0, "kg", 1.00, "opis"));
//        ingredients.add(new Ingredient("nazwa2", "typ1", 1.0, "kg", 1.00, "opis"));
        grid.setItems(ingredients);
        grid.setColumns("id", "name", "type", "quantity", "measureUnit", "price", "description");
//        grid.getColumnByKey("name").setHeader("Nazwa");
//        grid.getColumnByKey("type").setHeader("Rodzaj");
//        grid.getColumnByKey("quantity").setHeader("Ilość");
//        grid.getColumnByKey("measureUnit").setHeader("Jednostka miary");
//        grid.getColumnByKey("price").setHeader("Cena");
//        grid.getColumnByKey("description").setHeader("Opis");
    }
}
