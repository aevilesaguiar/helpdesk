package com.aeviles.helpdesk.domain;

public enum Perfil {

    //ADMIN,CLIENTE,TECNICO; ESSA IMPLEMENTAÇÃO É FRAGIL POIS SE INCLUIR UM NOVO ATRIBUTO, PODE ALTERAR O INDICE DO ARRAY

    //foi implementado dessa forma por que será implementado autenticação e autorização e o spring security olha para isso
    ADMIN(0, "ROLE_ADMIN"), CLIENTE(1,"ROLE_CLIENTE"), TECNICO(2,"ROLE_TECNICO");

   private Integer codigo;
   private String descricao;

   //O CONTRUTOR DE UMA ENUM É SEMPRE PRVADO
    private Perfil(Integer codigo, String descricao) {
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

    public static Perfil toEnum(Integer cod) {
        //se for passado um código nulo ele retorna nulo ex: 3 ele retorna nulo
        if (cod == null) {
            return null;
        }
        //se o codigo que for passado for igual então retorna o x
    for (Perfil x:Perfil.values()){
        if(cod.equals(x.getCodigo())){
            return x;
        }
    }
    //senão entrar em nenhum desses caso é lançado uma exceção
    throw new IllegalArgumentException("Perfil Inválido");
    }

}
