import java.util.*;


public class Main {
    public static void main(String[] args) {


        ArrayList<SimpleMovie> movies = MovieDatabaseBuilder.getMovieDB("src/movie_data");
        HashMap<String, ArrayList<SimpleMovie>> actorsByMovies = SimpleMovie.getMoviesbyActor();
        movies.sort(Comparator.comparing(SimpleMovie::getNumOfActors).reversed());
//        System.out.println("Number of movies: " + movies.size());
//        System.out.println("Number of Actors: " + SimpleMovie.getMoviesbyActor().size());
        String name = "";
        while (!name.equals("q")) {
            Scanner s = new Scanner(System.in);
            System.out.print("Enter actor name (or q to quit): ");
            name = s.nextLine();
            if (name.equals("q"))
            {
                break;
            }
//        String name = "Leonardo DiCaprio";
            HashSet<ArrayList<String>> actorstoParse = new HashSet<>();
            int baconNum = 0;
            HashSet<SimpleMovie> moviestoParse = new HashSet<>(actorsByMovies.get(name));
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
                for (SimpleMovie movie : moviestoParse) {
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
                for (ArrayList<String> actorsMovies : actorstoParse) {
                    for (String actorsMovie : actorsMovies) {
                        moviestoParse.addAll(actorsByMovies.get(actorsMovie));
                    }
                }
//            System.out.println(moviestoParse + " " +  baconNum);
                actorstoParse.clear();
            }


            if (name.equals("Kevin Bacon")) {
                baconNum = 0;
            }
            ArrayList<String> path = new ArrayList<>();
            System.out.println(name + " has a Bacon Number of " + baconNum + ".");
            if (baconNum == 0) {
                path.add("Kevin Bacon");
            }
            if (baconNum == 1) {
                path = findOneBaconPath(linkingMovie, name);
            }
            if (baconNum == 2) {
                path = findTwoBaconPath(linkingMovie, name, actorsByMovies);
            }
            if (baconNum == 3) {
                path = findThreeBaconPath(linkingMovie, name, actorsByMovies);
            }
            if (baconNum == 4) {
                path = findFourBaconPath(linkingMovie, name, actorsByMovies);
            }
//        if (baconNum == 5)
//        {
//            path = findFiveBaconPath(linkingMovie, name, actorsByMovies);
//        }
//        System.out.println(moviesToRevParse);
//        System.out.println(moviesToRevParse.getFirst().getActors());
//        System.out.println(baconNum);
            for (int i = 0; i < path.size(); i++) {
                if (i < (path.size() - 1)) {
                    System.out.print(path.reversed().get(i) + " -> ");
                } else {
                    System.out.println(path.reversed().get(i));
                }
            }
//        System.out.println(path.reversed());


        }
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
        for (String actor : actors) {
            ArrayList<SimpleMovie> movies1 = actorsByMovies.get(actor);
            for (SimpleMovie movie1 : movies1) {
                ArrayList<String> actors1 = movie1.getActors();
                for (String s : actors1) {
                    ArrayList<SimpleMovie> movies2 = actorsByMovies.get(s);
                    for (SimpleMovie simpleMovie : movies2) {
                        if (simpleMovie.getActors().contains(name)) {
                            path.add(actor);
                            path.add(movie1.getTitle());
                            path.add(s);
                            path.add(simpleMovie.getTitle());
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
        for (String actor : actors) {
            ArrayList<SimpleMovie> movies1 = actorsByMovies.get(actor);
            for (SimpleMovie movie1 : movies1) {
                ArrayList<String> actors1 = movie1.getActors();
                for (String s : actors1) {
                    ArrayList<SimpleMovie> movies2 = actorsByMovies.get(s);
                    for (SimpleMovie movie3 : movies2) {
                        ArrayList<String> actors2 = movie3.getActors();
                        for (String string : actors2) {
                            ArrayList<SimpleMovie> movies3 = actorsByMovies.get(string);
                            for (SimpleMovie simpleMovie : movies3) {
                                if (simpleMovie.getActors().contains(name)) {
                                    path.add(actor);
                                    path.add(movie1.getTitle());
                                    path.add(s);
                                    path.add(movie3.getTitle());
                                    path.add(string);
                                    path.add(simpleMovie.getTitle());
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

//    public static ArrayList<String> findFiveBaconPath(SimpleMovie movie, String name, HashMap<String, ArrayList<SimpleMovie>> actorsByMovies) {
//        ArrayList<String> path = new ArrayList<>();
//        path.add("Kevin Bacon");
//        path.add(movie.getTitle());
//        ArrayList<String> actors = movie.getActors();
//        for (int i = 0; i < actors.size(); i++)
//        {
//            System.out.println( i + "/" + actors.size());
//            String actor = actors.get(i);
//            ArrayList<SimpleMovie> movies1 = actorsByMovies.get(actor);
//            movies1.sort(Comparator.comparing(SimpleMovie::getNumOfActors).reversed());
//            for (int j = 0; j < movies1.size(); j++){
//                System.out.println(j + "/" + movies1.size());
//                SimpleMovie movie1 = movies1.get(j);
//                ArrayList<String> actors1 = movie1.getActors();
//                for (int k = 0; k < actors1.size(); k++)
//                {
//                    System.out.println(k + "/" + actors1.size());
//                    ArrayList<SimpleMovie> movies2 = actorsByMovies.get(actors1.get(k));
//                    movies2.sort(Comparator.comparing(SimpleMovie::getNumOfActors).reversed());
//                    for (SimpleMovie movie3 : movies2) {
//                        ArrayList<String> actors2 = movie3.getActors();
//                        for (String s : actors2) {
//                            ArrayList<SimpleMovie> movies3 = actorsByMovies.get(s);
//                            movies3.sort(Comparator.comparing(SimpleMovie::getNumOfActors).reversed());
//                            for (SimpleMovie movie4 : movies3) {
//                                ArrayList<String> actors3 = movie4.getActors();
//                                for (String string : actors3) {
//                                    ArrayList<SimpleMovie> movies4 = actorsByMovies.get(string);
//                                    movies4.sort(Comparator.comparing(SimpleMovie::getNumOfActors).reversed());
//                                    for (SimpleMovie simpleMovie : movies4) {
//                                        if (simpleMovie.getActors().contains(name)) {
//                                            path.add(actor);
//                                            path.add(movie1.getTitle());
//                                            path.add(actors1.get(k));
//                                            path.add(movie3.getTitle());
//                                            path.add(s);
//                                            path.add(movie4.getTitle());
//                                            path.add(string);
//                                            path.add(simpleMovie.getTitle());
//                                            path.add(name);
//                                            return path;
//                                        } else {
//                                            movies1.remove(simpleMovie);
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        return path;
//
//    }



}
