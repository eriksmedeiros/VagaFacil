package br.com.vagafacil.models;

public enum AreaAtuacao {
    ENGENHARIA, 
    SAUDE, 
    EDUCACAO, 
    FINANCAS, 
    DIREITO, 
    MARKETING, 
    VENDAS, 
    ADMINISTRACAO, 
    TECNOLOGIA_DA_INFORMACAO, 
    RECURSOS_HUMANOS,
    OUTRO;

    public static void exibirOpções() {
        for (AreaAtuacao a : AreaAtuacao.values()) {
            System.out.println(a.ordinal() + " - " + a);
        }
    }
}
