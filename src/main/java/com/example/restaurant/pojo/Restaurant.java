package com.example.restaurant.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Restaurant")
public class Restaurant {
    @Id
    private String id;
    //used for searching only
    private double similarity;
    private String businessUserName;
    private String name;
    private String address;
    private int averageBill;
    private String photoURL;
    private double rating;
    private String[] tags;
    private int numOfStaffs;
    private int capacity;

    public Restaurant(String id, String name, String address, int averageBill, String photoURL, double rating,String businessUserName,
                      String[] tags, int numOfStaffs, int capacity) {
        this.name = name;
        this.id = id;
        this.businessUserName = businessUserName;
        this.address = address;
        this.averageBill = averageBill;
        this.photoURL = photoURL;
        this.rating = rating;
        this.tags = tags;
        this.numOfStaffs = numOfStaffs;
        this.capacity = capacity;
    }
    public Restaurant(){}

    public Restaurant(String name, String address, int averageBill, int capacity, String photo, int numOfStaffs, String businessUserName){
        this.name = name;
        this.address = address;
        this.averageBill = averageBill;
        this.capacity = capacity;
        this.photoURL = photo;
        this.numOfStaffs = numOfStaffs;
        this.businessUserName = businessUserName;

    }

    public Restaurant(String name, String address, int averageBill, int capacity, String photo, int numOfStaffs,
                      String[] tags) {
    }

    public Restaurant(String restaurantName) {
        this.name = restaurantName;
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
        return photoURL;
    }

    public void setPhoto(String photo) {
        this.photoURL = photo;
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
