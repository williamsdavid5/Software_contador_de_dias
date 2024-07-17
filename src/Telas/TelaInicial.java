/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Telas;


import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
/**
 *
 * @author david
 */
public class TelaInicial extends javax.swing.JFrame {
    Dados dados;
    ArrayList<Objetivo> objetivos;
    LocalDate dataDoSoftware;
    /**
     * Creates new form TelaInicial
     */
    public TelaInicial() {
        initComponents();
        this.dados = new Dados();
        objetivos = new ArrayList<>();
        
        try {
            resgatarDados();
            
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    //método para resgatar os dados salvos no pc, se existirem
    //também para preencher a tabela
    public void resgatarDados(){
        
        try {
            dados = dados.desserializar();
            objetivos = dados.getObjetivos();
            
            this.dataDoSoftware = dados.getDataDoSoftware();
            
            LocalDate dataAtual  = LocalDate.now();
            
            if (!(dataDoSoftware.equals(dataAtual)) && dataAtual.isAfter(dataDoSoftware)){
                long diferencaDeDias = ChronoUnit.DAYS.between(dataDoSoftware, dataAtual);
                atualizarObjetivos(diferencaDeDias);
                
            } else if (!(dataDoSoftware.equals(dataAtual)) && dataDoSoftware.isAfter(dataAtual)) {
                String dataSalva = dataDoSoftware.getDayOfMonth() + "/" + dataDoSoftware.getMonthValue() + "/" + dataDoSoftware.getYear();
                JOptionPane.showMessageDialog(null, "Algo está errado no seu calendário! Data salva pelo software: " + dataSalva);
                System.exit(0);
            }
            
            labelData.setText("Data: " +dataDoSoftware.getDayOfMonth() + "/" + dataDoSoftware.getMonthValue() + "/" + dataDoSoftware.getYear());
            inserirNaTabela(dados);
            
            if (dados.getObjetivos().isEmpty()){
                atualizarInformacoes(0, 0, "");
                progressoLabel.setText("-");
                
            } else {
                Objetivo primeiro = dados.getObjetivos().getFirst();

                if (primeiro.getDiasMeta().isEmpty()){
                    atualizarInformacoes(primeiro.getDiasPassados(), 0, primeiro.getNome());
                } else {
                    atualizarInformacoes(primeiro.getDiasPassados(), primeiro.getDiasMeta().get(primeiro.getMetaAtual()), primeiro.getNome());
                }
            }
            
            //atualizarInformacoes(primeiro.getDiasPassados(), primeiro.getDiasMeta().get(primeiro.getMetaAtual()), primeiro.getNome());
            
        } catch (java.lang.NullPointerException e){
            //System.out.println(e);
            
        } catch (Exception e){
            //System.out.print(e);
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados. Sinto muito, mas seus dados foram excluídos...\nErro: " + e, "Erro", JOptionPane.ERROR_MESSAGE);
            dados.excluirDados();

            DefaultTableModel modeloTabela = (DefaultTableModel) tabela.getModel();
            modeloTabela.setRowCount(0);

            atualizarInformacoes(0, 0, " ");
            progressoLabel.setText("-");
        }
    }
    
    //este metodo deve percorrer todos os objetivos cadastrados
    public void atualizarObjetivos(long diferencaDeDias){
        Iterator iterator = objetivos.iterator();
        ArrayList<Objetivo> objetivosAtualizados = new ArrayList<>();
        
        while (iterator.hasNext()){
            Objetivo atual = (Objetivo) iterator.next();
            
            String nomeObjetivo = atual.getNome();
            
            int opcao = JOptionPane.showConfirmDialog(null, "Você manteve seu objetivo \"" + nomeObjetivo + "\" durante esses " + String.valueOf(diferencaDeDias) + " dias?", "Seja sincero!", JOptionPane.YES_NO_OPTION);
            
            if (opcao == 0){
                atual.setDiasPassados(atual.getDiasPassados() + (int) diferencaDeDias);
                objetivosAtualizados.add(atual);
                
            } if (opcao == 1){
                atual.setDiasPassados(0);
                objetivosAtualizados.add(atual);
                JOptionPane.showMessageDialog(null, "Seu progresso foi zerado nesse objetivo! Me desulpe, mas é para o seu bem...");
            }
        }
        
        objetivos = objetivosAtualizados;
        dados.setObjetivos(objetivosAtualizados);
        dados.setDataDoSoftware(LocalDate.now());
        dados.serializar(dados); ////////////////////////////////////////////////////////////////////////////
    }
    
    public void atualizarInformacoes(int diasPassados, int proximaMeta, String nome){
        //System.out.print(proximaMeta);
        
        try {
            double progresso = ((double) diasPassados / proximaMeta) * 100;
            jProgressBar1.setValue((int) progresso);
            metaLabel.setText("Objetivo: " + nome);
            
            if (nome.trim().equals("")){
                progressoLabel.setText("-");
            } else {
                progressoLabel.setText(String.valueOf(diasPassados) + " Dias");
            }

            if (proximaMeta == 0){
                proximaMetaLabel.setText("-");
            } else {
                proximaMetaLabel.setText(String.valueOf(proximaMeta));
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao atualizar informações.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    public void inserirNaTabela(Dados dados){
        
        DefaultTableModel modeloTabela = (DefaultTableModel) tabela.getModel();
        modeloTabela.setRowCount(0);
        
        if (!dados.getObjetivos().isEmpty()){
            objetivos = dados.getObjetivos();

            Iterator iterator = objetivos.iterator();


            while (iterator.hasNext()){
                Objetivo atual = (Objetivo) iterator.next();

                String nome = atual.getNome();
                Integer diasPassados = atual.getDiasPassados();

                try {
                    Integer proximaMeta = atual.getDiasMeta().get(atual.getMetaAtual());
                    modeloTabela.addRow(new Object [] {nome, diasPassados, proximaMeta});
                } catch (Exception e){
                    modeloTabela.addRow(new Object [] {nome, diasPassados, "-"});
                }
            } 
        }
        /*
        metaLabel.setText("Objetivo: ");
        progressoLabel.setText("-");
        proximaMetaLabel.setText("-");
*/
    }
    
    public void salvarDados(){
        this.dados.setObjetivos(this.objetivos);
        dados.serializar(dados);
    }
    
    public void salvarDados(String caminho){
        this.dados.setObjetivos(this.objetivos);
        dados.serializar(dados, caminho);
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
        jLabel3 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        metaLabel = new javax.swing.JLabel();
        proximaMetaLabel = new javax.swing.JLabel();
        progressoLabel = new javax.swing.JLabel();
        labelData = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        botaoNovo = new javax.swing.JButton();
        botaoEditar = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        carregarDadosMenu = new javax.swing.JMenuItem();
        excluirDadosMenu = new javax.swing.JMenuItem();
        salvarDadosMenu = new javax.swing.JMenuItem();
        ajudaMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Bem vindo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jLabel3.setText("Progresso:");

        jProgressBar1.setStringPainted(true);

        metaLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        metaLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        metaLabel.setText("Objetivo:");

        proximaMetaLabel.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        proximaMetaLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        proximaMetaLabel.setText("-");
        proximaMetaLabel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Próximo", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));

        progressoLabel.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        progressoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        progressoLabel.setText("-");
        progressoLabel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Atual", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));

        labelData.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelData.setText("Data:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(metaLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(progressoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(proximaMetaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelData, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(metaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(progressoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(proximaMetaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(labelData))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Metas atuais", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Objetivo", "Dias passados", "Próxima meta"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabela.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tabela.setName(""); // NOI18N
        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabela);

        botaoNovo.setText("Novo");
        botaoNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoNovoActionPerformed(evt);
            }
        });

        botaoEditar.setText("Editar");
        botaoEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoEditarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(botaoEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoNovo)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoNovo)
                    .addComponent(botaoEditar)))
        );

        botaoEditar.setVisible(false);

        jMenu1.setText("Arquivo");

        carregarDadosMenu.setText("Carregar dados de...");
        carregarDadosMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                carregarDadosMenuActionPerformed(evt);
            }
        });
        jMenu1.add(carregarDadosMenu);

        excluirDadosMenu.setText("Excluir dados");
        excluirDadosMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excluirDadosMenuActionPerformed(evt);
            }
        });
        jMenu1.add(excluirDadosMenu);

        salvarDadosMenu.setText("Salvar dados em...");
        salvarDadosMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvarDadosMenuActionPerformed(evt);
            }
        });
        jMenu1.add(salvarDadosMenu);

        jMenuBar1.add(jMenu1);

        ajudaMenu.setText("Ajuda");
        ajudaMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ajudaMenuMouseClicked(evt);
            }
        });
        jMenuBar1.add(ajudaMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMouseClicked
        botaoEditar.setVisible(true);
        
        int selecionado = tabela.getSelectedRow();
        String nome = (String) tabela.getValueAt(selecionado, 0);
        int diasPassados = (int) tabela.getValueAt(selecionado, 1);
        
        try {
            int proximaMeta = (int) tabela.getValueAt(selecionado, 2);
            atualizarInformacoes(diasPassados, proximaMeta, nome);
        } catch (java.lang.ClassCastException e){
            int proximaMeta = 0;
            atualizarInformacoes(diasPassados, proximaMeta, nome);
        }
    }//GEN-LAST:event_tabelaMouseClicked

    private void carregarDadosMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_carregarDadosMenuActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("bin", "bin");
        fileChooser.setFileFilter(filtro);
        
        int selecao = fileChooser.showOpenDialog(this);
        
        if (selecao == JFileChooser.APPROVE_OPTION){
            java.io.File dado = fileChooser.getSelectedFile();
            
            try (FileInputStream fileIn = new FileInputStream(dado); ObjectInputStream in = new ObjectInputStream(fileIn)){
                this.dados = (Dados) in.readObject();
                
                int substituir = javax.swing.JOptionPane.showConfirmDialog(null, "Importar dados do disco irá substituir todos os seus dados já salvos. Substituir dados existentes? (Muito cuidado!)", "Importar", JOptionPane.YES_NO_OPTION);
                
                if (substituir == 0){
                    dados.serializar(dados);
                }
                
                this.objetivos = dados.getObjetivos();
                
                inserirNaTabela(dados);
                
                Objetivo primeiro = dados.getObjetivos().getFirst();
                
                if (primeiro.getDiasMeta().isEmpty()){
                    atualizarInformacoes(primeiro.getDiasPassados(), 0, primeiro.getNome());
                } else {
                    atualizarInformacoes(primeiro.getDiasPassados(), primeiro.getDiasMeta().get(primeiro.getMetaAtual()), primeiro.getNome());
                }
                
                //atualizarInformacoes(primeiro.getDiasPassados(), primeiro.getDiasMeta().get(primeiro.getMetaAtual()), primeiro.getNome());
                
            } catch (Exception e){
                javax.swing.JOptionPane.showMessageDialog(null, "Atenção", "Erro: " + e, ERROR);
                //javax.swing.JOptionPane.showMessageDialog(null, "Atenção", "Erro na leitura!", ERROR);

            }
        }
        
    }//GEN-LAST:event_carregarDadosMenuActionPerformed

    private void excluirDadosMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excluirDadosMenuActionPerformed
        int opcao = javax.swing.JOptionPane.showConfirmDialog(null, "Exclior todos os dados existentes? (Muito cuidado!)","Excluir",JOptionPane.YES_NO_OPTION);
        
        try {
            if (opcao == JFileChooser.APPROVE_OPTION){
                dados.excluirDados();
                this.dados = new Dados();
                this.objetivos = new ArrayList<>();
                //resgatarDados();
                
                DefaultTableModel modeloTabela = (DefaultTableModel) tabela.getModel();
                modeloTabela.setRowCount(0);

                atualizarInformacoes(0, 0, " ");
                progressoLabel.setText("-");
                botaoEditar.setVisible(false);
            }

        } catch (java.lang.NullPointerException e){
            JOptionPane.showMessageDialog(null, "Não há dados a serem excluídos.");
        } catch (Exception e){
            javax.swing.JOptionPane.showMessageDialog(null, "Atenção", "Erro: " + e, ERROR);
        }

    }//GEN-LAST:event_excluirDadosMenuActionPerformed

    private void salvarDadosMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvarDadosMenuActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        int selecao = fileChooser.showOpenDialog(null);
        
        if (selecao == JFileChooser.APPROVE_OPTION){
            String diretorio = fileChooser.getSelectedFile().getAbsolutePath();
            
            this.dados.setObjetivos(this.objetivos);
            dados.serializar(dados, diretorio);
        }
    }//GEN-LAST:event_salvarDadosMenuActionPerformed

    private void ajudaMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ajudaMenuMouseClicked
        new TelaAjuda(this).setVisible(true);
    }//GEN-LAST:event_ajudaMenuMouseClicked

    private void botaoNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoNovoActionPerformed
        new TelaAdicionarObjetivo(this).setVisible(true);
        this.enable(false);
        botaoEditar.setVisible(false);
        
    }//GEN-LAST:event_botaoNovoActionPerformed

    private void botaoEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoEditarActionPerformed

        try {
            int selecionado = tabela.getSelectedRow();
            
            Objetivo obj = objetivos.get(selecionado);
            
            TelaAdicionarObjetivo telaAdicionar = new TelaAdicionarObjetivo(this);
            telaAdicionar.setVisible(true);
            telaAdicionar.editarObjetivo(obj, selecionado);
            botaoEditar.setVisible(false);
            //telaAdicionar.setVisible(true);
            
        } catch (Exception e){
            
            javax.swing.JOptionPane.showMessageDialog(null, "Atenção", "Erro: " + e, ERROR);
        }
    }//GEN-LAST:event_botaoEditarActionPerformed

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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaInicial().setVisible(true);
            }
        });
    }

    public Dados getDados() {
        return dados;
    }

    public void setDados(Dados dados) {
        this.dados = dados;
    }

    public ArrayList<Objetivo> getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(ArrayList<Objetivo> objetivos) {
        this.objetivos = objetivos;
    }

    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu ajudaMenu;
    private javax.swing.JButton botaoEditar;
    private javax.swing.JButton botaoNovo;
    private javax.swing.JMenuItem carregarDadosMenu;
    private javax.swing.JMenuItem excluirDadosMenu;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelData;
    private javax.swing.JLabel metaLabel;
    private javax.swing.JLabel progressoLabel;
    private javax.swing.JLabel proximaMetaLabel;
    private javax.swing.JMenuItem salvarDadosMenu;
    private javax.swing.JTable tabela;
    // End of variables declaration//GEN-END:variables
}
