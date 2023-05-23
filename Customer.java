package toolsProject;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;



@Entity
@Table(name = "customer")
public class Customer{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String password;
   // private List<Order> orders;
    public Customer()
    {

    }
    public Customer(int id, String name, String password)
    {
    	 this.id = id;
         this.name = name;
         
         //this.orders = new ArrayList<>();
        
    }
    public void setName(String name) {
		this.name = name;
	}
//    public void setOrders(List<Order> orders) {
//		this.orders = orders;
//	}
   
    public void setPassword(String password) {
		this.password = password;
	}
    public String getName() {
		return name;
	}
    public String getPassword() {
		return password;
	}
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
//   public List<Order> getOrders() {
//	return orders;
//   }

}