# 🗳️ Simulação de Votação Eletrônica (Spring Boot + H2 + Thymeleaf)

Este projeto é uma **simulação educacional** de um sistema de votação eletrônica, desenvolvido em **Java 17** com **Spring Boot**, **Spring Data JPA**, **H2 Database** e **Thymeleaf**.  
⚠️ **Atenção:** não é destinado para uso real em eleições oficiais. O objetivo é demonstrar conceitos como tokens de elegibilidade, registro imutável de votos com hash encadeado e apuração verificável.

---

## 🚀 Tecnologias utilizadas

- **Spring Boot 3.3.x**
- **Spring Web (MVC)**
- **Spring Data JPA**
- **H2 Database (modo arquivo)**
- **Thymeleaf** (templates HTML)
- **Maven** (gerenciador de dependências)

---

## 📂 Estrutura do projeto

src/
├─ main/
│   ├─ java/com/example/votacao/
│   │   ├─ VotacaoApplication.java
│   │   ├─ model/ (Entidades JPA)
│   │   ├─ repository/ (Repositórios Spring Data)
│   │   ├─ service/ (Regras de negócio)
│   │   ├─ controller/ (Web + API REST)
│   │   └─ util/ (CryptoUtil)
│   └─ resources/
│       ├─ templates/ (index.html, resultado.html)
│       ├─ application.properties
│       └─ static/ (CSS/JS opcionais)
└─ test/java/com/example/votacao/ (Testes JUnit)

Código

---

## ⚙️ Configuração do banco H2

O projeto usa **H2 em arquivo**, garantindo persistência entre execuções.

`src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:h2:file:./data/votacao;AUTO_SERVER=TRUE
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
📌 O banco será salvo em ./data/votacao.mv.db.

▶️ Como executar
Clone o repositório ou extraia o .zip.

Compile e rode com Maven:

bash
mvn clean package
mvn spring-boot:run
Acesse:

Aplicação: http://localhost:8080

Console H2: http://localhost:8080/h2-console

JDBC URL: jdbc:h2:file:./data/votacao

User: sa

Password: vazio

🖥️ Fluxo da aplicação
Emitir Token

Clique em "Emitir token" para gerar um identificador único de eleitor.

Votar

Insira o token e escolha um candidato (A, B ou Branco).

O voto é registrado com assinatura digital simplificada e hash encadeado.

Resultado

Veja a apuração com totais por candidato, quantidade de votos, hash final e verificação da cadeia.

📊 Exemplo de recibo
Após votar, você recebe um recibo:

Código
idVoto=123e4567-e89b-12d3-a456-426614174000 | hash=ab34f9...
Esse recibo prova que seu voto foi incluído na cadeia, sem revelar sua escolha.

✅ Funcionalidades
Emissão de tokens únicos para cada eleitor.

Registro imutável de votos com hash encadeado.

Assinatura digital simplificada (hash + chave privada fictícia).

Apuração transparente com relatório verificável.

Interface web com Thymeleaf.

API REST (/api/token, /api/votar, /api/relatorio).

🧪 Testes
O projeto inclui testes básicos com JUnit:

bash
mvn test
📌 Observações
Este projeto é didático.

Para uso real, seria necessário:

Criptografia forte (RSA/Ed25519).

Separação entre módulos de elegibilidade e votação.

Persistência segura (WORM, logs assinados).

Auditoria independente.

👨‍💻 Autor
Projeto desenvolvido como exemplo de simulação de urna eletrônica em Spring Boot.
