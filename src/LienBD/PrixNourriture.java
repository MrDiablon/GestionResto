package LienBD;

public abstract class PrixNourriture {
	private float prix;
	
	public float getPrix(){
		return prix;
	}
	
	public Boolean isPlat(){
		return false;
	}
}
