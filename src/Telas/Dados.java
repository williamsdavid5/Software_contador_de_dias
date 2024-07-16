/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Telas;


import static java.awt.image.ImageObserver.ERROR;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.io.Serializable;
import java.time.LocalDate;


/**
 *
 * @author david
 */
public class Dados implements Serializable{
    //a classe dados é apenas uma forma de facilitar a serialzação e desserialização dos dados do software
    
    private ArrayList<Objetivo> objetivos; //todos os objetivos cadastrados do usuário
    private LocalDate dataDoSoftware;
    
    public Dados(){
        objetivos = new ArrayList<>();
    }

    public void serializar(Dados dados){
        try(FileOutputStream fileout = new FileOutputStream("Dados.bin"); ObjectOutputStream out = new ObjectOutputStream(fileout)){
            out.writeObject(dados); //vai serializar os dados que recebeu
            
        } catch (IOException i) {
            javax.swing.JOptionPane.showMessageDialog(null,"Atenção","Problema ao salvar!", ERROR);
        }  
    }
    
    public void serializar(Dados dados, String caminho){
        try(FileOutputStream fileout = new FileOutputStream(caminho + "\\Dados.bin"); ObjectOutputStream out = new ObjectOutputStream(fileout)){
            out.writeObject(dados); //vai serializar os dados que recebeu
            
        } catch (IOException i) {
            javax.swing.JOptionPane.showMessageDialog(null,"Atenção","Problema ao salvar!", ERROR);
        }  
    }
    
    public Dados desserializar(){
        
        Dados dados = null;
        
        try(FileInputStream fileIN = new FileInputStream("Dados.bin");ObjectInputStream in = new ObjectInputStream(fileIN)){
            dados = (Dados) in.readObject();

        } catch (IOException i) {
            //System.err.println("Erro na leitura: " + i.getMessage());
            //i.printStackTrace();
        } catch (ClassNotFoundException c) {
            //System.err.println("Classe Dados não encontrada: " + c.getMessage());
            //c.printStackTrace();
        }
        return dados;
    }
    
    public void excluirDados(){
        try {
            new File("Dados.bin").delete();
        } catch (Exception e){
            javax.swing.JOptionPane.showMessageDialog(null, "Atenção", "Erro na leitura!", ERROR);
        }
    }

    public ArrayList<Objetivo> getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(ArrayList<Objetivo> objetivos) {
        this.objetivos = objetivos;
    }

    public LocalDate getDataDoSoftware() {
        return dataDoSoftware;
    }

    public void setDataDoSoftware(LocalDate dataDoSoftware) {
        this.dataDoSoftware = dataDoSoftware;
    }
    
    
}
