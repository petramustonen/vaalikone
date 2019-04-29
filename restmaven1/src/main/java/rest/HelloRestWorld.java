package rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.appengine.api.datastore.Query;

import data.Riista;
import model.Ehdokkaat;
 
@Path("/riistaservice")
public class HelloRestWorld {
 
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String terve() {
		String output = "Terve!";
		return output;
	}

	@GET
	@Path("/terve")
	@Produces(MediaType.TEXT_PLAIN)
	public String tervehdi() {
		String output = "Terve, Pena!";
		return output;
	}

	@GET
	@Path("/terve/{param}")
	@Produces(MediaType.TEXT_PLAIN)
	public String tervehdiViesti(@PathParam("param") String msg) {
 		String output = msg+", Pena!";
		return output;
	}
	
	@GET
	@Path("/riista")
	@Produces(MediaType.APPLICATION_JSON)
	public Riista getRiista() {
		Riista r=new Riista(1, "Metsäkauris", 12.9f);
		return r;
	}
	@GET
	@Path("/kaikki")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getKaikki(ArrayList<Ehdokkaat> lista) {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("restmaven1");
		EntityManager em=emf.createEntityManager();
		
     javax.persistence.Query q = em.createQuery("SELECT e FROM Ehdokkaat e");
        List<Ehdokkaat> kaikkiEhdokkaat = q.getResultList();
        
    		return Response.status(200).entity(kaikkiEhdokkaat).build();
    	}
	
	
	@POST
	@Path("/lihotariista")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Riista postRiista(Riista r) {
		r.setPaino(r.getPaino()*2);
		r.setLaji(r.getLaji()+r.getPaino());
		return r;
	}
	
	
	@POST
	@Path("/lihotakaikki")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<Ehdokkaat> tallennaRiistat(ArrayList<Ehdokkaat> lista) {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("restmaven1");
		EntityManager em=emf.createEntityManager();
	    javax.persistence.Query q = em.createQuery("SELECT e FROM Ehdokkaat e");
	    
//	    List<Ehdokkaat> kaikkiEhdokkaat = q.getResultList();
		for (Ehdokkaat r: lista) {
			r.setIka(r.getIka()*3);
		}
		return (ArrayList<Ehdokkaat>) q;
		 
	}
	


		
 
      
	
	@POST
	@Path("/addehdokkaat")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Ehdokkaat addehdokkaat(Ehdokkaat r)
	{ 
			EntityManagerFactory emf;
			EntityManager em;
		 emf = Persistence.createEntityManagerFactory("restmaven1");
		em = emf.createEntityManager();
		System.out.println("Ehdokkaat palvelussa Ehdokkaat : " + r );
		r.setEtunimi(r.getEtunimi());
		r.setSukunimi(r.getSukunimi());
		r.setPuolue(r.getPuolue());
		 
		r.setMitaAsioitaHaluatEdistaa(r.getMitaAsioitaHaluatEdistaa());
		r.setMitaAsioitaHaluatEdistaa(r.getMitaAsioitaHaluatEdistaa());
		r.setMiksiEduskuntaan(r.getMiksiEduskuntaan());
		r.setKotipaikkakunta(r.getKotipaikkakunta());
		
		r.setIka(2*r.getIka()); 
		
		 em.getTransaction().begin();
		  em.persist(r);
		  em.getTransaction().commit();
	 
		
		return r; 
		
	}

	
	
	@GET
	@Path("/laihdutatakaikki")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response taallennaRiistat(ArrayList<Ehdokkaat> lista) {
		for (Ehdokkaat r: lista) {
			r.setIka(r.getIka()/3);
		}
		return Response.status(200).entity(lista).build();
	}
}