package com.kodilla.restaurantfrontend.context;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public final class OwnAppContext {
    private static OwnAppContext ownAppContextInstance = null;

    private Long selectedDishInDishViewId;
    private Long selectedEmployeeInEmployeeViewId;
    private Long selectedTableOrderInTableOrdersViewId;
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
