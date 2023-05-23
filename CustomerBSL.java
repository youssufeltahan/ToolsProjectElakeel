package toolsProject;
import java.io.ObjectInputFilter.Status;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import javax.ejb.Stateless;
import javax.enterprise.inject.New;
import javax.naming.AuthenticationException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



@Stateless
@Path("/Customer")

public class CustomerBSL {
	private activeUser activeUser;
	public void setActiveUser(activeUser activeUser) {
		this.activeUser = activeUser;
	}
	public activeUser getActiveUser() {
		return activeUser;
	}
	@PersistenceContext
	
	private EntityManager entityManager;
	@POST
	@Path("/createOrder/{resID}/{item}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public String createOrder(@PathParam("resID") int resID, @PathParam("item") String item) {
	    double deliveryfees = 0.0;
	    String runnerString = null;
	    int runnerID = 0;
	    int custID =  1; //activeUser.getActiveUserId();
	    Meal meal = new Meal();
	    // Fetch the desired meal from the database
//	    Meal meal = null;
	    String mealQueryString = "SELECT m FROM Meal m";
	    javax.persistence.Query mealQuery = entityManager.createQuery(mealQueryString);
	    List<Meal> meals = mealQuery.getResultList();
	    for (Meal meal2 : meals) {
	    	if (meal2.getRestaurantid() == resID) {
	    		meal.setId(meal2.getId());
	    		meal.setName(meal2.getName());
	    		meal.setPrice(meal2.getPrice());
	    		meal.setRestaurantid(resID);
	    	}
	    }
	    String queryString = "SELECT e FROM Runner e";
	    javax.persistence.Query query = entityManager.createQuery(queryString);
	    List<Runner> runners = query.getResultList();
	    for (Runner runner : runners) {
	        if (runner.getStatus().equals("Available")) {
	            runnerID = runner.getId();
	            runnerString = runner.getName();
	            deliveryfees = runner.getDeliveryFees();
	        }
	    }

	    String resNameString = "";
	    String queryString2 = "SELECT e FROM Restaurant e";
	    javax.persistence.Query query2 = entityManager.createQuery(queryString2);
	    List<Restaurant> restaurants = query2.getResultList();
	    for (Restaurant restaurant : restaurants) {
	        if (restaurant.getId() == resID) {
	            resNameString = restaurant.getName();
	        }
	    }

	    Order order = new Order();
	    order.setFk_restaurantid(resID);
	    order.setFk_runnerid(runnerID);
	    order.setCustId(custID);
	    order.setItem(item);
	    order.setOrderStatus("Prepared");
	    LocalDateTime currentDateTime = LocalDateTime.now();
	    order.setDate(currentDateTime);
	    order.setTotalprice(meal.getPrice());
	    entityManager.persist(order);

	    return "Order Placed Successfully \n"
	            + "Your Order Details are : \n"
	            + "Meal : " + item + " \n"
	            + "Resaurant : " + resNameString + "\n"
	            + "Delivery Fees : " + deliveryfees + "\n"
	            + "Meal Price : " + meal.getPrice() + "\n"
	            + "Total : " + (meal.getPrice() + deliveryfees) + "\n"
	            + "Runner name : " + runnerString + "\n"
	            + "Date : " + currentDateTime + "\n";
	}

	
	@PUT
	@Path("/updateOrder/{OrderID}/{toDo}/{meal}")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Response editOrder(@PathParam("OrderID") int OrderID,@PathParam("toDo") String toDo,@PathParam("meal") String meal) {
    	String OrdersQuery = "SELECT e FROM Order e";
    	javax.persistence.Query query = entityManager.createQuery(OrdersQuery);
    	List<Order> orders = query.getResultList();
    	for (Order order : orders) {
    		if (order.getId() == OrderID) {
    			if(toDo.equals("Remove")) {
    				order.removeItem(order);
    				entityManager.merge(order);
    			}
    				else if (toDo.equals("Update")) {
    					order.updateItem(order, meal);
    					entityManager.merge(order);
    				}
    			}
    		}
    	
		return Response.status(Response.Status.BAD_REQUEST).build();
	}
	@GET
	@Path("/listRes")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Restaurant> lestRes(){
		return RestaurantOwnerBSL.getRestaurants();
	}
}

