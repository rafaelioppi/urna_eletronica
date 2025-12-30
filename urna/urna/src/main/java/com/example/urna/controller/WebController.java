package com.example.urna.controller;

import com.example.urna.model.RelatorioApuracao;
import com.example.urna.model.Voto;
import com.example.urna.service.UrnaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebController {

    private final UrnaService urnaService;

    public WebController(UrnaService urnaService) {
        this.urnaService = urnaService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("candidatos", urnaService.getCandidatos());
        return "index";
    }

    @PostMapping("/emitir-token")
    public String emitirToken(Model model) {
        String token = urnaService.emitirToken();
        model.addAttribute("token", token);
        model.addAttribute("candidatos", urnaService.getCandidatos());
        model.addAttribute("mensagem", "Token emitido. Use-o para votar.");
        return "index";
    }

    @PostMapping("/votar")
    public String votar(@RequestParam String token,
                        @RequestParam String escolha,
                        Model model) {
        try {
            Voto voto = urnaService.votar(token, escolha);
            model.addAttribute("recibo", "idVoto=" + voto.getIdVoto() + " | hash=" + voto.getHashAtual());
            model.addAttribute("mensagem", "Voto registrado com sucesso.");
        } catch (RuntimeException e) {
            model.addAttribute("erro", e.getMessage());
        }
        model.addAttribute("candidatos", urnaService.getCandidatos());
        return "index";
    }

    @GetMapping("/resultado")
    public String resultado(Model model) {
        RelatorioApuracao r = urnaService.relatorio();
        model.addAttribute("totais", r.getTotais());
        model.addAttribute("qtdVotos", r.getQtdVotos());
        model.addAttribute("hashFinal", r.getHashFinal());
        model.addAttribute("cadeiaValida", r.isCadeiaValida());
        return "resultado";
    }
}
