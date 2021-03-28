/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

/**
 *
 * @author nidha
 */
public class sparkAideCom {
    int id,id_sujet ;
    String commaintre ;
    
  public sparkAideCom() {
    }
    
    public sparkAideCom(int id, int id_sujet, String commaintre) {
        this.id = id;
        this.id_sujet = id_sujet;
        this.commaintre = commaintre;
    }

    public int getId() {
        return id;
    }

    public int getId_sujet() {
        return id_sujet;
    }

    public String getCommaintre() {
        return commaintre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_sujet(int id_sujet) {
        this.id_sujet = id_sujet;
    }

    public void setCommaintre(String commaintre) {
        this.commaintre = commaintre;
    }

    @Override
    public String toString() {
        return "sparkAideCom{" + "id=" + id + ", id_sujet=" + id_sujet + ", commaintre=" + commaintre + '}';
    }

    
  
    
    
}
