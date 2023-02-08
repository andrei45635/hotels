package com.example.hotels.controller;

import com.example.hotels.domain.Hotel;
import com.example.hotels.domain.SpecialOffer;
import com.example.hotels.service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class OffersViewController {
    @FXML
    private TableView<SpecialOffer> offersTableView;
    @FXML
    private TableColumn<SpecialOffer, LocalDate> startDateColumn;
    @FXML
    private TableColumn<SpecialOffer, LocalDate> endDateColumn;
    @FXML
    private TableColumn<SpecialOffer, Integer> percentsColumn;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;
    private ObservableList<SpecialOffer> specialOffersModel = FXCollections.observableArrayList();
    private Service service;
    private Hotel selectedHotel;

    @FXML
    public void initialize(){
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        percentsColumn.setCellValueFactory(new PropertyValueFactory<>("percents"));

        offersTableView.setItems(specialOffersModel);
        handleListeners();
    }

    public void setService(Service service){
        this.service = service;
    }

    public void setHotel(Hotel hotel){
        this.selectedHotel = hotel;
    }

    public void initModel(){
        LocalDate start = startDatePicker.getValue();
        LocalDate end = endDatePicker.getValue();
        specialOffersModel.setAll(service.getAllOffersForDates(selectedHotel.getId(), start, end));
    }

    public void handleListeners(){
        endDatePicker.valueProperty().addListener(f -> {
            if (f!=null){
            initModel();}});
        }
}
