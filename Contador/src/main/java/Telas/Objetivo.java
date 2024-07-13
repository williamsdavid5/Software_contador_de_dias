/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Telas;

/**
 *
 * @author david
 */

import java.io.Serializable;
import java.time.LocalDate;

public class Objetivo implements Serializable{
    private int diasMeta[]; //quantos dias o usuário definiu para aquela meta
    private int metaAtual; //caso existam multiplas metas em um objetivo, esse atributo irá marcar em qual meta estamos atualmente
    private int diasPassados; //quantos dias já se passaram desde a data de inicio
    //private LocalDate diaInicio; //dia em que a meta foi estabelecida para calcular a contagem
    private boolean concluido;
    //private LocalDate diaAtual;
    private String nome; //nome do objetivo do usuário
    private LocalDate dataInicio; //data inicial do objetivo

    //criação de um objetivo com metas de dia
    public Objetivo(int diasMeta[], String nome){
        this.diasMeta = diasMeta;
        this.metaAtual = 0; //ao iniciar um objetivo, obviamente iremos começar pela primeira meta, ou seja, a primeira posição do vetor de metas
        this.diasPassados = 0;
        this.concluido = false;
        this.nome = nome;
        this.dataInicio = LocalDate.now(); //ao criar o objetivo, a data atual será a data inicial
    }

    //criação de objetivo sem metas de dia
    public Objetivo(String nome){
        this.diasMeta = new int[]{0};
        this.metaAtual = 0; //ao iniciar um objetivo, obviamente iremos começar pela primeira meta, ou seja, a primeira posição do vetor de metas
        this.diasPassados = 0;
        this.concluido = false;
        this.nome = nome;
        this.dataInicio = LocalDate.now(); //ao criar o objetivo, a data atual será a data inicial
    }
/*    
    @Override
    public String toString(){

    }
*/   
    public boolean verificaMeta(){
        //verifica se a meta atua, já foi batida
        if (this.diasPassados == this.diasMeta[metaAtual]){
            //se foi, precisamos verificar então se há outra meta na lista
            try {
                int teste = diasMeta[metaAtual+1]; //variavel teste para verificar se ha mais um elemento na lista
                metaAtual += 1; //se tudo der certo, podemos aumentar a meta atual em 1 e seguir para a proxima meta que ja sabemos que está na lista
                
            } catch (Exception e){
                this.concluido = true; //se nao der certo, signica que não há proximo elemento na lista, então a meta atual era a ultima.
            }
        }
        
        return this.concluido;
    }

    public void atualizaMeta(){
        this.diasPassados += 1;
    }
    
    //////////////////////////////////////// gets e sets ////////////////////////////////////////

    public int[] getDiasMeta() {
        return diasMeta;
    }

    public void setDiasMeta(int[] diasMeta) {
        this.diasMeta = diasMeta;
    }

    public int getMetaAtual() {
        return metaAtual;
    }

    public void setMetaAtual(int metaAtual) {
        this.metaAtual = metaAtual;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getDiasPassados() {
        return diasPassados;
    }

    public void setDiasPassados(int diasPassados) {
        this.diasPassados = diasPassados;
    }

    public boolean isConcluido() {
        return concluido;
    }

    public void setConcluido(boolean concluido) {
        this.concluido = concluido;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }
    
}
