package ex05.pyrmont.startup;

import ex05.pyrmont.core.SimpleContext;
import ex05.pyrmont.core.SimpleContextMapper;
import ex05.pyrmont.core.SimpleLoader;
import ex05.pyrmont.core.SimpleWrapper;
import ex05.pyrmont.valves.ClientIPLoggerValve;
import ex05.pyrmont.valves.HeaderLoggerValve;
import org.apache.catalina.Context;
import org.apache.catalina.Loader;
import org.apache.catalina.Mapper;
import org.apache.catalina.Pipeline;
import org.apache.catalina.Valve;
import org.apache.catalina.Wrapper;
import org.apache.catalina.connector.http.HttpConnector;

public final class Bootstrap2 {
    public static void main(String[] args) {
        HttpConnector connector = new HttpConnector();
        Wrapper wrapper1 = new SimpleWrapper();

//        请仔细注意"uri"-"子容器"-"wrapper的名字"-"servletClass"的名字。
        wrapper1.setName("Primitive");  //wrapper的名字就是servlet的名字。
        wrapper1.setServletClass("PrimitiveServlet");
        Wrapper wrapper2 = new SimpleWrapper();
        wrapper2.setName("Modern");
        wrapper2.setServletClass("ModernServlet");

        Context context = new SimpleContext();
        context.addChild(wrapper1);
        context.addChild(wrapper2);

        Valve valve1 = new HeaderLoggerValve();
        Valve valve2 = new ClientIPLoggerValve();

        ((Pipeline) context).addValve(valve1);  //难道valve不是与wrapper关联吗a?
        ((Pipeline) context).addValve(valve2);

        Mapper mapper = new SimpleContextMapper();
//        mapper.setProtocol("HTTP/1.1");

        mapper.setProtocol("http/0.9");
        //这里没有用到mapper，因为该SimpleContextMapper被用作为SimpleContext容器的默认Mapper了。
        // 详见SimpleContext的findMapper方法。

        context.addMapper(mapper);  //
        Loader loader = new SimpleLoader();
        context.setLoader(loader);
        // context.addServletMapping(pattern, name);
        context.addServletMapping("/Primitive", "Primitive");   //将URI对应到"servlet的名字"
        context.addServletMapping("/Modern", "Modern");
        connector.setContainer(context);
        try {
            connector.initialize();
            connector.start();

            // make the application wait until we press a key.
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}