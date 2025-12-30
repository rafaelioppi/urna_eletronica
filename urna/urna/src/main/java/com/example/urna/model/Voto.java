package com.example.urna.model;

import jakarta.persistence.*;

@Entity
@Table(name = "voto")
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="id_voto", nullable = false, unique = true)
    private String idVoto;

    @Column(name="timestamp", nullable = false)
    private long timestamp;

    @Column(name="escolha", nullable = false)
    private String escolha;

    @Column(name="assinatura", nullable = false, length = 128)
    private String assinatura;

    @Column(name="hash_prev", nullable = false, length = 128)
    private String hashPrev;

    @Column(name="hash_atual", nullable = false, length = 128)
    private String hashAtual;

    public Voto() {}

    public Voto(String idVoto, long timestamp, String escolha,
                String assinatura, String hashPrev, String hashAtual) {
        this.idVoto = idVoto;
        this.timestamp = timestamp;
        this.escolha = escolha;
        this.assinatura = assinatura;
        this.hashPrev = hashPrev;
        this.hashAtual = hashAtual;
    }

    public Long getId() { return id; }
    public String getIdVoto() { return idVoto; }
    public void setIdVoto(String idVoto) { this.idVoto = idVoto; }
    public long getTimestamp() { return timestamp; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
    public String getEscolha() { return escolha; }
    public void setEscolha(String escolha) { this.escolha = escolha; }
    public String getAssinatura() { return assinatura; }
    public void setAssinatura(String assinatura) { this.assinatura = assinatura; }
    public String getHashPrev() { return hashPrev; }
    public void setHashPrev(String hashPrev) { this.hashPrev = hashPrev; }
    public String getHashAtual() { return hashAtual; }
    public void setHashAtual(String hashAtual) { this.hashAtual = hashAtual; }
}
