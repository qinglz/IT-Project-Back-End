package com.example.restaurant.Dto;

import com.example.restaurant.utils.TimeUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {
    private String restaurantName;
    private String customerName;
    private String customerPhoneNumber;
    private String customerEmail;
    private LocalDateTime dateTime;
    private int tableNumber;
    private int numPeople;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public int getNumPeople() {
        return numPeople;
    }

    public void setNumPeople(int numPeople) {
        this.numPeople = numPeople;
    }

    @Override
    public String toString() {
        return "Here is the booking information:" +
                "Restaurant Name : '" + restaurantName + '\'' +
                ",   Customer Name : '" + customerName + '\'' +
                ",   Customer Phone Number : '" + customerPhoneNumber + '\'' +
                ",   Customer Email : '" + customerEmail + '\'' +
                ",   Date Time : " + TimeUtil.toSqlDateTime(dateTime) +
                ",   Table Number : " + tableNumber +
                ",   Num People : " + numPeople + ".";
    }
}
