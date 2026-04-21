package controller;

public class MovieController 
{
    public void addMovie(String title, String genre, int year)
    {
        MovieDAO.addMovie(title, genre, year);        
    }

    public void removeMovie(String title)
    {
        MovieDAO.removeMovie(title);
    }

    public void updateMovie(String title, String genre, int year)
    {
        MovieDAO.updateMovie(title, genre, year);
    }

    public void getAllMovies()
    {
        MovieDAO.getAllMovies();
    }
}
