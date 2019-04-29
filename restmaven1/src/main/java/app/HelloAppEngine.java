package app;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;

import data.Riista;

@WebServlet(
    name = "HelloAppEngine",
    urlPatterns = {"/hello"}
)
public class HelloAppEngine extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException {

	  response.setContentType("text/html;charset=UTF-8");
	  PrintWriter out=response.getWriter();
		String uri = "http://127.0.0.1:8080/rest/riistaservice/lihotariista";
		try {
			Riista riista=new Riista();
			riista.setId(1001);
			riista.setLaji("Koirasusi");
			riista.setPaino(12.5f);

			Client asiakas=ClientBuilder.newClient();
			
			WebTarget wt=asiakas.target(uri);
			Builder b=wt.request();
			Entity e=Entity.entity(riista,MediaType.APPLICATION_JSON);
			Riista palautettu=b.post(e, Riista.class);
//			Riista palautettu=b.put(e, Riista.class);
			
			out.println(palautettu);
			out.println(riista);
			
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
  }
}