package com.example.restaurant.entities;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SMSDetails {

        private String to;
        private String message;

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }


        @Override
        public String toString() {
            return "SMS{" +
                    "to='" + to + '\'' +
                    ", message='" + message + '\'' +
                    '}';
        }

}
