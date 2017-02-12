package model;

import java.io.Serializable;

public class Song implements Comparable<Song>, Serializable{
	private String name;
	private String artist;
	private String album;
	private int year;
	
	public Song(String name, String album, String artist, int year){
		this.name = name;
		this.artist = artist;
		this.album = album;
		this.year = year;
	}
	
	public Song(String name, String album, String artist){
		this(name, album, artist, 0);
	}
	
	public Song(String name, String artist, int year){
		this(name, artist, "", year);
	}
	
	public Song(String name, String artist){
		this(name, artist, "", 0);
	}
	
	
	public String getName(){
		return this.name;
	}
	
	public String getArtist(){
		return this.artist;
	}
	
	public String getAlbum(){
		return this.album;
	}
	
	public int getYear(){
		return this.year;
	}
	
	public void setName(){
		this.name = name;
	}
	
	public void setArtist(String artist){
		this.artist = artist;
	}
	
	public void setAlbum(String album){
		this.album = album;
	}
	
	public void setYear(int year){
		this.year = year;
	}
	
	public String toString(){
		return this.getName();
	}
	
	public int compareTo(Song other){ // helpful for alphabetical search
		if (this.equals(other)){
			return 0; 
		}else if(this.name.compareToIgnoreCase(other.name)>0){
			return 1;
			
		} else{
			return -1;
		}
	}
	
	public boolean equals(Object other){
		if(other != null && (other instanceof Song)){
			Song song = (Song) other;
			if(this.name.equalsIgnoreCase(song.name) && this.artist.equalsIgnoreCase(song.artist)){
				return true;
			}
		}
		return false;
	}
}
