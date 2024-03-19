/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import interfaces.PlannableAsset;
import java.time.LocalDate;

/**
 *
 * @author Codepal
 */
public class ReservationAsset implements PlannableAsset{
        private String category;
        private String name;
        private LocalDate startDate;
        private LocalDate endDate;
        private String client;

        public ReservationAsset(String category, String name, String startDate, String endDate, String client) {
            this.category = category;
            this.name = name;
            this.startDate = LocalDate.parse(startDate);
            this.endDate = LocalDate.parse(endDate);
            this.client = client;
        }

        @Override
        public String getCategory() {
            return category;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public LocalDate getStartDate() {
            return startDate;
        }

        @Override
        public LocalDate getEndDate() {
            return endDate;
        }

        @Override
        public String getClient() {
            return client;
        }
}
