package com.example.restaurant.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_restaurant")

public class Restaurant {
    @TableId(type = IdType.AUTO)
    private int id;
    //used for searching only
    @TableField(exist = false)
    private double similarity;
    private int ownerId;
    private String name;
    private String address;
    private int averageBill;
    private String photo;
    private double rating;
    @TableField(exist = false)
    private String[] tags;
    private int numStaff;
    private int capacity;
    private int deleted;

    public Restaurant(String name,String address,int ownerId,int capacity,int numStaff){
        this.name = name;
        this.address = address;
        this.ownerId = ownerId;
        this.capacity = capacity;
        this.numStaff = numStaff;
        this.deleted = 0;
    }
    public Restaurant(String id, String name,String address,int ownerId,int capacity,int numStaff){
        this.id = Integer.parseInt(id);
        this.name = name;
        this.address = address;
        this.ownerId = ownerId;
        this.capacity = capacity;
        this.numStaff = numStaff;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSimilarity() {
        return similarity;
    }

    public void setSimilarity(double similarity) {
        this.similarity = similarity;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
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

    public String getPhotoURL() {
        return photo;
    }

    public void setPhotoURL(String photoURL) {
        this.photo = photoURL;
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

    public int getNumStaff() {
        return numStaff;
    }

    public void setNumStaff(int numStaff) {
        this.numStaff = numStaff;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
