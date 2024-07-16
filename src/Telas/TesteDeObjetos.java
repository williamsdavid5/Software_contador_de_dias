/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Telas;

import java.sql.DriverManager;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author david
 */

//codigo criado para testar os objetos individualmente

public class TesteDeObjetos {
    /*
    public static void testeObjetivo(){
        int metas[] = {10, 15, 20, 53};
        Objetivo obj = new Objetivo(metas, "estudo");
        
        boolean concluido = false;
        
        while (!concluido){
            concluido = obj.verificaMeta();
            System.out.println("Meta atual: " + obj.getNome());
            System.out.print("dias passados: " + obj.getDiasPassados());
            System.out.println(" -- meta atual: " + obj.getDiasMeta()[obj.getMetaAtual()]);
            obj.atualizaMeta();
        }

    }
*/
    
    public static void main(String[] args) {
        
        
        ArrayList<Objetivo> lista = new ArrayList<>();
        
        //int[] metas = {10, 15, 20, 30};
        
        ArrayList<Integer> metas = new ArrayList<>();
        metas.add(10);
        metas.add(15);
        metas.add(20);
        metas.add(30);
        
        Objetivo obj = new Objetivo(new ArrayList<>(metas), "Estudar mais");
        obj.setDiasPassados(7);
        lista.add(obj);
        
        metas.clear();
        metas.add(50);
                
        obj = new Objetivo(new ArrayList<>(metas), "Sem redes sociais");
        obj.setDiasPassados(39);
        lista.add(obj);
        
        obj = new Objetivo("Estudar ingles");
        obj.setDiasPassados(352);
        lista.add(obj);
        
        obj = new Objetivo("Exercicios fisicos");
        obj.setDiasPassados(56);
        lista.add(obj);
        
        Dados dados = new Dados();
        dados.setObjetivos(lista);
        dados.setDataDoSoftware(LocalDate.of(2024, 07, 14));
        
        dados.serializar(dados);

    }
}
