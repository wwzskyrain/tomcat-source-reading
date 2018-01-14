import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

public class PrimitiveServlet implements Servlet {

    public void init(ServletConfig config) throws ServletException {
        System.out.println("init");
    }

    public void service(ServletRequest request, ServletResponse response)
            throws ServletException, IOException {
        System.out.println("from service");

//        Request castedRequest = (Request) request;
//        castedRequest.parse();
//       method parser() should not be access in servlet.

        PrintWriter out = response.getWriter();
//      http response headers
        out.println("HTTP/1.1 200 OK");
        out.println();

//      http response body.
        out.println("Hello. Roses are red.");
        out.print("Violets are blue.");
//        out.flush();    //out默认是没有自动刷新，如果注释掉这句，那么Violets are blue就不会发送的给客户端.

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
