package controller;

public class RatingController 
{
    public void getMovies(Double rating)
    {
        RatingDAO.getMovies(rating);
    }
}
