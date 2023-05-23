package toolsProject;

public class RestaurantReport {

    private int restaurantId;

    private double totalEarnings;

    private int numCompletedOrders;

    private int numCanceledOrders;

	    public int getRestaurantId() {

		return restaurantId;

	}

	public void setRestaurantId(int restaurantId) {

		this.restaurantId = restaurantId;

	}

	public double getTotalEarnings() {

		return totalEarnings;

	}

	public void setTotalEarnings(double totalEarnings) {

		this.totalEarnings = totalEarnings;

	}

	public int getNumCompletedOrders() {

		return numCompletedOrders;

	}

	public void setNumCompletedOrders(int numCompletedOrders) {

		this.numCompletedOrders = numCompletedOrders;

	}

	public int getNumCanceledOrders() {

		return numCanceledOrders;

	}

	public void setNumCanceledOrders(Object object) {

		this.numCanceledOrders = (int) object;

	}

	public static RestaurantReport createRestaurantReport(int restaurantId2) {

		// TODO Auto-generated method stub

		return null;

	}

	

}
