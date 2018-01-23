package org.apache.catalina.connector;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.catalina.HttpRequest;
import org.apache.catalina.Request;
import org.apache.catalina.session.StandardSessionFacade;


/**
 * Facade class that wraps a Catalina-internal <b>HttpRequest</b>
 * object.  All methods are delegated to the wrapped request.
 *
 * @author Remy Maucherat
 * @version $Revision: 1.2 $ $Date: 2001/07/22 20:25:06 $
 */

public final class HttpRequestFacade
    extends RequestFacade
    implements HttpServletRequest {


    // ----------------------------------------------------------- Constructors


    /**
     * Construct a wrapper for the specified request.
     *
     * @param request The request to be wrapped
     */
    public HttpRequestFacade(HttpRequest request) {
        super(request);
    }


    // --------------------------------------------- HttpServletRequest Methods


    public String getAuthType() {
        return ((HttpServletRequest) request).getAuthType();
    }


    public Cookie[] getCookies() {
        return ((HttpServletRequest) request).getCookies();
    }


    public long getDateHeader(String name) {
        return ((HttpServletRequest) request).getDateHeader(name);
    }


    public String getHeader(String name) {
        return ((HttpServletRequest) request).getHeader(name);
    }


    public Enumeration getHeaders(String name) {
        return ((HttpServletRequest) request).getHeaders(name);
    }


    public Enumeration getHeaderNames() {
        return ((HttpServletRequest) request).getHeaderNames();
    }


    public int getIntHeader(String name) {
        return ((HttpServletRequest) request).getIntHeader(name);
    }


    public String getMethod() {
        return ((HttpServletRequest) request).getMethod();
    }


    public String getPathInfo() {
        return ((HttpServletRequest) request).getPathInfo();
    }


    public String getPathTranslated() {
        return ((HttpServletRequest) request).getPathTranslated();
    }


    public String getContextPath() {
        return ((HttpServletRequest) request).getContextPath();
    }


    public String getQueryString() {
        return ((HttpServletRequest) request).getQueryString();
    }


    public String getRemoteUser() {
        return ((HttpServletRequest) request).getRemoteUser();
    }


    public boolean isUserInRole(String role) {
        return ((HttpServletRequest) request).isUserInRole(role);
    }


    public java.security.Principal getUserPrincipal() {
        return ((HttpServletRequest) request).getUserPrincipal();
    }


    public String getRequestedSessionId() {
        return ((HttpServletRequest) request).getRequestedSessionId();
    }


    public String getRequestURI() {
        return ((HttpServletRequest) request).getRequestURI();
    }


    public StringBuffer getRequestURL() {
        return ((HttpServletRequest) request).getRequestURL();
    }


    public String getServletPath() {
        return ((HttpServletRequest) request).getServletPath();
    }


    public HttpSession getSession(boolean create) {
        HttpSession session =
            ((HttpServletRequest) request).getSession(create);
        if (session == null)
            return null;
        else
            return new StandardSessionFacade(session);
    }


    public HttpSession getSession() {
        return getSession(true);
    }


    public boolean isRequestedSessionIdValid() {
        return ((HttpServletRequest) request).isRequestedSessionIdValid();
    }


    public boolean isRequestedSessionIdFromCookie() {
        return ((HttpServletRequest) request).isRequestedSessionIdFromCookie();
    }


    public boolean isRequestedSessionIdFromURL() {
        return ((HttpServletRequest) request).isRequestedSessionIdFromURL();
    }


    public boolean isRequestedSessionIdFromUrl() {
        return ((HttpServletRequest) request).isRequestedSessionIdFromURL();
    }

    public HttpRequestFacade(Request request) {
        super(request);
    }

    @Override
    public void clear() {
        super.clear();
    }

    @Override
    public Object getAttribute(String name) {
        return super.getAttribute(name);
    }

    @Override
    public Enumeration getAttributeNames() {
        return super.getAttributeNames();
    }

    @Override
    public String getCharacterEncoding() {
        return super.getCharacterEncoding();
    }

    @Override
    public void setCharacterEncoding(String env) throws UnsupportedEncodingException {
        super.setCharacterEncoding(env);
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
    public ServletInputStream getInputStream() throws IOException {
        return super.getInputStream();
    }

    @Override
    public String getParameter(String name) {
        return super.getParameter(name);
    }

    @Override
    public Enumeration getParameterNames() {
        return super.getParameterNames();
    }

    @Override
    public String[] getParameterValues(String name) {
        return super.getParameterValues(name);
    }

    @Override
    public Map getParameterMap() {
        return super.getParameterMap();
    }

    @Override
    public String getProtocol() {
        return super.getProtocol();
    }

    @Override
    public String getScheme() {
        return super.getScheme();
    }

    @Override
    public String getServerName() {
        return super.getServerName();
    }

    @Override
    public int getServerPort() {
        return super.getServerPort();
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return super.getReader();
    }

    @Override
    public String getRemoteAddr() {
        return super.getRemoteAddr();
    }

    @Override
    public String getRemoteHost() {
        return super.getRemoteHost();
    }

    @Override
    public void setAttribute(String name, Object o) {
        super.setAttribute(name, o);
    }

    @Override
    public void removeAttribute(String name) {
        super.removeAttribute(name);
    }

    @Override
    public Locale getLocale() {
        return super.getLocale();
    }

    @Override
    public Enumeration getLocales() {
        return super.getLocales();
    }

    @Override
    public boolean isSecure() {
        return super.isSecure();
    }

    @Override
    public RequestDispatcher getRequestDispatcher(String path) {
        return super.getRequestDispatcher(path);
    }

    @Override
    public String getRealPath(String path) {
        return super.getRealPath(path);
    }

    @Override
    public boolean authenticate(HttpServletResponse response) throws IOException, ServletException {
        return false;
    }

    @Override
    public void login(String username, String password) throws ServletException {

    }

    @Override
    public void logout() throws ServletException {

    }

    @Override
    public Collection<Part> getParts() throws IOException, ServletException {
        return null;
    }

    @Override
    public Part getPart(String name) throws IOException, ServletException {
        return null;
    }

    @Override
    public int getRemotePort() {
        return 0;
    }

    @Override
    public String getLocalName() {
        return null;
    }

    @Override
    public String getLocalAddr() {
        return null;
    }

    @Override
    public int getLocalPort() {
        return 0;
    }

    @Override
    public ServletContext getServletContext() {
        return null;
    }

    @Override
    public AsyncContext startAsync() throws IllegalStateException {
        return null;
    }

    @Override
    public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse) throws IllegalStateException {
        return null;
    }

    @Override
    public boolean isAsyncStarted() {
        return false;
    }

    @Override
    public boolean isAsyncSupported() {
        return false;
    }

    @Override
    public AsyncContext getAsyncContext() {
        return null;
    }

    @Override
    public DispatcherType getDispatcherType() {
        return null;
    }
}
