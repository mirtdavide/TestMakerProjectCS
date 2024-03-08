/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.Serializable;

/**
 *
 * @author soprasteria
 */
public class Answer implements Serializable{
    
    private String answertext;
    private boolean correct;

    public Answer() {
    }

    public Answer(String answertext, boolean correct) {
        this.answertext = answertext;
        this.correct = correct;
    }

    public String getAnswertext() {
        return answertext;
    }

    public void setAnswertext(String answertext) {
        this.answertext = answertext;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    @Override
    public String toString() {
        return "Answer{" + "answertext=" + answertext + ", correct=" + correct + '}';
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
