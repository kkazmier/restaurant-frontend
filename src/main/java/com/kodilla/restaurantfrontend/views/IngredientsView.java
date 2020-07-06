package com.kodilla.restaurantfrontend.views;

import com.kodilla.restaurantfrontend.domain.Ingredient;
import com.kodilla.restaurantfrontend.forms.IngredientForm;
import com.kodilla.restaurantfrontend.service.IngredientService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("ingredients")
public class IngredientsView extends VerticalLayout {
    private IngredientService service = new IngredientService();
    Button mainViewBtn = new Button("Strona główna");
    private Grid<Ingredient> grid = new Grid<>(Ingredient.class);
    private IngredientForm form = new IngredientForm(this);

    public IngredientsView() {
        addClickListeners();
        setGridProperties();
        add(
                mainViewBtn,
                form,
                grid
        );
        refresh();
    }

    public void addClickListeners(){
        mainViewBtn.addClickListener(
                e -> mainViewBtn.getUI().ifPresent(
                        ui -> ui.navigate("")
                ));
    }

    public void setGridProperties(){
        grid.setColumns("id", "name", "type", "quantity", "measureUnit", "price", "description");
        grid.getColumnByKey("name").setHeader("Nazwa");
        grid.getColumnByKey("type").setHeader("Rodzaj");
        grid.getColumnByKey("quantity").setHeader("Ilość");
        grid.getColumnByKey("measureUnit").setHeader("Jednostka miary");
        grid.getColumnByKey("price").setHeader("Cena");
        grid.getColumnByKey("description").setHeader("Opis");
    }

    public void refresh(){
        grid.setItems(service.getIngredients());
    }
}
