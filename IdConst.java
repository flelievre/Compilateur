public class IdConst extends Ident {
	protected int val;
	
	/**
	 * Constructeur d'un IdConst
	 * @param t le type de l'Id
	 * @param v la valeur de l'Id
	 */
	public IdConst (int t, int v){
		super();
		val = v;
		type = t;
	}

	/**
	 * @return la valeur de l'IdConst
	 */
	public int getVal() {
		return val;
	}	
	
	/**
	 * Methode d'appel a la fonction iconst de YVM
	 */
	public void yvm() {
		Yaka.yvm.iconst(val);
	}
	
	public String toString() {
		return "( type : " + type + " ; val : " + val + ")";
	}
}
