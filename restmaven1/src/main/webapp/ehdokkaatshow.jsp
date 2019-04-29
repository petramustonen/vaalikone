<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="model.Ehdokkaat"%>
    <%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table  border ="1">
<tr><td>ID</td>
<td>ETUNIMI</td>
<td>SUKUNIMI</td>
<td>PUOLUE</td>
<td>KOTIPAIKKAKUNTA</td>
<td>IKA</td>
<td>MIKSI_EDUSKUNTAAN</td>
<td>MITA_ASIOITA_HALUAT_EDISTAA</td>
<td>AMMATTI</td>

<td>DELETE</td>
<td>EDIT</td>
</tr> 
<%  

List<Ehdokkaat> kaikkiEhdokkaat=(List<Ehdokkaat>)(request.getAttribute("kaikkiEhdokkaat"));

for (int i=0;kaikkiEhdokkaat!=null && i<kaikkiEhdokkaat.size();i++){
	Ehdokkaat x=kaikkiEhdokkaat.get(i);
	out.println("<tr><td>"+x.getEhdokasId()+"<td>"+x.getEtunimi()+
			"<td>"+x.getSukunimi()+
			"<td>"+x.getPuolue()+"<td>"+x.getKotipaikkakunta()+"<td>"+x.getIka()+"<td>"+x.getMiksiEduskuntaan()+
			"<td>"+x.getMitaAsioitaHaluatEdistaa()+"<td>"+x.getAmmatti()+
			
			
			"<td><a href='Remove?poistettavaId="+x.getEhdokasId()+"'>Poista</a>" +	
			"<td><a href='GetEhdokkaat?poistettavaId="+x.getEhdokasId()+"'>Edit</a>");
}

%>
</table>
</body>
</html>