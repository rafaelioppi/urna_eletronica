package com.example.urna.controller;

import com.example.urna.model.RelatorioApuracao;
import com.example.urna.model.Voto;
import com.example.urna.service.UrnaService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final UrnaService urnaService;

    public ApiController(UrnaService urnaService) {
        this.urnaService = urnaService;
    }

    @PostMapping("/token")
    public Map<String, String> token() {
        return Map.of("token", urnaService.emitirToken());
    }

    @PostMapping("/votar")
    public Voto votar(@RequestParam String token, @RequestParam String escolha) {
        return urnaService.votar(token, escolha);
    }

    @GetMapping("/relatorio")
    public RelatorioApuracao relatorio() {
        return urnaService.relatorio();
    }
}
