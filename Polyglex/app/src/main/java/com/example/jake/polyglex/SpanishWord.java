package com.example.jake.polyglex;

/**
 * Created by Jake on 8/21/2017.
 */

public class SpanishWord {

    private String spanishWordTitle;

    public String getSpanishWordTitle(){
        return spanishWordTitle;
    }

    public void setSpanishWordTitle(String spanishWordTitle) {
        this.spanishWordTitle = spanishWordTitle;
    }

    public SpanishWord(String title){
        this.spanishWordTitle = title;
    }
}
