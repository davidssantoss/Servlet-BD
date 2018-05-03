import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PC
 */
public class Basedadatos {
  
    int band= -1;
    java.sql.Connection conex;
    java.sql.Statement st;
    Basedadatos() throws SQLException{
      /*String st1="SELECT count(*) "+
      "FROM information_schema.TABLES "+
      "WHERE (TABLE_SCHEMA = "+ "\""+"eje"+"\"" +
         ") AND (TABLE_NAME = "+"\""+"wood"+"\""+")";
      
      conex = DriverManager
              .getConnection("jdbc:mysql://localhost:3306/", 
                "root", "root27?");
      
      st=conex.createStatement();
      ResultSet rs=st.executeQuery(st1);
      if(rs.next()==false){
        band=-1;
      }
      else band=1;
      conex.close();*/
    }
    String creabasededatos() throws SQLException, ClassNotFoundException {
        String todo="[ ";
        if(band==-1){
            
            Class
              .forName("com.mysql.jdbc.Driver");
              conex = DriverManager
                      .getConnection
              ("jdbc:mysql://localhost:3306/",
              "root","root27?");
              
              String univ;
              univ = "drop DATABASE eje ";
              java.sql.Statement st=
                   conex.createStatement();
              st.executeUpdate(univ);
              
              
              
              univ = "CREATE DATABASE eje ";
              st=  conex.createStatement();
              st.executeUpdate(univ);
              conex.close();
            todo=todo+ " creo:";
            ////////////////////////////////////
              ///////////////////////////////////
              conex = DriverManager
                      .getConnection
              ("jdbc:mysql://localhost:3306/eje",
              "root","root27?");
            st=conex.createStatement();
            String t="CREATE TABLE "+" wood "+
        " (id int, nombre varchar(15), PRIMARY KEY (id)  ); ";
            st.executeUpdate(  t   );
            conex.close();
            todo=todo+ " creo tabla:";
            
            ////////////////////////////////////
              ///////////////////////////////////
              conex = DriverManager
                      .getConnection
              ("jdbc:mysql://localhost:3306/eje",
              "root","root27?");
            st=conex.createStatement();
            String g ;
            String Query;
            for (int i=0; i < 15; i++) {
                g = "beta_"+i;
                Query = "INSERT INTO " + 
                  "wood  " + 
                  " VALUES("+ i+ ", " + "\"" + g + "\""  + ")";
                st = conex.createStatement();
                st.executeUpdate(Query);
                todo=todo+g+"<br>";

            }
            conex.close();
            band=1;
            
        }
        return todo;
    }
    String leerbasededatos() throws SQLException, ClassNotFoundException {
        ResultSet rs;
        Class.forName("com.mysql.jdbc.Driver");
        conex = DriverManager
          .getConnection("jdbc:mysql://localhost:3306/eje", 
            "root", "root27?");
        
        st=conex.createStatement();
        rs=st.executeQuery("select * from wood;");
        
        ///
        String todo="";
        while (rs.next()) {
            int id= rs.getInt("id");
            String n= rs.getString("nombre");
            todo = todo + id+" "+n+"<br>";
        }
        conex.close();
        return todo;
    }
}
