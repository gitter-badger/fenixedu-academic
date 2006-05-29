/*
 * Created on 7/Nov/2003
 * 
 * To change the template for this generated file go to Window - Preferences -
 * Java - Code Generation - Code and Comments
 */
package net.sourceforge.fenixedu.domain.teacher;

import net.sourceforge.fenixedu.domain.RootDomainObject;


/**
 * @author Leonor Almeida
 * @author Sergio Montelobo
 * 
 */
public class Category extends Category_Base implements Comparable {

	public Category() {
		super();
		setRootDomainObject(RootDomainObject.getInstance());
	}

    public int compareTo(Object o) {
        Category category = (Category) o;
        final int weightCompare = this.getWeight().compareTo(category.getWeight()); 
        return weightCompare == 0 ? this.getLongName().compareTo(category.getLongName()) : weightCompare;
    }

}
