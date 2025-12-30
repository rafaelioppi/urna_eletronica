package com.example.urna.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptoUtil {

    // Chaves didáticas (não usar em produção)
    private static final String PRIVATE_KEY = "chave_privada_demo";
    private static final String PUBLIC_KEY = "chave_publica_demo";

    /**
     * Calcula o hash SHA-256 de uma string.
     * @param content a string a ser hasheada
     * @return o hash em formato hexadecimal
     */
    public static String sha256(String content) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(content.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao calcular SHA-256: algoritmo não encontrado", e);
        }
    }

    /**
     * Assinatura didática: hash(payload + chave privada).
     * @param payload o conteúdo a ser assinado
     * @return a assinatura
     */
    public static String sign(String payload) {
        return sha256(payload + PRIVATE_KEY);
    }

    /**
     * Verificação didática: compara com hash(payload + chave pública) (apenas ilustrativo).
     * @param payload o conteúdo original
     * @param signature a assinatura a verificar
     * @return true se válida
     */
    public static boolean verify(String payload, String signature) {
        String expected = sha256(payload + PUBLIC_KEY);
        return expected.equals(signature);
    }

    /**
     * Calcula o hash de uma cadeia (hash anterior + item).
     * @param prev hash anterior
     * @param item item atual
     * @return o novo hash
     */
    public static String hashChain(String prev, String item) {
        return sha256(prev + item);
    }
}
