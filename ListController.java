package view;

import java.util.ArrayList;
import java.util.Optional;

import com.sun.glass.events.MouseEvent;

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
	
	public void start(Stage mainStage){
		
		// create an ObservableList 
	      // from an ArrayList   
			
		Song song1 = new Song("Hello", "Adele", "25", 2015);
		Song song2 = new Song("Thirller", "MJ", "Thriller", 1982);
		Song song3 = new Song("Billie Jean", "MJ", "Thriller", 1982);
		Song song4 = new Song("Alejandro", "Lady Gaga", "The Fame Monster", 2009);
		Song song5 = new Song("Starboy", "The Weeknd", "Starboy", 2016);
		Song song6 = new Song("Bohemian Rhapsody", "Queen", "A Night at the Opera", 1975);
		Song song7 = new Song("Shape of You", "Ed Sheeran", "Divide", 2017);
		Song song8 = new Song("The Greatest", "Sia", "This is Acting- Deluxe Edition", 2016);
		Song song9 = new Song("Hymn for the Weekend", "Coldplay", "A Head Full of Dreams", 2015);
		Song song10 = new Song("Dear Theodosia", "Lin-Manuel Miranda", "Hamilton", 2015);
		Song song11 = new Song("Cycle", "Beck", "Morning Phase", 2014);
		Song song12 = new Song("Get Lucky", "Daft Punk ft. Pharrell Williams", "Random Access Memories", 2013);
		Song song13 = new Song("Hymn for the Weekend", "Coldplay", "A Head Full of Dreams", 2015);
		Song song14 = new Song("How Far I'll Go", "Alesia Cara", "Moana", 2016);
		Song song15 = new Song("I Don't Wanna Live Forever","Taylor Swift", "Fifty Shades Darker", 2017);
		
	      obsList = FXCollections.observableArrayList(                               
	    		  	song1,
	    		  	song2,
	    		  	song3,
	    		  	song4,
	    		  	song5,
	    		  	song6,
	    		  	song7,
	    		  	song8,
	    		  	song9,
	    		  	song10,
	    		  	song11,
	    		  	song12,
	    		  	song13,
	    		  	song14,
	    		  	song15);               
	      listView.setItems(obsList);  
	    
	      listView
	      	.getSelectionModel()
	      	.selectedIndexProperty()
	      	.addListener((obs,oldVal,newVal)->
	      			    	showInfo());
	      
	      // select the first item if list is not empty 
	      if (obsList.size() >1){
	    	  listView.getSelectionModel().select(0);
	      }
	      
	    
	}
	
	private void showInfo(){
		try{
			Song item = listView.getSelectionModel().getSelectedItem();
			song_name.setText(item.getName());
			song_album.setText(item.getAlbum());
			song_artist.setText(item.getArtist());
			song_year.setText(""+item.getYear());
			fill_edit();
		}catch(Exception e){
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Error!");
			alert.setHeaderText("Error the song cannot be shown");
			String content = "It seems the list is empty";
			alert.setContentText(content);
			clearInfo();
			alert.showAndWait();
		}
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
	
	private void showItemInputDialog(Stage mainStage){
		
//		//String item = listView.getSelectionModel().getSelectedItem();
//		int index = listView.getSelectionModel().getSelectedIndex();
//		
//		TextInputDialog dialog = new TextInputDialog(item);
//		dialog.initOwner(mainStage);
//		dialog.setTitle("List Item:"+item);
//		dialog.setHeaderText("Selected Item (Index: "+ index+ ")");
//		dialog.setContentText("Enter name: ");
//		
//		Optional<String> result = dialog.showAndWait();
		
		//if (result.isPresent()){ obsList.set(index,result.get());}
//		Alert alert = new Alert(AlertType.INFORMATION);
//		alert.initOwner(mainStage);
//		alert.setTitle("List Item: "+listView.getSelectionModel().getSelectedItem());
//		alert.setHeaderText("Selected list item properties");
//		
//		String content = "Index: "+ 
//						listView.getSelectionModel().getSelectedIndex()
//						+"\nValue:"+
//						listView.getSelectionModel().getSelectedItem();
//		alert.setContentText(content);
//		alert.showAndWait();
	}
	
	
	public void add(){ // add alphabetically
		String song = add_song.getText();
		String album = add_album.getText(); // should be optional
		String artist = add_artist.getText();
		int year = Integer.parseInt(add_year.getText()); // should be optional
		Song new_song = new Song(song,album,artist,year);
		obsList.add(new_song);
		listView.getSelectionModel().select(obsList.size()-1);
		
		// add check if it name+artist combo exists
			// if it does
			//pop up alert dialog, don't add
		// add pop up to confirm if you want to add
		
		//clear info if user hits yes confirm
		add_song.setText("");
		add_album.setText("");
		add_artist.setText("");
		add_year.setText("");
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
		int year = Integer.parseInt(edit_year.getText()); // should be optional
		Song edited_song = new Song(song,album,artist,year);
		obsList.set(index, edited_song);
		listView.getSelectionModel().select(index);
		showInfo();
		// add check if it name+artist combo exists
					// if it does
					//pop up alert dialog, don't add
				// add pop up to confirm if you want to add
				
				//clear info if user hits yes confirm
		//clear info if user hits yes confirm
				edit_song.setText("");
				edit_album.setText("");
				edit_artist.setText("");
				edit_year.setText("");
		
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
