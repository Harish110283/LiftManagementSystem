package com.lms.entity;

public interface Visitor {
	/**
	 * visits the Floor.
	 * @param visitable
	 */
	public void visit(Visitable visitable);

}
