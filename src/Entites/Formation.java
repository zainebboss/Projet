package Entites;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import IService.DaoFormation;

public class Formation{
	private int id,prix;
	private String titre, description;

	
	
	
	public Formation() {
		super();
	}

	public Formation(String titre, int prix, String description) {
		super();
		this.titre = titre;
		this.description = description;
                this.prix=prix;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }



	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public void createFormation() {
		// TODO Auto-generated method stub
		try {

			Connexion c = new Connexion();
			java.sql.PreparedStatement statement = c.conn
					.prepareStatement("INSERT INTO `formation`( `titre`, `prix`, `description`) VALUES ('"
							+ this.getTitre() + "','" + this.getPrix()+ "','" + this.getDescription() + "')");
			statement.executeUpdate();
			System.out.println("ajout� avec succ�s");
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		}

	}

	
	public void updateFormation(int idf) {
		// TODO Auto-generated method stub
		try
		{
			Connexion c=new Connexion();
			String sql="UPDATE `formation` SET `titre`='"+this.getTitre()+"',`prix`='"+this.getPrix()+"',`description`='"+this.getDescription()+"' WHERE id='"+idf+"';";
					java.sql.PreparedStatement statement =
					c.conn.prepareStatement(sql);
					statement.executeUpdate();
					System.out.println("modifi� avec succ�s");
			
			
		}
		catch(Exception ex)
		{
			
			System.out.println("erreur lors de la modification "+ ex.toString());
			
		}
		
	}

	
	public void deleteFormation(int idf) {
		// TODO Auto-generated method stub
		try {
			Connexion c=new Connexion();
			String sql = "DELETE FROM `formation` WHERE `id`=?";
			java.sql.PreparedStatement statement =
			c.conn.prepareStatement(sql); statement.setInt(1,idf);
			
			statement.execute();
			System.out.println("supprim� avec succ�s");
			} catch (SQLException ex) {
				System.out.println("erreur lors de la suppression "+ex.toString());
				
			}
	}

	
	public ResultSet listFormation() throws SQLException {
		// TODO Auto-generated method stub
		Connexion c=new Connexion();
		PreparedStatement pst;
		pst = (PreparedStatement)
		c.conn.prepareStatement("SELECT * FROM formation");
		pst.executeQuery();
		ResultSet rs = (ResultSet) pst.executeQuery();
		return rs;
	}

	
	public Formation findFormation(String titre) throws SQLException {
		// TODO Auto-generated method stub
		Connexion c=new Connexion();
		PreparedStatement pst2;
		pst2 = (PreparedStatement)
		c.conn.prepareStatement("SELECT * FROM formation where titre='"+titre+"'");
		pst2.executeQuery();
		ResultSet rs = (ResultSet) pst2.executeQuery();
if(rs.first())
{
Formation f=new Formation(rs.getString("titre"), rs.getInt("prix"), rs.getString("description"));
f.setId(rs.getInt("id"));
return f;
}
else 
	return null;
	
	}

	
	public Formation searchFormation(int id) throws SQLException {
		// TODO Auto-generated method stub
		Connexion c=new Connexion();
		PreparedStatement pst2;
		pst2 = (PreparedStatement)
		c.conn.prepareStatement("SELECT * FROM formation where id='"+id+"'");
		pst2.executeQuery();
		ResultSet rs = (ResultSet) pst2.executeQuery();
if(rs.first())
{
Formation f=new Formation(rs.getString("titre"), rs.getInt("prix"), rs.getString("description"));
f.setId(rs.getInt("id"));
return f;
}
else 
	return null;
	}

}
