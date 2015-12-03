import java.util.* ;

public class TabIdent implements YakaConstants{
	private HashMap<String,IdFonction> globaux;
	private HashMap<String,Ident> locaux;
	private Stack<IdParam> pileParams;
	
	
	/**
	 * Constructeur
	 */
	public TabIdent(){
		globaux = new HashMap<String,IdFonction>();
		locaux = new HashMap<String,Ident>();
		pileParams = new Stack<IdParam>();
	}
	
	/**
	 * Si la clef est presente dans la Map on renvoit l'ident.
	 * Sinon on renvoit un ident ayant pour type erreur.
	 * @param la clef
	 * @return l'ident
	 */
	public Ident chercheIdent (String clef){
		Ident id;
		if (!locaux.containsKey(clef)) {
			id = new IdConst(ERREUR, 0);
			System.out.println("ERREUR ligne " + Yaka.ligne + " : l'element '" + clef + "' n'est pas declare");
			this.rangeIdent(clef, id);
		} else {
			id = locaux.get(clef); 
		}
		return id;
	}
	
	/**
	 * Si la clef est presente dans la Map on renvoit l'ident.
	 * Sinon on renvoit un ident ayant pour type erreur.
	 * @param la clef
	 * @return l'ident
	 */
	public IdFonction chercheIdentGlob (String clef){
		if (!globaux.containsKey(clef)) {
			System.out.println("ERREUR ligne " + Yaka.ligne + " : la fonction '" + clef + "' n'existe pas");
			return null;
		} else {
			return globaux.get(clef); 
		}
	}
	
	/**
	 * Range un ident dans les locaux
	 * @param la clef
	 * @param l'id
	 */
	public void rangeIdent (String clef, Ident id)
	{
		if (locaux.containsKey(clef)) {
			System.out.println("ERREUR ligne " + Yaka.ligne + " : l'element '" + clef + "' est deja declare");
		} else {
			if(id instanceof IdParam){
				pileParams.push((IdParam)id);
			}
			locaux.put(clef,id);
		}
	}
	
	/**
	 * Range un IdFonction dans les globaux
	 * @param la clef
	 * @param l'id
	 */
	public void rangeIdentGlob (String clef, IdFonction id)
	{
		if (globaux.containsKey(clef)) {
			System.out.println("ERREUR ligne " + Yaka.ligne + " : la fonction '" + clef + "' est deja declaree");
			globaux.get(clef).setRedefini(true);
		} else {
			globaux.put(clef,id);
		}
	}
	
	/**
	 * Verifie si une fonction est declaree
	 */
	public boolean isIdFonction(String cle) {
		return globaux.containsKey(cle);
	}
	
	/**
	 * Remet a zero les param static pour le soffset
	 */
	public void raz() {
		locaux.clear();
		IdParam.raz();
		IdVar.raz();
	}
	
	/**
	 * Lorsque tous les IdParam ont ete definis, affecte aux IdPram les bons offsets
	 */
	public void setOffsets(){
		while(!(pileParams.isEmpty())){
			pileParams.pop().setOffset();
		}
	}
	
	public String toString() {
		return "locaux : " + locaux.toString() + "\nGlobaux : " + globaux.toString()+"\n";
	}
	
}
