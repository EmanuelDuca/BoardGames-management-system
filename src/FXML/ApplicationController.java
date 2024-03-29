package FXML;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javax.swing.JOptionPane;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Class for Managing the Application.
 * @author Group 6
 * @version 1.0
 */
public class ApplicationController
{
  ModelManager modelManager=new ModelManager("gameCollection.bin","playerList.bin",
      "reservationList.bin","eventList.bin");


  private  ObservableList<Player> playerData= FXCollections.observableArrayList();
  private  ObservableList<Player> memberData= FXCollections.observableArrayList();
  private  ObservableList<Game> gameData= FXCollections.observableArrayList();
  private  ObservableList<Reservation> reservationData = FXCollections.observableArrayList();
  private  ObservableList<Integer> ratingData = FXCollections.observableArrayList();
  private  ObservableList<Integer> hourData = FXCollections.observableArrayList();




  // Game - Add Game
  @FXML private ComboBox<Player> addOwners_Game;
  @FXML private TextField addTitle_Game;
  @FXML private TextField addMaxNumOfPlayers_Game;
  @FXML private TextField addImage_Game;
  @FXML private Button addSave_Game;
  // Game - Edit Game
  @FXML private ComboBox<Player> editOwners_Game;
  @FXML private ComboBox<Game> editGames_Game;
  @FXML private TextField editTitle_Game;
  @FXML private TextField editMaxNumOfPlayers_Game;
  @FXML private Button editSave_Game;
  //Game - Remove Game
  @FXML private ComboBox<Game> removeGames_Game;
  @FXML private Button removeSave_Game;
  //Game - Game List
  @FXML private TextArea displayGames_Game;

  @FXML private ComboBox<Game> ratingTabGameComboBox_Game;
  @FXML private ComboBox<Integer> ratingTabRatingComboBox_Game;
  @FXML private Button ratingTabSaveButton_Game;

  @FXML private ComboBox<Player> studentIdBorr_BorrowReserve;
  @FXML private ComboBox<Game> gameBorr_BorrowReserve;
  @FXML private DatePicker reserveToBorr_BorrowReserve;
  @FXML private Button borrow_BorrowReserve;
  @FXML private ComboBox<Integer> hourBorr_BorrowReserve;



  @FXML private ComboBox<Player> studentIdRes_BorrowReserve;
  @FXML private ComboBox<Game> gameRes_BorrowReserve;
  @FXML private DatePicker fromRes_BorrowReserve;
  @FXML private DatePicker toRes_BorrowReserve;
  @FXML private Button reserve_BorrowReserve;
  @FXML private ComboBox<Integer> startHour_BorrowReserve;
  @FXML private ComboBox<Integer> endHour_BorrowReserve;

  @FXML private Button removeReservation_BorrowReserve;
  @FXML private ComboBox<Reservation> reservationsToRemove_BorrowReserve;

  @FXML private TextArea reservationList_BorrowReserve;


  @FXML private TextField playerName;
  @FXML private TextField playerID;
  @FXML private TextField name2;
  @FXML private ComboBox<Player> playerBox;
  @FXML private Label labelForPlayers;
  @FXML private Button revokeButton;
  @FXML private Button saveChangesButton;
  @FXML private Button removePlayerButton;
  @FXML private Button addMember_Player;
  @FXML private Button addGuest_Player;

  @FXML private TextField titleInput_Event;
  @FXML private TextArea descriptionInput_Event;
  @FXML private TextField imageURLInput_Event;
  @FXML private DatePicker startDateInput_Event;
  @FXML private DatePicker endDateInput_Event;
  @FXML private Button saveButton_Event;
  @FXML private TextArea eventsDisplayed;
  @FXML private ComboBox<Event> eventComboBox;

  @FXML private TextField titleInputEdit_Event;
  @FXML private TextArea descriptionInputEdit_Event;
  @FXML private TextField imageURLEDIT_Event;
  @FXML private DatePicker startDateEdit_Event;
  @FXML private DatePicker endDateEdit_Event;
  @FXML private Button saveEditButton_Event;
  @FXML private Button removeButton_Event;

  @FXML private TextField vote1;
  @FXML private TextField vote2;
  @FXML private TextField vote3;
  @FXML private Button buttonPlusVote1;
  @FXML private Button buttonPlusVote2;
  @FXML private Button buttonPlusVote3;
  @FXML private Button buttonMinusVote1;
  @FXML private Button buttonMinusVote2;
  @FXML private Button buttonMinusVote3;
  @FXML private Button closePollButton;

  private Alert a = new Alert(Alert.AlertType.NONE);

  /**
   * The method that makes changes in the Event Tab according to the Buttons that were pressed.
   * @param e Button that was pressed
   * @throws FileNotFoundException
   */
  public void handleEvent(ActionEvent e) throws FileNotFoundException
  {
    if(e.getSource() == saveButton_Event)
    {
      try
      {
        String title = titleInput_Event.getText();
        String description = descriptionInput_Event.getText();
        String imageURL = imageURLInput_Event.getText();
        int startYear = startDateInput_Event.getValue().getYear(), startMonth = startDateInput_Event.getValue().getMonthValue(), startDay = startDateInput_Event.getValue().getDayOfMonth();
        int endYear = endDateInput_Event.getValue().getYear(), endMonth = endDateInput_Event.getValue().getMonthValue(), endDay = endDateInput_Event.getValue().getDayOfMonth();

        DateTime start=new DateTime(startYear,startMonth,startDay);
        DateTime end=new DateTime(endYear,endMonth,endDay);
        if(!title.equals("") && !description.equals(""))
        {
          if(start.isBefore(end)&&!start.isBefore(DateTime.today()))
          {
            modelManager.addEvent(title, description, imageURL, start, end);
            //          JOptionPane.showMessageDialog(null,"The event was created","Confirmation message",
            //              JOptionPane.INFORMATION_MESSAGE);
            a.setAlertType(Alert.AlertType.CONFIRMATION);
            a.setContentText("The event was successfully created");
            a.show();
          }else
          {
            //          JOptionPane.showMessageDialog(null,"The date is in the past!","Error message",JOptionPane.ERROR_MESSAGE);
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("The dates cannot be in the past");
            a.show();
          }
        }

        titleInput_Event.setText("");
        descriptionInput_Event.setText("");
        imageURLInput_Event.setText("");
        startDateInput_Event.setValue(null);
        endDateInput_Event.setValue(null);

        refreshComboEvent();
        reloadEventListAndDisplay();
      }catch (RuntimeException f)
      {
        f.fillInStackTrace();
        a.setAlertType(Alert.AlertType.ERROR);
        a.setContentText("Fill out all the fields");
        a.show();
      }



    }
    else if(e.getSource() == saveEditButton_Event)
    {

        try
        {
          EventList eventList = modelManager.getAllEvents();
          Event temp = eventList.getEvent(eventComboBox.getSelectionModel().getSelectedItem().getTitle());
          eventList.removeEvent(temp);
          modelManager.saveEvents(eventList);

          String title = titleInputEdit_Event.getText();
          String description = descriptionInputEdit_Event.getText();
          String imageURL = imageURLEDIT_Event.getText();
          int startYear = startDateEdit_Event.getValue().getYear(), startMonth = startDateEdit_Event.getValue().getMonthValue(), startDay = startDateEdit_Event.getValue().getDayOfMonth();
          int endYear = endDateEdit_Event.getValue().getYear(), endMonth = endDateEdit_Event.getValue().getMonthValue(), endDay = endDateEdit_Event.getValue().getDayOfMonth();
          DateTime start = new DateTime(startYear, startMonth, startDay);
          DateTime end = new DateTime(endYear, endMonth, endDay);


          if(start.isBefore(end)&&!start.isBefore(DateTime.today()))
          {
            modelManager.addEvent(title, description, imageURL, start, end);
            a.setAlertType(Alert.AlertType.CONFIRMATION);
            a.setContentText("The event was edited");
            a.show();
          }else
          {
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("The dates cannot be in the past");
            a.show();
          }


          reloadEventListAndDisplay();
          refreshComboEvent();

          titleInputEdit_Event.clear();
          descriptionInputEdit_Event.clear();
          imageURLEDIT_Event.clear();
          startDateEdit_Event.setValue(null);
          endDateEdit_Event.setValue(null);
          eventComboBox.getSelectionModel().clearSelection();
        }
        catch (NullPointerException exception)
        {
          exception.fillInStackTrace();
          a.setAlertType(Alert.AlertType.ERROR);
          a.setContentText("Please select an event");
          a.show();
        }

    }
    else if(e.getSource() == removeButton_Event)
    {
      try
      {
        EventList eventList = modelManager.getAllEvents();
        Event temp = eventList.getEvent(eventComboBox.getSelectionModel().getSelectedItem().getTitle());
        eventList.removeEvent(temp);
        modelManager.saveEvents(eventList);

        reloadEventListAndDisplay();
        refreshComboEvent();

        a.setAlertType(Alert.AlertType.CONFIRMATION);
        a.setContentText("The event was successfully removed");
        a.show();

        titleInputEdit_Event.clear();
        descriptionInputEdit_Event.clear();
        imageURLEDIT_Event.clear();
        startDateEdit_Event.setValue(null);
        endDateEdit_Event.setValue(null);
        eventComboBox.getSelectionModel().clearSelection();

      }
      catch (NullPointerException exception)
      {
        exception.fillInStackTrace();
        a.setAlertType(Alert.AlertType.ERROR);
        a.setContentText("Please select an event");
        a.show();
      }
    }
    else if(e.getSource() == eventComboBox)
    {
      try
      {
        Event event = eventComboBox.getSelectionModel().getSelectedItem();


        titleInputEdit_Event.setText(event.getTitle());
        descriptionInputEdit_Event.setText(event.getDescription());
        imageURLEDIT_Event.setText(event.getImage());
      }
      catch (NullPointerException exception)
      {
        exception.fillInStackTrace();
      }
    }

    modelManager.XMLFile();
  }

  /**
   * Update the Event list in the Event Tab
   */
  public void reloadEventListAndDisplay()
  {
    String text="";
    ArrayList<Event> events = modelManager.getAllEvents().getList();
    for (int i = 0; i < events.size(); i++)
    {
      text += events.get(i).toString()+"\n\n";
    }
    eventsDisplayed.setText(text);
  }

  /**
   * Method thar refresh the event combo box
   */
  public void refreshComboEvent()
  {
    try
    {
      ArrayList<Event> events = modelManager.getAllEvents().getList();
      eventComboBox.getItems().clear();

      for(Event element: events)
      {
        eventComboBox.getItems().add(element);
      }
    }
    catch (NullPointerException exception)
    {
      exception.fillInStackTrace();
    }
  }

  /**
   * The method that makes changes in the Game Tab according to the Buttons that were pressed.
   * @param e Button that was pressed
   * @throws FileNotFoundException
   */
  public void handlerGame (ActionEvent e) throws FileNotFoundException
  {
    if (e.getSource()== addSave_Game)
    {
      try
      {

          Game newGame=new Game(addTitle_Game.getText(),Integer.parseInt(
              addMaxNumOfPlayers_Game.getText()),
              addOwners_Game.getSelectionModel().getSelectedItem(), addImage_Game.getText());
          modelManager.addGame(newGame);

          a.setAlertType(Alert.AlertType.CONFIRMATION);
          a.setContentText("The game was successfully added");
          a.show();


        initialize();
        displayRefreshedGameList();

      }
      catch (RuntimeException f)
      {
        f.fillInStackTrace();
        a.setAlertType(Alert.AlertType.ERROR);
        a.setContentText("Please select an game, and fill out the fields");
        a.show();
      }
      }
    if (e.getSource()== editSave_Game)
    {
      try
      {
        Game selectedGame = editGames_Game.getSelectionModel().getSelectedItem();
        Player oldPlayer = selectedGame.getOwner();
        String img = selectedGame.getImage();
        Player selectedPlayer = editOwners_Game.getSelectionModel().getSelectedItem();

        if(selectedPlayer != null)
        {
          oldPlayer = selectedPlayer;
        }

        if(selectedGame != null)
        {

          modelManager.removeGame(selectedGame);
          Game gameToAdd = new Game(editTitle_Game.getText(),Integer.parseInt(editMaxNumOfPlayers_Game.getText()),oldPlayer, img);
          gameToAdd.addRating((int)Math.ceil(selectedGame.getAverageRating()));
          modelManager.addGame(gameToAdd);
          editTitle_Game.setText("");
          editMaxNumOfPlayers_Game.setText("");

          a.setAlertType(Alert.AlertType.CONFIRMATION);
          a.setContentText("The game was successfully edited");
          a.show();
          initialize();

        }
        else {
          a.setAlertType(Alert.AlertType.ERROR);
          a.setContentText("Please fill out all the fields");
          a.show();
        }
      }
      catch (NullPointerException exception)
      {
        exception.fillInStackTrace();
        a.setAlertType(Alert.AlertType.ERROR);
        a.setContentText("Please fill out all the fields");
        a.show();
      }

    }
    if (e.getSource()== removeSave_Game)
    {
      try
      {
        Game gameToRemove= removeGames_Game.getSelectionModel().getSelectedItem();
        if(!gameToRemove.equals(null))
        {
          modelManager.removeGame(gameToRemove);
          a.setAlertType(Alert.AlertType.CONFIRMATION);
          a.setContentText("The game was successfully removed");
          a.show();
        }

        initialize();
        displayRefreshedGameList();
      }
      catch (NullPointerException exception)
      {
        exception.fillInStackTrace();
        a.setAlertType(Alert.AlertType.ERROR);
        a.setContentText("Please select a game");
        a.show();
      }


    }

    if(e.getSource() == ratingTabSaveButton_Game)
    {
      try
      {

        Game game = ratingTabGameComboBox_Game.getValue();
        int rating = ratingTabRatingComboBox_Game.getValue();
        modelManager.rateAGame(game,rating);
        a.setAlertType(Alert.AlertType.CONFIRMATION);
        a.setContentText("Rating saved");
        a.show();
      }
      catch (NullPointerException exception)
      {
        exception.fillInStackTrace();
        a.setAlertType(Alert.AlertType.CONFIRMATION);
        a.setContentText("Please make sure you selected a game and a rating");
        a.show();
      }

      initialize();
    }

    if (e.getSource()==editGames_Game)
    {
      try
      {
        Game game=editGames_Game.getSelectionModel().getSelectedItem();
        editTitle_Game.setText(game.getTitle());
        editMaxNumOfPlayers_Game.setText(Integer.toString(game.getMaxPlayers()));
      }
      catch (NullPointerException exception)
      {
        exception.fillInStackTrace();
      }
    }

    if(e.getSource() == buttonPlusVote1)
    {
      int value = Integer.parseInt(vote1.getText());
      value++;
      vote1.setText(String.valueOf(value));
    }
    else if(e.getSource() == buttonMinusVote1)
    {
      int value = Integer.parseInt(vote1.getText());
      if(value != 0)
      {
        value--;
      }
      vote1.setText(String.valueOf(value));
    }

    if(e.getSource() == buttonPlusVote2)
    {
      int value = Integer.parseInt(vote2.getText());
      value++;
      vote2.setText(String.valueOf(value));
    }
    else if(e.getSource() == buttonMinusVote2)
    {
      int value = Integer.parseInt(vote2.getText());
      if(value != 0)
      {
        value--;
      }
      vote2.setText(String.valueOf(value));
    }
    if(e.getSource() == buttonPlusVote3)
    {
      int value = Integer.parseInt(vote3.getText());
      value++;
      vote3.setText(String.valueOf(value));
    }
    else if(e.getSource() == buttonMinusVote3)
    {
      int value = Integer.parseInt(vote3.getText());
      if(value != 0)
      {
        value--;
      }
      vote3.setText(String.valueOf(value));
    }
    else if(e.getSource() == closePollButton)
    {
      int v1 = Integer.parseInt(vote1.getText()), v2 = Integer.parseInt(vote2.getText()), v3 = Integer.parseInt(vote3.getText());
      if(v1 >= v2 && v1 >= v3)
      {
        a.setAlertType(Alert.AlertType.INFORMATION);
        a.setContentText("The first game won the poll");
        a.show();
      }
      else if(v2 >= v1 && v2 >= v3)
      {
        a.setAlertType(Alert.AlertType.INFORMATION);
        a.setContentText("The second game won the poll");
        a.show();
      }
      else
      {
        a.setAlertType(Alert.AlertType.INFORMATION);
        a.setContentText("The third game won the poll");
        a.show();
      }
    }

    modelManager.XMLFile();
  }

  /**
   * The method that updates all the combo boxes and fields for displaying the information.
   */
  public void initialize()
  {
    ArrayList<Player> players = modelManager.getAllPlayers().getList();
    ArrayList<Game> collection = modelManager.getAllGames().getList();
    ArrayList<Reservation> reservations = modelManager.getAllReservations().getList();
    ArrayList<Player> members=modelManager.getAllPlayers().getList();
    members.removeIf(element -> !element.isMembership());

    memberData.clear();
    memberData.addAll(members);
    studentIdRes_BorrowReserve.setItems(memberData);

    reservationData.clear();
    reservationData.addAll(reservations);
    reservationsToRemove_BorrowReserve.setItems(reservationData);

    playerData.clear();
    playerData.addAll(players);
    addOwners_Game.setItems(playerData);
    editOwners_Game.setItems(playerData);
    studentIdBorr_BorrowReserve.setItems(playerData);

    gameData.clear();
    gameData.addAll(collection);
    editGames_Game.setItems(gameData);
    removeGames_Game.setItems(gameData);
    ratingTabGameComboBox_Game.setItems(gameData);
    gameBorr_BorrowReserve.setItems(gameData);
    gameRes_BorrowReserve.setItems(gameData);

    name2.setEditable(true);
    updatePlayersArea();
    resetPlayer();
    reloadEventListAndDisplay();
    refreshComboEvent();

    ratingData.clear();
    Integer[] ratings = {1,2,3,4,5};
    ratingData.addAll(ratings);
    ratingTabRatingComboBox_Game.setItems(ratingData);

    hourData.clear();
    Integer[] hours={0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23};
    hourData.addAll(hours);
    startHour_BorrowReserve.setItems(hourData);
    endHour_BorrowReserve.setItems(hourData);
    hourBorr_BorrowReserve.setItems(hourData);
  }

  /**
   * Update the Game list in the Game Tab
   */
  public void displayRefreshedGameList()
  {
    String text="";
    ArrayList<Game> collection=modelManager.getAllGames().getList();
    for (int i = 0; i < collection.size(); i++)
    {
      text += collection.get(i).toString()+"\n";
    }
    displayGames_Game.setText(text);
  }

  /**
   * The method that makes changes in the Reservation / Borrow Tab according to the Buttons that were pressed.
   * @param e Button that was pressed
   * @throws FileNotFoundException
   */
  public void handlerBorrowReserve(ActionEvent e) throws FileNotFoundException
  {

    if(e.getSource() == borrow_BorrowReserve)
    {
      if(studentIdBorr_BorrowReserve.getSelectionModel().getSelectedItem() != null && gameBorr_BorrowReserve.getSelectionModel().getSelectedItem() != null)
      {
        if(studentIdBorr_BorrowReserve.getValue().getStudentID() != null && gameBorr_BorrowReserve.getValue() != null && reserveToBorr_BorrowReserve.getValue() != null && hourBorr_BorrowReserve.getValue() != null)
        {
          Player player = modelManager.getPlayerByStudentID(
              studentIdBorr_BorrowReserve.getValue().getStudentID());
          Game gameSelect = gameBorr_BorrowReserve.getValue();

          int endDay = reserveToBorr_BorrowReserve.getValue().getDayOfMonth();
          int endMonth = reserveToBorr_BorrowReserve.getValue().getMonthValue();
          int endYear = reserveToBorr_BorrowReserve.getValue().getYear();
          int endHour = hourBorr_BorrowReserve.getValue();
          DateTime end = new DateTime(endYear, endMonth, endDay, endHour);
          DateTime start=DateTime.today();

          if (!end.isBefore(DateTime.today()))
          {
            boolean isTrue=true;
            ArrayList<Reservation> reservations=modelManager.getAllReservations().getList();
            //          System.out.println(reservations);
            for (Reservation element:reservations)
            {
              if (element.getGame().equals(gameBorr_BorrowReserve.getValue()))
              {
                if (end.isBefore(element.getEndDate())&&element.getStartDate().isBefore(end))
                {
                  a.setAlertType(Alert.AlertType.ERROR);
                  a.setContentText("Cannot reserve in this time period\n"
                      +"The game is reserved from: "+element.getStartDate()+"\n"
                      + "To: "+element.getEndDate());
                  a.show();
                  isTrue=false;
                  break;
                  //                initialize();
                }
                if (!start.isBefore(element.getStartDate())&&start.isBefore(element.getEndDate()))
                {
                  a.setAlertType(Alert.AlertType.ERROR);
                  a.setContentText("Cannot reserve in this time period\n"
                      +"The game is reserved from: "+element.getStartDate()+"\n"
                      + "To: "+element.getEndDate());
                  a.show();
                  isTrue=false;
                  break;
                  //                initialize();
                }
                if (!start.isBefore(element.getStartDate())&&start.isBefore(element.getEndDate())&&end.isBefore(element.getEndDate())&&!end.isBefore(element.getStartDate()))
                {
                  a.setAlertType(Alert.AlertType.ERROR);
                  a.setContentText("Cannot reserve in this time period\n"
                      +"The game is reserved from: "+element.getStartDate()+"\n"
                      + "To: "+element.getEndDate());
                  a.show();
                  isTrue=false;
                  break;
                  //                initialize();
                }
                if (start.isBefore(element.getStartDate())&&!end.isBefore(element.getEndDate()))
                {

                  a.setAlertType(Alert.AlertType.ERROR);
                  a.setContentText("Cannot reserve in this time period\n"
                      +"The game is reserved from: "+element.getStartDate()+"\n"
                      + "To: "+element.getEndDate());
                  a.show();
                  isTrue=false;
                  break;
                  //                initialize();
                }
              }
            }
            if (isTrue)
            {
              modelManager.borrow(player, gameSelect, end);

              a.setAlertType(Alert.AlertType.CONFIRMATION);
              a.setContentText(gameSelect + "game has been borrowed to: " + player + "till: "
                  + endHour + "hour");
              a.show();
              initialize();
            }

          }else
          {
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("Cannot borrow in the past");
            a.show();
            initialize();
          }
        }
        else {
          a.setAlertType(Alert.AlertType.ERROR);
          a.setContentText("Please fill out all the fields");
          a.show();
          initialize();
        }
      }
      else
      {
        a.setAlertType(Alert.AlertType.ERROR);
        a.setContentText("Please fill out all the fields");
        a.show();
      }

    }

    if(e.getSource() == reserve_BorrowReserve)
    {
      if(studentIdRes_BorrowReserve.getSelectionModel().getSelectedItem() != null && gameRes_BorrowReserve.getSelectionModel().getSelectedItem() != null)
      {
        if(studentIdRes_BorrowReserve.getValue().getStudentID() != null && gameRes_BorrowReserve.getValue() != null && fromRes_BorrowReserve.getValue() != null
            && toRes_BorrowReserve.getValue() != null &&
            startHour_BorrowReserve.getValue()!= null && endHour_BorrowReserve.getValue() != null)
        {
          Player player = modelManager.getPlayerByStudentID(
              studentIdRes_BorrowReserve.getValue().getStudentID());
          Game gameSelect = gameRes_BorrowReserve.getValue();

          int startDay = fromRes_BorrowReserve.getValue().getDayOfMonth();
          int startMonth = fromRes_BorrowReserve.getValue().getMonthValue();
          int startYear = fromRes_BorrowReserve.getValue().getYear();
          int startHour = startHour_BorrowReserve.getValue();
          DateTime start = new DateTime(startYear, startMonth, startDay, startHour);

          int endDay = toRes_BorrowReserve.getValue().getDayOfMonth();
          int endMonth = toRes_BorrowReserve.getValue().getMonthValue();
          int endYear = toRes_BorrowReserve.getValue().getYear();
          int endHour = endHour_BorrowReserve.getValue();
          DateTime end = new DateTime(endYear, endMonth, endDay, endHour);

          if (start.isBefore(end))
          {
            boolean isTrue=true;
            ArrayList<Reservation> reservations=modelManager.getAllReservations().getList();
            for (Reservation element:reservations)
            {
              if (element.getGame().equals(gameRes_BorrowReserve.getValue()))
              {
                if (end.isBefore(element.getEndDate())&&element.getStartDate().isBefore(end))
                {
                  a.setAlertType(Alert.AlertType.ERROR);
                  a.setContentText("Cannot reserve in this time period\n"
                      +"The game is reserved from: "+element.getStartDate()+"\n"
                      + "To: "+element.getEndDate());
                  a.show();
                  isTrue=false;
                  break;
                }
                if (!start.isBefore(element.getStartDate())&&start.isBefore(element.getEndDate()))
                {

                  a.setAlertType(Alert.AlertType.ERROR);
                  a.setContentText("Cannot reserve in this time period\n"
                      +"The game is reserved from: "+element.getStartDate()+"\n"
                      + "To: "+element.getEndDate());
                  a.show();
                  isTrue=false;
                  break;
                  //                initialize();
                }
                if (!start.isBefore(element.getStartDate())&&start.isBefore(element.getEndDate())&&end.isBefore(element.getEndDate())&&!end.isBefore(element.getStartDate()))
                {

                  a.setAlertType(Alert.AlertType.ERROR);
                  a.setContentText("Cannot reserve in this time period\n"
                      +"The game is reserved from: "+element.getStartDate()+"\n"
                      + "To: "+element.getEndDate());
                  a.show();
                  isTrue=false;
                  break;
                  //                initialize();
                }
                if (start.isBefore(element.getStartDate())&&!end.isBefore(element.getEndDate()))
                {

                  a.setAlertType(Alert.AlertType.ERROR);
                  a.setContentText("Cannot reserve in this time period\n"
                      +"The game is reserved from: "+element.getStartDate()+"\n"
                      + "To: "+element.getEndDate());
                  a.show();
                  isTrue=false;
                  break;
                  //                initialize();
                }
              }
            }
            if (isTrue)
            {
              modelManager.reserve(player, gameSelect, start, end);

              a.setAlertType(Alert.AlertType.CONFIRMATION);
              a.setContentText("Reservation was registered");
              a.show();
              initialize();
            }
          }else
          {
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("The date is in the past");
            a.show();
          }
        }
        else {
          a.setAlertType(Alert.AlertType.ERROR);
          a.setContentText("Please fill out all the fields");
          a.show();
        }
      }
      else
      {
        a.setAlertType(Alert.AlertType.ERROR);
        a.setContentText("Please fill out all the fields");
        a.show();
      }

    }

    if (e.getSource() == removeReservation_BorrowReserve)
    {
      try{
        ReservationList reservationList = modelManager.getAllReservations();
        Reservation reservationToRemove= reservationsToRemove_BorrowReserve.getSelectionModel().getSelectedItem();
        reservationList.removeReservation(reservationToRemove);
        modelManager.saveReservations(reservationList);

        a.setAlertType(Alert.AlertType.CONFIRMATION);
        a.setContentText("Reservation was removed");
        a.show();

        displayRefreshedReservationList();
      }catch (RuntimeException f)
      {
        f.fillInStackTrace();
        a.setAlertType(Alert.AlertType.ERROR);
        a.setContentText("Please fill out all the fields");
        a.show();
      }

    }
    initialize();
    try
    {
      modelManager.XMLFile();
    }

    catch(NullPointerException f)
    {
      f.fillInStackTrace();
    }
  }

  /**
   * Update the Reservation list in the Reservation Tab
   */
  public void displayRefreshedReservationList()
  {
    String text="";
    ArrayList<Reservation> reservations = modelManager.getAllReservations().getList();
    for (int i = 0; i < reservations.size(); i++)
    {
      if (reservations.get(i).getEndDate().isBefore(DateTime.today()))
      {
        reservations.remove(reservations.get(i));
      }else
      {
        text+=reservations.get(i).toString()+"\n";
      }

    }
    reservationList_BorrowReserve.setText(text);
  }

  /**
   * The method that add member/guest according to the Buttons that were pressed.
   * @param e Buttons that were pressed
   * @throws FileNotFoundException
   */
  public void addPlayer(ActionEvent e) throws FileNotFoundException
  {
    String name = playerName.getText();
    String iD = playerID.getText();

    if(!name.equals("") && !iD.equals(""))
    {
      if(e.getSource()==addMember_Player)
      {
        modelManager.addPlayer(name, iD, true);
        a.setAlertType(Alert.AlertType.CONFIRMATION);
        a.setContentText("Member was added");
        a.show();


      } else if(e.getSource()==addGuest_Player)
      {
        modelManager.addPlayer(name, iD, false);
        a.setAlertType(Alert.AlertType.CONFIRMATION);
        a.setContentText("Guest was added");
        a.show();
      }
      playerName.setText("");
      playerID.setText("");
    }
    else
    {
      a.setAlertType(Alert.AlertType.ERROR);
      a.setContentText("Fill out all the fields");
      a.show();
    }
    initialize();
    modelManager.XMLFile();
  }

  /**
   * Update the Player list in the Player Tab
   */
  private void updatePlayersArea()
  {
    PlayerList playerList = modelManager.getAllPlayers();
    labelForPlayers.setText(playerList.toString());
  }

  /**
   * Set text in the field with the name of the player from combo box
   */
  public void resetPlayer()
  {
    if(modelManager!=null)
    {
      updatePlayersBox();
      Player temp = playerBox.getSelectionModel().getSelectedItem();

      if (temp != null)
      {
        name2.setText(temp.getName());
      }
    }
  }

  /**
   * Update the player combo box in the Player Tab.
   */
  private void updatePlayersBox()
  {
    try{
      int currentIndex = playerBox.getSelectionModel().getSelectedIndex();
    playerBox.getItems().clear();

    PlayerList players = modelManager.getAllPlayers();
    ArrayList<Player> playerArrayList = players.getList();
    for (Player element: playerArrayList)
    {
      playerBox.getItems().add(element);
    }

    if (currentIndex == -1 && playerBox.getItems().size() > 0)
    {
      playerBox.getSelectionModel().select(0);
    }
    else
    {
      playerBox.getSelectionModel().select(currentIndex);
    }
    }
    catch(IndexOutOfBoundsException p)
    {
      p.fillInStackTrace();
    }
  }

  /**
   * The method that make changes in the Player Tab according to the Buttons that were pressed.
   * @param e Buttons that were pressed
   * @throws FileNotFoundException
   */
  public void handleActionsPlayer(ActionEvent e) throws FileNotFoundException
  {
    if (e.getSource() == saveChangesButton)
    {
      Player temp = playerBox.getSelectionModel().getSelectedItem();
      String firstName = name2.getText();

      if(temp != null)
      {
        modelManager.editPlayer(firstName, playerBox.getSelectionModel().getSelectedItem().getStudentID(),
            playerBox.getSelectionModel().getSelectedItem().isMembership());

        a.setAlertType(Alert.AlertType.CONFIRMATION);
        a.setContentText("Changes were saved");
        a.show();

        updatePlayersBox();
        updatePlayersArea();
      }
    }
    else if (e.getSource() == playerBox)
    {
      Player temp = playerBox.getSelectionModel().getSelectedItem();

      if (temp != null)
      {
        name2.setText(temp.getName());
      }
    }
    else if (e.getSource() == revokeButton)
    {
      Player temp = playerBox.getSelectionModel().getSelectedItem();
      if (temp != null)
      {
        temp.setMembership(!temp.isMembership());
        modelManager.editPlayer(temp.getName(), temp.getStudentID(), temp.isMembership());
        a.setAlertType(Alert.AlertType.CONFIRMATION);
        a.setContentText("Changes were saved");
        a.show();
        updatePlayersBox();
        updatePlayersArea();
      }
    }
    else if (e.getSource() == removePlayerButton)
    {
      Player temp = playerBox.getSelectionModel().getSelectedItem();
      ReservationList reservations = modelManager.getAllReservations();
      ArrayList<Reservation> copy;
      if (temp!=null)
      {
        copy=reservations.getByPlayer(temp);

        reservations.getList().removeAll(copy);
        modelManager.saveReservations(reservations);

        modelManager.removePlayer(temp);
        name2.clear();
        a.setAlertType(Alert.AlertType.CONFIRMATION);
        a.setContentText("Player was removed");
        a.show();
        updatePlayersBox();
        updatePlayersArea();
        initialize();
        displayRefreshedReservationList();

      }

    }

    modelManager.XMLFile();
  }

}
