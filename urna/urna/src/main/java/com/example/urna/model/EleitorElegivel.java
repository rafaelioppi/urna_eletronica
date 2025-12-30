package com.example.urna.model;

import jakarta.persistence.*;

@Entity
@Table(name = "eleitor_elegivel")
public class EleitorElegivel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_anonimo", nullable = false)
    private String idAnonimo;

    @Column(name = "token", unique = true, nullable = false)
    private String token;

    @Column(name = "usado", nullable = false)
    private boolean usado;

    public EleitorElegivel() {}

    public EleitorElegivel(String idAnonimo, String token, boolean usado) {
        this.idAnonimo = idAnonimo;
        this.token = token;
        this.usado = usado;
    }

    public Long getId() { return id; }
    public String getIdAnonimo() { return idAnonimo; }
    public void setIdAnonimo(String idAnonimo) { this.idAnonimo = idAnonimo; }
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public boolean isUsado() { return usado; }
    public void setUsado(boolean usado) { this.usado = usado; }
}
