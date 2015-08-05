/**
 * 
 */
package xgt.easy.utils.search;

/**
 * @author Gavin
 *
 */
public enum LogicType {
	EQ("="),LT("<"),LTE("<="),GT(">"),GTE(">="),LIKE_RIGHT("like"),LIKE_FULL("like"),IGNORE("ignore");
	private String name;
	
	private LogicType(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
}
