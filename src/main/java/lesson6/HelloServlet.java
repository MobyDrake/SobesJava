package lesson6;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Сделал через Tomcat 9. Пришлось скопировать класс в папку ...\Tomcat 9.0\webapps\helloapp\WEB-INF\classes
 * (единственное нужно было удалить строчку package lesson6).
 */

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        try {
            writer.println("<h1> Hello World! </h1>");
        } finally {
            writer.close();
        }
    }
}
