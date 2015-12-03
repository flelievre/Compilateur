public class IdVar extends Ident {
	private static int prochain_offset;
	private boolean affecte;
	private static int compteur = 0;
	
	/**
	 * Constrcuteur d'un IdVar
	 * @param t le type de la variable
	 */
	public IdVar (int t){
		super();
		super.type = t;
		prochain_offset -= 2;
		super.offset = prochain_offset;
		affecte = false;
		compteur++;
	}
	
	/**
	 * Lorsque l'on affecte une valeur a un Ident, on utilise setAffecte afin d'informer que l'Ident a bien ete
	 * initialise. Par contre, si l'ident n'est pas un IdVar, elle affiche un message d'erreur
	 * @param affecte
	 */
	public void setAffecte(boolean affecte) {
		this.affecte = affecte;
	}
	
	/**
	 * Cette methode permet de remettre le static int prochain_offset a 0
	 */
	public static void raz(){
		prochain_offset = 0;
		compteur = 0;
	}
	
	/**
	 * Cette methode permet de savoir combien de d'IdVar ont ete declare *2
	 * @return me nombre d'IdVar declare *2
	 */
	public static int getNbVarL(){
		return  -prochain_offset;
	}
	
	/**
	 * Methode d'appel a la fonction iload de YVM
	 * Si la variable n'a pas ete declaree, elle affiche une erreur
	 */
	public void yvm() {
		if (!affecte) {
			System.out.println("ATTENTION ligne " + Yaka.ligne + " : une des variables utilisee dans l'expression n'a pas ete initialisee");
		}
		Yaka.yvm.iload(offset);
	}
	
	/**
	 * @return le nombre de variable declaree
	 */
	public static int getCompteur() {
		return compteur;
	}
	
	public String toString() {
		return "( type : " + type + " ; offset : " + offset + ")";
	}
}
