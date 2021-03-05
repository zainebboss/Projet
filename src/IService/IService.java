/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;


import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author House
 */
public interface IService<U> {
    void ajouter(U u) throws SQLException;
    boolean supprimer(U u) throws SQLException;
    boolean modifier(U u) throws SQLException;
    List<U> afficher() throws SQLException;
}

