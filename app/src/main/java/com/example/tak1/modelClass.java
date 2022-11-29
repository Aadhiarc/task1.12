package com.example.tak1;

public class modelClass {
    // VIEWS DECLARATION
    String textView1;
    String textView2;


    // MODEL CLASS CONSTRUCTOR
    public modelClass(String textView1, String textView2) {
        this.textView1 = textView1;
        this.textView2 = textView2;
    }

    // MODEL CLASS GET METHODS
    public String getTextView1() {
        return this.textView1;
    }

    public String getTextView2() {
        return this.textView2;
    }
}
