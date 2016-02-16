
package managedBeans;

import dataBaseEntities.meal;
import dataBaseOperations.mealsOperations;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class allMeals {

    public allMeals() {
    }

    private List<meal> meals;

    public List<meal> getMeals() {
        mealsOperations db=new mealsOperations();
        meals=db.returnMeals();
        return this.meals;
    }

    public void setMeals(List<meal> meals) {
        this.meals = meals;
    }

   
    
}
