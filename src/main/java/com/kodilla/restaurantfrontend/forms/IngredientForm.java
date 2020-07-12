package com.kodilla.restaurantfrontend.forms;

import com.google.gson.Gson;
import com.kodilla.restaurantfrontend.backend.BackendIngredient;
import com.kodilla.restaurantfrontend.domain.Ingredient;
import com.kodilla.restaurantfrontend.mapper.IngredientMapper;
import com.kodilla.restaurantfrontend.service.IngredientService;
import com.kodilla.restaurantfrontend.views.IngredientsView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToDoubleConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IngredientForm extends FormLayout {
    private final Logger logger = LoggerFactory.getLogger(IngredientForm.class);

    private IngredientsView ingredientsView;
    private TextField id = new TextField("Id");
    private TextField name = new TextField("Nazwa");
    private TextField type = new TextField("Rodzaj");
    private TextField quantity = new TextField("Ilość");
    private TextField measureUnit = new TextField("Jednostka miary");
    private TextField price = new TextField("Cena");
    private TextField description = new TextField("Opis");
    private Button saveBtn = new Button("Zapisz");
    private Button deleteBtn = new Button("Usuń");
    private Binder<Ingredient> binder = new Binder<>(Ingredient.class);
    private IngredientService service = new IngredientService();

    public IngredientForm(IngredientsView ingredientView){
        this.ingredientsView = ingredientView;
        HorizontalLayout buttons = new HorizontalLayout(saveBtn, deleteBtn);
        add(id, name, type, quantity, measureUnit, price, description, buttons);
        binder.bindInstanceFields(this);
        Ingredient ingredient = new Ingredient();
        ingredient.setId("0");
        ingredient.setName("nazwa");
        ingredient.setDescription("opis");
        ingredient.setMeasureUnit("g");
        ingredient.setPrice("0.00");
        ingredient.setQuantity("0.0");
        ingredient.setType("rodzaj");
        binder.setBean(ingredient);
        addClickListeners();
    }

    public void addClickListeners(){
        saveBtn.addClickListener(e -> save());
        deleteBtn.addClickListener(e -> delete());
    }

    private void save(){
        Ingredient ingredient = binder.getBean();
        logger.info("Save: " + ingredient.toString());
        service.saveIngredient(ingredient);
        ingredientsView.refresh();

    }

    private void delete(){
        Ingredient ingredient = binder.getBean();

    }
}
