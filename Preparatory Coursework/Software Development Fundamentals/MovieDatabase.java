import java.util.*;
import java.io.*;

public class MovieDatabase {
	//Instance variables
	ArrayList<Movie> movieList;
	ArrayList<Actor> actorList;
	
	//Constructor
	public MovieDatabase() {
		movieList = new ArrayList<>();
		actorList = new ArrayList<>();
	}
	
	//Getters and Setters
	public ArrayList<Movie> getMovieList() {
		return movieList;
	}

	public void setMovieList(ArrayList<Movie> movieList) {
		this.movieList = movieList;
	}

	public ArrayList<Actor> getActorList() {
		return actorList;
	}

	public void setActorList(ArrayList<Actor> actorList) {
		this.actorList = actorList;
	}
	
	public void addMovie(String name, String[] actors) {
		Movie newMovie = new Movie(name);
		if(!movieList.contains(newMovie)) {
			movieList.add(newMovie);
			for(String actorName : actors) {
				Actor actor = new Actor(actorName);
				if(!actorList.contains(actor)) {
					actorList.add(actor);
				}else {
					actor = actorList.get(actorList.indexOf(actor));
				}
				newMovie.getActors().add(actor);
				actor.getMovies().add(newMovie);
			}
		}
	}
	
	public void addRating(String name, double rating) {
		if (movieList.indexOf(new Movie(name)) != -1) {
			movieList.get(movieList.indexOf(new Movie(name))).setRating(rating);
		}
	}
	
	public void updateRating(String name,double newRating) {
		movieList.get(movieList.indexOf(new Movie(name))).setRating(newRating);
	}
	
	public String getBestMovie() {
		Collections.sort(movieList);
		return movieList.get(movieList.size() - 1).getName();
	}
	
	public String getBestActor() {
		Collections.sort(actorList);
		return actorList.get(actorList.size() - 1).getName();
	}
	
	public static void main(String[] args) throws FileNotFoundException{
		MovieDatabase db = new MovieDatabase();
		Map<String, List<String>> movies = new HashMap<>();
		Scanner sc = new Scanner(new File("movies.txt"));
        while(sc.hasNextLine()) {
        	String[] actors = sc.nextLine().split(", ");
        	for (int i = 1; i < actors.length; i++) {
        		if (!movies.containsKey(actors[i])) {
        			movies.put(actors[i], new ArrayList<String>());
        		}
        		movies.get(actors[i]).add(actors[0]);
        	}
        }
        for (String movie : movies.keySet()) {
        	List<String> actors = movies.get(movie);
        	db.addMovie(movie, actors.toArray(new String[actors.size()]));
        }
        
        sc = new Scanner(new File("ratings.txt"));
        sc.nextLine();
        while(sc.hasNextLine()){
        	String[] ratings = sc.nextLine().split("\t");
        	db.addRating(ratings[0], Double.parseDouble(ratings[1]));
        }


		System.out.println("Best movie: " + db.getBestMovie());
		System.out.println("Best actor: " + db.getBestActor());
	}			

}
