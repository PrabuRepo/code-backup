package com.practice.ood.problems;

import java.util.Queue;
import java.util.Set;

public class JukeBox {
	private CDPlayer cdPlayer;
	private User user;
	private Set<CD> cdCollection;
	private SongSelector ts;

	public JukeBox(CDPlayer cdPlayer, User user, Set<CD> cdCollection, SongSelector ts) {
		super();
		this.cdPlayer = cdPlayer;
		this.user = user;
		this.cdCollection = cdCollection;
		this.ts = ts;
	}

	public Song getCurrentSong() {
		return ts.getCurrentSong();
	}

	public void setUser(User u) {
		this.user = u;
	}
}

class CD {

}

class CDPlayer {
	private Playlist p;
	private CD c;

	public Playlist getPlaylist() {
		return p;
	}

	public void setPlaylist(Playlist p) {
		this.p = p;
	}

	public CD getCD() {
		return c;
	}

	public void setCD(CD c) {
		this.c = c;
	}

	public CDPlayer(Playlist p) {
		this.p = p;
	}

	public CDPlayer(CD c, Playlist p) {
		this.p = p;
		this.c = c;
	}

	public CDPlayer(CD c) {
		this.c = c;
	}

	public void playSong(Song s) {
	}
}

class Playlist {
	private Song song;
	private Queue<Song> queue;

	public Playlist(Song song, Queue<Song> queue) {
		super();
		this.song = song;
		this.queue = queue;
	}

	public Song getNextSongToPlay() {
		return queue.peek();
	}

	public void queueUpSong(Song s) {
		queue.add(s);
	}
}

class Song {
	private String songName;

	public String toString() {
		return songName;
	}
}

class SongSelector {
	private Song currentSong;

	public SongSelector(Song s) {
		currentSong = s;
	}

	public void setSong(Song s) {
		currentSong = s;
	}

	public Song getCurrentSong() {
		return currentSong;
	}
}

class User {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	private long ID;

	public User(String name, long iD) {
		this.name = name;
		ID = iD;
	}

	public User getUser() {
		return this;
	}

	public static User addUser(String name, long iD) {
		return new User(name, iD);
	}
}
