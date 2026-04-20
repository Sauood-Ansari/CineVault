# 🖥️ View Package – CineVault

The **view** package contains all the Java Swing UI screens of the application. It is responsible for displaying data and capturing user input.

## 📁 Classes

* **LoginPage.java**
  Provides UI for user login and authentication input.

* **Dashboard.java**
  Acts as the main navigation screen to access different features.

* **AddMoviePage.java**
  Form interface to add new movie details.

* **MovieCollectionPage.java**
  Displays list of movies with options to update, delete, and search.

* **RatingPage.java**
  Allows users to rate movies and view recommendations.

## 🎯 Purpose

* Displays information to the user
* Takes input from the user
* Sends user actions to the controller

## 🔁 Flow

```text id="k8m2zp"
View → Controller → DAO → Database
```

## ⚠️ Notes

* Contains only UI code (Swing components)
* Should not include SQL queries or database logic
* Should not contain business logic
* Directly communicates with controller classes (no interfaces used)