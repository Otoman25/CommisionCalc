package commisioncalc;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.json.simple.JSONArray;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Home
 */
public class MainFrame extends javax.swing.JFrame {
    
    private final LoadSave file = new LoadSave(); //Simple JSON interface
    private ArrayList<Person> staff = new ArrayList<>();
    private Person selectedStaff = null;
    private int selectedPosition = 0;
    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        
        //staff = file.getPeople(); //Retrieve data from saved file, if not provide some dummy values and create a new file
        
        /* using test data */
        initTestData();
        
        
        updateTable();
        
        
        
        WindowListener exitListener = new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                closeWindow();
            }
        };
        addWindowListener(exitListener);
    }
    
    private void initTestData(){
        ArrayList<SimpleEntry<String,Float>> testData = new ArrayList<>();
        // Range (0) to (19,999)
        testData.add(new SimpleEntry("0 Joe Bloggs", 4627.0f));
        testData.add(new SimpleEntry("0 Bloggs Joe", 11349.0f));
        testData.add(new SimpleEntry("0 Bob Briggs", -10.0f));
        testData.add(new SimpleEntry("0 Alex Arnold", 19999.0f));
        // ------------------------------------
        
        // Range (20,000) to (30,999)
        testData.add(new SimpleEntry("1 Joe Bloggs", 22735.0f));
        testData.add(new SimpleEntry("1 Bloggs Joe", 27305.0f));
        testData.add(new SimpleEntry("1 Bob Briggs", 20000.0f));
        testData.add(new SimpleEntry("1 Alex Arnold", 30999.0f));
        // ------------------------------------
        
        // Range (31,000) to (45,999)
        testData.add(new SimpleEntry("2 Joe Bloggs", 43347.0f));
        testData.add(new SimpleEntry("2 Bloggs Joe", 43158.0f));
        testData.add(new SimpleEntry("2 Bob Briggs", 31000.0f));
        testData.add(new SimpleEntry("2 Alex Arnold", 45999.0f));
        // ------------------------------------
        
        // Range (46,000) to (59,999)
        testData.add(new SimpleEntry("3 Joe Bloggs", 51530.0f));
        testData.add(new SimpleEntry("3 Bloggs Joe", 54304.0f));
        testData.add(new SimpleEntry("3 Bob Briggs", 46000.0f));
        testData.add(new SimpleEntry("3 Alex Arnold", 59999.0f));
        // ------------------------------------
        
        // Range (60,000)+
        testData.add(new SimpleEntry("4 Joe Bloggs", 63471.0f));
        testData.add(new SimpleEntry("4 Bloggs Joe", 141732.0f));
        testData.add(new SimpleEntry("4 Bob Briggs", 60000.0f));
        testData.add(new SimpleEntry("4 Alex Arnold", 3000000000.0f));
        // ------------------------------------
        int i = 0;
        Iterator it = testData.iterator();
        while(it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
            Person person = new Person(i,(String)pair.getKey(),(float)pair.getValue());
            staff.add(person);
            i++;
        }
        
    }

    private void updateTable(){
        //Populate table with salesperson data stored in staff Arraylist
        
        int staffCount = staff.size();
        Object data[][] = new Object[staffCount][5];
        String columnHeadings[] = {"ID", "Salesperson", "Sales Total", "Commission" , "Bonus"};

        
        for(int i = 0; i < staffCount; i++){
            float totalSales = staff.get(i).getTotalSales();
            
            data[i][0] = staff.get(i).getId(); //id
            data[i][1] = staff.get(i).getName(); //name
            data[i][2] = "£" + totalSales; //sales total
            data[i][3] = staff.get(i).calculateCommission(totalSales) + "%"; //commission %
            data[i][4] = "£" + staff.get(i).calculateBonus(totalSales); // actual bonus
            
            
        }
        
        salespersonTable.setModel(new DefaultTableModel(data,columnHeadings){ // Stop user from editing table directly
        @Override
        public boolean isCellEditable(int row,int column){
            return false;
        }
        });
        
        TableColumn column = salespersonTable.getColumnModel().getColumn(0); // Shrinking ID column size
        column.setPreferredWidth(3);
        
        salespersonTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        updateTextFields();
    }
    
    private void updateTextFields(){
        //Updates text field with currently selected saleperson data, will default to empty if none selected
        if(selectedStaff != null){
            idField.setText(String.valueOf(selectedStaff.getId()));
            nameField.setText(selectedStaff.getName());
            salesField.setText("£" + String.valueOf(selectedStaff.getTotalSales()));
        } else {
            idField.setText("");
            nameField.setText("");
            salesField.setText("");
        }
    }
    
    
    
    private void closeWindow(){
        //Prepare array to be saved to disk
        JSONArray staffArray = new JSONArray();
        int staffCount = staff.size();
        
        for(int i = 0;i < staffCount; i++){
            staffArray.add(staff.get(i).exportToJSON());
        }
        
        file.saveData(staffArray);
        
        this.dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jSplitPane2 = new javax.swing.JSplitPane();
        jSplitPane3 = new javax.swing.JSplitPane();
        jSplitPane4 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        idField = new javax.swing.JTextField();
        nameField = new javax.swing.JTextField();
        salesField = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        manageSalesButton = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        editSalespersonButton = new javax.swing.JButton();
        newSalespersonButton = new javax.swing.JButton();
        deleteSalespersonButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        salespersonTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Commission calculator");
        setLocation(new java.awt.Point(500, 200));
        setResizable(false);

        jSplitPane1.setDividerLocation(120);
        jSplitPane1.setDividerSize(0);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jSplitPane2.setDividerLocation(200);
        jSplitPane2.setDividerSize(0);

        jSplitPane3.setDividerSize(0);
        jSplitPane3.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jSplitPane4.setDividerLocation(80);
        jSplitPane4.setDividerSize(0);

        jLabel1.setText("ID");

        jLabel2.setText("Name");

        jLabel3.setText("Total sales");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addContainerGap())
        );

        jSplitPane4.setLeftComponent(jPanel1);

        idField.setEditable(false);
        idField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idFieldActionPerformed(evt);
            }
        });

        nameField.setEditable(false);

        salesField.setEditable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nameField)
                    .addComponent(salesField)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(idField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 40, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(idField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(salesField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jSplitPane4.setRightComponent(jPanel2);

        jSplitPane3.setTopComponent(jSplitPane4);

        manageSalesButton.setText("Manage Sales");
        manageSalesButton.setEnabled(false);
        manageSalesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageSalesButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(manageSalesButton)
                .addContainerGap(71, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(manageSalesButton, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                .addContainerGap())
        );

        jSplitPane3.setRightComponent(jPanel3);

        jSplitPane2.setLeftComponent(jSplitPane3);

        jLabel4.setText("Salesperson Management");

        editSalespersonButton.setText("Edit salesperson");
        editSalespersonButton.setEnabled(false);
        editSalespersonButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editSalespersonButtonActionPerformed(evt);
            }
        });

        newSalespersonButton.setText("New salesperson");
        newSalespersonButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newSalespersonButtonActionPerformed(evt);
            }
        });

        deleteSalespersonButton.setText("Delete salesperson");
        deleteSalespersonButton.setEnabled(false);
        deleteSalespersonButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteSalespersonButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(editSalespersonButton)
                    .addComponent(newSalespersonButton)
                    .addComponent(deleteSalespersonButton))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {deleteSalespersonButton, editSalespersonButton, newSalespersonButton});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(editSalespersonButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(deleteSalespersonButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(newSalespersonButton)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jSplitPane2.setRightComponent(jPanel4);

        jSplitPane1.setBottomComponent(jSplitPane2);

        salespersonTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Salesperson", "Commission", "Sales Total"
            }
        ));
        salespersonTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                salespersonTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(salespersonTable);

        jSplitPane1.setLeftComponent(jScrollPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void idFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idFieldActionPerformed

    private void manageSalesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageSalesButtonActionPerformed
        //Show sales management, checks for isEdited boolean, if true then update data in ArrayList staff
        if(selectedStaff != null){
            SaleManagement history = new SaleManagement(selectedStaff);
            history.setLocation(this.getX()+10, this.getY()+10);
            history.setVisible(true);
            
            if(history.isEdited()){
                staff.set(selectedPosition, history.getSalesPerson());
                updateTable();
                salespersonTable.setRowSelectionInterval(selectedPosition, selectedPosition);
            }
        }
        
    }//GEN-LAST:event_manageSalesButtonActionPerformed

    private void editSalespersonButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editSalespersonButtonActionPerformed
        //Edit salesperson button
        if(selectedStaff != null){
            SalesPersonDialog sPerson = new SalesPersonDialog("Edit",selectedStaff.getId(),selectedStaff.getName(),selectedStaff.getTotalSales());
            sPerson.setEditableIdField(false);
            sPerson.setEditableSalesField(false);
            sPerson.setLocation(this.getX()+10, this.getY()+10);
            sPerson.setVisible(true);
            
            if(sPerson.isM_toSave()){
                staff.get(selectedStaff.getId()).setName(sPerson.getM_name());
                updateTable();
            }
        }
    }//GEN-LAST:event_editSalespersonButtonActionPerformed

    private void newSalespersonButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newSalespersonButtonActionPerformed
        //New salesperson button
        int nextId = staff.size();
        
        SalesPersonDialog sPerson = new SalesPersonDialog("New",nextId);
        
        sPerson.setEditableIdField(false);
        sPerson.setLocation(this.getX()+10, this.getY()+10);
        sPerson.setVisible(true);
        
        if(sPerson.isM_toSave()){
            staff.add(nextId,new Person(sPerson.getM_id(),sPerson.getM_name(),sPerson.getM_totalSales()));
            updateTable();
        }
    }//GEN-LAST:event_newSalespersonButtonActionPerformed

    private void salespersonTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salespersonTableMouseClicked
        JTable table = (JTable) evt.getSource();
        int row = table.getSelectedRow();
        
        selectedStaff = staff.get(row);
        selectedPosition = row;
        updateTextFields();
        
        editSalespersonButton.setEnabled(true);
        manageSalesButton.setEnabled(true);
        deleteSalespersonButton.setEnabled(true);
    }//GEN-LAST:event_salespersonTableMouseClicked

    private void deleteSalespersonButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteSalespersonButtonActionPerformed
        // If a staff member is selected then show a YES/NO dialog. If yes then delete the record from the ArrayList staff. Then update tables
        if(selectedStaff != null){
            int dialog = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete " + selectedStaff.getName(),"Warning",JOptionPane.YES_NO_OPTION);
            
            if(dialog == JOptionPane.YES_OPTION){
                staff.remove(selectedPosition);
                selectedStaff = null;
                deleteSalespersonButton.setEnabled(false);
                editSalespersonButton.setEnabled(false);
                refactorIds(selectedPosition);
                selectedPosition = 0;
                updateTable();
            }
        }
    }//GEN-LAST:event_deleteSalespersonButtonActionPerformed

    private void refactorIds(int pos){
        // After deleting a salesperson, run through ArrayList and remove any gaps by moving data back one index
        int size = staff.size();
        if(size > 0){
            if(pos > 0) pos -= 1;

            while(pos < size)  {
                staff.get(pos).setId((pos));
                pos++;
            }


            updateTable();
        }
    }
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton deleteSalespersonButton;
    private javax.swing.JButton editSalespersonButton;
    private javax.swing.JTextField idField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JSplitPane jSplitPane3;
    private javax.swing.JSplitPane jSplitPane4;
    private javax.swing.JButton manageSalesButton;
    private javax.swing.JTextField nameField;
    private javax.swing.JButton newSalespersonButton;
    private javax.swing.JTextField salesField;
    private javax.swing.JTable salespersonTable;
    // End of variables declaration//GEN-END:variables
}
