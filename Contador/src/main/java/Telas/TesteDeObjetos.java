/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Telas;

import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author david
 */

//codigo criado para testar os objetos individualmente

public class TesteDeObjetos {
    
    public static void testeMeta(){
        int metas[] = {10, 15, 20, 53}; //vamos supor que esse usuário tenha essas três metas de dia
        Meta meta = new Meta(metas); //uma nova meta é criada, ela é subdividida em vários intervalos como se fossem várias metas
        //nesse caso, se ele conseguir 10 dias, a proxima meta é 15 dias, e a proxima é 20 e a próxima é 53! (um exagero, mas é apenas para testes)
        
        System.out.println("teste");
        System.out.println("Dias passados: " + meta.getDiasPassados());
        
        //para simular a passagem dos dias
        boolean concluida = meta.verificaMeta();
        while (!concluida){
            meta.atualizaMeta();
            System.out.println("Dias passados: " + meta.getDiasPassados() + " ");
            System.out.println(" Meta atual: " + meta.getMetaAtual());
            concluida = meta.verificaMeta();
        }
        
        System.out.println("Parabens, concluiu a meta!");  
    }
    
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

    
    public static void main(String[] args) {
        
        
        ArrayList<Objetivo> lista = new ArrayList<>();
        
        int[] metas = {10, 15, 20, 30};
        Objetivo obj = new Objetivo(metas, "Estudar mais");
        obj.setDiasPassados(7);
        lista.add(obj);
        
        metas = new int[] {50};
        obj = new Objetivo(metas, "Sem redes sociais");
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
        
        dados.serializar(dados);

    }
}
