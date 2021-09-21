package com.example.mareu.repository;

import com.example.mareu.R;
import com.example.mareu.model.Meeting;
import com.example.mareu.model.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyMeetingGenerator {

    public static List<Room> DUMMY_ROOMS = Arrays.asList(
            new Room(1, "Brasilia", R.drawable.brasilia),
            new Room(2, "Cactus", R.drawable.cactus),
            new Room(3, "Caipirinha", R.drawable.caipirinha),
            new Room(4, "Cubalibre", R.drawable.cubalibre),
            new Room(5, "Daiquiri", R.drawable.daiquiri),
            new Room(6, "Maitai", R.drawable.maitai),
            new Room(7, "Mojito", R.drawable.mojito),
            new Room(8, "Pinacolada", R.drawable.pinacolada),
            new Room(9, "Planteur", R.drawable.planteur),
            new Room(10, "Tipunch", R.drawable.tipunch)
    );

    public static List<Meeting> DUMMY_FAKE_MEETING = Arrays.asList(
            new Meeting(1, "Soutenance", "20/09/2021",
                    "10:00", "1h",
                    Arrays.asList("alex@lamzone.com","fabien@lamzone.com","maelle@lamzone.com","awatef@lamzone.com")),
            new Meeting(5, "Reunion d'equipe", "23/09/2021",
                    "8:00", "30min",
                    Arrays.asList("jack@lamzone.com","viviane@lamzone.com","seb@lamzone.com","achraf@lamzone.com")),
            new Meeting(6, "Reunion d'information", "22/09/2021",
                    "11:00", "1h30",
                    Arrays.asList("denis@lamzone.com","marie@lamzone.com","sophie@lamzone.com","milena@lamzone.com")),
            new Meeting(2, "Soutenance", "21/09/2021",
                    "15:00", "15min",
                    Arrays.asList("alex@lamzone.com","fabien@lamzone.com","maelle@lamzone.com","awatef@lamzone.com")),
            new Meeting(6, "Reunion d'equipe", "29/09/2021",
                    "18:00", "45min",
                    Arrays.asList("jack@lamzone.com","viviane@lamzone.com","seb@lamzone.com","achraf@lamzone.com"))

    );

    static List<Room> generateRooms() {
        return new ArrayList<>(DUMMY_ROOMS);
    }

    static List<Meeting> generateMeeting() {
      return new ArrayList<>(DUMMY_FAKE_MEETING);
    }
}