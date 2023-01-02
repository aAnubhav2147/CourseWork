import java.util.*;

public class Actor implements Comparable<Actor> {
	
	//Instance variables
	 String name;
	 ArrayList<Movie> movies;
	
	//Constructor
	public Actor(String name) {
		this.name = name;
		movies = new ArrayList<>();
	}
	
	//Getters and Setters
	public String getName() {
		return name;
	}

	public void setActorName(String name) {
		this.name = name;
	}

	public ArrayList<Movie> getMovies() {
		return movies;
	}

	public void setMovies(ArrayList<Movie> movies) {
		this.movies = movies;
	}
	
	public double getAverageActorRating() {
		double ratingSum = 0;
		for (Movie movie : movies) {
			ratingSum += movie.getRating();
		}
		return ratingSum / movies.size();
	}
	
	public boolean equals(Object object) {
		return ((Actor) object).getName().equals(name);
	}
	
	public String toString() {
		return name + " average rating: " + getAverageActorRating();
	}
	
	public int compareTo(Actor o) {
		// TODO Auto-generated method stub
		Double rating = getAverageActorRating();
		if (rating > o.getAverageActorRating()) {
			return 1;
		} else if (rating < o.getAverageActorRating()) {
			return -1;
		} else {
			return 0;
		}
	}

}
