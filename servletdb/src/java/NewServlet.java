/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import BD.Basededatos;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Estudiantes
 */
@WebServlet(urlPatterns = {"/NewServlet"})
public class NewServlet extends HttpServlet {
    String devolver;
    String devolver1;
    String result;
    java.sql.Connection conex;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NewServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>" + devolver+"--"+request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
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

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String quehacer=request.getParameter("quehacer");
        
    int qh=Integer.parseInt(quehacer);
    if(qh==2){
        try {

            Basededatos bd=new Basededatos(); 

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



    }
    else if(qh==3){
        //////

        try {
          Basededatos bd=new Basededatos(); 
            result=bd.borrarDB();
            processRequestbd(request, response);
        } catch (SQLException ex) {
            result="error leyendo "+ex.toString();
            processRequestbd(request, response);
        } catch (ClassNotFoundException ex) {
        result="error1 Bd .."+ex.toString();
      }
    }
    else if(qh==4){
        //////

        try {
          Basededatos bd=new Basededatos(); 
            result=bd.crearTabla();
            processRequestbd(request, response);
        } catch (SQLException ex) {
            result="error leyendo "+ex.toString();
            processRequestbd(request, response);
        }
    }
    else if(qh==1){
        String s1 = request.getParameter("cod");
        int id = Integer.parseInt(s1);
        String s2 = request.getParameter("nom");
        //////

        try {
          Basededatos bd=new Basededatos(); 
            result=bd.insertarDatos(id, s2);
            processRequestbd(request, response);
        } catch (SQLException ex) {
            result="error leyendo "+ex.toString();
            processRequestbd(request, response);
        }
        
    }
    else if(qh==5){
        try {
          Basededatos bd=new Basededatos(); 
            result=bd.leerbasededatos();
            processRequestbd(request, response);
        } catch (SQLException ex) {
            result="error leyendo "+ex.toString();
            processRequestbd(request, response);
        }   catch (ClassNotFoundException ex) {
                result="error consultando "+ex.toString();
            processRequestbd(request, response);
            }
        
    }
    else {
        result="error...";
        processRequestbd(request, response);

    }
    
    }
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
