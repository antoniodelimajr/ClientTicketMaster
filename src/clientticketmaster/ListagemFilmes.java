package clientticketmaster;

import com.google.gson.Gson;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import javax.ws.rs.core.GenericType;
import model.Filme;
import model.FilmeTableModel;
import ws.ClientFilme;

public class ListagemFilmes extends javax.swing.JInternalFrame implements ActionListener {

  private AbstractTableModel model;
  private JMenuItem menuItemAlterar;
  private JMenuItem menuItemRemove;

  public ListagemFilmes() {

    model = new FilmeTableModel();

    initComponents();

    menuItemAlterar = new JMenuItem("Alterar Filme");
    menuItemAlterar.addActionListener(this);

    menuItemRemove = new JMenuItem("Remover Filme");
    menuItemRemove.addActionListener(this);

    jPopupMenu1.add(menuItemRemove);
    jPopupMenu1.add(menuItemAlterar);

    jTable1.setComponentPopupMenu(jPopupMenu1);
  }

  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setClosable(true);
        setTitle("Listagem de Filmes");

        jTable1.setModel(model);
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Exportar JSON");
        jButton1.setActionCommand("");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 753, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

      DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy_HHmmss");
      try (FileWriter file = new FileWriter("C:/Users/antoniojr/Desktop/Filmes_" + dateFormat.format(new Date()) + ".json")) {
        FilmeTableModel ft = new FilmeTableModel();
        file.write(new Gson().toJson(ft.filmes));
        JOptionPane.showMessageDialog(this, "Arquivo JSON exportado com sucesso para a área de trabalho.");
      } catch (IOException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

  @Override
  public void actionPerformed(ActionEvent event) {
    
    JMenuItem menu = (JMenuItem) event.getSource();
    ClientFilme cliente = new ClientFilme();
    
    if (menu == menuItemRemove) {      
      cliente.remover((String) jTable1.getValueAt(jTable1.getSelectedRow(), 0));
      model = new FilmeTableModel();
      jTable1.setModel(model);

    } else if (menu == menuItemAlterar) {
      Filme filme = cliente.getPorId(new GenericType<Filme>() {}, (String) jTable1.getValueAt(jTable1.getSelectedRow(), 0));
      filme.setNome(JOptionPane.showInputDialog("Informe o novo nome: "));
      filme.setGenero(JOptionPane.showInputDialog("Informe o novo gênero: "));
      filme.setSinopse(JOptionPane.showInputDialog("Informe o novo sinopse: "));
      cliente.alterar(filme);
      model = new FilmeTableModel();
      jTable1.setModel(model);
    }
  }
}
