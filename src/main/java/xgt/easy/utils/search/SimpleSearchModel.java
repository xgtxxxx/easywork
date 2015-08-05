/**
 * 
 */
package xgt.easy.utils.search;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 
 * @author Gavin
 *
 */
public abstract class SimpleSearchModel {
	/**
	 * It can get simple query hql by this method. Just suitable for 'and' not
	 * support 'or' support : like, equal, lt, gt.
	 * 
	 * @return
	 */
	public String getQueryString() {
		Class<?> clazz = this.getClass();
		StringBuffer stringBuffer = new StringBuffer();
		List<Sort> sorts = new ArrayList<Sort>();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			try {
				field.setAccessible(true);
				String key = field.getName();
				Logic logic = field.getAnnotation(Logic.class);
				LogicType type = LogicType.EQ;
				if (logic != null) {
					type = logic.type();
					if (!StringUtils.isEmpty(logic.field())) {
						key = logic.field();
					}
				}
				if (type == LogicType.IGNORE) {
					continue;
				}
				
				OrderBy orderBy = field.getAnnotation(OrderBy.class);
				if(orderBy!=null){
					sorts.add(new Sort(orderBy.index(),orderBy.derection(),key));
				}
				
				Object value = field.get(this);
				if (value == null) {
					continue;
				}
				this.build(stringBuffer, type, key, value);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		SearchFrom from = clazz.getAnnotation(SearchFrom.class);
		if(from==null){
			stringBuffer.insert(0,"from "+clazz.getSimpleName());
		}else{
			stringBuffer.insert(0,"from "+from.name());
		}
		stringBuffer = this.buildOrderBy(stringBuffer, sorts);
		return stringBuffer.toString();
	}
	
	private StringBuffer buildOrderBy(StringBuffer stringBuffer,List<Sort> sorts){
		stringBuffer.append(" order by ");
		Collections.sort(sorts);
		for (int i = 0; i<sorts.size(); i++) {
			if(i>0){
				stringBuffer.append(" , ");
			}
			Sort sort = sorts.get(i);
			stringBuffer.append(sort.getField()).append(" ").append(sort.getDerection());
		}
		return stringBuffer;
	}

	private StringBuffer build(StringBuffer stringBuffer, LogicType type,
			String key, Object value) {
		if (stringBuffer.length() <= 0) {
			stringBuffer.append(" where ");
		} else {
			stringBuffer.append(" and ");
		}
		stringBuffer.append(key).append(" ").append(type.getName()).append(" ");
		switch (type) {
		case LIKE_RIGHT:
			return stringBuffer.append("'").append(value).append("%").append("'");
		case LIKE_FULL:
			return stringBuffer.append("'").append("%").append(value).append("%").append("'");
		default:
			return stringBuffer.append("'").append(value).append("'");
		}

	}
	
	class Sort implements Comparable<Sort>{
		/**
		 * @param index
		 * @param derection
		 * @param field
		 */
		public Sort(int index, String derection, String field) {
			super();
			this.index = index;
			this.derection = derection;
			this.field = field;
		}
		private int index;
		private String derection;
		private String field;
		/**
		 * @return the index
		 */
		public int getIndex() {
			return index;
		}
		/**
		 * @param index the index to set
		 */
		public void setIndex(int index) {
			this.index = index;
		}
		/**
		 * @return the derection
		 */
		public String getDerection() {
			return derection;
		}
		/**
		 * @param derection the derection to set
		 */
		public void setDerection(String derection) {
			this.derection = derection;
		}
		/**
		 * @return the field
		 */
		public String getField() {
			return field;
		}
		/**
		 * @param field the field to set
		 */
		public void setField(String field) {
			this.field = field;
		}
		@Override
		public int compareTo(Sort o) {
			return this.index-o.index;
		}
		
	}
}
