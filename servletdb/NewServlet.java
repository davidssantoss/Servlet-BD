/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author PC
 */
public class NewServlet extends HttpServlet {
  String devolver;
  String devolver1;
  String result;
  java.sql.Connection conex;
  
  protected void processRequestbd(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    try (PrintWriter out = response.getWriter()) {
      /* TODO output your page here. You may use following sample code. */
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<title>prueba</title>");      
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>" + result+ "</h1>");
      out.println("</body>");
      out.println("</html>");
    }
  }
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    try (PrintWriter out = response.getWriter()) {
      /* TODO output your page here. You may use following sample code. */
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<title>prueba</title>");      
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>" + devolver+"--"+request.getContextPath() + "</h1>");
      out.println("</body>");
      out.println("</html>");
    }
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    processRequest(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    
    String quehacer=request.getParameter("quehacer");
    int qh=Integer.parseInt(quehacer);
    if(qh==4){
String s = "123456789";
String cad = "(\\d{3})(\\d\\d)(.*)";
Pattern patron = Pattern.compile(cad);

Matcher m = patron.matcher(s);
if(m.matches()){
  devolver= m.group(3)+m.group(2)+m.group(1);
}
else devolver="e";

      processRequest(request, response);
      ////System.out.println("xxxxxxxxxxxxxxxxxx");
    }
    else if(qh==1){
      devolver=request.getParameter("accion1");
      devolver1=request.getParameter("accion2");
      int i,k;
      i=Integer.parseInt(devolver);
      k=Integer.parseInt(devolver1);
      devolver=""+(i+k+qh);
      processRequest(request, response);
      ////System.out.println("xxxxxxxxxxxxxxxxxx");
    }
    else if(qh==2){
      try {
         
            Basedadatos bd=new Basedadatos(); 
            
            result=bd.creabasededatos();
            //System.out.println("creada base de datos y tabla1");
            processRequestbd(request, response);
            
        } catch (SQLException ex) {
            result="error Bd .."+ex.toString();
            processRequestbd(request, response);
        } catch (ClassNotFoundException ex) {
            result="error1 Bd .."+ex.toString();
            processRequestbd(request, response);
      }
        //} catch (ClassNotFoundException ex) {
           //   result="error1 Bd creada..";
           // processRequestbd(request, response);
            
     // }
        //////
       
        
    }
    else if(qh==3){
        //////
       
        try {
          Basedadatos bd=new Basedadatos(); 
            result=bd.leerbasededatos();
            processRequestbd(request, response);
        } catch (SQLException ex) {
            result="error leyendo "+ex.toString();
            processRequestbd(request, response);
        } catch (ClassNotFoundException ex) {
        Logger.getLogger(NewServlet.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    else {
      result="error...";
      processRequestbd(request, response);
      
    }
  }
  String consultar(){
    String todo="";
        
    try{
      
      conectar();
      String Query;
        Query = "select idalu, nombrealu, "+
          "nombremateria from  " + 
          "alumnos2 " + 
          "inner join materias2 on "+
          "alumnos2.idmateria_foran "+
          " = materias2.idmateria";
        java.sql.Statement st = 
          conex.createStatement();
        ResultSet resultSet;
        resultSet = 
           st.executeQuery(Query);
        while (resultSet.next()) {
            todo=todo+"Cod: " + 
              resultSet.getString("idalu") + " "
              + "  Nombre: " + 
              resultSet.getString("nombrealu") ;
            todo=todo+"  materia: " +
              resultSet
               .getString("nombremateria")  +"<br>";
        }
        desconectar();
      }
      catch (Exception ex) {
         todo=todo+"error...consultas:   "+ex.toString();
         return todo;
     }
    return todo;
  }
  String conectar() throws ClassNotFoundException{
      try{
         Class.forName("com.mysql.jdbc.Driver");
        conex =  DriverManager
          .getConnection
        ("jdbc:mysql://localhost:3306/univ3",
          "root","root27?");
        return "ok1";
      }
      catch(SQLException e1){
        return ("creacion...");
      }
    }
    void desconectar(){
      try{
        conex.close();
      }
      catch(SQLException e1){
        System.out.println("creación...");
      }
    }
  
}
