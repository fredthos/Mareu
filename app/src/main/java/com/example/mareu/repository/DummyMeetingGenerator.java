package com.example.mareu.repository;

import com.example.mareu.R;
import com.example.mareu.model.Meeting;
import com.example.mareu.model.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyMeetingGenerator {

    public static List<Room> DUMMY_ROOMS = Arrays.asList(
            new Room(1, "Tipunch", R.drawable.tipunch),
            new Room(2, "Planteur", R.drawable.planteur),
            new Room(3, "Mojito", R.drawable.mojito),
            new Room(4, "Cubalibre", R.drawable.cubalibre),
            new Room(5, "Cactus", R.drawable.cactus),
            new Room(6, "Maitai", R.drawable.maitai),
            new Room(7, "Daiquiri", R.drawable.daiquiri),
            new Room(8, "Caipirinha", R.drawable.caipirinha),
            new Room(9, "Pinacolada", R.drawable.pinacolada),
            new Room(10, "Brasilia", R.drawable.brasilia)
    );

    public static List<Meeting> DUMMY_MEETING = Arrays.asList(
            new Meeting(1, "Soutenance", 10.06,
                    10, 11, "maxime@lamzone.com" + "alex@lamzone.com"),
            new Meeting(5, "Reunion d'equipe", 12.06,
                    7, 10, "viviane@lamzone.com" + "seb@lamzone.com"),
            new Meeting(8, "Reunion d'information", 15.06,
                    8, 9, "guillaume@lamzone.com" + "fabien@lamzone.com")
    );

    static List<Room> generateRooms() {
        return new ArrayList<>(DUMMY_ROOMS);
    }

    static List<Meeting> generateMeeting() {
      return new ArrayList<>(DUMMY_MEETING);
    }
}