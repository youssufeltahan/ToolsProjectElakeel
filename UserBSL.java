package toolsProject;

import javax.ws.rs.core.MediaType;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.management.Query;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.persistence.EntityManager;

@Stateless
@Path("/User")
public class UserBSL {
    @PersistenceContext
    private EntityManager entityManager;
    
   

    @GET
    @Path("/signIn/{name}/{password}/{role}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String signIn(@PathParam("name") String name, @PathParam("password") String password,@PathParam ("role") String Role) {
    	String queryString = "SELECT e FROM User e";
    	javax.persistence.Query query = entityManager.createQuery(queryString);
    	List<User> users = query.getResultList();
    	for (User user : users) {
    		if (user.getName().equals(name) && user.getPassword().equals(password) && user.getRole().equals(Role)) {
    			activeUser activeuser = new activeUser(name,user.getID());
    			if (Role.equals("Customer")) {
    				CustomerBSL customerBSL = new CustomerBSL();
        			customerBSL.setActiveUser(activeuser);
    			}
    			else if (Role.equals("Runner")) {
    				RunnerBSL runnerBSL = new RunnerBSL();
        			runnerBSL.setActiveUser(activeuser);
    			}
    			else if (Role.equals("Restraunat owner")) {
    				RestaurantOwnerBSL restaurantOwnerBSL = new RestaurantOwnerBSL();
        			restaurantOwnerBSL.setActiveUser(activeuser);
    			}
    			int id = user.getID();
    			return "Welcome " + name + id ;
    	    }
    	}
    		return"Wrong Credentials";
    }
        

    @POST
    @Path("/signUp")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String signUp(User user) {
        String role = user.getRole();
    	
        if (role.equals("Customer")) {
        	Customer customer = new Customer();
        	customer.setId(user.getID());
        	customer.setName(user.getName());
        	customer.setPassword(user.getPassword());
        	try {
            	entityManager.persist(customer);
            	return "user signed up successfully";
    		} catch (PersistenceException persistenceException) {
    			return "Failed";
    		}
        } 
        else if (role.equals("Runner")) {
        	Runner runner = new Runner();
        	runner.setId(user.getID());
        	runner.setName(user.getName());
        	runner.setPassword(user.getPassword());
        	runner.setStatus("Available");
        	try {
            	entityManager.persist(runner);
            	return "user signed up successfully";
    		}
        	 catch (PersistenceException persistenceException) {
     			return "Failed";
     		}
        } 
        else if (role.equals("Restraunat owner")) {
        	RestaurantOwner restaurentOwner = new RestaurantOwner();
        	restaurentOwner.setId(user.getID());
        	restaurentOwner.setName(user.getName());
        	restaurentOwner.setPassword(user.getPassword());

        }
        return "Failed";
        
        
    }
    
}

