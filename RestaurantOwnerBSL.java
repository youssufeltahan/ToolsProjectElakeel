package toolsProject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



@Path("/restaurants")
public class RestaurantOwnerBSL{
	private activeUser activeUser;
	public void setActiveUser(activeUser activeUser) {
		this.activeUser = activeUser;
	}
	public activeUser getActiveUser() {
		return activeUser;
	}
	
	private static ArrayList<Restaurant> restaurants = new ArrayList<>();
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@POST
    @Path("/addMeal/{restaurantId}/menu/{mealName}/{mealPrice}")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    @Transactional
	public Response createRestaurantMenu(@PathParam("restaurantId") int restaurantId,
                                         @PathParam("mealName") String mealName,
                                         @PathParam("mealPrice") int mealPrice) {
		Restaurant restaurant = new Restaurant();
		restaurant.setId(1);
		restaurant.setName("ahmed");
        List<Meal> mealList = new ArrayList<>();
        Meal ml = new Meal();
        ml.setRestaurantid(restaurantId);
        ml.setName(mealName);
        ml.setPrice(mealPrice);
        mealList.add(ml);
        entityManager.persist(ml);
        return Response.status(Response.Status.CREATED).entity(ml).build();
    }

    
	 	@PUT
	    @Path("editMenu/{restaurantId}/menu/{mealId}")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response editRestaurantMenu(@PathParam("restaurantId") int restaurantId,
	                                       @PathParam("mealId") int mealId,
	                                       Meal updatedMeal) {
	        
	        Restaurant restaurant = findRestaurantById(restaurantId);
	        Meal meal = findMealById(mealId);

	        if (restaurant == null || meal == null) {
	            return Response.status(Response.Status.NOT_FOUND).build();
	        }

	        if (meal.getRestaurantid()!=restaurantId) {
	            return Response.status(Response.Status.BAD_REQUEST).build();
	        }

	        
	        meal.setName(updatedMeal.getName());
	        meal.setPrice(updatedMeal.getPrice());

	       
	        updateMeal(meal);
	        entityManager.merge(meal);
	        
	        return Response.status(Response.Status.OK).entity(meal).build();
	    }
	 
	
	 public Restaurant findRestaurantById(int restaurantId) {
	        return entityManager.find(Restaurant.class, restaurantId);
	    }

	public Meal updateMeal(Meal meal) {
	        return entityManager.merge(meal);
	    }


	private Meal findMealById(int mealId) {
        return entityManager.find(Meal.class, mealId);}

	@POST
	@Path("/createRes/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public String createRestraunat(@PathParam("name") String resName) {
		Restaurant restaurant = new Restaurant();
		restaurant.setName(resName);
		restaurants.add(restaurant);
		entityManager.persist(restaurant);
		return restaurant.getName();
	}
	
	public static ArrayList<Restaurant> getRestaurants() {
		return restaurants;
	}
    
}
    
