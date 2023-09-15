package jmwdev.testdome;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

public class Song {
    /**
     * A playlist is considered a repeating playlist if any of the songs contain a reference to a previous song in the playlist. Otherwise, the playlist will end with the last song which points to null.
     * Implement a function isInRepeatingPlaylist that efficiently with repest to time used, returns true if a playlist is repeating or false if it is not.
     */
    static Logger log = LogManager.getLogger(Song.class);
    private final String name;
    private Song nextSong;

    public Song(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        Song first = new Song("Hello");
        Song second = new Song("Eye of the tiger");

        first.setNextSong(second);
        second.setNextSong(first);

        log.info("{} {}", first.name, first.isInRepeatingPlaylist());
    }

    public void setNextSong(Song nextSong) {
        this.nextSong = nextSong;
    }

    public boolean isInRepeatingPlaylist() {
        if (nextSong == null) {
            return false;
        } else {
            Set<Song> playlist = new HashSet<>();
            playlist.add(this);
            return isInRepeatingPlaylist(playlist, this.nextSong);
        }
    }

    private boolean isInRepeatingPlaylist(Set<Song> playlist, Song song) {
        if (song != null) {
            if (playlist.contains(song)) {
                return true;
            } else {
                playlist.add(song);
                return isInRepeatingPlaylist(playlist, song.nextSong);
            }
        }
        return false;
    }
}
