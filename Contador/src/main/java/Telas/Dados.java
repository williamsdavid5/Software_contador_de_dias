/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Telas;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.swing.JOptionPane;

/**
 *
 * @author david
 */
public class Dados implements Serializable{
    //a classe dados é apenas uma forma de facilitar a serialzação e desserialização dos dados do software
    
    private ArrayList<Objetivo> objetivos; //todos os objetivos cadastrados do usuário
    
    public Dados(){
        objetivos = null;
    }
    
    public void serializar(ArrayList<Objetivo> objetivos){
        this.objetivos = objetivos;
        try(FileOutputStream fileout = new FileOutputStream("Dados.bin"); ObjectOutputStream out = new ObjectOutputStream(fileout)){
            out.writeObject(this); //tenta serializar a si mesmo
        } catch (IOException i){
            System.out.println("erro na escrita");
        }  
    }
    
    public ArrayList<Objetivo> desserializar(){
        try(FileInputStream fileIN = new FileInputStream("Dados.bin");ObjectInputStream in = new ObjectInputStream(fileIN)){
            Dados dados = (Dados) in.readObject();
            this.setObjetivos(dados.getObjetivos());

        } catch (Exception e){
            System.out.println("erro na leitura");
        }
        
        return this.objetivos;
    }

    public ArrayList<Objetivo> getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(ArrayList<Objetivo> objetivos) {
        this.objetivos = objetivos;
    }
    
    
}
