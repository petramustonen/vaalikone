package app;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import data.Riista;

import java.util.List;

/**
 * Servlet implementation class LueKaikkiRiistat
 */
@WebServlet("/LueKaikkiRiistat")
public class LueKaikkiRiistat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LueKaikkiRiistat() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		String uri = "http://localhost:8080/rest/riistaservice/kaikki";
		Client client = ClientBuilder.newClient();
		
		//Luodaan uusi tietotyyppi
		GenericType<List<Riista>> GenericLista = new GenericType<List<Riista>>() {};
		
		//Luetaan Riista-olioiden lista em. osoitteesta - parametrina uusi tyyppi GenericLista
		List<Riista> lista = client.target(uri).request().get(GenericLista);
		
		//Tulostetaan saatu lista
		out.println("<ol>");
		out.println("<li>Riistat RESTiltä");
		for (Riista x:lista){
			out.println("<li>"+x);
		}
		out.println("</ol>");

		//Pari rivinvaihtoa
		out.println("<br><br>");

		//Vaihdetaan uri osoittamaan POST-tyyppiseen metodiin 
		uri = "http://localhost:8080/rest/riistaservice/lihotakaikki";
		
		Client asiakas=ClientBuilder.newClient();
		WebTarget wt=asiakas.target(uri);
		Builder b=wt.request();//Invocation.Builder b = ....
		Entity e=Entity.entity(lista,MediaType.APPLICATION_JSON);
		
		//Luetaan POSTilta Responsena
		Response palautettu=b.post(e);
		
		out.println("palautettu="+palautettu+"<br>");
		out.println("palautettu.toString="+palautettu.toString()+"<br>");
		
		//Muutetaan responnse List<Riista> -tyyppiseksi  - readEntityn parametrina tulee olla edellä oleva GenericLista
		List<Riista> list=palautettu.readEntity(GenericLista);
		out.println(list);
		
		out.println("<ol>");
		out.println("<li>Lihotetut riistat");
		for (Riista r: list) {
			out.println("<li>"+r);
		}
		out.println("</ol>");

		/**
		 * Toisella tavalla - suorempaan luettuna
		 */
		
		List<Riista> palatettu2=b.post(e, GenericLista);
		out.println("<ol>");
		out.println("<li>Toisella tavalla luetut lihotetut riistat");
		for (Riista r: palatettu2) {
			out.println("<li>"+r);
		}
		out.println("</ol>");


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
