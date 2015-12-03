
public class IdParam extends Ident{
	private static int prochain_offset = 4;
	private static int compteur = 0;
	
	/**
	 * Constructeur de IdParam
	 * @param t le type du parametre
	 */
	public IdParam (int t){
		super();
		super.type = t;
		compteur++;
	}
	
	/**
	 * Methode permettant d'affecter un offset a un IdParam
	 */
	public void setOffset(){
		super.offset = prochain_offset;
		prochain_offset += 2;
	}
	
	/**
	 * Cette methode permet de remettre a zero les variable static
	 */
	public static void raz(){
		prochain_offset = 4;
		compteur = 0;
	}
	
	/**
	 * Cette methode permet de remettre le static int compteur a 0
	 */
	public static void razCompteur() {
		
	}
	
	/**
	 * Getters pour obtenir le nombre de IdParam
	 * @return la valeur du compteur
	 */
	public static int getCompteur() {
		return compteur;
	}
	
	/**
	 * Methode d'appel a la fonction iconst de YVM
	 */
	public void yvm() {
		Yaka.yvm.iload(super.offset);
	}
	
	public String toString() {
		return "( Parametre : type : " + type + " ; offset : " + offset + ")";
	}
}