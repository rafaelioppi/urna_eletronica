package com.example.urna.model;

import java.util.Map;

public class RelatorioApuracao {
    private Map<String, Integer> totais;
    private int qtdVotos;
    private String hashFinal;
    private boolean cadeiaValida;

    public RelatorioApuracao(Map<String, Integer> totais, int qtdVotos, String hashFinal, boolean cadeiaValida) {
        this.totais = totais;
        this.qtdVotos = qtdVotos;
        this.hashFinal = hashFinal;
        this.cadeiaValida = cadeiaValida;
    }

    public Map<String, Integer> getTotais() { return totais; }
    public int getQtdVotos() { return qtdVotos; }
    public String getHashFinal() { return hashFinal; }
    public boolean isCadeiaValida() { return cadeiaValida; }
}
