/**-------------------------------
 * Fouad Elian Id:40273045
 * COMP249 
 * Assignment 2
 * Due Date: 27/03/24
 * ---------------------------------
 */



import java.util.Scanner;
import java.util.Scanner;import java.util.Scanner;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;


class BadYearException extends Exception{ // class that will be used to handle bad year 
	public BadYearException(String b) {
		super(b);
	}
	
}
class BadTitleException extends Exception{ // class that will be used to handle bad title 
	public BadTitleException(String b) {
		super(b);
	}
	
}
class BadGenreException extends Exception{ // class that will be used to handle bad genre
	public BadGenreException(String b) {
		super(b);
	}
	
}

class BadScoreException extends Exception{// class that will be used to handle  bad score 
	public BadScoreException(String b) {
		super(b);
	}
	
}
class BadDurationException extends Exception{ // class that will be used to handle bad duration
	public BadDurationException(String b) {
		super(b);
	}
	
}
class BadRatingException extends Exception{ // class that will be used to handle bad rating
	public BadRatingException(String b) {
		super(b);
	}
	
}
class BadNameException extends Exception{ // class that will be used to handle bad name
	public BadNameException(String b) {
		super(b);
	}
	
}

class MissingQuoteException extends Exception{ // class that will be used to handle missing quotes
	public MissingQuoteException(String b) {
		super(b);
	}
	
}
class ExcessFieldsException extends Exception{ // class that will be used to handle excess fields 
	public ExcessFieldsException(String b) {
		super(b);
	}
	
}

class MissingFieldsException extends Exception{// class that will be used to handle missing fields 
	public MissingFieldsException(String b) {
		super(b);
	}
	
}






public class driver{
	
	
	public static boolean isNotNumber(String str) { // method that will return true if the string can be converted to an int else it will return false
	    try {
	        Integer.parseInt(str); // 
	        return false; // It's a number
	    } catch (NumberFormatException e) {
	        return true; // It's not a number
	    }
	}
	public static boolean isNotDouble(String str) { // methods that will return true if the string can be converted to a double else it will return false.
	    try {
	        Double.parseDouble(str);
	        return false; // It's a number
	    } catch (NumberFormatException e) {
	        return true; // It's not a number
	    }
	}
	

	/**
	 * This method is used to handle quotes in a string retrieved from each CSV file.
	 * It splits the string based on commas and adjusts the elements accordingly, 
	 * especially handling cases where quotes are missing or present.
	 * 
	 * @param splitted The array of strings obtained by splitting the CSV string.
	 * @param movie_string The original string retrieved from the CSV file.
	 * @param pos The position of the opening quote in the original string.
	 * @return An array of strings after processing.
	 * @throws MissingQuoteException If a closing quote is missing.
	 */
	public static String[] ReturnString(String[] splitted,String movie_string,int pos) throws MissingQuoteException{ // method that will be used to handle the quotes in the string retrived from each CSV files.
		int counter =0;// will be used to see at which index the closing quote appears
		int counter1=0;// will be used to see where the first quote is 
		int counter2=0;// will be used to see where the closing quote is 
		String error =""; //
		int verifier2=0;
		boolean verifier = false; // boolean will be used to checkk if there is missing quotes or not
		String title =" "; // will take the substring of the string between both quotes
		for(int i =1;i<splitted.length;i++) { // we go throught the array that was created by splliting the string when , was found.
			if(splitted[i].length()!=0) {
			if (splitted[i].charAt(splitted[i].length() - 1) == '"') {// if the last character of one of the last elements is a quote that means that a closing quote is available therefore we set verifier to true and change the value of counter.
				counter=i;
				verifier = true;
			}
			}
		}
		if(verifier) { //if verifier that means that there is a closing quote
			for(int i=0;i<movie_string.length();i++) { // here we do a for loop for the length of the whole string that we retrived from the CSV file. 
				if( counter1==0 && movie_string.charAt(i) == '"') { // here we give the index of the first quote to the counter1 variable.
					counter1=i; 
				}
				else if(counter1!=0 && movie_string.charAt(i) == '"') { // if counter1 is differrent then 0 that means that we already found the first quote therefore now we search for the second quote and give the index to the counter2 variable .
					counter2=i;
				}
			}
			title = movie_string.substring(counter1+1,counter2); // title will now have the string that is between both quotes
	
		}
		try {
		if(counter==0) {
			verifier2++;
			throw new MissingQuoteException("missing quote"); // if counter ==0 that means that no second quote was found therefore we throw an error.
			
		}
		}
		catch(MissingQuoteException e) {
			error+=e.toString(); // here we catch the error but there is no use to it since I created a second method that will retrive this error.
		}
	
		String[] new_array = new String[counter]; // we create a new array that will have a length of how many elements passed before we found a closing quote 
		for(int i =0; i<new_array.length;i++) {
			new_array[i]=splitted[i+1];
			
		}
		String[] Final_array=null; 
		String s3="";
		String s4="";
		if(pos-counter==0   || verifier2!=0) { // here we check if the closing quote and the opening quote are in the same element, if they are that means we still have the correct amount of elements which is 10 therefore we do no changes we just add the title that is between the quotes.
		   Final_array = new String[splitted.length];
			for(int i =0; i<Final_array.length;i++) {
				if(i==pos) {
					
					Final_array[pos]=splitted[pos].substring(1,splitted[pos].length()-1);
				}
				else {
				if(i>5) {
					 for (int j = 0; j < splitted[i].length(); j++) {
					        if (Character.isUpperCase(splitted[i].charAt(j))) { // Check if the character is uppercase
					            for (int w = j + 1; w < splitted[i ].length(); w++) { // Start from j+1 to avoid getting the same character again
					                if (Character.isUpperCase(splitted[i].charAt(w))) {
					                    s3 = splitted[i].substring(j, w);
					                    s4 = splitted[i].substring(w, splitted[i].length());
					           // this is used to separate the name and the last name because I removed all the spaces from the string
					                   
					                    break; // Break the inner loop once the string is split
					                }
					            }
					        }
					        if(i<Final_array.length) {
								Final_array[i] = s3 + " " +s4;
							}
							else {
								Final_array[i] = s3 + " " +s4;
							}
							
							break;
					    }
				}
				else {
				Final_array[i]=splitted[i];
				}
				}
			}
		}
		else { // if the closing quote is not in the same index as the opening quote.
		Final_array = new String[splitted.length-new_array.length+1]; // we create a new array of the length of the splitted array - the new array we created, because if the index of the opening quote and the closing is different that means that the out corrected array will have less elements.
		String s1 ="";
		String s2 = "";
		for(int i =0; i<Final_array.length;i++) {
			if(i==0) {
				Final_array[0]=splitted[0]; // we copy the year at the same index because the place of the year won't change.
				
			}
			else if(i==pos) {
				
				Final_array[pos]=title; // at the position where the opening quote is we will put the new title.
				
			}
			else if(i!=pos && i!=0) { // if i is different then the pos and 0.
				if (i + new_array.length - 1 > 6 && isNotNumber(splitted[i + new_array.length - 1]) && isNotDouble(splitted[i + new_array.length - 1])) {
				    for (int j = 0; j < splitted[i + new_array.length - 1].length(); j++) {
				        if (Character.isUpperCase(splitted[i + new_array.length - 1].charAt(j))) { // Check if the character is uppercase
				            for (int w = j + 1; w < splitted[i + new_array.length - 1].length(); w++) { // Start from j+1 to avoid getting the same character again
				                if (Character.isUpperCase(splitted[i + new_array.length - 1].charAt(w))) {
				                    s1 = splitted[i + new_array.length - 1].substring(j, w);
				                    s2 = splitted[i + new_array.length - 1].substring(w, splitted[i+new_array.length-1].length());
				          // same here helps to seperate the first and last name
				                    
				                    break; // Break the inner loop once the string is split
				                }
				            }
				        }
				        if(i<Final_array.length) {
							Final_array[i] = s1 + " " +s2;
						}
						else {
							Final_array[i] = s1 + " " +s2;
						}
						
						break;
				    }
				    
				}
				else {
				Final_array[i]=splitted[i+new_array.length-1];
				
				}
			}
		}
		
		}
		
		
	return Final_array;
		
	}
	/**
	 * This method is used to generate an error message for missing quotes in the input array.
	 *
	 * @param yes The array of strings to check for missing quotes.
	 * @return A string containing the error message for missing quotes.
	 * @throws MissingQuoteException If a closing quote is missing.
	 */
	public static String ReturnError(String[] yes) throws MissingQuoteException{ // this method is used to add to the bad_movies the missing quote excpetions if it is there.
		int counter =0;
		String error ="";
		for(int i =1;i<yes.length;i++) {
			if(yes[i].length()!=0) {
			if (yes[i].charAt(yes[i].length() - 1) == '"') {
				counter=i;
				
			}
			}
		}
		
		try {
		if(counter==0) {
			throw new MissingQuoteException("missing quote");
			
		}
		}
		catch(MissingQuoteException e) {
			error+=e.toString();
		}
	
		
	return error;
		
	}
	
	
	
	/**
	 * Reads from part1_manifest.txt, processes movie records from CSV files, 
	 * performs data validation, categorizes movies into respective genre files, and generates an error log.
	 *
	 * @param name The name of the part1_manifest.txt file.
	 * @return The name of the generated part2_manifest.txt file.
	 */
	
	public static String do_part1(String name) {
		Scanner inputStream = null; // read from the part1 manifest file
		Scanner inputStream2 = null;// read from each csv file
		PrintWriter outputstream=null;  // those Printwriters will be used to write the movies to each genre respectively
		PrintWriter musical=null;
		PrintWriter comedy=null;
		PrintWriter animation=null;
		PrintWriter adventure=null;
		PrintWriter drama=null;
		PrintWriter crime=null;
		PrintWriter biography=null;
		PrintWriter horror=null;
		PrintWriter action=null;
		PrintWriter documentary=null;
		PrintWriter fantasy=null;
		PrintWriter mystery=null;
		PrintWriter sci_fi=null;
		PrintWriter family=null;
		PrintWriter western=null;
		PrintWriter romance=null;
		PrintWriter thriller=null;
		PrintWriter Genres = null;
		Scanner outputStream2=null;
		int counter = 0;
		int counter2=0;
		String[] test2 = null;
		String test3="";
		String[] array_split=null;
		String next_line = "";
		String test ="";
		String error_message ="";// this will take the errore message that is going to be outputed to the bad_movies file.
		String[] array = {"musical.csv","comedy.csv","animation.csv","adventure.csv","drama.csv","crime.csv","biography.csv","horror.csv","action.csv","documentary.csv","fantasy.csv","mystery.csv","sci-fi.csv","family.csv","western.csv","romance.csv","thriller.csv"}; // contains the name of the files that we need to create
		
		try {
			 // Print the file name
			inputStream = new Scanner(new FileInputStream(name));// name here the part1_manifest text file.
			outputstream = new PrintWriter(new FileOutputStream("bad_movies_records.txt"),true); // write to the bad_movies but since we don't want to reset the value inside of it we add the true.
			musical = new PrintWriter(new FileOutputStream("musical.csv"),true);// Same here for the rest of the files we are writing.
			comedy = new PrintWriter(new FileOutputStream("comedy.csv"),true);
			animation = new PrintWriter(new FileOutputStream("animation.csv"),true);
			adventure = new PrintWriter(new FileOutputStream("adventure.csv"),true);
			drama = new PrintWriter(new FileOutputStream("drama.csv"),true);
			crime = new PrintWriter(new FileOutputStream("crime.csv"),true);
			biography = new PrintWriter(new FileOutputStream("biography.csv"),true);
			horror = new PrintWriter(new FileOutputStream("horror.csv"),true);
			action = new PrintWriter(new FileOutputStream("action.csv"),true);
			documentary = new PrintWriter(new FileOutputStream("documentary.csv"),true);
			fantasy = new PrintWriter(new FileOutputStream("fantasy.csv"),true);
			mystery = new PrintWriter(new FileOutputStream("mystery.csv"),true);
			sci_fi = new PrintWriter(new FileOutputStream("sci-fi.csv"),true);
			family = new PrintWriter(new FileOutputStream("family.csv"),true);
			western = new PrintWriter(new FileOutputStream("western.csv"),true);
			romance = new PrintWriter(new FileOutputStream("romance.csv"),true);
			thriller = new PrintWriter(new FileOutputStream("thriller.csv"),true);

			while (inputStream.hasNextLine()) { // we keep going in the while if there is a next line
				
				try{
					int pos_checker=0;// this will help check the position of each line
					int position=0;// this will take the actual position of the line
					next_line = inputStream.nextLine(); // we read the next line of the part2_manifest file
					inputStream2= new Scanner(new FileInputStream(next_line)); // we take this line and we read the file of it.
				 
					position=0;
					
					while(inputStream2.hasNextLine()) { // while there is a next line we stay in the same file 
						 pos_checker++;// we increment pos checker by one
						if(pos_checker==1) { // if pos checker is 1 that means that we are at the first line therefore position is 0.
							position=0;
						} 
						else {
							position++; // else the position is incremented by one.
						}
						error_message="";// we reinitiliaze the error string
						boolean check = false;// this boolean will help to see if there is an existing quote
						test=inputStream2.nextLine();// we read the current line in the file
						array_split = test.split(","); // we split the array
					for(int i=0; i<array_split.length;i++) {
						if(array_split[i]!="" ) {
						if(array_split[i].charAt(0) == '"') { // we check if there is an opening quote
							counter2=i; // counter2 takes the index in which there is the opening quote
							check=true;// check is true 
							
						}
						}
					}

					if(check) { // since check is true 
						test =test.replaceAll("\\s","");// we replace all the spaces by empty strings
						test2= ReturnString(array_split,test,counter2); // we call our returnString function
						error_message+=ReturnError(array_split); // we call our ReturnError function to check if there is any errors.
						
						
					}	
					
					else {
						test2 = array_split; // else we just give test 2 the array that we split.
					}
					
					
					if(test!="") {	 // here if test is not an empty string because in some lines the last lines are empty lines.
					
				
					if(test2.length==10) { // if the length of the array is 10 we will go and check if every element is correct.
					  
					  try {
						  if(test2[0]=="" || test2[0]==" ") { // we check if year is missing 
								
							  throw new MissingFieldsException("Year is missing");
							} 
						  
						  
						  else if(isNotNumber(test2[0])) { // we check if it is not a valid year 
							  throw new BadYearException("invalid year");
							  
						  }
						  else {
							  
							  if(Integer.parseInt(test2[0])<1990 || Integer.parseInt(test2[0])>1999 ) { // we check also here if it is bigger then 1999 or smaller then 1990
								  throw new BadYearException("invalid year");
							  }
						  }
						  
					  }
					  catch(MissingFieldsException e) { // here we catch the exceptions that we threw
						  error_message+=" "+e.toString();
						}
					  catch(BadYearException e) {
						  error_message+=" "+e.toString();
						 
					  }
					  try {
						  if(test2[2]=="" || test2[2]==" ") { // we check if the duration is missing
								throw new MissingFieldsException("Duration is missing");
							} 
						  
						  
					  else if(isNotNumber(test2[2])) { // we check if the duration is invalid
							  throw new BadDurationException("invalid duration");
							  
						  }
					  else if(Integer.parseInt(test2[2])<30 || Integer.parseInt(test2[2])>300){ // we check if the duration is too big or too small.
							throw new BadDurationException("invalid duration");
							  
						  }
					  }
					  catch(MissingFieldsException e) { // we catch the exceptions that we threw 
		
						  error_message+=" "+e.toString();
						}
					  
					  catch(BadDurationException e) {
						  
						  error_message+=" "+e.toString();
					  }
					  try {
						  if(test2[5]=="" || test2[5]==" ") { // we check if the score is missing
								throw new MissingFieldsException("Score is missing");
							}
						  
					  else if(isNotDouble(test2[5])) { // we check if the score is an invalid number
							  throw new BadScoreException("invalid score");
							  
						  }
						  else {
							  if(Double.parseDouble(test2[5])<0 || Double.parseDouble(test2[5])>=10) { // we check if the score is bigger then 10 or smaller then 0
								  throw new BadScoreException("invalid score");
							  }
						  }
					  }
					  catch(MissingFieldsException e) { // we catch the exceptions that we threw.
						  error_message+=" "+e.toString();
						}
					  catch(BadScoreException e) {
						  error_message+=" "+e.toString();
					  }
					
					  
					  try {
							String rating_correct3 = test2[4].replaceAll("\\s",""); // here we replace all the spaces with empty strings
							if(test2[4]==" " || test2[4]=="") { // we check if the rating is missing
								throw new MissingFieldsException("Rating is missing");
							}
							else if(!rating_correct3.equalsIgnoreCase("PG") && !rating_correct3.equalsIgnoreCase("Unrated") && !rating_correct3.equalsIgnoreCase("G") && !rating_correct3.equalsIgnoreCase("R") && !rating_correct3.equalsIgnoreCase("PG-13") && !rating_correct3.equalsIgnoreCase("NC-17")) { // we check if the rating is not equal to one of those.
								throw new BadRatingException("Invalid Rating");
							}
						}
						catch(MissingFieldsException e) { // we catch all the exceptions that we threw
							 error_message+=" "+e.toString();
						}
						catch(BadRatingException e) {
							 error_message+=" "+e.toString();
						}
					  try {
						  if(test2[1] ==""|| test2[0]==" ") { // we check if there is a missing title
							  throw new MissingFieldsException("Missing title");
						  }
					  }
					  catch(MissingFieldsException e) {
						  error_message+=" "+e.toString();
					  }
					  try {
						  if(test2[6] ==""|| test2[6]==" " || test2[7] ==""|| test2[7]==" " || test2[8] ==""|| test2[8]==" " || test2[9] ==""|| test2[9]==" "  ) { // we check if there is a missing author or director 
							  throw new MissingFieldsException("Missing Name");
						  }
					  }
					  catch(MissingFieldsException e) {
						  error_message+=" "+e.toString();
					  }
					  
					  try {
						  String genre_correct = test2[3].replaceAll("\\s","");
						  if(test2[3]=="" || test2[3]==" ") { // check if the genre is missing
								throw new MissingFieldsException("Genre is missing");
							} 
						  else if(!genre_correct.equalsIgnoreCase("musical") && !genre_correct.equalsIgnoreCase("comedy") && !genre_correct.equalsIgnoreCase("animation") && !genre_correct.equalsIgnoreCase("adventure") && !genre_correct.equalsIgnoreCase("drama") && !genre_correct.equalsIgnoreCase("crime") && !genre_correct.equalsIgnoreCase("biography") 
								  && !genre_correct.equalsIgnoreCase("horror") && !genre_correct.equalsIgnoreCase("Action") && !genre_correct.equalsIgnoreCase("documentary") && !genre_correct.equalsIgnoreCase("fantasy") &&!genre_correct.equalsIgnoreCase("mystery") 
								  &&!genre_correct.equalsIgnoreCase("sci-fi") && !genre_correct.equalsIgnoreCase("family") && !genre_correct.equalsIgnoreCase("romance") && !genre_correct.equalsIgnoreCase("thriller") &&!genre_correct.equalsIgnoreCase("western")) { // check if the genre is not equal to one of those
							  
							  throw new BadGenreException("Genre is invalid");
						  }
						  else if((genre_correct.equalsIgnoreCase("musical") || genre_correct.equalsIgnoreCase("comedy") || genre_correct.equalsIgnoreCase("animation") || genre_correct.equalsIgnoreCase("adventure") || genre_correct.equalsIgnoreCase("drama") || genre_correct.equalsIgnoreCase("crime") || genre_correct.equalsIgnoreCase("biography") 
								  || genre_correct.equalsIgnoreCase("horror") || genre_correct.equalsIgnoreCase("Action") || genre_correct.equalsIgnoreCase("documentary") || genre_correct.equalsIgnoreCase("fantasy") || genre_correct.equalsIgnoreCase("mystery") 
								  || genre_correct.equalsIgnoreCase("sci-fi") || genre_correct.equalsIgnoreCase("family") || genre_correct.equalsIgnoreCase("romance") || genre_correct.equalsIgnoreCase("thriller") || genre_correct.equalsIgnoreCase("western")) && error_message.isEmpty() ) { // if genre is equal to one of those and there is no error then we start adding the movies to their respective file
							
							
							  if(genre_correct.equalsIgnoreCase("musical")) { // we check here the genre is of what type and then we add it to the respective file.
								 
								  musical.write(test+"\n");
							  }
							  else if(genre_correct.equalsIgnoreCase("horror")) {
								  horror.write(test+"\n");
							  }
							  else if(genre_correct.equalsIgnoreCase("sci-fi")) {
								  sci_fi.write(test+"\n");
							  }
							  else if(genre_correct.equalsIgnoreCase("comedy")) {
								  comedy.write(test+"\n");
							  }
							  else if(genre_correct.equalsIgnoreCase("Action")) {
								  action.write(test+"\n");
							  }
							  else if(genre_correct.equalsIgnoreCase("family")) {
								  family.write(test+"\n");
							  }
							  else if(genre_correct.equalsIgnoreCase("animation")) {
								  animation.write(test+"\n");
							  }
							  else if(genre_correct.equalsIgnoreCase("documentary")) {
								  documentary.write(test+"\n");
							  }
							  else if(genre_correct.equalsIgnoreCase("romance")) {
								  romance.write(test+"\n");
							  }
							  else if(genre_correct.equalsIgnoreCase("adventure")) {
								  adventure.write(test+"\n");
							  }
							  else if(genre_correct.equalsIgnoreCase("thriller")) {
								  thriller.write(test+"\n");
							  }
							  else if(genre_correct.equalsIgnoreCase("drama")) {
								  drama.write(test+"\n");
							  }
							  else if(genre_correct.equalsIgnoreCase("mystery")) {
								  mystery.write(test+"\n");
							  }
							  else if(genre_correct.equalsIgnoreCase("western")) {
								  western.write(test+"\n");
							  }
							  else if(genre_correct.equalsIgnoreCase("crime")) {
								  crime.write(test+"\n");
							  }
							  else if(genre_correct.equalsIgnoreCase("biography")) {
								  biography.write(test+"\n");
							  }
							  else if(genre_correct.equalsIgnoreCase("fantasy")) {
								  fantasy.write(test+"\n");
							  }
							  
							  
							 
							  
							  
							  
						  }
						  
					  }
					  
					  catch(MissingFieldsException e) {
						  error_message+=" "+e.toString();
						}
					  catch(BadGenreException e) {
						  error_message+=" "+e.toString();
						}
					  
						
					}
						
					
					else if( test2.length>10) {// if there is more then 10 elements we throw an error 
						
						try {
								
								throw new ExcessFieldsException("there is too many fields");
							
							
						}
						
						catch(ExcessFieldsException e) {
							 error_message+=" "+e.toString();
						}
						
						
					}
					else if(test2.length<10) { // if there is less then 10 elements we throw an error
						try {
						throw new MissingFieldsException("There is some missing fields");
						}
						catch(MissingFieldsException e) {
							 error_message+=" "+e.toString();
						}
					
					
					}
					
						
					}
				
					
					 if(!error_message.isEmpty()) { // if the error message is not empty that means that there is an actual error so we add the movie to the bad_movie file
						 outputstream.write( test+ " Errors: " + error_message+ " In file :"+ next_line + " At line :" + position+"\n");
					 }
					
				
					}
					
					
					
				}
				
				 
				
				
				catch(FileNotFoundException e) {
					error_message+=e.toString();
				}
				
			}
			// we close all the PrintWriter.
			outputstream.close();
			musical.close();
			comedy.close();
			animation.close();
			adventure.close();
			drama.close();
			crime.close();
			biography.close();
			horror.close();
			action.close();
			documentary.close();
			fantasy.close();
			mystery.close();
			sci_fi.close();
			family.close();
			western.close();
			romance.close();
			thriller.close();
			
			
		
		} catch (Exception e) {
			// Handle the exception
			error_message+=e.toString();
		} 
		try {
		Genres = new PrintWriter(new FileOutputStream("part2_manifest.txt"),true); // we write the names of each file into the part2_manifest file
		for(int i=0;i<array.length;i++) {
			Genres.write(array[i]+"\n");
		}
		
		}
		catch (Exception e) {
			// Handle the exception
			error_message+=e.toString();
		} 
		Genres.close();
		return "part2_manifest.txt";
	}
	/**
	 * Parses movie data from a string array and creates a Movie object.
	 *
	 * @param test2 An array containing movie data:
	 *             
	 * @return A Movie object populated with the parsed movie data.
	 */
	public static Movie parseMovieData(String[] test2) { // this method will be used to create a new object of the class Movie to add to the binary files
	    Movie obj1 = new Movie();
	    obj1.setYear(Integer.parseInt(test2[0]));
		obj1.setTitle(test2[1]);
		obj1.setDuration(Integer.parseInt(test2[2]));
		obj1.setGenre(test2[3]);
		obj1.setRating(test2[4]);
		obj1.setScore(Double.parseDouble(test2[5]));
		obj1.setDirector(test2[6]);
		obj1.setActor1(test2[7]);
		obj1.setActor2(test2[8]);
		obj1.setActor3(test2[9]);
	    return obj1;
	}	 
	
	/**
	 * Counts the total number of lines in all the files listed in the given filename.
	 *
	 * @param filename The name of the file containing a list of other file names.
	 * @return The total number of lines across all files.
	 */
	public static int countLines(String filename) { 
	        int lines = 0;
	        Scanner reader2=null;
	        String line1=null;
	        try (Scanner reader = new Scanner(new FileInputStream(filename))) {
	            while (reader.hasNextLine()) {
	            	line1=reader.nextLine();
	            	reader2 = new Scanner(new FileInputStream(line1));
	            	while(reader2.hasNextLine()) {
	            		reader2.nextLine();
	            		lines++;
	            	}
	            	
	            	
	            	
	            }
	            
	            	
	            }
	         catch (Exception e) {
	            e.printStackTrace(); // Handle IO exception
	        }
	        return lines;
	    }
	
	/**
	 * Parses movie data from files listed in the part2_manifest file and writes them into corresponding binary files based on genres.
	 *
	 * @param file The name of the file containing a list of movie files.
	 * @return The name of the file (part3_manifest.txt) containing a list of binary files created.
	 */
	
	public static String do_part2(String file) {
		Scanner input=null; // scanner will be used to read the names of each files that are in the part2_manifest file
		int position=0;// will take the position of the current line so that we know to which index to add the object to.
		int pos_checker=0;// will help to find the correct position
		String next_line2=""; // will take the name of the files that we are gonna add to the objects 
		String test =""; // will take each movie string of each file .
		String[] test2 =null; // will have the array type of the string.
		String[] array_split = null; // this will have the splitted array
		int counter2 =0; // will take the index in which the opening quote is present.
		Scanner input2=null; // this will be used to read each specific genre file
        int count3= countLines(file); // this int will have the amount of movies we have in total.
        Movie[] Array3 = new Movie[count3]; // movie array that will have the length of the amount of movies
		ObjectOutputStream musical=null; // will be used to write the obejcts to the binary files
		ObjectOutputStream comedy=null;
		ObjectOutputStream animation=null;
		ObjectOutputStream adventure=null;
		ObjectOutputStream drama=null;
		ObjectOutputStream crime=null;
		ObjectOutputStream biography=null;
		ObjectOutputStream horror=null;
		ObjectOutputStream action=null;
		ObjectOutputStream documentary=null;
		ObjectOutputStream fantasy=null;
		ObjectOutputStream mystery=null;
		ObjectOutputStream sci_fi=null;
		ObjectOutputStream family=null;
		ObjectOutputStream western=null;
		ObjectOutputStream romance=null;
		ObjectOutputStream thriller=null;
		PrintWriter Genres = null; // will be used to write the name of the files to the part3_manifest file
		String[] array = {"musical.ser","comedy.ser","animation.ser","adventure.ser","drama.ser","crime.ser","biography.ser","horror.ser","action.ser","documentary.ser","fantasy.ser","mystery.ser","sci_fi.ser","family.ser","western.ser","romance.ser","thriller.ser"};// this array will have the names of the files.
	
		try {
		
			input = new Scanner(new FileInputStream(file)); 
			musical = new ObjectOutputStream(new FileOutputStream("musical.ser"));
			comedy = new ObjectOutputStream(new FileOutputStream("comedy.ser"));
			animation = new ObjectOutputStream(new FileOutputStream("animation.ser"));
			adventure = new ObjectOutputStream(new FileOutputStream("adventure.ser"));
			drama = new ObjectOutputStream(new FileOutputStream("drama.ser"));
			crime = new ObjectOutputStream(new FileOutputStream("crime.ser"));
			biography = new ObjectOutputStream(new FileOutputStream("biography.ser"));
			action = new ObjectOutputStream(new FileOutputStream("action.ser"));
			documentary = new ObjectOutputStream(new FileOutputStream("documentary.ser"));
			fantasy = new ObjectOutputStream(new FileOutputStream("fantasy.ser"));
			horror = new ObjectOutputStream(new FileOutputStream("horror.ser"));
			mystery = new ObjectOutputStream(new FileOutputStream("mystery.ser"));
			sci_fi = new ObjectOutputStream(new FileOutputStream("sci_fi.ser"));
			family = new ObjectOutputStream(new FileOutputStream("family.ser"));
			western = new ObjectOutputStream(new FileOutputStream("western.ser"));
			romance = new ObjectOutputStream(new FileOutputStream("romance.ser"));
			thriller = new ObjectOutputStream(new FileOutputStream("thriller.ser"));
			position=0;
			while(input.hasNextLine()) { // while there is a line in the part2_manifest, we go in the while
				pos_checker++;
				next_line2=input.nextLine();// we read the line.
				input2=new Scanner(new FileInputStream(next_line2)); 
				while(input2.hasNextLine()) { // while the file has a line we go in the while
					if(pos_checker==1) { // if pos_checker is 1 that means we are at the first element therefore position is 0
						position=0;
					} 
					else {
						position++;
					}
					boolean check = false;
					test=input2.nextLine();
					array_split = test.split(",");
				for(int i=0; i<array_split.length;i++) { // same explanation as the do_part1
					if(array_split[i]!="" ) {
					if(array_split[i].charAt(0) == '"') {
						counter2=i;
						check=true;
						
					}
					}
				}

				if(check) { // same explanation as the do_part1
					test =test.replaceAll("\\s","");
					test2= ReturnString(array_split,test,counter2);
				
					
				}	
				
				else { // same explanation as the do_part1
					test2 = array_split;
					
				}
				Array3[position-1]=new Movie(parseMovieData(test2)); // here we add the object to the array
				
				
				if(Array3[position-1].getGenre().equalsIgnoreCase("musical")) { // here we check the genre of the object and add it to the respective binary file.
					try {
						musical.writeObject(Array3[position-1]);
						
					}
					catch(Exception e){
						
						System.out.println(e);
					}
					
					
				}
				else if(Array3[position-1].getGenre().equalsIgnoreCase("comedy")) {
					try {
						comedy.writeObject(Array3[position-1]);
						
					}
					catch(Exception e){
						
						System.out.println(e);
					}
					
					
				}
				else if(Array3[position-1].getGenre().equalsIgnoreCase("animation")) {
					
					try {
						animation.writeObject(Array3[position-1]);
						
					}
					catch(Exception e){
					
						System.out.println(e);
					}
					
					
				}
				else if(Array3[position-1].getGenre().equalsIgnoreCase("adventure")) {
					
					try {
						adventure.writeObject(Array3[position-1]);
						
					}
					catch(Exception e){
						
						System.out.println(e);
					}
					
					
				}
				else if(Array3[position-1].getGenre().equalsIgnoreCase("drama")) {
					
					try {
						drama.writeObject(Array3[position-1]);
						
					}
					catch(Exception e){
						
						System.out.println(e);
					}
					
					
				}
				else if(Array3[position-1].getGenre().equalsIgnoreCase("crime")) {
					
					try {
						crime.writeObject(Array3[position-1]);
						
					}
					catch(Exception e){
						
						System.out.println(e);
					}
					
					
				}
				else if(Array3[position-1].getGenre().equalsIgnoreCase("biography")) {
					
					try {
						biography.writeObject(Array3[position-1]);
						
					}
					catch(Exception e){
						
						System.out.println(e);
					}
					
					
				}
				else if(Array3[position-1].getGenre().equalsIgnoreCase("action")) {
					
					try {
						action.writeObject(Array3[position-1]);
						
					}
					catch(Exception e){
						
						System.out.println(e);
					}
					
					
				}
				else if(Array3[position-1].getGenre().equalsIgnoreCase("documentary")) {
					
					try {
						documentary.writeObject(Array3[position-1]);
						
					}
					catch(Exception e){
						
						System.out.println(e);
					}
					
					
				}
				else if(Array3[position-1].getGenre().equalsIgnoreCase("fantasy")) {
					
					try {
						fantasy.writeObject(Array3[position-1]);
						
					}
					catch(Exception e){
						
						System.out.println(e);
					}
					
					
				}
				else if(Array3[position-1].getGenre().equalsIgnoreCase("mystery")) {
					
					try {
						mystery.writeObject(Array3[position-1]);
						
					}
					catch(Exception e){
						
						System.out.println(e);
					}
					
					
				}
				else if(Array3[position-1].getGenre().equalsIgnoreCase("sci_fi")) {
					
					try {
						sci_fi.writeObject(Array3[position-1]);
						
					}
					catch(Exception e){
						
						System.out.println(e);
					}
					
					
				}
				else if(Array3[position-1].getGenre().equalsIgnoreCase("family")) {
					
					try {
						family.writeObject(Array3[position-1]);
						
					}
					catch(Exception e){
						
						System.out.println(e);
					}
					
					
				}
				else if(Array3[position-1].getGenre().equalsIgnoreCase("western")) {
					
					try {
						western.writeObject(Array3[position-1]);
						
					}
					catch(Exception e){
						
						System.out.println(e);
					}
					
					
				}
				else if(Array3[position-1].getGenre().equalsIgnoreCase("horror")) {
					
					try {
						horror.writeObject(Array3[position-1]);
						
					}
					catch(Exception e){
						
						System.out.println(e);
					}
					
					
				}
				else if(Array3[position-1].getGenre().equalsIgnoreCase("romance")) {
					
					try {
						romance.writeObject(Array3[position-1]);
						
					}
					catch(Exception e){
						
						System.out.println(e);
					}
					
					
				}
				else if(Array3[position-1].getGenre().equalsIgnoreCase("thriller")) {
					try {
						thriller.writeObject(Array3[position-1]);
						
					}
					catch(Exception e){
						
						System.out.println(e);
					}
					
					
				}
				try { // here we add the names of the binary files to the part3_manifest file.
					Genres = new PrintWriter(new FileOutputStream("part3_manifest.txt"),true);
					for(int i=0;i<array.length;i++) {
						Genres.write(array[i]+"\n");
					}
					
					}
					catch (Exception e) {
			
						
					} 
				
					Genres.close();
				
				
	            
	        
					
					
				}
				
				
			}
			
			

		}
		catch(Exception e) {
			
			System.out.println(e);
		}
		try { // we close all the ObjectOutputStream
			musical.close();
			comedy.close();
			animation.close();
			adventure.close();
			drama.close();
			crime.close();
			biography.close();
			horror.close();
			action.close();
			documentary.close();
			fantasy.close();
			mystery.close();
			sci_fi.close();
			family.close();
			western.close();
			romance.close();
			thriller.close();
		
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		
		
		
		return "part3_manifest.txt"; // we return the part3_manifest.txt so that we can use it in the do_part3 method.
		
	}
	
	/**
	 * Counts the number of objects stored in a binary file.
	 *
	 * @param file The name of the binary file.
	 * @return The number of objects stored in the binary file.
	 */
public static int num_object(String file) { // this method will be used to find how many objects we have in each binary file.
    int count = 0;

    try (ObjectInputStream inStreamName = new ObjectInputStream(new FileInputStream(file))) {
        // Attempt to read objects until EOFException is caught
        while (true) {
            try {
                // Read object from the file
                Object obj = inStreamName.readObject();
                // If an object is successfully read, increment the count
                count++;
            } catch (EOFException e) {
                // End of file reached, break out of the loop
                break;
            } catch (ClassNotFoundException e) {
                // Class of a serialized object cannot be found
                e.printStackTrace();
            }
        }
    } catch (IOException e) {
        // Error reading from the file
        e.printStackTrace();
    }

    return count;
}
		
/**
 * Returns the current position of the cursor when the user enters a negative value.
 *
 * @param array: The 2D array of Movie objects.
 * @param choice3: The user's choice of genre .
 * @param choice_4 : The user's input value (negative integer).
 * @param current_position The current position of the cursor.
 * @return The updated current position after processing the negative value.
 */
public static int negNumber(Movie[][] array, int choice3, int choice_4, int current_position) {
        int absolute = Math.abs(choice_4);
        if (absolute - 1 > 0 && current_position - absolute - 1 > 0) { // if it is bigger then 0 then there is enough elements.
            for (int i = current_position; i > current_position - absolute - 1; i--) { // since it is a negative value, we will print from the current to the current position - the value entered by the user.
                System.out.println(array[choice3 - 1][i] + "\n");
            }
            current_position = current_position - absolute - 1; // we set the new value of the current position
        } else if (absolute - 1 > 0 && current_position - absolute < 0) { // if it is smaller then 0 then there is not enough movies therefore BOF has been reached.
            System.out.println("BOF has been reached");
            if (current_position < 0) {
                for (int i = array[choice3 - 1].length; i >= 0; i--) {
                    System.out.println(array[choice3 - 1][i] + "\n");
                }
            } else {
                for (int i = current_position; i >= 0; i--) {
                    System.out.println(array[choice3 - 1][i] + "\n");
                }
            }
            current_position = 0;
        }
    
    return current_position;
}
/**
 * Returns the current position of the cursor when the user enters a positive value.
 *
 * @param array: The 2D array of Movie objects.
 * @param choice3: The user's choice of genre.
 * @param choice_4: The user's input value (positive integer).
 * @param current_position : The current position of the cursor.
 * @return The updated current position after processing the positive value.
 */		
public static int posNumber(Movie[][] array, int choice3, int choice_4, int current_position) {// this method will be used to return the current posistion of the cursor when the user enters a positive value.
        if (choice_4 - 1 > 0 && current_position + choice_4 - 1 < array[choice3 - 1].length) {
            for (int i = current_position; i < current_position + choice_4; i++) {
                System.out.println(array[choice3 - 1][i] + "\n");
            }
            current_position += choice_4 - 1;
       
        } else if (choice_4 - 1 > 0 && choice_4 - 1 + current_position >= array[choice3 - 1].length) {
            System.out.println("BOF has been reached");
            if (current_position > array[choice3 - 1].length) {
                for (int i = 0; i < array[choice3 - 1].length; i++) {
                    System.out.println(array[choice3 - 1][i] + "\n");
                }
            } else {
                for (int i = current_position; i < array[choice3 - 1].length; i++) {
                    System.out.println(array[choice3 - 1][i] + "\n");
                }
            }
            current_position = array[choice3 - 1].length - 1;
            
        }
    
    return current_position;
}
	
/**
 * Reads objects from each binary file and adds them to their respectful position in the 2D array of Movie.
 * Pritns out the menu, and handles the user's input
 * @param file The file containing the list of genres.
 */
	public static void do_part3(String file) {
		Scanner input =null;
		Scanner input2 = new Scanner(System.in);
		int pos_checker =0;
		String next_line2 = null;
		int num_m=0;
		int num_c=0;
		int num_a=0;
		int num_a2=0;
		int num_d=0;
		int num_c2=0;
		int num_b=0;
		int num_h=0;
		int num_a3=0;
		int num_d2=0;
		int num_f=0;
		int num_c3=0;
		int num_m2=0;
		int num_s=0;
		int num_f2=0;
		int num_w=0;
		int num_r=0;
		int num_t=0;
		String[] array2 = {"musical","comedy","animation","adventure","drama","crime","biography","horror","action","documentary","fantasy","mystery","sci_fi","family","western","romance","thriller"};
		boolean checker =true;
		ObjectInputStream musical=null;
		ObjectInputStream comedy=null;
		ObjectInputStream animation=null;
		ObjectInputStream adventure=null;
		ObjectInputStream drama=null;
		ObjectInputStream crime=null;
		ObjectInputStream biography=null;
		ObjectInputStream horror=null;
		ObjectInputStream action=null;
		ObjectInputStream documentary=null;
		ObjectInputStream fantasy=null;
		ObjectInputStream mystery=null;
		ObjectInputStream sci_fi=null;
		ObjectInputStream family=null;
		ObjectInputStream western=null;
		ObjectInputStream romance=null;
		ObjectInputStream thriller=null;
		
		
		try {
			musical = new ObjectInputStream(new FileInputStream("musical.ser"));
			comedy = new ObjectInputStream(new FileInputStream("comedy.ser"));
			animation = new ObjectInputStream(new FileInputStream("animation.ser"));
			adventure = new ObjectInputStream(new FileInputStream("adventure.ser"));
			drama = new ObjectInputStream(new FileInputStream("drama.ser"));
			crime = new ObjectInputStream(new FileInputStream("crime.ser"));
			biography = new ObjectInputStream(new FileInputStream("biography.ser"));
			action = new ObjectInputStream(new FileInputStream("action.ser"));
			documentary = new ObjectInputStream(new FileInputStream("documentary.ser"));
			fantasy = new ObjectInputStream(new FileInputStream("fantasy.ser"));
			horror = new ObjectInputStream(new FileInputStream("horror.ser"));
			mystery = new ObjectInputStream(new FileInputStream("mystery.ser"));
			sci_fi = new ObjectInputStream(new FileInputStream("sci_fi.ser"));
			family = new ObjectInputStream(new FileInputStream("family.ser"));
			western = new ObjectInputStream(new FileInputStream("western.ser"));
			romance = new ObjectInputStream(new FileInputStream("romance.ser"));
			thriller = new ObjectInputStream(new FileInputStream("thriller.ser"));
			input = new Scanner(new FileInputStream(file));
			while(input.hasNextLine()) {
				pos_checker++;
				next_line2=input.nextLine();
				int number = num_object(next_line2);
				if(pos_checker==1) {
					num_m=number;
					
				}
				else if(pos_checker==2) {
					num_c=number;
				}
				else if(pos_checker==3) {
					num_a=number;
				}
				else if(pos_checker==4) {
					num_a2=number;
				}
				else if(pos_checker==5) {
					num_d=number;
				}
				else if(pos_checker==6) {
					num_c2=number;
				}
				else if(pos_checker==7) {
					num_b=number;
				}
				else if(pos_checker==8) {
					num_h=number;
				}
				else if(pos_checker==9) {
					num_a3=number;
				}
				else if(pos_checker==10) {
					num_d2=number;
				}
				else if(pos_checker==11) {
					num_f=number;
				}
				else if(pos_checker==12) {
					num_m2=number;
				}
				else if(pos_checker==13) {
					num_s=number;
				}
				else if(pos_checker==14) {
					num_f2=number;
				}
				else if(pos_checker==15) {
					num_w=number;
				}
				else if(pos_checker==16) {
					num_r=number;
				}
				else if(pos_checker==17) {
					num_t=number;
				}
				
				
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		Movie[][] array = new Movie[][] {    
		    new Movie[num_m],    
		    new Movie[num_c],    
		    new Movie[num_a],   
		    new Movie[num_a2],    
		    new Movie[num_d],   
		    new Movie[num_c2],   
		    new Movie[num_b],   
		    new Movie[num_h],   
		    new Movie[num_a3],   
		    new Movie[num_d2],    
		    new Movie[num_f],   
		    new Movie[num_m2],    
		    new Movie[num_s],   
		    new Movie[num_f2],   
		    new Movie[num_w],    
		    new Movie[num_r] ,  
		    new Movie[num_t]
		};
		try {
		
		for(int i=0;i<num_c;i++) {
			array[1][i] =(Movie) comedy.readObject();
		}
		
		for(int i=0;i<num_a;i++) {
			array[2][i] =(Movie) animation.readObject();
		}
		
		for(int i=0;i<num_a2;i++) {
			array[3][i] =(Movie) adventure.readObject();
		}
		
		for(int i=0;i<num_d;i++) {
			array[4][i] =(Movie) drama.readObject();
		}
		
		for(int i=0;i<num_c2;i++) {
			array[5][i] =(Movie) crime.readObject();
		}
	
		for(int i=0;i<num_b;i++) {
			array[6][i] =(Movie) biography.readObject();
		}

		for(int i=0;i<num_h;i++) {
			array[7][i] =(Movie) horror.readObject();
		}
	
		for(int i=0;i<num_a3;i++) {
			array[8][i] =(Movie) action.readObject();
		}

		for(int i=0;i<num_d2;i++) {
			array[9][i] =(Movie) documentary.readObject();
		}
		for(int i=0;i<num_f2;i++) {
			array[13][i] =(Movie) family.readObject();
		}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		
		}
	
		int choice3=1;
		int current_position=0;
		int pos_musical=0;
		int pos_comedy=0;
		int pos_animation=0;
		int pos_adventure=0;
		int pos_drama=0;
		int pos_crime=0;
		int pos_bio =0;
		int pos_horror =0;
		int pos_action=0;
		int pos_doc=0;
		int pos_fantasy=0;
		int pos_mystery=0;
		int pos_sci=0;
		int pos_family=0;
		int pos_western=0;
		int pos_romance=0;
		int pos_thriller=0;
		
				
		
		System.out.println("Welcome to Fouad's Movie's : ");
		while(checker) {
			
		System.out.println("-----------------------------\n             Main Menu\n-----------------------------\n s  Select a movie array to navigate\n n  Navigate "+ array2[choice3-1]+ " movies" +"("+  array[choice3-1].length+" Records)"+"\n x  Exit\n------------------------------");
		System.out.println("Enter your choice: ");
		String choice1 = input2.next();
			if(choice1.equalsIgnoreCase("s")) {
				System.out.println("---------------------------\n     Genre Sub-Menu\n--------------------------");
				for(int i =0; i<17;i++) {
					System.out.println((i+1)+ " "+ array2[i]+"                                  ("+ array[i].length+" Movies)");
				
				}
				System.out.println("18 Exit");
				System.out.println("--------------------");
				System.out.println("Enter your choice: ");
				int taker =choice3;
				choice3 = input2.nextInt();
				if(choice3 ==18) {
					choice3=taker;
				}
				
			}
			else if(choice1.equalsIgnoreCase("n")) { // if the user enters n
				System.out.println("Navigating " + array2[choice3-1]+ " movies" +"("+  array[choice3-1].length+" Records)");
				System.out.println("Enter choice: ");
				int choice_4= input2.nextInt();
				if(choice_4==0) {
				
				}
				
				else if(choice_4<0) { // here we call the negNumber method to take the new value of each cursor
					if(choice3==1) {
						pos_musical = negNumber(array,choice3,choice_4,pos_musical);
					}
					else if(choice3==2) {
						pos_comedy = negNumber(array,choice3,choice_4,pos_comedy);
					}
					else if(choice3==3) {
						pos_animation = negNumber(array,choice3,choice_4,pos_animation);
					}
					else if(choice3==4) {
						pos_adventure = negNumber(array,choice3,choice_4,pos_adventure);
					}
					else if(choice3==5) {
						pos_drama = negNumber(array,choice3,choice_4,pos_drama);
					}
					else if(choice3==6) {
						pos_crime = negNumber(array,choice3,choice_4,pos_crime);
					}
					else if(choice3==7) {
						pos_bio = negNumber(array,choice3,choice_4,pos_bio);
					}
					else if(choice3==8) {
						pos_horror = negNumber(array,choice3,choice_4,pos_horror);
					}
					else if(choice3==9) {
						pos_action = negNumber(array,choice3,choice_4,pos_action);
					}
					else if(choice3==10) {
						pos_doc = negNumber(array,choice3,choice_4,pos_doc);
					}
					else if(choice3==11) {
						pos_fantasy = negNumber(array,choice3,choice_4,pos_fantasy);
					}
					else if(choice3==12) {
						pos_mystery = negNumber(array,choice3,choice_4,pos_mystery);
					}
					else if(choice3==13) {
						pos_sci = negNumber(array,choice3,choice_4,pos_sci);
					}
					else if(choice3==14) {
						pos_family = negNumber(array,choice3,choice_4,pos_family);
					}
					else if(choice3==15) {
						pos_western = negNumber(array,choice3,choice_4,pos_western);
					}
					else if(choice3==16) {
						pos_romance = negNumber(array,choice3,choice_4,pos_romance);
					}
					else if(choice3==17) {
						pos_thriller = negNumber(array,choice3,choice_4,pos_thriller);
					}
					
					
				}
				else if(choice_4>0) { // here we call the posNumber method to take the new value of each cursor
					if(choice3==1) {
						pos_musical = posNumber(array,choice3,choice_4,pos_musical);
					}
					else if(choice3==2) {
						pos_comedy = posNumber(array,choice3,choice_4,pos_comedy);
					}
					else if(choice3==3) {
						pos_animation = posNumber(array,choice3,choice_4,pos_animation);
					}
					else if(choice3==4) {
						pos_adventure = posNumber(array,choice3,choice_4,pos_adventure);
					}
					else if(choice3==5) {
						pos_drama = posNumber(array,choice3,choice_4,pos_drama);
					}
					else if(choice3==6) {
						pos_crime = posNumber(array,choice3,choice_4,pos_crime);
					}
					else if(choice3==7) {
						pos_bio = posNumber(array,choice3,choice_4,pos_bio);
					}
					else if(choice3==8) {
						pos_horror = posNumber(array,choice3,choice_4,pos_horror);
					}
					else if(choice3==9) {
						pos_action = posNumber(array,choice3,choice_4,pos_action);
					}
					else if(choice3==10) {
						pos_doc = posNumber(array,choice3,choice_4,pos_doc);
					}
					else if(choice3==11) {
						pos_fantasy = posNumber(array,choice3,choice_4,pos_fantasy);
					}
					else if(choice3==12) {
						pos_mystery = posNumber(array,choice3,choice_4,pos_mystery);
					}
					else if(choice3==13) {
						pos_sci = posNumber(array,choice3,choice_4,pos_sci);
					}
					else if(choice3==14) {
						pos_family = posNumber(array,choice3,choice_4,pos_family);
					}
					else if(choice3==15) {
						pos_western = posNumber(array,choice3,choice_4,pos_western);
					}
					else if(choice3==16) {
						pos_romance = posNumber(array,choice3,choice_4,pos_romance);
					}
					else if(choice3==17) {
						pos_thriller = posNumber(array,choice3,choice_4,pos_thriller);
					}
					
					
					
				}
			}
			else if(choice1.equalsIgnoreCase("x")) {
				System.out.println("Thank you for trying my program!!");
				System.exit(0);
			}
		
		
		}
		
	}
	
	
	


	
	
	
	
	public static void main(String[] args) {
		
		
		
		String part1_manifest = "part1_manifest.txt";

		
		String part2_manifest = do_part1(part1_manifest /*, ... */); // partition
		
	
		String part3_manifest = do_part2(part2_manifest /*, ... */); // serialize
		do_part3(part3_manifest /*, ... */); 
		// and navigate
		
	}

}