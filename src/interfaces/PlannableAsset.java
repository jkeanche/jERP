/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaces;

import java.time.LocalDate;

/**
 *
 * @author Codepal
 */
public interface PlannableAsset {
    String getCategory();
    String getName();
    LocalDate getStartDate();
    LocalDate getEndDate();
    String getClient();
}
