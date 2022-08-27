package com.aeviles.helpdesk.domain;

public enum Prioridade {

    //ADMIN,CLIENTE,TECNICO; ESSA IMPLEMENTAÇÃO É FRAGIL POIS SE INCLUIR UM NOVO ATRIBUTO, PODE ALTERAR O INDICE DO ARRAY

    //foi implementado dessa forma por que será implementado autenticação e autorização e o spring security olha para isso
    BAIXA(0, "BAIXA"), MEDIA(1,"MEDIA"), ALTA(2,"ALTA");

   private Integer codigo;
   private String descricao;

   //O CONTRUTOR DE UMA ENUM É SEMPRE PRVADO
    private Prioridade(Integer codigo, String descricao) {
    this.codigo=codigo;
    this.descricao=descricao;

    }

    //métodos acessores
    public Integer getCodigo() {
        return codigo;
    }


    public String getDescricao() {
        return descricao;
    }

    public static Prioridade toEnum(Integer cod) {
        //se for passado um código nulo ele retorna nulo ex: 3 ele retorna nulo
        if (cod == null) {
            return null;
        }
        //se o codigo que for passado for igual então retorna o x
    for (Prioridade x: Prioridade.values()){
        if(cod.equals(x.getCodigo())){
            return x;
        }
    }
    //senão entrar em nenhum desses caso é lançado uma exceção
    throw new IllegalArgumentException("Prioridade Inválida");
    }

}
