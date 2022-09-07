package com.example.restaurant.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Restaurant")
public class Restaurant {
    @Id
    private String id;
    private double similarity;
    private BusinessUser businessUser;
    private String name;
    private String address;
    private int averageBill;
    private String photo;
    private double rating;
    private String[] tags;
    private int numOfStaffs;
    private int capacity;

    public Restaurant(String id, String name, String address,
                      int averageBill, String photo, double rating, String[] tags, int numOfStaffs, int capacity) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.averageBill = averageBill;
        this.photo = photo;
        this.rating = rating;
        this.tags = tags;
        this.numOfStaffs = numOfStaffs;
        this.capacity = capacity;
    }
    public Restaurant(){}

    public Restaurant(String name, String address, int averageBill, int capacity, String photo, int numOfStaffs) {
    }

    public Restaurant(String name, String address, int averageBill, int capacity, String photo, int numOfStaffs,
                      String[] tags) {
    }

    public Restaurant(String restaurantName) {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAverageBill() {
        return averageBill;
    }

    public void setAverageBill(int averageBill) {
        this.averageBill = averageBill;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public int getNumOfStaffs() {
        return numOfStaffs;
    }

    public void setNumOfStaffs(int numOfStaffs) {
        this.numOfStaffs = numOfStaffs;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setSimilarity(double similarity) {
        this.similarity = similarity;
    }

    public double getSimilarity() {
        return similarity;
    }
}
