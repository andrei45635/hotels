package com.example.hotels.controller;

import com.example.hotels.domain.GuestType;
import com.example.hotels.domain.Hotel;
import com.example.hotels.domain.Location;
import com.example.hotels.service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class HelloController {
    private Service service;
    private ObservableList<Hotel> hotelModel = FXCollections.observableArrayList();
    @FXML
    private TableView<Hotel> hotelTableView;
    @FXML
    private TableColumn<Hotel, String> hotelNameColumn;
    @FXML
    private TableColumn<Hotel, Integer> noRoomsColumn;
    @FXML
    private TableColumn<Hotel, Double> pricePerNightColumn;
    @FXML
    private TableColumn<Hotel, GuestType> typeColumn;
    @FXML
    private ComboBox<String> chooseLocComboBox;

    public void setService(Service service){
        this.service = service;
        chooseLocComboBox.setItems(FXCollections.observableArrayList(service.getAllLocations()));
    }

    @FXML
    public void initialize(){
        hotelNameColumn.setCellValueFactory(new PropertyValueFactory<>("hotelName"));
        noRoomsColumn.setCellValueFactory(new PropertyValueFactory<>("noRooms"));
        pricePerNightColumn.setCellValueFactory(new PropertyValueFactory<>("pricePerNight"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        hotelTableView.setItems(hotelModel);
        chooseLocComboBox.getSelectionModel().selectedItemProperty().addListener(f -> initModel());
    }

    private void initModel() {
        Location loc = service.getLocationByName(chooseLocComboBox.getValue());
        List<Hotel> hotels = service.getHotelsForLocation(loc.getId());
        hotelModel.setAll(hotels);
        clicker();
    }

    @FXML
    private void onClickFind(ActionEvent actionEvent) {
        initModel();
    }

    public void clicker(){
        var selected1 = hotelTableView.getFocusModel().getFocusedItem();
        hotelTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../offers-view.fxml"));
                    Parent root;
                    try {
                        root = loader.load();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    OffersViewController offersViewController = loader.getController();
                    offersViewController.setService(service);
                    offersViewController.setHotel(selected1);
                    System.out.println();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root, 600, 600));
                    stage.setTitle("Hello!");
                    stage.show();
                }
            }
        });
    }
}