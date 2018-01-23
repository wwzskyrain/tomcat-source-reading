package org.apache.catalina.connector.http;


import org.apache.catalina.Connector;
import org.apache.catalina.Context;
import org.apache.catalina.Request;
import org.apache.catalina.connector.HttpResponseBase;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;


/**
 * Implementation of <b>HttpResponse</b> specific to the HTTP connector.
 *
 * @author Craig R. McClanahan
 * @author <a href="mailto:remm@apache.org">Remy Maucherat</a>
 * @version $Revision: 1.13 $ $Date: 2002/03/18 07:15:40 $
 * @deprecated
 */

final class HttpResponseImpl
    extends HttpResponseBase {


    // ----------------------------------------------------- Instance Variables


    /**
     * Descriptive information about this Response implementation.
     */
    protected static final String info =
        "org.apache.catalina.connector.http.HttpResponseImpl/1.0";


    /**
     * True if chunking is allowed.
     */
    protected boolean allowChunking;


    /**
     * Associated HTTP response stream.
     */
    protected HttpResponseStream responseStream;


    // ------------------------------------------------------------- Properties


    /**
     * Return descriptive information about this Response implementation and
     * the corresponding version number, in the format
     * <code>&lt;description&gt;/&lt;version&gt;</code>.
     */
    public String getInfo() {

        return (info);

    }


    /**
     * Set the chunking flag.
     */
    void setAllowChunking(boolean allowChunking) {
        this.allowChunking = allowChunking;
    }


    /**
     * True if chunking is allowed.
     */
    public boolean isChunkingAllowed() {
        return allowChunking;
    }


    // ------------------------------------------------------ Protected Methods

    /**
     * Return the HTTP protocol version implemented by this response
     * object.
     *
     * @return The &quot;HTTP/1.1&quot; string.
     */
    protected String getProtocol() {
        return("HTTP/1.1");
    }


    // --------------------------------------------------------- Public Methods

    /**
     * Release all object references, and initialize instance variables, in
     * preparation for reuse of this object.
     */
    public void recycle() {

        super.recycle();
        responseStream = null;
        allowChunking = false;

    }


    /**
     * Send an error response with the specified status and message.
     *
     * @param status HTTP status code to send
     * @param message Corresponding message to send
     *
     * @exception IllegalStateException if this response has
     *  already been committed
     * @exception IOException if an input/output error occurs
     */
    public void sendError(int status, String message) throws IOException {

        addHeader("Connection", "close");
        super.sendError(status, message);

    }


    /**
     * Clear any content written to the buffer.  In addition, all cookies
     * and headers are cleared, and the status is reset.
     *
     * @exception IllegalStateException if this response has already
     *  been committed
     */
    public void reset() {

        // Saving important HTTP/1.1 specific headers
        String connectionValue =
            (String) getHeader("Connection");
        String transferEncodingValue =
            (String) getHeader("Transfer-Encoding");
        super.reset();
        if (connectionValue != null)
            addHeader("Connection", connectionValue);
        if (transferEncodingValue != null)
            addHeader("Transfer-Encoding", transferEncodingValue);

    }


    /**
     * Create and return a ServletOutputStream to write the content
     * associated with this Response.
     *
     * @exception IOException if an input/output error occurs
     */
    public ServletOutputStream createOutputStream() throws IOException {

        responseStream = new HttpResponseStream(this);
        return (responseStream);

    }


    /**
     * Tests is the connection will be closed after the processing of the
     * request.
     */
    public boolean isCloseConnection() {
        String connectionValue = (String) getHeader("Connection");
        return (connectionValue != null
                && connectionValue.equals("close"));
    }


    /**
     * Removes the specified header.
     *
     * @param name Name of the header to remove
     * @param value Value to remove
     */
    public void removeHeader(String name, String value) {

        if (isCommitted())
            return;

        if (included)
            return;     // Ignore any call from an included servlet

        synchronized (headers) {
            ArrayList values = (ArrayList) headers.get(name);
            if ((values != null) && (!values.isEmpty())) {
                values.remove(value);
                if (values.isEmpty())
                    headers.remove(name);
            }
        }

    }


    /**
     * Has stream been created ?
     */
    public boolean isStreamInitialized() {
        return (responseStream != null);
    }


    /**
     * Perform whatever actions are required to flush and close the output
     * stream or writer, in a single operation.
     *
     * @exception IOException if an input/output error occurs
     */
    public void finishResponse() throws IOException {

        if (getStatus() < HttpServletResponse.SC_BAD_REQUEST) {
            if ((!isStreamInitialized()) && (getContentLength() == -1)
                && (getStatus() >= 200)
                && (getStatus() != SC_NOT_MODIFIED)
                && (getStatus() != SC_NO_CONTENT))
                setContentLength(0);
        } else {
            setHeader("Connection", "close");
        }
        super.finishResponse();

    }


    // -------------------------------------------- HttpServletResponse Methods


    /**
     * Set the HTTP status to be returned with this response.
     *
     * @param status The new HTTP status
     */
    public void setStatus(int status) {

        super.setStatus(status);

        if (responseStream != null)
            responseStream.checkChunking(this);

    }


    /**
     * Set the content length (in bytes) for this Response.
     *
     * @param length The new content length
     */
    public void setContentLength(int length) {

        if (isCommitted())
            return;

        if (included)
            return;     // Ignore any call from an included servlet

        super.setContentLength(length);

        if (responseStream != null)
            responseStream.checkChunking(this);

    }

    public HttpResponseImpl() {
        super();
    }

    @Override
    public ServletResponse getResponse() {
        return super.getResponse();
    }

    @Override
    public Cookie[] getCookies() {
        return super.getCookies();
    }

    @Override
    public String getHeader(String name) {
        return super.getHeader(name);
    }

    @Override
    public String[] getHeaderNames() {
        return super.getHeaderNames();
    }

    @Override
    public String[] getHeaderValues(String name) {
        return super.getHeaderValues(name);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public int getStatus() {
        return super.getStatus();
    }

    @Override
    public void reset(int status, String message) {
        super.reset(status, message);
    }

    @Override
    protected String getStatusMessage(int status) {
        return super.getStatusMessage(status);
    }

    @Override
    protected void sendHeaders() throws IOException {
        super.sendHeaders();
    }

    @Override
    public void flushBuffer() throws IOException {
        super.flushBuffer();
    }

    @Override
    public void setContentType(String type) {
        super.setContentType(type);
    }

    @Override
    public void setLocale(Locale locale) {
        super.setLocale(locale);
    }

    @Override
    public void addCookie(Cookie cookie) {
        super.addCookie(cookie);
    }

    @Override
    public void addDateHeader(String name, long value) {
        super.addDateHeader(name, value);
    }

    @Override
    public void addHeader(String name, String value) {
        super.addHeader(name, value);
    }

    @Override
    public void addIntHeader(String name, int value) {
        super.addIntHeader(name, value);
    }

    @Override
    public boolean containsHeader(String name) {
        return super.containsHeader(name);
    }

    @Override
    public String encodeRedirectURL(String url) {
        return super.encodeRedirectURL(url);
    }

    @Override
    public String encodeRedirectUrl(String url) {
        return super.encodeRedirectUrl(url);
    }

    @Override
    public String encodeURL(String url) {
        return super.encodeURL(url);
    }

    @Override
    public String encodeUrl(String url) {
        return super.encodeUrl(url);
    }

    @Override
    public void sendAcknowledgement() throws IOException {
        super.sendAcknowledgement();
    }

    @Override
    public void sendError(int status) throws IOException {
        super.sendError(status);
    }

    @Override
    public void sendRedirect(String location) throws IOException {
        super.sendRedirect(location);
    }

    @Override
    public void setDateHeader(String name, long value) {
        super.setDateHeader(name, value);
    }

    @Override
    public void setHeader(String name, String value) {
        super.setHeader(name, value);
    }

    @Override
    public void setIntHeader(String name, int value) {
        super.setIntHeader(name, value);
    }

    @Override
    public void setStatus(int status, String message) {
        super.setStatus(status, message);
    }

    @Override
    public void setAppCommitted(boolean appCommitted) {
        super.setAppCommitted(appCommitted);
    }

    @Override
    public boolean isAppCommitted() {
        return super.isAppCommitted();
    }

    @Override
    public Connector getConnector() {
        return super.getConnector();
    }

    @Override
    public void setConnector(Connector connector) {
        super.setConnector(connector);
    }

    @Override
    public int getContentCount() {
        return super.getContentCount();
    }

    @Override
    public Context getContext() {
        return super.getContext();
    }

    @Override
    public void setContext(Context context) {
        super.setContext(context);
    }

    @Override
    public boolean getIncluded() {
        return super.getIncluded();
    }

    @Override
    public void setIncluded(boolean included) {
        super.setIncluded(included);
    }

    @Override
    public Request getRequest() {
        return super.getRequest();
    }

    @Override
    public void setRequest(Request request) {
        super.setRequest(request);
    }

    @Override
    public OutputStream getStream() {
        return super.getStream();
    }

    @Override
    public void setStream(OutputStream stream) {
        super.setStream(stream);
    }

    @Override
    public void setSuspended(boolean suspended) {
        super.setSuspended(suspended);
    }

    @Override
    public boolean isSuspended() {
        return super.isSuspended();
    }

    @Override
    public void setError() {
        super.setError();
    }

    @Override
    public boolean isError() {
        return super.isError();
    }

    @Override
    public int getContentLength() {
        return super.getContentLength();
    }

    @Override
    public String getContentType() {
        return super.getContentType();
    }

    @Override
    public PrintWriter getReporter() {
        return super.getReporter();
    }

    @Override
    public void write(int b) throws IOException {
        super.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException {
        super.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        super.write(b, off, len);
    }

    @Override
    public int getBufferSize() {
        return super.getBufferSize();
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
    public Locale getLocale() {
        return super.getLocale();
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return super.getWriter();
    }

    @Override
    public boolean isCommitted() {
        return super.isCommitted();
    }

    @Override
    public void resetBuffer() {
        super.resetBuffer();
    }

    @Override
    public void setBufferSize(int size) {
        super.setBufferSize(size);
    }

    @Override
    public Collection<String> getHeaders(String name) {
        return super.getHeaders(name);
    }

    @Override
    public void setCharacterEncoding(String charset) {
        super.setCharacterEncoding(charset);
    }
}
