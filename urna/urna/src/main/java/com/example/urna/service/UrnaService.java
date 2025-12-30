package com.example.urna.service;

import com.example.urna.model.EleitorElegivel;
import com.example.urna.model.RelatorioApuracao;
import com.example.urna.model.Voto;
import com.example.urna.repository.EleitorRepository;
import com.example.urna.util.CryptoUtil;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UrnaService {

    private final EleitorRepository eleitorRepo;
    private final RegistroImutavelService registro;
    private final Set<String> candidatos = new LinkedHashSet<>(List.of("A", "B", "Branco"));

    public UrnaService(EleitorRepository eleitorRepo, RegistroImutavelService registro) {
        this.eleitorRepo = eleitorRepo;
        this.registro = registro;
    }

    public synchronized String emitirToken() {
        String idAnonimo = UUID.randomUUID().toString().substring(0, 8);
        String token = UUID.randomUUID().toString();
        EleitorElegivel e = new EleitorElegivel(idAnonimo, token, false);
        eleitorRepo.save(e);
        return token;
    }

    public synchronized Voto votar(String token, String escolha) {
        EleitorElegivel e = eleitorRepo.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Token inválido"));

        if (e.isUsado()) throw new RuntimeException("Token já usado");
        if (!candidatos.contains(escolha)) throw new RuntimeException("Candidato inexistente");

        e.setUsado(true);
        eleitorRepo.save(e);

        String idVoto = UUID.randomUUID().toString();
        long ts = System.currentTimeMillis();
        String payload = idVoto + ts + escolha;
        String assinatura = CryptoUtil.sign(payload);

        String hPrev = registro.getHeadHash();
        String hAtual = CryptoUtil.hashChain(hPrev, payload + assinatura);

        Voto voto = new Voto(idVoto, ts, escolha, assinatura, hPrev, hAtual);
        registro.append(voto);
        return voto;
    }

    public synchronized Map<String, Integer> apurar() {
        Map<String, Integer> totais = new LinkedHashMap<>();
        for (String c : candidatos) totais.put(c, 0);
        registro.getVotos().forEach(v ->
                totais.put(v.getEscolha(), totais.get(v.getEscolha()) + 1)
        );
        return totais;
    }

    public synchronized RelatorioApuracao relatorio() {
        return new RelatorioApuracao(
                apurar(),
                registro.getVotos().size(),
                registro.getHeadHash(),
                registro.verificarCadeia()
        );
    }

    public Set<String> getCandidatos() {
        return Collections.unmodifiableSet(candidatos);
    }
}
