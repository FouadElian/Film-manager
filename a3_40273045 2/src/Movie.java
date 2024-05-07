/**-------------------------------
 * Fouad Elian Id:40273045
 * COMP249 
 * Assignment 2
 * Due Date: 27/03/24
 * ---------------------------------
 */

 import java.io.Serializable;
 import java.util.Objects;
  public class Movie implements Serializable{
	 private String title;
	 private int year;
	 private int duration;
	 private String genre;
	 private double score;
	 private String rating;
	 private String director;
	 private String actor1;
	 private String actor2;
	 private String actor3;
	 
	 
	 
	 
	 
	 /**
 * Constructs a new Movie object with the specified attributes.
 *
 * @param title     the title of the movie
 * @param year      the release year of the movie
 * @param duration  the duration of the movie in minutes
 * @param genre     the genre of the movie
 * @param score     the score or rating of the movie
 * @param rating    the content rating of the movie
 * @param director  the director of the movie
 * @param actor1    the name of the first main actor or actress in the movie
 * @param actor2    the name of the second main actor or actress in the movie
 * @param actor3    the name of the third main actor or actress in the movie
 */
	 public Movie(String title, int year, int duration, String genre, double score, String rating, String director,
		 String actor1, String actor2, String actor3) {
		 this.title = title;
		 this.year = year;
		 this.duration = duration;
		 this.genre = genre;
		 this.score = score;
		 this.rating = rating;
		 this.director = director;
		 this.actor1 = actor1;
		 this.actor2 = actor2;
		 this.actor3 = actor3;
	 }
	 
	 
	 
	 public Movie() {
		 
	 }
	 /**
 * Constructs a new Movie object by copying the values from another Movie object.
 *
 * @param obj the Movie object to copy
 */
	 public Movie(Movie obj) {
			 this.title = obj.title;
			 this.year = obj.year;
			 this.duration = obj.duration;
			 this.genre = obj.genre;
			 this.score = obj.score;
			 this.rating = obj.rating;
			 this.director = obj.director;
			 this.actor1 = obj.actor1;
			 this.actor2 = obj.actor2;
			 this.actor3 = obj.actor3;
		 }
		 
 
 
	 public String getTitle() {
		 return this.title;
	 }
	 public String getGenre() {
		 return this.genre;
	 }
	 public String getRating() {
		 return this.rating;
	 }
	 public String getDirector() {
		 return this.director;
	 }
	 public String getActor1() {
		 return this.actor1;
	 }
	 public String getActor2() {
		 return this.actor2;
	 }
	 public String getActor3() {
		 return this.actor3;
	 }
	 public int getYear() {
		 return this.year;
	 }
	 public int getDuration() {
		 return this.duration;
	 }
	 public double getScore() {
		 return this.score;
	 }
	 
	 public void setTitle(String title1) {
		 this.title=title1;
	 }
	 public void setGenre(String genre1) {
		 this.genre=genre1;
	 }
	 public void setRating(String rating1) {
		 this.rating=rating1;
	 }
	 public void setDirector(String director1) {
		 this.director=director1;
	 }
	 public void setActor1(String actor11) {
		 this.actor1=actor11;
	 }
	 public void setActor2(String actor21) {
		 this.actor2=actor21;
	 }
	 public void setActor3(String actor31) {
		 this.actor3=actor31;
	 }
	 public void setYear(int year1) {
		 this.year=year1;
	 }
	 public void setDuration(int duration1) {
		 this.duration=duration1;
	 }
	 public void setScore(double score1) {
		 this.score=score1;
	 }
	 
	
 
 
 /**
 * Indicates whether some other object is "equal to" this one.
 * Two Movie objects are considered equal if they are of the same class and
 * have the same values for year, title, genre, rating, director, duration,
 * score, and actors.
 *
 * @param obj the reference object with which to compare
 * @return {true} if this Movie object is the same as the obj
 *         argument; {false} otherwise
 */
	 @Override
	 public boolean equals(Object obj) {
		 if (this == obj)
			 return true;
		 if (obj == null)
			 return false;
		 if (getClass() != obj.getClass())
			 return false;
		 Movie other = (Movie) obj;
		 return Objects.equals(actor1, other.actor1) && Objects.equals(actor2, other.actor2)
				 && Objects.equals(actor3, other.actor3) && Objects.equals(director, other.director)
				 && duration == other.duration && Objects.equals(genre, other.genre)
				 && Objects.equals(rating, other.rating)
				 && Double.doubleToLongBits(score) == Double.doubleToLongBits(other.score)
				 && Objects.equals(title, other.title) && year == other.year;
	 }
 
 
 
	 
	 /**
 * Returns a string representation of the Movie object.
 * The string includes information about the movie's title, release year, duration, genre, rating,
 * score, director, and main actors.
 *
 * @return a string containing information about the movie
 */
@Override
	 public String toString() {
		 return "Title of the movie: " + this.title
				 + "\nYear of release: "+this.year
				 +"\nDuration: "+ this.duration
				 +"\nGenre: "+ this.genre
				 +"\nRating: "+ this.rating
				 +"\nScore: " + this.score
				 +"\nDirector: " + this.director
				 +"\nAll Actors: "+this.actor1+" , "+this.actor2+" , " + this.actor3;
		 
	 }
	 
 }
  
  