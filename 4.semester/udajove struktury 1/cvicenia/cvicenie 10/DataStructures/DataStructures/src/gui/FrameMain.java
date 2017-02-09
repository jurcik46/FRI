package gui;

import data.Person;
import datastructures.Trees.KWayTree.KWayTree;
import datastructures.fronts.Front.Front;
import datastructures.fronts.IFront;
import datastructures.fronts.Stack.Stack;
import datastructures.lists.ArrayList.ArrayList;
import datastructures.lists.LinkedList.LinkedList;
import datastructures.priorityFronts.PriorityFrontAL.PFListAL;
import datastructures.priorityFronts.PriorityFrontLL.PFListLL;

/**
 *
 * @author Michal Varga
 */
public class FrameMain extends javax.swing.JFrame {

    // New data structures should appear here.
    ArrayList<Person> aArrayList;    
    LinkedList<Person> aLinkedList;
    Front<Person> aFront;
    Stack<Person> aStack;
    PFListLL<Person> aPFListLL;
    PFListAL<Person> aPFListAL;
    KWayTree<Person> aTree;
    
    // Dialog implementing IEditData interface. Can be replaced by custom data editor.
    private final DialogEditPerson aEditorPerson;
    
    /**
     * Creates new form frameMain
     */
    public FrameMain() {
        initComponents();
        aEditorPerson = new DialogEditPerson(this,true);
        
        // Initialize data structures.
         aArrayList = new ArrayList<Person>(Person.class);
         aLinkedList = new LinkedList<Person>();
         aFront = new Front<Person>();
         aStack = new Stack<Person>();
         aPFListLL = new PFListLL<Person>();
         aPFListAL = new PFListAL<Person>();
         aTree = new KWayTree<Person>(3);
         
        // Initialize control panels with data structures.
        panelArrayList.init(aArrayList, aEditorPerson); 
        panelLinkedList.init(aLinkedList, aEditorPerson);
        panelFront.init(aFront, aEditorPerson);
        panelStack.init(aStack, aEditorPerson);
        panelPFListLL.init(aPFListLL, aEditorPerson);
        panelPFListAL.init(aPFListAL, aEditorPerson);
        panelTree.init(aTree, aEditorPerson);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        tabbedPaneMain = new javax.swing.JTabbedPane();
        tabbedPaneLists = new javax.swing.JTabbedPane();
        panelArrayList = new gui.PanelList();
        panelLinkedList = new gui.PanelList();
        panelDoubleLinkedList = new gui.PanelList();
        panelCyclicalList = new gui.PanelList();
        tabbedPaneFronts = new javax.swing.JTabbedPane();
        panelFront = new gui.PanelFront();
        panelStack = new gui.PanelFront();
        tabbedPanePriorityFronts = new javax.swing.JTabbedPane();
        panelPFListLL = new gui.PanelPriorityFront();
        panelPFListAL = new gui.PanelPriorityFront();
        tabbedPaneTrees = new javax.swing.JTabbedPane();
        panelTree = new gui.PanelTree();
        tabbedPaneTables = new javax.swing.JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tabbedPaneLists.addTab("Array list", panelArrayList);
        tabbedPaneLists.addTab("Linked list", panelLinkedList);
        tabbedPaneLists.addTab("Double linked list", panelDoubleLinkedList);
        tabbedPaneLists.addTab("Cyclical list", panelCyclicalList);

        tabbedPaneMain.addTab("Lists", tabbedPaneLists);

        tabbedPaneFronts.addTab("Front", panelFront);
        tabbedPaneFronts.addTab("Stack", panelStack);

        tabbedPaneMain.addTab("Fronts", tabbedPaneFronts);

        tabbedPanePriorityFronts.addTab("LList", panelPFListLL);
        tabbedPanePriorityFronts.addTab("AList", panelPFListAL);

        tabbedPaneMain.addTab("Priority fronts", tabbedPanePriorityFronts);

        tabbedPaneTrees.addTab("K-cestny strom", panelTree);

        tabbedPaneMain.addTab("Trees", tabbedPaneTrees);
        tabbedPaneMain.addTab("Tables", tabbedPaneTables);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPaneMain)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPaneMain, javax.swing.GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FrameMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar jProgressBar1;
    private gui.PanelList panelArrayList;
    private gui.PanelList panelCyclicalList;
    private gui.PanelList panelDoubleLinkedList;
    private gui.PanelFront panelFront;
    private gui.PanelList panelLinkedList;
    private gui.PanelPriorityFront panelPFListAL;
    private gui.PanelPriorityFront panelPFListLL;
    private gui.PanelFront panelStack;
    private gui.PanelTree panelTree;
    private javax.swing.JTabbedPane tabbedPaneFronts;
    private javax.swing.JTabbedPane tabbedPaneLists;
    private javax.swing.JTabbedPane tabbedPaneMain;
    private javax.swing.JTabbedPane tabbedPanePriorityFronts;
    private javax.swing.JTabbedPane tabbedPaneTables;
    private javax.swing.JTabbedPane tabbedPaneTrees;
    // End of variables declaration//GEN-END:variables
}
