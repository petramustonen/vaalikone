package rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.persistence.config.ResultSetType;

import com.google.appengine.api.datastore.Query;
import com.google.appengine.repackaged.com.google.common.flogger.backend.TemplateContext;


import model.Ehdokkaat;
import model.Kysymykset;
 
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
		String output = "Terve, Pikka";
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
	@Path("/kaikki")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getKaikki(ArrayList<Ehdokkaat> lista) {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("restmaven1");
		EntityManager em=emf.createEntityManager();
		
     javax.persistence.Query q = em.createQuery("SELECT e FROM Ehdokkaat e");
        List<Ehdokkaat> kaikkiEhdokkaat = q.getResultList();
        
    		return Response.status(200).entity(kaikkiEhdokkaat).build();
    	}
	
	

	
	
//	@POST
//	@Path("/lihotakaikki")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public ArrayList<Ehdokkaat> tallennaRiistat(ArrayList<Ehdokkaat> lista) {
//		EntityManagerFactory emf=Persistence.createEntityManagerFactory("restmaven1");
//		EntityManager em=emf.createEntityManager();
//	    javax.persistence.Query q = em.createQuery("SELECT e FROM Ehdokkaat e");
//	    
////	    List<Ehdokkaat> kaikkiEhdokkaat = q.getResultList();
//		for (Ehdokkaat r: lista) {
//			r.setIka(r.getIka()*3);
//		}
//		return (ArrayList<Ehdokkaat>) q;
//		 
//	}
	
	@POST
	@Path("/delete")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.persistence.Query deleteehdokkaat(Ehdokkaat r) throws SQLException
	{ String poistettavaId = Integer(r).toString();
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("restmaven1");
		EntityManager em=emf.createEntityManager();
		
	     javax.persistence.Query q = em.createQuery("delete from Ehdokkaat where ehdokas_id=?");
	     
	
	  
	        ((PreparedStatement) q).setString(1,poistettavaId);
			return q;
	      
	        
	    		
	    	
		

		}
 
	
		 
	


		
 
      
	
	private Object Integer(Ehdokkaat r) {
	// TODO Auto-generated method stub
	return null;
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
		
		
		
		 em.getTransaction().begin();
		  em.persist(r);
		  em.getTransaction().commit();
	 
		
		return r; 
		
	}
	
	
	///////////////////////////////////
	
	@POST
	@Path("/addkysymykset")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Kysymykset addkysymykset(Kysymykset r)
	{ 
	 EntityManagerFactory emf;
	 EntityManager em;
	 emf = Persistence.createEntityManagerFactory("restmaven1");
	em = emf.createEntityManager();
	System.out.println("Kysymykset palvelussa Kysymykset : " + r );
	r.setKysymys(r.getKysymys());

	em.getTransaction().begin();
	  em.persist(r);
	  em.getTransaction().commit();
	 return r;
	}

/////////////////////////////////////////////
	
	@GET
	@Path("/kysymyslista")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLista(ArrayList<Kysymykset> lista) {
	 EntityManagerFactory emf=Persistence.createEntityManagerFactory("restmaven1");
	 EntityManager em=emf.createEntityManager();
	 
	     javax.persistence.Query q = em.createQuery("SELECT e FROM Kysymykset e");
	        List<Kysymykset> kaikkiKysymykset = q.getResultList();
	        
	      return Response.status(200).entity(kaikkiKysymykset).build();
	     }



	
	


}