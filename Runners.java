package toolsProject;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "runner")
public class Runner  {
	
    public static final String Status = null;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;
    
    @Column(name="password")
    private String password;
    
    @Column(name = "status")
    private String status;

    @Column(name = "delivery_fees")
    private double deliveryFees;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="restaurant_id")
    private Restaurant restaurant;

    @OneToMany(mappedBy ="runner", fetch = FetchType.LAZY)
    private List<Order> orders;


    public Runner() {
    }

    public Runner(String name, String status, double deliveryFees) {
        this.name = name;
        this.status = status;
        this.deliveryFees = deliveryFees;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getDeliveryFees() {
        return deliveryFees;
    }

    public void setDeliveryFees(double deliveryFees) {
        this.deliveryFees = deliveryFees;
    }
    public void setPassword(String password) {
		this.password = password;
	}
    public String getPassword() {
		return password;
	}
}
