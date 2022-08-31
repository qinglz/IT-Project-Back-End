package com.example.restaurant.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Table")
public class Table {
    @Id
    private String id;
    private int capacity;
    private int tableNumber;
    private String type;
    private String status;

    public Table(String id, int capacity, int tableNumber, String type, String status) {
        this.id = id;
        this.capacity = capacity;
        this.tableNumber = tableNumber;
        this.type = type;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
