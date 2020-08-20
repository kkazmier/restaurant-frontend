package com.kodilla.restaurantfrontend.view;

import com.kodilla.restaurantfrontend.domain.Ingredient;
import com.kodilla.restaurantfrontend.form.IngredientForm;
import com.kodilla.restaurantfrontend.service.IngredientService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.selection.SingleSelect;
import com.vaadin.flow.router.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Route("ingredients")
public class IngredientsView extends VerticalLayout {
    private Logger logger = LoggerFactory.getLogger(IngredientsView.class);
    private IngredientService service = new IngredientService();
    private Button mainViewBtn = new Button("Strona główna");
    private Grid<Ingredient> grid = new Grid<>(Ingredient.class);
    private IngredientForm form = new IngredientForm(this);
    private SingleSelect<Grid<Ingredient>, Ingredient> selectedRow = grid.asSingleSelect();
    private Ingredient selectedIngredient;

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
                        ui -> ui.navigate("main")
                ));
        grid.addItemDoubleClickListener(e -> {
            logger.info("double click on: " + e.getItem().toString());

        });
        selectedRow.addValueChangeListener(e -> {
            selectedIngredient = e.getValue();
            form.getBinder().setBean(selectedIngredient);
        });
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

    public Long getIdSelectedIngredient(){
        if(selectedIngredient != null){
            return Long.parseLong(selectedIngredient.getId());
        }
        else {
            return -1L;
        }
    }
}
