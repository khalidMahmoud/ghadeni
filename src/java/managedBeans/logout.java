package managedBeans;

import dataBaseOperations.orderOperations;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class logout {

    public logout() {
    }

    public void logout(int userId){
        orderOperations db=new orderOperations();
        db.deleteBill(userId);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("userId");
        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "/index");
    }
}
