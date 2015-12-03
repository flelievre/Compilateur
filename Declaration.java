public class Declaration implements YakaConstants{
	private int type;
	private String nom;
	private int valeur;
	private String nomFonction;
	
	/******** Getters & Setters ********/
	/**
	 * Setter du parametre nom
     * @param String : nom
     * @return ne retourne rien (void)
     */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/**
	 * Getter du parametre nomFonction
	 * @param aucun
	 * @return String : nom de la fonction
	 */
	public String getNomFonction(){
		return this.nomFonction;
	}
	
	/**
	 * Setter du type a la constante ENTIER
     * @param aucun
     * @return ne retourne rien (void)
     */
	public void setTypeEntier() {
		this.type = ENTIER;
	}
	/**
	 * Setter du type a la constante BOOLEEN
     * @param aucun
     * @return ne retourne rien (void)
     */
	public void setTypeBool() {
		this.type = BOOLEEN;
	}
	/**
	 * Setter d'une valeur entiere
     * @param int : val (valeur stockee)
     * @return ne retourne rien (void)
     */
	public void setValEntier(int val) {
		valeur = val;
		type = ENTIER;
	}
	
	/**
	 * Setter d'une valeur booleenne a FAUX
     * @param aucun
     * @return ne retourne rien (void)
     */
	public void setValFAUX() {
		valeur = 0;
		type = BOOLEEN;
	}
	
	/**
	 * Setter d'une valeur booleenne a VRAI
     * @param aucun
     * @return ne retourne rien (void)
     */
	public void setValVRAI() {
		valeur = -1;
		type = BOOLEEN;
	}
	
	/**
	 * Setter d'une valeur a partir d'un Ident
     * @param int : val (valeur stockee)
     * @return ne retourne rien (void)
     */
	public void setValIdent(String val){
		Ident id = Yaka.tabIdent.chercheIdent(val);
		type = id.getType();
		valeur = ((IdConst) id).getVal();
	}
	
	/******** Gestion des des ident const et var ********/
	/**
	 * Methode qui rajoute un Identconst a tabIdent
	 * @param aucun
	 * @return ne retourne rien (void)
	 */
	void ajoutIdentConst(){
		Yaka.tabIdent.rangeIdent(nom, new IdConst(type, valeur));
	}
	
	/**
	 * Methode qui rajoute un IdentVar a tabIdent
	 * @param aucun
	 * @return ne retourne rien (void)
	 */
	void ajoutIdentVar(String nom){
		Yaka.tabIdent.rangeIdent(nom, new IdVar(type));
	}
	
	
	/******** Gestion des fonctions ********/
	/**
	 * Methode qui rajoute un IdentParam a tabIdent
	 * @param aucun
	 * @return ne retourne rien (void)
	 */
	void ajoutIdentParam(String nom){
		Yaka.tabIdent.chercheIdentGlob(nomFonction).addParam(type);
		Yaka.tabIdent.rangeIdent(nom, new IdParam(type));
	}	
	
	/**
	 * Methode qui rajoute un IdentFonction a tabIdent
	 * @param aucun
	 * @return ne retourne rien (void)
	 */
	void ajoutIdentFonc(String nom){
		Yaka.tabIdent.rangeIdentGlob(nom, new IdFonction(type, nom));
		nomFonction = nom;
	}
}
