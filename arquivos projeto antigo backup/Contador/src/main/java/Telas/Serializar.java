/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Telas;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author david
 */
public class Serializar {
    public void serializar(Dados dados){
        try(FileOutputStream fileout = new FileOutputStream("Dados.bin"); ObjectOutputStream out = new ObjectOutputStream(fileout)){
            out.writeObject(dados); //vai serializar os dados que recebeu
            
        } catch (IOException i) {
            System.err.println("Erro na escrita: " + i.getMessage());
            i.printStackTrace();
        }  
    }
    
    public Dados desserializar(){
        
        Dados dados = null;
        
        try(FileInputStream fileIN = new FileInputStream("Dados.bin");ObjectInputStream in = new ObjectInputStream(fileIN)){
            dados = (Dados) in.readObject();

        } catch (IOException i) {
            System.err.println("Erro na leitura: " + i.getMessage());
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.err.println("Classe Dados não encontrada: " + c.getMessage());
            c.printStackTrace();
        }
        
        return dados;
    } 
}
