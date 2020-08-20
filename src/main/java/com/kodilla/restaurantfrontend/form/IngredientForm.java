package com.kodilla.restaurantfrontend.form;

import com.kodilla.restaurantfrontend.domain.Ingredient;
import com.kodilla.restaurantfrontend.service.IngredientService;
import com.kodilla.restaurantfrontend.view.IngredientsView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IngredientForm extends FormLayout {
    private final Logger logger = LoggerFactory.getLogger(IngredientForm.class);

    private IngredientsView ingredientsView;
    //private TextField id = new TextField("Id");
    private TextField name = new TextField("Nazwa");
    private TextField type = new TextField("Rodzaj");
    private TextField quantity = new TextField("Ilość");
    private TextField measureUnit = new TextField("Jednostka miary");
    private TextField price = new TextField("Cena");
    private TextField description = new TextField("Opis");
    private Button newBtn = new Button("Nowy");
    private Button saveBtn = new Button("Zapisz");
    private Button deleteBtn = new Button("Usuń");
    private Binder<Ingredient> binder = new Binder<>(Ingredient.class);
    private IngredientService service = new IngredientService();

    public IngredientForm(IngredientsView ingredientView){
        this.ingredientsView = ingredientView;
        HorizontalLayout buttons = new HorizontalLayout(saveBtn, deleteBtn, newBtn);
        add(name, type, quantity, measureUnit, price, description, buttons);
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
        newBtn.addClickListener(e -> newIngredient());
    }

    private void save(){
        Ingredient ingredient = binder.getBean();
        if(ingredient != null){
            logger.info("Save: " + ingredient.toString());
            service.saveIngredient(ingredient);
            ingredientsView.refresh();
        } else {
            logger.info("Ingredient doesn't exist.");
        }
    }

    private void delete(){
        Long id = ingredientsView.getIdSelectedIngredient();
        service.deleteIngredient(id);
        ingredientsView.refresh();
    }

    private void newIngredient(){
        Ingredient ingredient = binder.getBean();
        logger.info("New: " + ingredient.toString());
        service.createIngredient(ingredient);
        ingredientsView.refresh();
    }

    public Binder<Ingredient> getBinder(){
        return binder;
    }
}
