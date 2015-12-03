import java.util.Stack;


public class Expression implements YakaConstants {
	private Stack<Integer> pileOperandes;
	private Stack<Integer> pileOperateurs ;
	private int typeAffectation = 0;
	private int offsetAffectation = 0;
	
	
	/**Attributs pour la gestion des Fonctions **/
	private IdFonction fonction;
	private Stack<IdFonction> pileFonctions = new Stack<IdFonction>();
	private Stack<Integer> pileCompteurs = new Stack<Integer>();
	
	/**
	 * Constructeur d'une expression
	 */
	public Expression(){
		pileOperandes = new Stack<Integer>();
		pileOperateurs =  new Stack<Integer>();
		
	}
	
	/**
	 * Cette fonction realise le controle de type : 
	 * elle verifie que les deux operandes en haut de pile corespondent a l'operation
	 * @return boolean
	 */
	public boolean controleType(){
		int a,b, op = 0;
		
		b = pileOperandes.pop();
		op = pileOperateurs.pop();
		a = pileOperandes.pop();
		
		
		if ((a == BOOLEEN) &&( b == BOOLEEN)) { 
			
			if (estOperateurEg(op) || estOperateurLogique(op)){
					pileOperandes.push(BOOLEEN);
					return true;
			}
			else {
					pileOperandes.push(ERREUR);
					return false;
			}	
		} else if ((a == ENTIER) && (b == ENTIER)) {
			if (estOperateurCalcul(op)){
				pileOperandes.push(ENTIER);
				return true ;
			}
			if (estOperateurComp(op) || estOperateurEg(op))
			{
				pileOperandes.push(BOOLEEN);
				return true;
			}
			else {
				pileOperandes.push(ERREUR);
				return false;
			}
		} else {
			pileOperandes.push(ERREUR);
			return false;
		}
		

	}
	
	/**
	 * cette fonction realise la meme operation que la methode precedente pour les operations neg et not
	 * @return boolean
	 */
	public boolean controleTypeNEG(){
		int a = pileOperandes.pop();
		int op = pileOperateurs.pop();
			if ((op == NEG) && (a == ENTIER)){
				pileOperandes.push(ENTIER);
				return true;
			}else if ((op == NON) && (a == BOOLEEN)){
				pileOperandes.push(BOOLEEN);
				return true;
			} else {
				pileOperandes.push(ERREUR);
				return false;
			}
	}

	
	/**
	 * Indique si notre operateur est + ou - ou * ou / 
	 * @param l'operation etudiee
	 * @return boolean
	 */
	public boolean estOperateurCalcul(int op) {
		return (op == PLUS || op == MOINS || op == MULT || op == DIV);
	}
	
	/**
	 * Indique si notre operateur est inf ou sup ou infoueg ou supoueg 
	 * @param l'operation etudiee
	 * @return boolean
	 */
	public boolean estOperateurComp (int op) {
		return (op == INF ||op == SUP || op == INFOUEG || op == SUPOUEG );
	}
	
	/**
	 * Indique si notre operateur est = ou !=  
	 * @param l'operation etudiee
	 * @return boolean
	 */
	public boolean estOperateurEg (int op) {
		return (op == EGAL || op == DIFFERENT);
	}
	
	/**
	 * Indique si notre operateur est et ou ou 
	 * @param l'operation etudiee
	 * @return boolean
	 */
	public boolean estOperateurLogique (int op) {
		return (op == ET || op == OU);
	}
	
	/**
	 *  Empiler : Permet d'orienter sur les Piles les valeurs ou les opérateurs.
	 */
	public void empiler(int i){
		switch (i)  {
		case PLUS :  pileOperateurs.add(i);
			break;
		case MOINS : pileOperateurs.add(i);
			break;
		case DIV : pileOperateurs.add(i);
			break;
		case MULT : pileOperateurs.add(i);
			break;
		case INF : pileOperateurs.add(i);
			break;
		case SUP : pileOperateurs.add(i);
			break;
		case INFOUEG : pileOperateurs.add(i);
			break;
		case SUPOUEG : pileOperateurs.add(i);
			break;
		case EGAL : pileOperateurs.add(i);
			break;
		case DIFFERENT : pileOperateurs.add(i);
			break;
		case NON : pileOperateurs.add(i);
			break;
		case NEG : pileOperateurs.add(i);
			break;
		case ET : pileOperateurs.add(i);
			break;
		case OU : pileOperateurs.add(i);
			break;
		case VRAI : pileOperandes.add(BOOLEEN);
			break;
		case FAUX : pileOperandes.add(BOOLEEN);
			break;
		case ENTIER : pileOperandes.add(ENTIER);
			break;
		case BOOLEEN : pileOperandes.add(BOOLEEN);
			break;
		default : pileOperandes.add(ERREUR);
			break;
		}
	}
	
	/**
	 * Cette methode est appelee lors de l'affectation 
	 * Elle verifie si le parametre de retour de l'instruction correspond a celui du param affecte
	 */
	public void clotureExpression(){
		int last = pileOperandes.pop();

		if (last == ERREUR){
			System.out.println("ERREUR ligne " + Yaka.ligne + " : expression incorrecte");
		}else if (typeAffectation > 0) {
			if(!(typeAffectation == last)){
				System.out.println("ERREUR ligne " + Yaka.ligne + " : lors de l'affectation");
			}else{
				Yaka.yvm.istore(offsetAffectation);
			}
		} else {
			if (last == BOOLEEN) Yaka.yvm.ecrireBool();
			else if (last == ENTIER) Yaka.yvm.ecrireEnt();
			else{}
		}
		
		typeAffectation = 0;
		offsetAffectation = 0;
	}
	
	/**
	 * Cette methode verifie si le type de retour est un booleen
	 */
	public void retourneBooleen() {
		int last = pileOperandes.pop();
		if ( last != BOOLEEN ) {
			System.out.println("ERREUR ligne " + Yaka.ligne + " : il faut une expression booleene");
	
		}
	}
	
	/**
	 * Cette fonction permet de sauvegarder le type de la variable lors de l'affectation
	 * @param le type de la variable
	 */
	public void setTypeAffectation(int a){typeAffectation = a;}
	
	/**
	 * Cette fonction permet de sauvegarder l'offset de la variable lors de l'affectation
	 * @param l'offset de la variable
	 */
	public void setOffsetAffectation(int a){offsetAffectation = a;}
	
	
	/****************** Conditionnelle et Iteration *******************/
	/**
	 * Cette methode est appelee lors du test de la condition de boucle ou de si
	 */
	public void clotureIterationCondition() {
		int last = pileOperandes.pop();
		if (last != BOOLEEN){
			System.out.println("ERREUR ligne " + Yaka.ligne + " : la condition ne renvoit pas un booleen");
		}
	}

	
	
	
	
	/*************************** Fonction ******************************/
	/** 
	 * Verifie si la valeur de retour d'une fonction est correcte
	 */
	public void testRetourne() {
		int type = pileOperandes.pop();
		int typeRetour = fonction.getResultat();
		if(type == typeRetour){
		}
		else{
			System.out.println("ERREUR ligne " + Yaka.ligne + " : le type de retour de la fonction n'est pas correct");
		}
	}
	
	/**
	 * Lors de la declaration d'une fonction, cette mettode permet lors du testRetourne 
	 * de savoir dans quelle fonction nous sommes
	 * @param f
	 */
	public void setFonction(IdFonction f){
		fonction = f;
	}
	
	/**
	 * Lors de l'appel a une fonction, cette mettode permet lors du testParam
	 * de savoir dans quelle fonction nous sommes
	 * @param f
	 */
	public void setFonctionPile(IdFonction f){
		pileFonctions.push(f);
		pileCompteurs.push(0);
	}
	
	/**
	 * Cette metode permet de tester si les parametres passes lors d'un appel a une fonction sont correctes
	 */
	public void testParam(){
		IdFonction id = pileFonctions.peek();
		if(id != null){ // Dans le cas ou la fonction n'existe pas
			int compteur = pileCompteurs.pop() + 1;
			pileCompteurs.push(compteur);
			int typeEnCours = pileOperandes.pop();
			int typeParam = pileFonctions.peek().getParam(compteur-1);
			if(typeEnCours != typeParam){
				System.out.println("ERREUR ligne " + Yaka.ligne + " : le type du parametre numero " + compteur + " ne correspond pas a celui de la fonction" );
			}
		}
	}
	
	/**
	 * Fonction intervenant apres la fin de l'appel d'une fonction dans le programme
	 * Verifie si le nombre de parametres est bon et empile son type sur la pile des operandes
	 */
	public void clotureFonction(){
		IdFonction id = pileFonctions.peek();
		if(id != null){ // Dans le cas ou la fonction n'existe pas
			int compteur = pileCompteurs.pop();
			int nbParam = pileFonctions.peek().getTaille();
			if (compteur != nbParam) {
				System.out.println("ERREUR ligne " + Yaka.ligne + " : Le nombre d'arguments de la fonction doit etre egal a " + nbParam);
			}
			int type = pileFonctions.peek().getResultat();
			pileOperandes.push(type);
		} else {
			pileOperandes.push(ERREUR);
		}
	}
	
	/**
	 * @return le nom de la fonction courante
	 */
	public String getNomFonction() {
			IdFonction fonction = pileFonctions.pop(); 
			if (fonction!=null)
				return fonction.getNom();
			else 
				return "RIEN";

	}
}