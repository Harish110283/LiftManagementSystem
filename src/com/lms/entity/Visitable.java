package com.lms.entity;

public interface Visitable {
	/**
	 *  Acceepts the visitor
	 * @param vistor
	 */
	public void accept(Visitor vistor);
	
}
