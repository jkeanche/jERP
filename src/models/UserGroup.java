/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Codepal
 */
public class UserGroup {
    private int id;
    private UserGroup parentUserGroup;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public UserGroup getParentUserGroup() {
        return parentUserGroup;
    }

    public void setParentUserGroup(UserGroup parentUserGroup) {
        this.parentUserGroup = parentUserGroup;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
