/**
 * 
 */
package xgt.easy.sys.model;

/**
 * @author Gavin
 *
 */
public enum ResourceType {
	
	LEAF(1),RESOURCE(2),OTHER(0);

	private ResourceType(int type){
		this.type=type;
	}
	private int type;
	
	public int getType(){
		return type;
	}
}
