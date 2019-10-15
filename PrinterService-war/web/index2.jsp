<%@page import="java.util.Set"%>
<%@page import="com.rta.opain.domain.RegisterInOut"%>
<%@page import="java.util.List"%>
<%
    Set<RegisterInOut> salidas = (Set<RegisterInOut>) session.getAttribute("salidas");

    for (RegisterInOut obj : salidas) {
        out.println("<p>" + obj.getVehiculoPlaca() + "</p>");
    }
%>