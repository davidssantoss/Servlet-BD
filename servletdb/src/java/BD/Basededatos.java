package BD;


import java.sql.DriverManager;
import java.sql.SQLException;


public class Basededatos {
    int band= -1;
    java.sql.Connection conex;
    java.sql.Statement st;
    String todo="[ ";
    public Basededatos() throws SQLException{

    }
    public String creabasededatos() throws SQLException, ClassNotFoundException {
        
        if(band==-1){           
            Class
              .forName("com.mysql.jdbc.Driver");
            conex = DriverManager
                      .getConnection
            ("jdbc:mysql://localhost:3306/",
            "root","mysql2018");
              
            String univ;
            //Crea la base de datos
              
              univ = "CREATE DATABASE Ejemplo ";
              st=  conex.createStatement();
              st.executeUpdate(univ);
              conex.close();
            todo=todo+ " creo:";
            

            }
        
        return todo;
       
    
    }
    public String crearTabla () throws SQLException{
        conex = DriverManager
                      .getConnection
              ("jdbc:mysql://localhost:3306/Ejemplo",
              "root","mysql2018");
            st=conex.createStatement();
            String t="CREATE TABLE "+" datos "+
        " (id int, nombre varchar(15), PRIMARY KEY (id)  ); ";
            st.executeUpdate(  t   );
            conex.close();
            todo=todo+ " creo tabla:";
        
        return todo;
    }
    public String insertarDatos () throws SQLException{
        conex = DriverManager
                      .getConnection
              ("jdbc:mysql://localhost:3306/Ejemplo",
              "root","mysql2018");
            st=conex.createStatement();
            String g ;
            String Query;
            for (int i=0; i < 15; i++) {
                g = "beta_"+i;
                Query = "INSERT INTO " + 
                  "datos  " + 
                  " VALUES("+ i+ ", " + "\"" + g + "\""  + ")";
                st = conex.createStatement();
                st.executeUpdate(Query);
                todo=todo+g+"<br>";
            }
        return todo;
            
    }
    public String borrarDB () throws SQLException, ClassNotFoundException{
        Class
              .forName("com.mysql.jdbc.Driver");
              conex = DriverManager
                      .getConnection
              ("jdbc:mysql://localhost:3306/",
              "root","mysql2018");
              // Borra la base de datos
              String univ;
              univ = "drop DATABASE Ejemplo ";
              java.sql.Statement st=
                   conex.createStatement();
              st.executeUpdate(univ);
              todo= todo + "Borro la base de datos";
        
              return todo;
    }
}
