package ex.util;

public class ResponseHelper {


    public static String getResponseWithOnlyHeaderLine() {

        String successHttpResponseHeader = "HTTP/1.1 200 OK \n\n";

        return successHttpResponseHeader;

    }

}
