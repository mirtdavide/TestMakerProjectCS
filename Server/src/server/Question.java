/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.*;

/**
 *
 * @author soprasteria
 */
public class Question implements Serializable{
    
    String questiontext;
    boolean image; 
    String imagepath;
    boolean twoanswers;

    public Question() {
    }
    
    public Question(String questiontext, boolean image, String imagepath, boolean twoanswers) {
        this.questiontext = questiontext;
        this.image = image;
        this.imagepath = imagepath;
        this.twoanswers = twoanswers;
    }

    public String getQuestiontext() {
        return questiontext;
    }

    public void setQuestiontext(String questiontext) {
        this.questiontext = questiontext;
    }

    public boolean hasImage() {
        return image;
    }

    public void setImage(boolean image) {
        this.image = image;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public boolean isTwoanswers() {
        return twoanswers;
    }

    public void setTwoanswers(boolean twoanswers) {
        this.twoanswers = twoanswers;
    }

    @Override
    public String toString() {
        return "Question{" + "questiontext=" + questiontext + ", image=" + image + ", imagepath=" + imagepath + ", twoanswers=" + twoanswers + '}';
    }
    
    
    
    
    
    
    
}
