import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;
import java.util.Scanner;
class Playlist {
    String playlistName;
    LinkedList<String> songs;
    public Playlist(String playlistName) {
        this.playlistName = playlistName;
        this.songs = new LinkedList<>(); }
    void addSong(String song) {
        songs.add(song);
        System.out.println(song + " has been added to the playlist.");    }
    void removeSong(String song) {
        if (songs.remove(song)) {
            System.out.println(song + " has been removed from the playlist.");
        } else {
            System.out.println(song + " is not in the playlist.");      }}
    void displaySongs() {
        if (songs.isEmpty()) {
            System.out.println("The playlist is empty.");
        } else {
            System.out.println("Songs in the playlist:");
            for (int i = 0; i < songs.size(); i++) {
                System.out.println((i + 1) + ". " + songs.get(i));     }}}
    void rearrangeSong(int currentPosition, int newPosition) {
        if (currentPosition < 1 || currentPosition > songs.size() ||
                newPosition < 1 || newPosition > songs.size()) {
            System.out.println("Invalid positions. Please try again.");
            return;                                                      }
        String song = songs.remove(currentPosition - 1); // Remove song from current position
        songs.add(newPosition - 1, song); // Add song to the new position
        System.out.println("Song moved to the new position."); }}

public class MusicPlayer {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to the Music Playlist Manager!");
        System.out.println("Enter Playlist Name:");
        String name = input.nextLine();
        Playlist playlist = new Playlist(name);
        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Add song");
            System.out.println("2. Remove Song");
            System.out.println("3. Display Songs");
            System.out.println("4. Play Songs");
            System.out.println("5. Rearrange Songs");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = input.nextInt();
            input.nextLine(); // Consume newline

            if (choice == 1) {
                System.out.print("Enter song name: ");
                String song = input.nextLine();
                playlist.addSong(song);
            } else if (choice == 2) {
                System.out.print("Enter song name to remove: ");
                String song = input.nextLine();
                playlist.removeSong(song);
            } else if (choice == 3) {
                playlist.displaySongs();
            } else if (choice == 4) {
                playSongs(playlist.songs);
            } else if (choice == 5){
                playlist.displaySongs();
                System.out.print("Enter the current position of the song: ");
                int currentPosition = input.nextInt();
                System.out.print("Enter the new position: ");
                int newPosition = input.nextInt();
                playlist.rearrangeSong(currentPosition, newPosition);
            } else if (choice == 6)  {
                System.out.println("Exiting Music Playlist Manager. Goodbye!");
                break;
            }
            else {
                System.out.println("Invalid option. Please try again.");
            }}
        input.close();
    }
    private static void playSongs(LinkedList<String> songs) {
        if (songs.isEmpty()) {
            System.out.println("The playlist is empty. Add songs to play.");
            return;  }
        ListIterator<String> iterator = songs.listIterator();
        Scanner input = new Scanner(System.in);
        boolean playing = true;
        boolean goingForward = true;
        boolean repeatCurrent = false;
        boolean repeatAll = false;
        boolean shuffleMode = false;

        String currentSong = iterator.next();
        System.out.println("Now playing: " + currentSong);

        while (playing) {
            System.out.println("\nPlayback Options:");
            System.out.println("1. Next");
            System.out.println("2. Previous");
            System.out.println("3. Repeat Current Song");
            System.out.println("4. Repeat All Songs");
            System.out.println("5. Shuffle Mode");
            System.out.println("6. Stop Playback");
            System.out.print("Choose an option: ");
            int option = input.nextInt();

            switch (option) {
                case 1: // Next
                    if (!goingForward && iterator.hasNext()) {
                        iterator.next(); // Adjust direction
                    }
                    goingForward = true;

                    if (shuffleMode) {
                        Random random = new Random();
                        currentSong = songs.get(random.nextInt(songs.size()));
                        System.out.println("Now playing: " + currentSong);
                    } else if (iterator.hasNext()) {
                        currentSong = iterator.next();
                        System.out.println("Now playing: " + currentSong);
                    } else if (repeatAll) {
                        iterator = songs.listIterator();
                        currentSong = iterator.next();
                        System.out.println("Now playing: " + currentSong);
                    } else {
                        System.out.println("End of playlist.");
                        goingForward = false;
                    }break;

                case 2: // Previous
                    if (goingForward && iterator.hasPrevious()) {
                        iterator.previous(); // Adjust direction
                    } goingForward = false;
                    if (iterator.hasPrevious()) {
                        currentSong = iterator.previous();
                        System.out.println("Now playing: " + currentSong);
                    } else if (repeatAll) {
                        iterator = songs.listIterator(songs.size());
                        currentSong = iterator.previous();
                        System.out.println("Now playing: " + currentSong);
                    } else {
                        System.out.println("Start of playlist.");
                        goingForward = true;
                    }break;
                case 3: // Repeat Current Song
                    repeatCurrent = true;
                    System.out.println("Repeating current song: " + currentSong);
                    break;
                case 4: // Repeat All Songs
                    repeatAll = true;
                    System.out.println("Repeat all songs enabled.");
                    break;
                case 5: // Shuffle Mode
                    shuffleMode = !shuffleMode;
                    System.out.println("Shuffle mode " + (shuffleMode ? "enabled." : "disabled."));
                    break;
                case 6: // Stop Playback
                    System.out.println("Playback stopped.");
                    playing = false;
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
            if (repeatCurrent) {
                System.out.println("Now repeating: " + currentSong);
            }}}}