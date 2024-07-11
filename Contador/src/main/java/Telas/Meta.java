/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Telas;

import java.time.LocalDate;
import java.time.Month;
/**
 *
 * @author david
 */
public class Meta {
    private int diasMeta[]; //quantos dias o usuário definiu para aquela meta
    private int metaAtual; //caso existam multiplas metas em um objetivo, esse atributo irá marcar em qual meta estamos atualmente
    private int diasPassados; //quantos dias já se passaram desde a data de inicio
    //private LocalDate diaInicio; //dia em que a meta foi estabelecida para calcular a contagem
    private boolean concluida;
    //private LocalDate diaAtual;
    
    //perceba, a meta pode ou não ser criada com uma quantidade especifica de dias
    //assim o usuário poderá ter metas especificas ou apenas contar os dias
    public Meta(int diasMeta[]){
        //this.diaInicio = LocalDate.of(2024, 7, 1); //data ficticia definida para testes do programa
        this.diasMeta = diasMeta;
        this.diasPassados = 0;
        this.metaAtual = 0;
        this.concluida = false;
    }
    
    public Meta(){
        //this.diaInicio = LocalDate.of(2024, 7, 1); //data ficticia definida para testes do programa
        this.diasMeta = new int[1];
        this.diasPassados = 0;
        this.metaAtual = 0;
        this.concluida = false;
    }
    
    public void atualizaMeta(){
        this.diasPassados += 1;
    }
    
    public boolean verificaMeta(){
        //verifica se a meta atua, já foi batida
        if (this.diasPassados == this.diasMeta[metaAtual]){
            //se foi, precisamos verificar então se há outra meta na lista
            try {
                int teste = diasMeta[metaAtual+1]; //variavel teste para verificar se ha mais um elemento na lista
                metaAtual += 1; //se tudo der certo, podemos aumentar a meta atual em 1 e seguir para a proxima meta que ja sabemos que está na lista
                
            } catch (Exception e){
                this.concluida = true; //se nao der certo, signica que não há proximo elemento na lista, então a meta atual era a ultima.
            }
        }
        
        return this.concluida;
    }

    ///////////////////////////////////gets e sets///////////////////////////////////
    public int getDiasPassados() {
        return diasPassados;
    }
/*
    public LocalDate getDiaInicio() {
        return diaInicio;
    }
    
    public void setDiaInicio(LocalDate diaInicio) {
        this.diaInicio = diaInicio;
    }

*/
    public void setDiasPassados(int diasPassados) {
        this.diasPassados = diasPassados;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public int getMetaAtual() {
        return metaAtual;
    }
    
}
