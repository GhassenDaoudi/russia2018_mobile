/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entite;

/**
 *
 * @author hseli
 */
public class Score {
    private int id;
    private int a;
    private int b;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
    public Score(){
        
    }

    public Score(int id, int a, int b) {
        this.id = id;
        this.a = a;
        this.b = b;
    }
   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
   
    
    @Override
    public String toString() {
        return "Score{" + "a=" + a + ", b=" + b + '}';
    }

 
    
    
}
