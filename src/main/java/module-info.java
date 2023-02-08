module com.example.hotels {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.hotels to javafx.fxml;
    exports com.example.hotels;
    exports com.example.hotels.controller;
    opens com.example.hotels.controller to javafx.fxml;
    exports com.example.hotels.domain;
    opens com.example.hotels.domain to java.base;
    exports com.example.hotels.service;
    exports com.example.hotels.repository.db;
}