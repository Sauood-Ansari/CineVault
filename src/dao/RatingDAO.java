package dao;

public class RatingDAO 
{
    public static void getMovies(Double Rating)
    {
        try(conn=DBConnection.getConnection())
        {
            if(conn!=null)
            {
                String query1="Select title from movies where rating>=?";
                String query2="Select year from movies where rating>=?";
                PreparedStatement stmt1=conn.prepareStatement(query1);
                PreparedStatement stmt2=conn.prepareStatement(query2);
                stmt1.setDouble(1, Rating);
                stmt2.setDouble(1, Rating);
                ResultSet rs1=stmt1.executeQuery();
                ResultSet rs2=stmt2.executeQuery();
                if(rs1.next())
                {
                    System.out.println("Movies with rating greater than or equal to " + Rating + ":");
                }
                while(rs1.next() && rs2.next())
                {
                    String title=rs1.getString("title");
                    int year=rs2.getInt("year");
                    System.out.println(title + " (" + year + ")");
                }
            }
        }
        catch(SQLException e)
        {
            System.out.println("An error occurred while connecting to the database: " + e.getMessage());
        }
    }
}
