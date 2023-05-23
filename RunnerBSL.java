package toolsProject;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
@Stateless
@Path("/Runner")
public class RunnerBSL {
	@PersistenceContext
	private EntityManager entityManager;
	@GET
	@Path("/tripCount")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public String getNumOfTrips() {
		return Response.ok("Number of trips is : " + entityManager.createQuery("SELECT count(r.orders) FROM Runner r")).build().toString();
	}
	@PUT
	@Path("/completeOrder/{OrderID}")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public String markOrderAsCompleted(@PathParam("OrderID") int OrderID) {
		String queryString = "SELECT e FROM Order e";
    	javax.persistence.Query query = entityManager.createQuery(queryString);
    	List<Order> orders = query.getResultList();
    	for (Order order : orders) {
    		if (order.getId() == OrderID) {
    			if (!order.getOrderStatus().equals("delivered")) {
    				order.setOrderStatus("delivered");
    				entityManager.merge(order);
    				return "Order is Delivered";
    			}
    				
    		}
    	}
    	return "Something wrong happened :(";	
	}
	@PUT
	@Path("/receiveOrder/{runnerID}/{deliveryFees}")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public String receiveOrder(@PathParam("runnerID") int runnnerID ,@PathParam("deliveryFees") int deliveryFees) {
		String queryString = "SELECT e FROM Runner e";
    	javax.persistence.Query query = entityManager.createQuery(queryString);
    	List<Runner> runners = query.getResultList();
    	for (Runner runner : runners) {
    		if (runner.getId() == runnnerID) {
    				runner.setStatus("Busy");
    				runner.setDeliveryFees(deliveryFees);
    				entityManager.merge(runner);
    				return "Your Status now is busy";
    			}
    		}
    	return "something wrong happened :(";
	}
	private activeUser activeUser;
	public void setActiveUser(activeUser activeUser) {
		this.activeUser = activeUser;
	}
	public activeUser getActiveUser() {
		return activeUser;
	}
	
}
