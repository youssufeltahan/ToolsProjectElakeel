package toolsProject;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "restaurant")
public class Restaurant {
//	private EntityManager entityManager;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy="restaurant" ,fetch = FetchType.LAZY)
    private List<Runner>runner;


    @OneToMany(mappedBy="restaurant" ,fetch = FetchType.LAZY)
    private List<Meal>meals;

    @OneToMany(mappedBy="restaurant" ,fetch = FetchType.LAZY)
    private List<Order>orders;

    public Restaurant() {
    }

    public Restaurant(String name) {
        this.name = name;
    }

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

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }
    public static List<Meal> GetMeals(List<Meal> meals) {
        return meals;
    }

    
}
//	public void setMenu(List<Meal> menu) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	public RestaurantOwner getOwner() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public Object calculateTotalEarnings() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public Object getNumberOfCompletedOrders() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public Object getNumberOfCanceledOrders() {
//		// TODO Auto-generated method stub
//		return null;
//	}

