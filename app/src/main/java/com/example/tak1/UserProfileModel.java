package com.example.tak1;

public class UserProfileModel {

    String firstName;
    String lastName;
    String email;
    String profileID;
    String isUserProfile;

    public UserProfileModel() {
    }

    public UserProfileModel(String firstName, String lastName, String email, String profileID, String isUserProfile) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.profileID = profileID;
        this.isUserProfile = isUserProfile;
    }

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
