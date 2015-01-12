package consumers;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author zaki
 */
public class sender extends HttpServlet {
    
    private static final String EXCHANGE_NAME = "configuration";
    
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
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String which = request.getParameter("which");
            String[] puppets = request.getParameterValues("ids");
            String config = request.getParameter("config");
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet sender</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet sender at " + request.getContextPath() + "</h1>");
            out.println("<H1>Recapitulatif des informations</H1>\n");
            for (String id : puppets) {
                out.println("<UL>\n"
                        + "  <LI>Consumer: "
                        + which+id + "\n"
                        + "  <LI>config: "
                        + config + "\n"
                        + "</UL>\n");
                out.println("<h1>" + send(which+id, config) + "</h1>");
            }
            out.println("</body>");
            out.println("</html>");
            response.sendRedirect("index.html");
        }
    }

    public String send(String who, String config) throws Exception {

        /* calling connectionFactory to create a custome connexion with
         * rabbitMQ server information.
         */
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("146.148.27.98");
        factory.setUsername("admin");
        factory.setPassword("adminadmin");
        factory.setPort(5672);

        // establish the connection with RabbitMQ server using our factory.
        Connection connection = factory.newConnection();

        // We're connected now, to the broker on the cloud machine.
        // If we wanted to connect to a broker on a the local machine we'd simply specify "localhost" as IP adresse.
        // creating a "configuration" direct channel/queue 
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");

        // the message and the distination
        String forWho = who;
        String message = config;

        // publish the message
        channel.basicPublish(EXCHANGE_NAME, forWho, null, message.getBytes());

        // close the queue and the connexion
        channel.close();
        connection.close();
        return " [x] Sent '" + forWho + "':'" + message + "'";
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(sender.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(sender.class.getName()).log(Level.SEVERE, null, ex);
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
