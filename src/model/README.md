# 📦 Model Package – CineVault

The **model** package contains the core data classes (POJOs) used in the application. These classes represent the structure of data stored in the database and transferred between different layers of the application.

## 📁 Classes

* **User.java**
  Represents user information such as id, name, email, and password.

* **Movie.java**
  Represents movie details including title, genre, year, and description.

* **Rating.java**
  Represents user ratings for movies, linking users and movies with a rating value.

## 🎯 Purpose

* Acts as a **data container layer**
* Maps directly to database tables (`users`, `movies`, `ratings`)
* Used by DAO and Controller layers for data transfer

## ⚠️ Notes

* Contains only **fields, constructors, getters, and setters**
* No business logic or database code should be written here