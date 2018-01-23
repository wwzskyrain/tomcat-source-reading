package org.apache.catalina.connector;


import org.apache.catalina.HttpResponse;
import org.apache.catalina.Response;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Locale;


/**
 * Facade class that wraps a Catalina-internal <b>HttpResponse</b>
 * object.  All methods are delegated to the wrapped response.
 *
 * @author Remy Maucherat
 * @author Craig R. McClanahan
 * @version $Revision: 1.5 $ $Date: 2001/09/28 16:53:49 $
 */

public final class HttpResponseFacade
        extends ResponseFacade
        implements HttpServletResponse {


    // ----------------------------------------------------------- Constructors


    /**
     * Construct a wrapper for the specified response.
     *
     * @param response The response to be wrapped
     */
    public HttpResponseFacade(HttpResponse response) {
        super(response);
    }


    // -------------------------------------------- HttpServletResponse Methods


    public void addCookie(Cookie cookie) {

        if (isCommitted())
            return;

        ((HttpServletResponse) response).addCookie(cookie);

    }


    public boolean containsHeader(String name) {
        return ((HttpServletResponse) response).containsHeader(name);
    }


    public String encodeURL(String url) {
        return ((HttpServletResponse) response).encodeURL(url);
    }


    public String encodeRedirectURL(String url) {
        return ((HttpServletResponse) response).encodeRedirectURL(url);
    }


    public String encodeUrl(String url) {
        return ((HttpServletResponse) response).encodeURL(url);
    }


    public String encodeRedirectUrl(String url) {
        return ((HttpServletResponse) response).encodeRedirectURL(url);
    }


    public void sendError(int sc, String msg)
            throws IOException {

        if (isCommitted())
            throw new IllegalStateException
                    (/*sm.getString("responseBase.reset.ise")*/);

        resp.setAppCommitted(true);

        ((HttpServletResponse) response).sendError(sc, msg);

    }


    public void sendError(int sc)
            throws IOException {

        if (isCommitted())
            throw new IllegalStateException
                    (/*sm.getString("responseBase.reset.ise")*/);

        resp.setAppCommitted(true);

        ((HttpServletResponse) response).sendError(sc);

    }


    public void sendRedirect(String location)
            throws IOException {

        if (isCommitted())
            throw new IllegalStateException
                    (/*sm.getString("responseBase.reset.ise")*/);

        resp.setAppCommitted(true);

        ((HttpServletResponse) response).sendRedirect(location);

    }


    public void setDateHeader(String name, long date) {

        if (isCommitted())
            return;

        ((HttpServletResponse) response).setDateHeader(name, date);

    }


    public void addDateHeader(String name, long date) {

        if (isCommitted())
            return;

        ((HttpServletResponse) response).addDateHeader(name, date);

    }


    public void setHeader(String name, String value) {

        if (isCommitted())
            return;

        ((HttpServletResponse) response).setHeader(name, value);

    }


    public void addHeader(String name, String value) {

        if (isCommitted())
            return;

        ((HttpServletResponse) response).addHeader(name, value);

    }


    public void setIntHeader(String name, int value) {

        if (isCommitted())
            return;

        ((HttpServletResponse) response).setIntHeader(name, value);

    }


    public void addIntHeader(String name, int value) {

        if (isCommitted())
            return;

        ((HttpServletResponse) response).addIntHeader(name, value);

    }


    public void setStatus(int sc) {

        if (isCommitted())
            return;

        ((HttpServletResponse) response).setStatus(sc);

    }


    public void setStatus(int sc, String sm) {

        if (isCommitted())
            return;

        ((HttpServletResponse) response).setStatus(sc, sm);

    }

//    ResponseFacade 方法默认实现

    public HttpResponseFacade(Response response) {
        super(response);
    }

    @Override
    public void clear() {
        super.clear();
    }

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    public boolean isFinished() {
        return super.isFinished();
    }

    @Override
    public String getCharacterEncoding() {
        return super.getCharacterEncoding();
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return super.getOutputStream();
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return super.getWriter();
    }

    @Override
    public void setContentLength(int len) {
        super.setContentLength(len);
    }

    @Override
    public void setContentType(String type) {
        super.setContentType(type);
    }

    @Override
    public void setBufferSize(int size) {
        super.setBufferSize(size);
    }

    @Override
    public int getBufferSize() {
        return super.getBufferSize();
    }

    @Override
    public void flushBuffer() throws IOException {
        super.flushBuffer();
    }

    @Override
    public void resetBuffer() {
        super.resetBuffer();
    }

    @Override
    public boolean isCommitted() {
        return super.isCommitted();
    }

    @Override
    public void reset() {
        super.reset();
    }

    @Override
    public void setLocale(Locale loc) {
        super.setLocale(loc);
    }

    @Override
    public Locale getLocale() {
        return super.getLocale();
    }

    //    ServletResponse接口方法默认实现
    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public void setCharacterEncoding(String charset) {

    }

    //  HttpServletResponse默认实现

    @Override
    public int getStatus() {
        return 0;
    }

    @Override
    public String getHeader(String name) {
        return null;
    }

    @Override
    public Collection<String> getHeaders(String name) {
        return null;
    }

    @Override
    public Collection<String> getHeaderNames() {
        return null;
    }


}
