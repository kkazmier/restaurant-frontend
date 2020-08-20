package com.kodilla.restaurantfrontend.context;

import com.kodilla.restaurantfrontend.domain.Dish;
import com.kodilla.restaurantfrontend.domain.Employee;
import com.kodilla.restaurantfrontend.domain.TableOrder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public final class OwnAppContext {
    private static OwnAppContext ownAppContextInstance = null;

    private Dish selectedDishInDishView;
    private Employee selectedEmployeeInEmployeeView;
    private TableOrder selectedTableOrderInTableOrdersView;
    private Long actuallyActiveUserId;

    private OwnAppContext(){ }

    public static OwnAppContext getInstance(){
        if(ownAppContextInstance == null){
            synchronized (OwnAppContext.class){
                if (ownAppContextInstance == null){
                    ownAppContextInstance = new OwnAppContext();
                }
            }
        }
        return ownAppContextInstance;
    }
}
