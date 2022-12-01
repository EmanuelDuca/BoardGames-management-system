import Model.*;
import utils.MyFileHandler;

public class testModelManager
{
  public static void main(String[] args)
  {
    ModelManager modelManager = new ModelManager("gameCollection.bin","playerList.bin",
        "reservationList.bin","eventList.bin");

    /*
    PlayerList playerList = new PlayerList();
    playerList.addMember("Emanuel","5179");
    playerList.addGuest("Vlad","4869");
    playerList.addMember("Agoston","3579");
    playerList.addGuest("Levente","5189");

    modelManager.savePlayers(playerList);

    modelManager.addPlayer("Ivan","7777",true);
    */

    /*
    GameCollection collection1 = modelManager.getAllGames();
    System.out.println(collection1);
    */

    /*
    EventList eventList = new EventList();
    eventList.addEvent("New Year Party","Everyone can join",new DateTime(2022,12,25,18),new DateTime(2022,12,26,5),"event1.jpg");

    System.out.println(eventList);



<<<<<<< HEAD
    EventList copyEvents = modelManager.getAllEvents();
    System.out.println(copyEvents);
    */

    GameCollection gameCollection = modelManager.getAllGames();
    System.out.println(gameCollection);


    ReservationList reservationList = new ReservationList();
    reservationList.addReservation(modelManager.getGame("Chess"),modelManager.getPlayerByName("Ivan"),DateTime.today(),new DateTime(2022,12,4));
    modelManager.saveReservations(reservationList);

    ReservationList copyReservation = modelManager.getReservationList();
    System.out.println(copyReservation);



  }
}
