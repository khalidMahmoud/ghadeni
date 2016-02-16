package dataBaseEntities;

import dataBaseOperations.userOperations;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Entity
@ManagedBean
@SessionScoped
public class user implements Serializable{

    private int id;
    private String name;
    private String email;
    private String password;
    private String phone;

    @Id
    @TableGenerator(name = "g", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "g")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void clear() {
        id = 0;
        name = "";
        email = "";
        password = "";
        phone = "";
    }

    public void register() {
        userOperations db = new userOperations();
        db.insert(this);
        clear();
    }

    public void login() {
        userOperations db = new userOperations();
        List<user> ls = db.selectUser(email, password);
        for (user u : ls) {
            id = u.id;
            name = u.name;
            email = u.email;
            password = u.password;
            phone = u.phone;
        }
        if (ls.isEmpty()) {
            return;
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            HttpSession session = request.getSession(true);
            session.setAttribute("userId", id);
            session.setAttribute("userEmail", email);
            FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "/home");
        
        
        }
    }

    public void validatePassword(ComponentSystemEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        UIComponent component = event.getComponent();

        //get password
        UIInput uiInputPassword = (UIInput) component.findComponent("password");
        String password = uiInputPassword.getLocalValue() == null ? "" : uiInputPassword.getLocalValue().toString();
        String passwordId = uiInputPassword.getClientId();

        //get confirm password
        UIInput uiInputConfirmPassword = (UIInput) component.findComponent("confirmPassword");
        String confirmPassword = uiInputConfirmPassword.getLocalValue() == null ? "" : uiInputConfirmPassword.getLocalValue().toString();

        if (password.isEmpty() || confirmPassword.isEmpty()) {
            return;
        }
        if (!password.equals(confirmPassword)) {
            FacesMessage message = new FacesMessage("password must match confirm password");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(passwordId, message);
            context.renderResponse();
        }
    }
}
