package dataBaseEntities;

import dataBaseOperations.mealsOperations;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.*;

@ManagedBean
@SessionScoped
@Entity
public class meal implements Serializable {

    private int id;
    private String name;
    private String price;
    private String description;

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addNewMeal(){
        mealsOperations db=new mealsOperations();
        db.addMeal(this);
    }
}
