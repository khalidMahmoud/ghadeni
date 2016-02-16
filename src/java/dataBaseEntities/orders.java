package dataBaseEntities;

import dataBaseOperations.orderOperations;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.*;

@ManagedBean
@SessionScoped
@Entity
public class orders implements Serializable {

    private int id;
    private int userId;
    private int number=1;
    private String name;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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

    public void insertOrder(int userId,String productName,String productPrice) {
        orderOperations db = new orderOperations();
        setUserId(userId);
        setName(productName);
        setPrice(productPrice);
        db.addToOrder(this);
    }
}
