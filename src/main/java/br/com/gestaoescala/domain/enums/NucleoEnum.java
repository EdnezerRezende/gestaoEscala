package br.com.gestaoescala.domain.enums;

public enum NucleoEnum {
	NORTE(1, "Norte"),
    SUL(2, "Sul");

    private int cod;
    private String descricao;

    NucleoEnum(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static NucleoEnum toEnum(Integer cod){
        if(cod == null){
            return null;
        }

        for(NucleoEnum tipo: NucleoEnum.values()){
            if(cod.equals((tipo.getCod()))){
                return tipo;
            }
        }
        
        throw new IllegalArgumentException("Id inválido: " + cod);
    }
}
