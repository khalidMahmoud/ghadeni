package dataBaseEntities;

import dataBaseOperations.offerOperations;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.*;

@Entity
@ManagedBean
@SessionScoped
public class offer implements Serializable{

    private int id;
    private String name;
    private String description;
    private String price;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

   public List<offer> viewOffers(){
        offerOperations db = new offerOperations();
        List<offer> offers = db.getOffers();
        return offers;
   }
   
}
