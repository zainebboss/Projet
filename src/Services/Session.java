/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

/**
 *
 * @author Hp Omen
 */

    public final class Session {
    
     private static Session instance;
     private String email;

    public static Session getInstance() {
        return instance;
    }

    public static void setInstance(Session instance) {
       Session.instance = instance;
    }


     
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Session(String email) {
        this.email = email;
    }
    
     public static Session getInstace(String email) {
        if(instance == null) {
            instance = new Session(email);
        }
        return instance;
    }
     public static Session CleanUser(String E){
       if(instance != null) {
            
            instance = new Session(E);
        }
        return instance;
     }

    @Override
    public String toString() {
        return email;
    }

    public Session() {
    }
    
}
