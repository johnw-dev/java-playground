package jmwdev.testdome;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SongTest {
    @Test
    void isInRepeatingPlaylistShouldReturnFalseWhenOneSong() {
        Song first = new Song("Hello");
        assertFalse(first.isInRepeatingPlaylist());
    }

    @Test
    void isInRepeatingPlaylistShouldReturnTrueWhenLoopFound() {
        Song first = new Song("Hello");
        Song second = new Song("Hello2");
        Song third = new Song("Hello3");
        first.setNextSong(second);
        second.setNextSong(third);
        third.setNextSong(second);
        assertTrue(first.isInRepeatingPlaylist());
    }

    @Test
    void isInRepeatingPlaylistShouldReturnFalseWhenLongerPlaylistEnds() {
        Song first = new Song("Hello");
        Song second = new Song("Hello2");
        Song third = new Song("Hello3");
        first.setNextSong(second);
        second.setNextSong(third);
        assertFalse(first.isInRepeatingPlaylist());
    }
}
