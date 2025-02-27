import java.util.*;


public class Main {
    public static void main(String[] args) {


        ArrayList<SimpleMovie> movies = MovieDatabaseBuilder.getMovieDB("src/movie_data");
        HashMap<String, ArrayList<SimpleMovie>> actorsByMovies = SimpleMovie.getMoviesbyActor();
        movies.sort(Comparator.comparing(SimpleMovie::getNumOfActors).reversed());
        System.out.println("Number of movies: " + movies.size());
        System.out.println("Number of Actors: " + SimpleMovie.getMoviesbyActor().size());
        Scanner s = new Scanner(System.in);
        System.out.print("Enter actor name: ");
        String name = s.nextLine();
//        String name = "Jithin Raj";
        HashSet<SimpleMovie> moviestoParse = new HashSet<>();
        HashSet<ArrayList<String>> actorstoParse = new HashSet<>();
        int baconNum = 0;
        for (int i = 0; i < actorsByMovies.get(name).size(); i++) {
            moviestoParse.add(actorsByMovies.get(name).get(i));
        }
//        for (int i = 0; i < movies.size(); i++)
//        {
//            if (movies.get(i).getActors().contains("Catherine Deneuve"))
//            {
//                System.out.println(movies.get(i));
//            }
//        }
        SimpleMovie linkingMovie = movies.getFirst();
        boolean foundBaconConnection = false;
        while (!foundBaconConnection) {
            Iterator<SimpleMovie> iterator = moviestoParse.iterator();
            while (iterator.hasNext()) {
                SimpleMovie movie = iterator.next();
                ArrayList<String> actors = movie.getActors();
                if (actors.contains("Kevin Bacon")) {
                    linkingMovie = movie;
                    foundBaconConnection = true;
                    break;
                } else {
                    actorstoParse.add(actors);
                }
            }
            baconNum++;
            moviestoParse.clear();
            Iterator<ArrayList<String>> iterator1 = actorstoParse.iterator();
            while (iterator1.hasNext()) {
                ArrayList<String> actorsMovies = iterator1.next();
                for (int i = 0; i < actorsMovies.size(); i++) {
                    moviestoParse.addAll(actorsByMovies.get(actorsMovies.get(i)));
                }
            }
//            System.out.println(moviestoParse + " " +  baconNum);
            actorstoParse.clear();
        }


        if (name == "Kevin Bacon")
        {
            baconNum = 0;
        }
        ArrayList<String> path = new ArrayList<>();
        if (baconNum == 0)
        {
            path.add("Kevin Bacon");
        }
        if (baconNum == 1) {
            path = findOneBaconPath(linkingMovie, name);
        }
        if (baconNum == 2) {
            path = findTwoBaconPath(linkingMovie, name, actorsByMovies);
        }
        if (baconNum == 3)
        {
            path = findThreeBaconPath(linkingMovie, name, actorsByMovies);
        }
        if (baconNum == 4)
        {
            path = findFourBaconPath(linkingMovie, name, actorsByMovies);
        }
        System.out.println(name + " has a Bacon Number of " + baconNum + ".");
//        System.out.println(moviesToRevParse);
//        System.out.println(moviesToRevParse.getFirst().getActors());
//        System.out.println(baconNum);
        System.out.println(path.reversed());




    }


    public static ArrayList<String> findOneBaconPath(SimpleMovie movie, String name) {
        ArrayList<String> path = new ArrayList<>();
        path.add("Kevin Bacon");
        path.add(movie.getTitle());
        path.add(name);
        return path;
    }




    public static ArrayList<String> findTwoBaconPath(SimpleMovie movie, String name, HashMap<String, ArrayList<SimpleMovie>> actorsByMovies) {
        ArrayList<String> path = new ArrayList<>();
        path.add("Kevin Bacon");
        path.add(movie.getTitle());
        for (int i = 0; i < movie.getActors().size(); i++) {
            String actor = movie.getActors().get(i);
            for (int j = 0; j < actorsByMovies.get(actor).size(); j++) {
                if (actorsByMovies.get(actor).get(j).getActors().contains(name)) {
                    path.add(actor);
                    path.add(actorsByMovies.get(actor).get(j).getTitle());
                    path.add(name);
                    return path;
                }
            }
        }
        return path;
    }


    public static ArrayList<String> findThreeBaconPath(SimpleMovie movie, String name, HashMap<String, ArrayList<SimpleMovie>> actorsByMovies) {
        ArrayList<String> path = new ArrayList<>();
        path.add("Kevin Bacon");
        path.add(movie.getTitle());
        ArrayList<String> actors = movie.getActors();
        for (int i = 0; i < actors.size(); i++)
        {
            String actor = actors.get(i);
            ArrayList<SimpleMovie> movies1 = actorsByMovies.get(actor);
            for (int j = 0; j < movies1.size(); j++)
            {
                SimpleMovie movie1 = movies1.get(j);
                ArrayList<String> actors1 = movie1.getActors();
                for (int k = 0; k < actors1.size(); k++)
                {
                    ArrayList<SimpleMovie> movies2 = actorsByMovies.get(actors1.get(k));
                    for (int l = 0; l < movies2.size(); l++) {
                        if (movies2.get(l).getActors().contains(name)){
                            path.add(actor);
                            path.add(movie1.getTitle());
                            path.add(actors1.get(k));
                            path.add(movies2.get(l).getTitle());
                            path.add(name);
                            return path;
                        }
                    }
                }
            }
        }
        return path;
    }


    public static ArrayList<String> findFourBaconPath(SimpleMovie movie, String name, HashMap<String, ArrayList<SimpleMovie>> actorsByMovies)
    {
        ArrayList<String> path = new ArrayList<>();
        path.add("Kevin Bacon");
        path.add(movie.getTitle());
        ArrayList<String> actors = movie.getActors();
        for (int i = 0; i < actors.size(); i++)
        {
            String actor = actors.get(i);
            ArrayList<SimpleMovie> movies1 = actorsByMovies.get(actor);
            for (int j = 0; j < movies1.size(); j++)
            {
                SimpleMovie movie1 = movies1.get(j);
                ArrayList<String> actors1 = movie1.getActors();
                for (int k = 0; k < actors1.size(); k++)
                {
                    ArrayList<SimpleMovie> movies2 = actorsByMovies.get(actors1.get(k));
                    for (int l = 0; l < movies2.size(); l++) {
                        SimpleMovie movie3 = movies2.get(l);
                        ArrayList<String> actors2 = movie3.getActors();
                        for (int z = 0; z < actors2.size(); z++)
                        {
                            ArrayList<SimpleMovie> movies3 = actorsByMovies.get(actors2.get(z));
                            for (int x = 0; x < movies3.size(); x++)
                            {
                                if (movies3.get(x).getActors().contains(name))
                                {
                                    path.add(actor);
                                    path.add(movie1.getTitle());
                                    path.add(actors1.get(k));
                                    path.add(movies2.get(l).getTitle());
                                    path.add(actors2.get(z));
                                    path.add(movies3.get(x).getTitle());
                                    path.add(name);
                                    return path;
                                }
                            }
                        }


                    }
                }
            }
        }
        return path;
    }


}
