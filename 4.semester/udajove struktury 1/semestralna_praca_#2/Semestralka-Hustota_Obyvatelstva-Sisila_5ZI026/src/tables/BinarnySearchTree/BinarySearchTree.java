/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tables.BinarnySearchTree;


import java.util.Iterator;
import tables.ETable;
import tables.ITable;
import tables.Sorts.KeyComparer;
import tables.TablePair;



    
/**
 *
 * @author sisila
 */
public class BinarySearchTree<K,E> implements ITable<K,E>{
    
    private int aSize;
    protected BSTNode<K,E> aRoot;
    private KeyComparer<K> aComparer;
    
    public BinarySearchTree(KeyComparer<K> paComparer){
        aSize = 0;
        aComparer = paComparer;
    }

    private BSTNode<K,E> findNode(K paKey, boolean paAlwaysReturnNode) throws ETable{
        if(isEmpty())
            return null;
        
        BSTNode<K,E> node = aRoot;
        
        while(node != null && !aComparer.areEqual(paKey, node.getKey())){
            if(aComparer.firstIsLess(paKey, node.getKey())){
                if(paAlwaysReturnNode && node.getLeft() == null)
                    return node;
                node = node.getLeft();  //ak si mensi, ides do lava
            }
            else{
                if(paAlwaysReturnNode && node.getRight() == null)
                    return node;
                node = node.getRight(); //ak si vacsi, 
            }
        }
        
        return node;
    }
    
    private void joinLeft(BSTNode<K,E> paParent, BSTNode<K,E> paChild) throws ETable{
        if(paParent.getLeft() != null)
            throw new ETable("uz mam laveho");
        detach(paChild);
        paParent.setLeft(paChild);
        paChild.setParent(paParent);
    }
    
    private void joinRight(BSTNode<K,E> paParent, BSTNode<K,E> paChild) throws ETable{
        if(paParent.getRight() != null)
            throw new ETable("uz mam praveho");
        
        detach(paChild);
        paParent.setRight(paChild);
        paChild.setParent(paParent);
    }
    
    private void detach(BSTNode<K,E> paChild){
        if(!paChild.isRoot())
            if(paChild.isLeft() )
                paChild.getParent().setLeft(null);
            else
                paChild.getParent().setRight(null);

            paChild.setParent(null);
    }

    @Override
    public void insert(K paKey, E paElement) throws ETable {
        BSTNode<K,E> newNode = new BSTNode<>(paKey, paElement);
        
        if(isEmpty() ){
            aRoot = newNode;
        }
        else{
            BSTNode<K,E> parent = findNode(paKey, true);    //alwaysreturnnode musi vzdy vratit uzol, preto true
            if(!aComparer.areEqual(paKey, parent.getKey() )) {
                if(aComparer.firstIsGreater(paKey, parent.getKey() ))
                    //vacsie prvky su vzdy vpravo
                    joinRight(parent, newNode);
                else
                    //mensie su vzdy vlavo
                    joinLeft(parent, newNode);
            }
            else{
                throw new ETable("kluc nie je unikatny");
            }
        }
        aSize++;
    }
    
    @Override
    public E delete(K paKey) throws ETable {
        BSTNode<K,E> vymazany;
        if(containsKey(paKey)){
            vymazany = findNode(paKey, false);
            extract(vymazany);
            return vymazany.getElement();
        }
        else{
            return null;
        }
    }
    
    @Override
    public E find(K paKey) throws ETable {
        return findNode(paKey, false).getElement();
    }

    @Override
    public void modify(K paKey, E paNewElement) throws ETable {
        findNode(paKey, false).setElement(paNewElement);
    }

    @Override
    public boolean containsKey(K paKey) throws ETable {
        return findNode(paKey, false) != null;
    }

    @Override
    public boolean isEmpty() throws ETable {
        return aSize == 0 || aRoot == null;
    }

    @Override
    public int size() throws ETable {
        return aSize;
    }

    @Override
    public void clear() throws ETable {
        aRoot = null;
    }

    @Override
    public Iterator<TablePair<K, E>> iterator() {
        return new BSTIterator<>(this);
    }
    
    private void extract(BSTNode<K,E> paNode) throws ETable{
        try{
            //ak som list, oddel sa od predka
            //if(paNode.degreeOfNode() == 0){
            if(!paNode.hasLeft() && !paNode.hasRight()){
                detach(paNode);
                paNode = null;
                aSize--;
            }

            //ak ma vymazavany uzol jedneho potomka, na rovnake miesto posun prave tohto potomka
            //if(paNode.degreeOfNode() == 1){
            else if((paNode.hasRight() && !paNode.hasLeft() ) || (paNode.hasLeft() && !paNode.hasRight() )){
                //uzol je lavy potomok predka
                if(paNode.isLeft() ){
                    if(paNode.hasLeft() ){
                        joinLeft(paNode.getLeft(), paNode.getParent());
                        aSize--;
                    }
                    if(paNode.hasRight()){
                        joinLeft(paNode.getRight(), paNode.getParent());
                        aSize--;
                    }
                }
                //uzol je pravy potomok predka
                else{
                    if(paNode.hasLeft() ){
                        joinRight(paNode.getLeft(), paNode.getParent());
                        aSize--;
                    }
                    if(paNode.hasRight()){
                        joinRight(paNode.getRight(), paNode.getParent());
                        aSize--;
                    }
                }
            }

            //ak ma vymazavany uzol dvoch potomkov zober bud najpravejsieho zlava alebo najlavejsieho sprava
            else{
                while(!paNode.hasRight()){
                    //chod dolava
                    if(paNode.getRight() != null){
                        joinLeft(paNode.getParent(), paNode.getRight());
                        aSize--;
                    }
                    else{
                        joinLeft(paNode.getParent(), paNode.getLeft());
                        aSize--;
                    }

                    //chod doprava
                    if(paNode.getLeft() != null){
                        joinRight(paNode.getParent(), paNode.getLeft());
                        aSize--;
                    }
                    else{
                        joinRight(paNode.getParent(), paNode.getRight());
                        aSize--;
                    }
                }

            }
        
        }catch(ETable ex){
            throw new ETable("chyba extrakcie" + ex.getMessage());
        }
    }
}