
package managedBeans;

import dataBaseEntities.orders;
import dataBaseOperations.orderOperations;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
@ManagedBean
@SessionScoped
public class orderList {

    public orderList() {
    }
    private orders o=new orders();
    orderOperations db=new orderOperations();
    private List<orders> order;
    
    public List get(int userId){
        order=db.order(userId);
        return this.order;
    }
    
}
