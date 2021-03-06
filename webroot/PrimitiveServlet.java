import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

public class PrimitiveServlet implements Servlet {

    public void init(ServletConfig config) throws ServletException {
        System.out.println("init");
    }

    private String getResponseWithOnlyHeaderLine() {

        String successHttpResponseHeader = "HTTP/1.1 200 OK \n\n";
        return successHttpResponseHeader;

    }

    public void service(ServletRequest request, ServletResponse response)
            throws ServletException, IOException {
        System.out.println("from service");

        PrintWriter out = response.getWriter();
//      http response headers
        out.println("HTTP/1.1 200 OK");
        out.println();

//      http response body.
        out.println("Hello. Roses are red.");
        out.print("Violets are blue.");
    }

    public void destroy() {
        System.out.println("destroy");
    }

    public String getServletInfo() {
        return null;
    }

    public ServletConfig getServletConfig() {
        return null;
    }

}
