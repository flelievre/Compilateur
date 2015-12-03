import java.util.ArrayList;

public class IdFonction extends Ident implements YakaConstants{
	private String nom = "";
	private int resultat;
	private ArrayList<Integer> parametre = new ArrayList<Integer>();
	private boolean redefini;
	
	/**
	 * Constructeur d'un IdFonction
	 * @param type de retour de la fonction
	 * @param nom de la fonction
	 */
	public IdFonction (int type, String nom){
		super();
		this.nom = nom;
		this.resultat = type;
		this.redefini = false;
	}
	
	/**
	 * Setters permettant d'affecter un Type(Entier ou Booleen) de retour pour cette fonction
	 * @param res
	 */
	public void setResultat (int res) {
		resultat = res;
	}
	
	/**
	 * @return retourne le type de retour de la fonction
	 */
	public int getResultat() {
		return resultat;
	}
	
	/**
	 * @return le nombre de parametre de la fonction
	 */
	public int getTaille() {
		return parametre.size();
	}
	
	/**
	 * @return le nom de la fonction
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * @param index
	 * @return le type du parametre numero index
	 */
	public int getParam(int index){
		if (index >= parametre.size()) {
			System.out.println("ERREUR ligne " + Yaka.ligne + " : Le nombre d'arguments de la fonction " + nom + " doit etre egal a " + parametre.size());
			return ERREUR;
		}
		return parametre.get(index);
	}
	
	/**
	 * Cette methode permet d'ajouter un parametre a la liste des parametres
	 * @param type
	 */
	public void addParam (int type) {
		if(!redefini)
			parametre.add(type);
	}

	/**
	 * @return le nombre de parametre * 2
	 */
	public int taille (){
		return (parametre.size() * 2 );
	}

	/**
	 * Modifie la valeur du booleen redefini qui permet de savoir si cette fonction a deja ete definie
	 */
	public void setRedefini(boolean b) {
		redefini = b;
	}

	public String toString() {
		return ("resultat : " + resultat + "/parametres : " + parametre);
	}
}
