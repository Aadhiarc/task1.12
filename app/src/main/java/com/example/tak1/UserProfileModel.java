package com.example.tak1;

public class UserProfileModel {
    //USER PROFILE MODEL VALUES
    String firstName;
    String lastName;
    String email;
    String profileID;
    String isUserProfile;
    //USER PROFILE MODEL EMPTY CONSTRUCTOR
    public UserProfileModel() {
    }
    //USER PROFILE MODEL CONSTRUCTOR
    public UserProfileModel(String firstName, String lastName, String email, String profileID, String isUserProfile) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.profileID = profileID;
        this.isUserProfile = isUserProfile;
    }
    //USER PROFILE MODEL GETTER AND SETTER METHODS
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getProfileID() {
        return profileID;
    }

    public String getIsUserProfile() {
        return isUserProfile;
    }
}
