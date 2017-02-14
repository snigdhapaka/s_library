package view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import java.util.Iterator;

import com.sun.glass.events.MouseEvent;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.text.Text;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import model.Song;


public class ListController {

	@FXML ListView<Song> listView;
	@FXML TabPane Add;
	@FXML Text song_name;
	@FXML Text song_album;
	@FXML Text song_artist;
	@FXML Text song_year;
	
	@FXML TextField add_song;
	@FXML TextField add_album;
	@FXML TextField add_artist;
	@FXML TextField add_year;
	
	@FXML TextField edit_song;
	@FXML TextField edit_album;
	@FXML TextField edit_artist;
	@FXML TextField edit_year;


	   private ObservableList<Song> obsList;              
	  
	   public void start(Stage mainStage) {    
		   
		   /*Song song1 = new Song("Hello", "25", "Adele", 2015);
			Song song2 = new Song("Thirller", "Thriller", "MJ", 1982);
			Song song3 = new Song("Billie Jean", "Thriller", "MJ", 1982);
			Song song4 = new Song("Alejandro", "The Fame Monster", "Lady Gaga", 2009);
			Song song5 = new Song("Starboy", "Starboy", "The Weeknd", 2016);
			
		      obsList = FXCollections.observableArrayList(                               
		    		  	song1,
		    		  	song2,
		    		  	song3,
		    		  	song4,
		    		  	song5
		    		  	);               
		      listView.setItems(obsList);  
		      */
		   	  
		   	  System.out.println("Extracting the songs from file");
		      ArrayList<Song> songs = new ArrayList<Song>();
		      
		      
		      try {
		    	  File file = new File("songlist.txt");
		    	  System.out.println("Reading file");
			      if(!file.exists()){
			    	System.out.println("File does not exist.");
		          	file.createNewFile();
		          }
			      FileReader fw = new FileReader(file);
			      BufferedReader bw = new BufferedReader(fw);
	            	String line;
	            	int year = 0;
	            	while((line = bw.readLine()) != null){
	            		System.out.println(line);
	            		String[] s = line.split(",");
	            		if(s[3].equals("")){
	            			year = 0;
	            		} else {
	            			year = Integer.parseInt(s[3]);
	            		}
	            		Song song = new Song(s[0], s[1], s[2], year);
	            		songs.add(song);
	            	}
	            	bw.close();
	             } catch (Exception ex) {
	            	 System.out.println("Finished writing file.");
	                 ex.printStackTrace();
	             }
		      
		      obsList = FXCollections.observableArrayList(songs);               
		      listView.setItems(obsList); 
		      
		   
		      
		      Collections.sort(obsList);
		      
		      listView
		      	.getSelectionModel()
		      	.selectedIndexProperty()
		      	.addListener((obs,oldVal,newVal)->
		      			    	showInfo());
		      
		      // select the first item if list is not empty 
		      if (obsList.size() > 1){
		    	  listView.getSelectionModel().select(0);
		      }
		      
		    
		
	      
	      
	      Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
		        public void run() {
		            System.out.println("In shutdown hook");
		            try {
		            	File file = new File("songlist.txt");
		            	if(file.exists()){
		            		file.delete();
		            	}
		            	file.createNewFile();
		            	FileWriter fw = new FileWriter(file);
		            	BufferedWriter bw = new BufferedWriter(fw);
		            	Iterator<Song> itr = obsList.iterator();
		            	while(itr.hasNext()){
		            		Song s = (Song) itr.next();
		            		bw.write(""+s.getName()+","+s.getAlbum()+","+s.getArtist()+","+s.getYear()+"\n");
		            	}
		            	bw.close();
		             } catch (Exception ex) {
		            	 System.out.println("Finished writing file.");
		                 //ex.printStackTrace();
		             }
		        }
		    }, "Shutdown-thread"));
	      

	   }
	   
	   private void showInfo(){
//			try{
				int index = listView.getSelectionModel().getSelectedIndex();
				if(index == -1 && obsList.size()>0){
					listView.getSelectionModel().select(0);
				}
				
				Song item = listView.getSelectionModel().getSelectedItem();	
				if (item != null){
					song_name.setText(item.getName());
					song_album.setText(item.getAlbum());
					song_artist.setText(item.getArtist());
					song_year.setText(""+item.getYear());
					fill_edit();
				} else
					System.out.println("NULL");
				
//			}catch(Exception e){
//				e.printStackTrace();
//				Alert alert = new Alert(AlertType.INFORMATION);
//				alert.setTitle("Error!");
//				alert.setHeaderText("Error the song cannot be shown");
//				String content = "It seems the list is empty";
//				alert.setContentText(content);
//				clearInfo();
//				alert.showAndWait();
//			}
		}
	   
	   private void clearInfo(){
			  song_name.setText("");
			  song_year.setText("");
			  song_artist.setText("");
			  song_album.setText("");
			  edit_song.setText("");
			  edit_year.setText("");
			  edit_artist.setText("");
			  edit_album.setText("");
			  
		}
	   public void alert(String message){
		   Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Error Message");
			alert.setHeaderText(message);
			/*String content = "Song Name: "+ song.getName()
			+"\nArtist: "+ song.getArtist();
			alert.setContentText(content);*/
	         ButtonType okButton = new ButtonType("OK", ButtonData.CANCEL_CLOSE);
	         Optional<ButtonType> result = alert.showAndWait();
	         if(result.get() == okButton)
	         { 
	             alert.close();
	         }
	   }
	   
	   public void add(){  // need to check if alphabetic insert works ok
			// ISSUE IF ITEM IS FIRST ITEM INSERTED	
			String song = add_song.getText();
			String album = add_album.getText(); // should be optional
			String artist = add_artist.getText();
			int year = 0; // should be optional
			String num = add_year.getText().trim();
			if(!num.isEmpty()){
				try{
					year = Integer.parseInt(num);
				} catch (Exception ex) {
					alert("Year should be formatted as a number.");
					return;
				}
			}
			
			if(song.trim().isEmpty() || artist.trim().isEmpty()){
				alert("Name and Artist are required fields.");
				return;
			}
			
			Song new_song = new Song(song,album,artist,year);
			
			if(!obsList.contains(new_song)){
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Edit Item: "+listView.getSelectionModel().getSelectedItem());
				alert.setHeaderText("Are you sure you want to add this song?");
			
				String content = "Song Name: "+ new_song.getName()
								+"\nArtist:"+ new_song.getArtist()
								+"\nAlbum:"+ new_song.getAlbum()
								+"\nYear:"+ new_song.getYear();
				  alert.setContentText(content);
				  ButtonType addButton = new ButtonType("Yes");
		          ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
	
		          alert.getButtonTypes().setAll(addButton,cancelButton);
	
		          Optional<ButtonType> result = alert.showAndWait();
		          if(result.get() == addButton)
		          {   
		  			  obsList.add(new_song);
		  			  Collections.sort(obsList);
		  			  listView.getSelectionModel().select(obsList.indexOf(new_song));
		  			  add_song.setText("");
					  add_album.setText("");
					  add_artist.setText("");
					  add_year.setText("");
		  			  showInfo();
		          }
		          else if(result.get() == cancelButton)
		          {
		              alert.close();
		          }
		         
			} else {
				alert("Sorry, this item already exists!");
			}
			
		}
	   
		
		public void fill_edit(){ //edit
			Song item = listView.getSelectionModel().getSelectedItem();
			edit_song.setText(item.getName());
			edit_album.setText(item.getAlbum());;
			edit_artist.setText(item.getArtist());
			edit_year.setText(""+item.getYear());
			
		}
		
		public void edit(){
			int index = listView.getSelectionModel().getSelectedIndex();
			String song = edit_song.getText();
			String album = edit_album.getText(); // should be optional
			String artist = edit_artist.getText();
			int year = 0; // should be optional
			String num = add_year.getText().trim();
			if(!num.isEmpty()){
				try{
					year = Integer.parseInt(num);
				} catch (Exception ex) {
					alert("Year should be formatted as a number.");
					return;
				}
			}
			
			if(song.trim().isEmpty() || artist.trim().isEmpty()){
				alert("Name and Artist are required fields.");
				return;
			}
			
			Song edited_song = new Song(song,album,artist,year);
			
			
			if(!obsList.contains(edited_song) || obsList.indexOf(edited_song) == index){
			
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Edit Item: "+listView.getSelectionModel().getSelectedItem());
				alert.setHeaderText("Are you sure you want to edit?");
			
				String content = "Song Name: "+ edited_song.getName()
								+"\nArtist:"+ edited_song.getArtist()
								+"\nAlbum:"+ edited_song.getAlbum()
								+"\nYear:"+ edited_song.getYear();
				  alert.setContentText(content);
				  ButtonType editButton = new ButtonType("Yes");
		          ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
	
		          alert.getButtonTypes().setAll(editButton,cancelButton);
	
		          Optional<ButtonType> result = alert.showAndWait();
		          if(result.get() == editButton)
		          {   
		  			  obsList.set(index, edited_song);
		  			  Collections.sort(obsList);
		  			  listView.getSelectionModel().select(obsList.indexOf(edited_song));
		  			  showInfo();
		          }
		          else if(result.get() == cancelButton)
		          {
		              alert.close();
		          }
			} else {
				alert("Sorry, this item already exists!");
			}
			
		}
		public void delete(){
			int index = listView.getSelectionModel().getSelectedIndex();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Delete Item: "+listView.getSelectionModel().getSelectedItem());
			alert.setHeaderText("Are you sure you want to delete?");
		
			String content = "Song Name: "+ 
							listView.getSelectionModel().getSelectedItem().getName()
							+"\nArtist:"+
							listView.getSelectionModel().getSelectedItem().getArtist()
							+"\nAlbum:"+listView.getSelectionModel().getSelectedItem().getAlbum()
							+"\nYear:"+listView.getSelectionModel().getSelectedItem().getYear();
			  alert.setContentText(content);
			  ButtonType deleteButton = new ButtonType("Yes");
	          ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

	          alert.getButtonTypes().setAll(deleteButton,cancelButton);

	          Optional<ButtonType> result = alert.showAndWait();
	          if(result.get() == deleteButton)
	          {
	              obsList.remove(index);
	              if (obsList.size() >2){
	    	    	  listView.getSelectionModel().select(index);
	    	      }else if(obsList.size()>1){
	    	    	  listView.getSelectionModel().select(0);
	    	      }
	             alert.close();
	          }
	          else if(result.get() == cancelButton)
	          {
	              alert.close();
	          }
			
		}

	}

