package data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Riista {

	private int id;
	private String laji;
	private float paino;
	

	public Riista() {
		//Default contructor is needed
	}
	public Riista(int id, String laji, float paino) {
		super();
		this.id = id;
		this.laji = laji;
		this.paino = paino;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLaji() {
		return laji;
	}
	public void setLaji(String laji) {
		this.laji = laji;
	}
	public float getPaino() {
		return paino;
	}
	public void setPaino(float paino) {
		this.paino = paino;
	}
	public String toString() {
		return id+" "+laji+" "+paino;
	}
}
