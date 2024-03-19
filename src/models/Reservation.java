/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.time.LocalDate;

/**
 *
 * @author Codepal
 */
   // Inner class representing a reservation
 public  class Reservation {
        private String room;
        private LocalDate startDate;
        private LocalDate endDate;
        private String client;

       
        public Reservation(String room, LocalDate startDate, LocalDate endDate, String client) {
            this.room = room;
            this.startDate = startDate;
            this.endDate = endDate;
            this.client = client;
        }

        // Getters
        public LocalDate getStartDate() {
            return startDate;
        }

        public LocalDate getEndDate() {
            return endDate;
        }

        public String getClient() {
            return client;
        }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
        
        
    }