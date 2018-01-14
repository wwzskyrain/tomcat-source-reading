package ex01.pyrmont;

import ex.util.ResponseHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/*
  HTTP Response = Status-Line
    *(( general-header | response-header | entity-header ) CRLF)
    CRLF
    [ message-body ]
    Status-Line = HTTP-Version SP Status-Code SP Reason-Phrase CRLF
*/

public class Response {

    private static final int BUFFER_SIZE = 1024;
    Request request;
    OutputStream output;

    public Response(OutputStream output) {
        this.output = output;
    }

    public void setRequest(Request request) {
        this.request = request;
    }


    public void sendStaticResource() throws IOException {
        byte[] bytes = new byte[BUFFER_SIZE];
        FileInputStream fis = null;
        try {
            File file = new File(HttpServer.WEB_ROOT, request.getUri());  //uri对应着文件路径
            if (file.exists()) {
                fis = new FileInputStream(file);
                //在发送正文之前，要发送HttpResponse第一行加0个或多个响应头，
                // 毕竟要保持Http响应报文的格式的。不然，浏览器会显示"invalid response"。
                output.write(ResponseHelper.getResponseWithOnlyHeaderLine().getBytes());

                int ch = fis.read(bytes, 0, BUFFER_SIZE); //发送的是文件原始数据，就是发送字节。
                while (ch != -1) {
                    output.write(bytes, 0, ch); //bytes字节数据是个数据交换区，文件fis往里面写入数据，output从里面读出数据，一写一读；
                    StringBuffer stringBuffer = new StringBuffer();
                    for (int i = 0; i < bytes.length; i++) {
                        if (bytes[i] > 0) {
                            stringBuffer.append((char) bytes[i]);
                        }

                    }
                    System.out.println("data that response sends" + stringBuffer);
                    ch = fis.read(bytes, 0, BUFFER_SIZE);
                }
            } else {
                // file not found
                String errorMessage = "HTTP/1.1 404 File Not Found\r\n" +
                        "Content-Type: text/html\r\n" +
                        "Content-Length: 23\r\n" +
                        "\r\n" +
                        "<h1>File Not Found</h1>";
                output.write(errorMessage.getBytes());
            }
        } catch (Exception e) {
            // thrown if cannot instantiate a File object
            System.out.println(e.toString());
        } finally {
            if (fis != null)
                fis.close();
        }
    }
}