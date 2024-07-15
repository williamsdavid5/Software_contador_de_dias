/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Telas;

import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;

/**
 *
 * @author david
 */
public class TelaAdicionarObjetivo extends javax.swing.JFrame {

    /**
     * Creates new form TelaAdicionarObjetivo
     */
    
    private Objetivo objetivo;
    private ArrayList<Integer> metas;
    private TelaInicial telaInicial;
    private boolean editar;
    
    public TelaAdicionarObjetivo(TelaInicial telaInicial) {
        initComponents();
        this.telaInicial = telaInicial;
        
        metas = new ArrayList<>();

    }
    
    public TelaAdicionarObjetivo(Objetivo objetivo) {
        initComponents();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        campoNomeObjetivo = new javax.swing.JTextField();
        metasLabel = new javax.swing.JLabel();
        botaoAdicionarMeta = new javax.swing.JButton();
        botaoSalvarMeta = new javax.swing.JButton();
        botaoRemoverMeta = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Objetivo");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Objetivo:");

        metasLabel.setText("Metas: Sem metas definidas");

        botaoAdicionarMeta.setText("+");
        botaoAdicionarMeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAdicionarMetaActionPerformed(evt);
            }
        });

        botaoSalvarMeta.setText("Salvar");
        botaoSalvarMeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSalvarMetaActionPerformed(evt);
            }
        });

        botaoRemoverMeta.setText("-");
        botaoRemoverMeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoRemoverMetaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(botaoSalvarMeta))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(campoNomeObjetivo, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(botaoAdicionarMeta)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(botaoRemoverMeta)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(metasLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoNomeObjetivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(metasLabel)
                    .addComponent(botaoAdicionarMeta, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(botaoRemoverMeta, javax.swing.GroupLayout.PREFERRED_SIZE, 17, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoSalvarMeta)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botaoAdicionarMetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAdicionarMetaActionPerformed
        
        String entrada = JOptionPane.showInputDialog(null, "Quantos dias?");
        
        try {
            int dias = Integer.parseInt(entrada);
            metas.add(dias);
            String label = "Metas:";
            
            Iterator iterator = metas.iterator();
            
            while (iterator.hasNext()){
                label = label + " -> " +(String.valueOf(iterator.next()));
            }
            
            metasLabel.setText(label);
            
        } catch (java.lang.NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Entrada inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_botaoAdicionarMetaActionPerformed

    private void botaoSalvarMetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarMetaActionPerformed
        
        int certeza = JOptionPane.showConfirmDialog(null, "Tem certeza?");
        
        if (certeza == 0){
            try {
                String nomeObjetivo = campoNomeObjetivo.getText();

                if (nomeObjetivo.trim().equals("")){
                    System.out.println("campo vazio!");
                } else {

                    if (metas.isEmpty()){
                        objetivo = new Objetivo(nomeObjetivo);
                    } else {

                        int diasMeta[] = metas.stream().mapToInt(Integer::intValue).toArray();

                        objetivo = new Objetivo(diasMeta, nomeObjetivo);
                    }
                    ArrayList<Objetivo> objetivos = telaInicial.getObjetivos();
                    objetivos.add(objetivo);
                    
                    Dados dados = telaInicial.getDados();
                    dados.setObjetivos(objetivos);
                    
                    telaInicial.setDados(dados);
                    telaInicial.setObjetivos(objetivos);
                    
                    telaInicial.dados.serializar(dados);
                    telaInicial.inserirNaTabela(dados);
                }

            } catch (Exception e){
                System.out.print(e);
            }
        }  
    }//GEN-LAST:event_botaoSalvarMetaActionPerformed

    private void botaoRemoverMetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoRemoverMetaActionPerformed

        try {
            metas.removeLast();
            
            String label = "Metas:";
            
            Iterator iterator = metas.iterator();
            
            while (iterator.hasNext()){
                label = label + " -> " +(String.valueOf(iterator.next()));
            }
            
            if (metas.isEmpty()){
                metasLabel.setText("Metas: Sem metas definidas");
            } else {
                metasLabel.setText(label);
            }
            
        } catch (Exception e){
        
        }
        
    }//GEN-LAST:event_botaoRemoverMetaActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        telaInicial.enable(true);
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        try {
            //UIManager.setLookAndFeel(new FlatMacDarkLaf());
            com.formdev.flatlaf.intellijthemes.FlatMaterialDesignDarkIJTheme.setup();
 
        } catch (Exception e){
            e.printStackTrace();
        }
        
        TelaInicial telaInicial = new TelaInicial();
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaAdicionarObjetivo(telaInicial).setVisible(true);
            }
        });
    }

    public void editarObjetivo(Objetivo objetivo, int indice) {
        this.objetivo = objetivo; //primeiramente define o objetivo que esta aqui com os dados do objetivo recebido da tela inicial
        campoNomeObjetivo.setText(objetivo.getNome()); //e ja podemos inserir seu nome no campo
        
        int [] diasMeta = objetivo.getDiasMeta(); //tiramos suas informações de meta salvas (mas e se não tiver? futuramente vemos isso...)
        
        metas = new ArrayList<>();
        String label = "Metas: ";
        
        //percorre esses objetivos e ja adicionda a lista de dias que temos aqui
        for(int i = 0; i<diasMeta.length; i++){
            metas.add(diasMeta[i]);
            
            //ao mesmo tempo, atualizamos a label que exibe essas informações
            label = label + " -> " + diasMeta[i];
        }
        metasLabel.setText(label);
        
    }

    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAdicionarMeta;
    private javax.swing.JButton botaoRemoverMeta;
    private javax.swing.JButton botaoSalvarMeta;
    private javax.swing.JTextField campoNomeObjetivo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel metasLabel;
    // End of variables declaration//GEN-END:variables
}
