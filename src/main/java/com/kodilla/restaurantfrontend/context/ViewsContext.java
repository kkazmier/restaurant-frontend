package com.kodilla.restaurantfrontend.context;

import com.kodilla.restaurantfrontend.domain.Dish;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public final class ViewsContext {
    private static ViewsContext viewsContextInstance = null;

    private Dish selectedDishInDishView;

    private ViewsContext(){

    }

    public static ViewsContext getInstance(){
        if(viewsContextInstance == null){
            synchronized (ViewsContext.class){
                if (viewsContextInstance == null){
                    viewsContextInstance = new ViewsContext();
                }
            }
        }
        return viewsContextInstance;
    }
}
