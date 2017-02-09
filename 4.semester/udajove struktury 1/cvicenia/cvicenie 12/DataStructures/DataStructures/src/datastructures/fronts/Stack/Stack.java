/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures.fronts.Stack;

import datastructures.fronts.AbsFront;
import datastructures.fronts.EFront;
import datastructures.fronts.IFront;
import datastructures.lists.EList;
import datastructures.lists.LinkedList.LinkedList;
import java.util.Iterator;

/**
 *  LIFO
 * @author sisila
 */
public class Stack<E> extends AbsFront<E> implements IFront<E>{
    
    public Stack(){
        super(new LinkedList());
    }

    /**
     * zlozitost O(1)
     * @param paElement
     * @throws EFront 
     */
    @Override
    public void push(E paElement) throws EFront {
        try{
            aList.add(paElement);   //pridavame na zaciatok
        } catch (EList ex) {
            throw new EFront(ex.getMessage());
        }
    }

    /**
     * zlozitost O(1)
     * @return
     * @throws EFront 
     */
    @Override
    public E pop() throws EFront {
        try{
            return aList.deleteFromIndex(size() -1);    //vymazavame z konca
        } catch (EList ex) {
            throw new EFront(ex.getMessage());
        }
    }

    /**
     * zlozitost O(n)
     * @return
     * @throws EFront 
     */
    @Override
    public E peek() throws EFront {
        try {
            return aList.get(size() -1);    //kukneme na koniec
        } catch (EList ex) {
            throw new EFront(ex.getMessage());
        }
    }
    
    //iterator sa nachadza v triede AbsFront, lebo iterator pre Front a Stack je rovnaky
}
