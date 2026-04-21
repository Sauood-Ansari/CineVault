package controller;

public class AuthController 
{
    public void auth(String username, String password)
    {
     if(password.equals(UserDAO.getPassword(username)))
     {
         System.out.println("Login successful");
     }
     else
     {
         System.out.println("Login failed");
     }
    }

    public void register(String username, String password)
    {
        if(UserDAO.addUser(username, password))
        {
            System.out.println("Registration successful");
        }
        else
        {
            System.out.println("Registration failed");
        }
    }
}
