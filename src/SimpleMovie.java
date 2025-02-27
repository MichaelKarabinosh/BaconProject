
import java.util.ArrayList;
import java.util.HashMap;


public class SimpleMovie {
    private String title;
    private String actorsData;
    private ArrayList<String> actors;
    private static HashMap<String, ArrayList<SimpleMovie>> moviesbyActor = new HashMap<>();


    public SimpleMovie(String t, String a) {
        title = t;
        actorsData = a;
        actors = new ArrayList<String>();
        String[] tempActors = actorsData.split(":");
        for (int i = 0; i < tempActors.length; i++) {
            actors.add(tempActors[i]);
        }


    }


    public String toString() {
        return "Title: " + title + "\n" + "Actors: " + actors + "\n";
    }


    public int getNumOfActors()
    {
        return actors.size();
    }


    public String getTitle()
    {
        return title;
    }


    public static void moviesbyActorAppend(String actor, SimpleMovie movie)
    {
        if (moviesbyActor.get(actor) == null)
        {
            ArrayList<SimpleMovie> movies = new ArrayList<>();
            movies.add(movie);
            moviesbyActor.put(actor, movies);
        }
        else {
            ArrayList<SimpleMovie> movies = moviesbyActor.get(actor);
            if (!movies.contains(movie))
            {
                movies.add(movie);
            }
            moviesbyActor.put(actor, movies);
        }
    }








    public ArrayList<String> getActors ()
    {
        return actors;
    }


    public static HashMap<String, ArrayList<SimpleMovie>> getMoviesbyActor()
    {
        return  moviesbyActor;
    }


}
