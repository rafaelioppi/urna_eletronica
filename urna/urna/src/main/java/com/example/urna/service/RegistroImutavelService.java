package com.example.urna.service;

import com.example.urna.model.*;
import com.example.urna.repository.*;
import com.example.urna.util.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistroImutavelService {

    private final VotoRepository votoRepo;
    private String headHash = "GENESIS";

    public RegistroImutavelService(VotoRepository votoRepo) {
        this.votoRepo = votoRepo;
    }

    public synchronized String getHeadHash() {
        return headHash;
    }

    public synchronized void append(Voto v) {
        votoRepo.save(v);
        headHash = v.getHashAtual();
    }

    public synchronized boolean verificarCadeia() {
        String prev = "GENESIS";
        List<Voto> votos = votoRepo.findAll();
        // Verificação usando payload (idVoto + timestamp + escolha) + assinatura
        for (Voto v : votos) {
            String payload = v.getIdVoto() + v.getTimestamp() + v.getEscolha();
            String recompute = CryptoUtil.hashChain(prev, payload + v.getAssinatura());
            if (!v.getHashPrev().equals(prev)) return false;
            if (!v.getHashAtual().equals(recompute)) return false;
            prev = v.getHashAtual();
        }
        return true;
    }

    public List<Voto> getVotos() {
        return votoRepo.findAll();
    }
}
