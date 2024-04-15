
import java.util.*;

class Movie {
    String name;
    double rating;

    Movie(String name, double rating) {
        this.name = name;
        this.rating = rating;
    }
}

public class MovieRatingSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // Number of movies
        int sortOrder = scanner.nextInt(); // Sorting order (1 for descending, -1 for ascending)
        scanner.nextLine(); // Consume newline

        List<Movie> movies = new ArrayList<>();

        // Input movies
        for (int i = 0; i < n; i++) {
            String[] movieInfo = scanner.nextLine().split(" ");
            String name = movieInfo[0];
            double rating = Double.parseDouble(movieInfo[1]);
            movies.add(new Movie(name, rating));
        }

        // Sort movies based on ratings
        Collections.sort(movies, new Comparator<Movie>() {
            @Override
            public int compare(Movie m1, Movie m2) {
                if (m1.rating < m2.rating) {
                    return sortOrder; // Sort in ascending order if sortOrder is -1
                } else if (m1.rating > m2.rating) {
                    return -sortOrder; // Sort in descending order if sortOrder is 1
                } else {
                    return 0;
                }
            }
        });

        // Output sorted movies
        for (Movie movie : movies) {
            System.out.println(movie.name + " " + movie.rating);
        }

        scanner.close();
    }
}

