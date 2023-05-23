package toolsProject;
import java.net.NoRouteToHostException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="orders")
public class Order {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
	private int customerId;
	private LocalDateTime date;
	private double deliveryFees;
    private String item;
    private int totalprice;
    private int fk_runnerid;
    private int fk_restaurantid;
    private String OrderStatus; 
   // private double totalPrice;
//    private OrderStatus orderStatus;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;   
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "runner_id")
    private Runner runner;
    
    public Order() 
    {
    	
    }
    public Order(int id, int totalprice, int fk_runnerid, int fk_restaurantid,String OrderStatus,String item ) {
        this.id = id;
        this.totalprice = totalprice;
        this.fk_runnerid = fk_runnerid;
        this.fk_restaurantid = fk_restaurantid;
        this.OrderStatus = OrderStatus;
        this.item = item;
        
    }
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public int getCustId() {
        return customerId;
    }
    public void setCustId(int customerId) {
        this.customerId = customerId;
    }
    public String getItem() {
        return item;
    }
    public void setItem(String item) {
        this.item = item;
    }
    public int getTotalprice() {
        return totalprice;
    }
    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }
    public int getFk_runnerid() {
        return fk_runnerid;
    }
    public void setFk_runnerid(int fk_runnerid) {
        this.fk_runnerid = fk_runnerid;
    }
    public int getFk_restaurantid() {
        return fk_restaurantid;
    }
    public void setFk_restaurantid(int fk_restaurantid) {
        this.fk_restaurantid = fk_restaurantid;
    }
    public String getOrderStatus() {
        return OrderStatus;
    }
    public void setOrderStatus(String orderStatus) {
        OrderStatus = orderStatus;
    }
    public void setDate(LocalDateTime date) {
		this.date = date;
	}
    public void setDeliveryFees(double deliveryFees) {
		this.deliveryFees = deliveryFees;
	}
    public Order removeItem(Order order) {
    	order.setItem(null);
    	return order;
    	
    } public Order updateItem(Order order,String mealString) {
    	order.setItem(mealString);
    	return order;
    }
//    public void setItems(List<Meal> items) {
//        this.items = items;
//    }
    
    
}
    
