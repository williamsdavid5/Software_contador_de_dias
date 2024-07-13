/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Telas;

import java.util.ArrayList;

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
        
        int metas[] = {10, 15, 20, 53};
        Objetivo obj = new Objetivo(metas, "estudo");
        
        for (int i = 0; i<6; i++){
            lista.add(obj);
        }
        
        Dados dados = new Dados();
        ArrayList<Objetivo> lista2 = null;
        lista2 = dados.desserializar();
        
        if (lista2 == null){
            System.out.println("nao ha dados para serializar");
        }
        
        dados.serializar(lista);
        lista2 = dados.desserializar();
        
        if (lista2 != null){
            System.out.println("ha dados sim!");
        }
        
    }
}
