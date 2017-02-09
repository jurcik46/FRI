package gui;

import java.awt.BorderLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import org.jfree.chart.ChartPanel;

/**
 *
 * @author Michal Varga
 */
public class PanelOperations extends javax.swing.JPanel {

    private class Operation {
        
        private final String aCaption;
        private long aInterval;
        private long aTime;              
        
        public Operation(String paCaption) {
            aCaption = paCaption;
            aInterval = 0;
            aTime = 0;
        }
        
        public void start() {
            aTime = 0;
            aInterval = System.nanoTime();        
        }                

        public long finish(String paMessage) {         
            long endTime = aInterval == 0 ? 0 : System.nanoTime();
            return (endTime - aInterval + aTime)/TIME_PRECISION;            
        }
        
        public void pause() {
            long endTime = System.nanoTime();
            aTime += endTime - aInterval;
            aInterval = 0;
        }

        public void resume() {
            aInterval = System.nanoTime();
        }
        
        public void abort() {
            aTime = 0;
            aInterval = 0;            
        }
                
    }
    
    public static final int TIME_PRECISION = 1000000;
    public static final String UNITS = "ms";        
    
    private final HashMap<Integer,Operation> aOperations;
    private final Chart aChart;
    
    /**
     * Creates new form panelOperations
     */
    public PanelOperations() {
        initComponents();
        aOperations = new HashMap<Integer,Operation>();        
        aChart = new Chart();
        
        panelChart.setLayout(new java.awt.BorderLayout());
        panelChart.add(new ChartPanel(aChart.getChart()),BorderLayout.CENTER);
        panelChart.validate();               
    }    
    
    public void registerOperation(int paKey, String paCaption) {
        aOperations.put(paKey, new Operation(paCaption));
    }
    
    public void start(int paOperation) {
        aOperations.get(paOperation).start();
    }

    public long finish(int paOperation, boolean paLog) { 
        return finish(paOperation,"",paLog);
    }

    public long finish(int paOperation, String paMessage, boolean paLog) {    
        Operation op = aOperations.get(paOperation);        
        long result = op.finish(paMessage);
        if(paLog) {
            String msg = "".equals(paMessage) ? "" : " ans = " + paMessage;
            log(op.aCaption + " finished in " + Long.toString(result) + "ms." + msg);
        }
        return result;
    }

    public void pause(int paOperation) {
        aOperations.get(paOperation).pause();
    }

    public void resume(int paOperation) {
        aOperations.get(paOperation).resume();
    }

    public void abort(int paOperation, boolean paLog) {
        Operation op = aOperations.get(paOperation);
        op.abort();
        if(paLog)
            log(op.aCaption + " aborted.");
    }

    public void except(int paOperation, Exception paException, boolean paLog) {
        Operation op = aOperations.get(paOperation);
        op.abort();
        if(paLog)
            log(op.aCaption + " raised an exception [" + paException.getMessage() + "].");
    }
    
    public void log(String paMessage) {
        String time = (new SimpleDateFormat("HH:mm:ss")).format(Calendar.getInstance().getTime());
        textOperations.setText(textOperations.getText() + "\n[" + time + "] " + paMessage);
    }    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblOperations = new javax.swing.JLabel();
        tabbedPaneOperations = new javax.swing.JTabbedPane();
        textPaneOperations = new javax.swing.JScrollPane();
        textOperations = new javax.swing.JTextArea();
        panelChart = new javax.swing.JPanel();

        lblOperations.setText("Operations");

        textOperations.setColumns(20);
        textOperations.setRows(5);
        textPaneOperations.setViewportView(textOperations);

        tabbedPaneOperations.addTab("Text", textPaneOperations);

        javax.swing.GroupLayout panelChartLayout = new javax.swing.GroupLayout(panelChart);
        panelChart.setLayout(panelChartLayout);
        panelChartLayout.setHorizontalGroup(
            panelChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 395, Short.MAX_VALUE)
        );
        panelChartLayout.setVerticalGroup(
            panelChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 347, Short.MAX_VALUE)
        );

        tabbedPaneOperations.addTab("Chart", panelChart);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblOperations)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(tabbedPaneOperations)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lblOperations)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabbedPaneOperations))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblOperations;
    private javax.swing.JPanel panelChart;
    private javax.swing.JTabbedPane tabbedPaneOperations;
    private javax.swing.JTextArea textOperations;
    private javax.swing.JScrollPane textPaneOperations;
    // End of variables declaration//GEN-END:variables
}
