package model;

public class Song implements Comparable<Song>{
	String name;
	String artist;
	String album;
	int year;
	
	public Song(String name, String artist, String album, int year){
		this.name = name;
		this.artist = artist;
		this.album = album;
		this.year = year;
	}
	
	public Song(String name, String artist, String album){
		this(name, artist, album, 0);
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
	
	public int compareTo(Song other){
		return 0;
	}
	
	public boolean equals(Song other){
		if(this.name.equals(other.name) && this.artist.equals(other.artist)){
			return true;
		}
		return false;
	}
}
