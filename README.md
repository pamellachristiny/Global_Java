# 🌐 Global Solution — LevelUP (FIAP)  
### Backend Java com Quarkus • JDBC • Oracle • MVC/DDD

Este projeto implementa uma API REST voltada para a gestão de cursos, usuários, professores e desafios (challenges), seguindo o padrão **MVC + DAO + JDBC + DDD** exigido pela FIAP.

Desenvolvido utilizando **Java 17 + Quarkus 3 + JDBC + Oracle**, com deploy preparado para **Render** via Docker.

---

# 📁 Arquitetura do Projeto

```

src/main/java/br/com/fiap/global/
│
├── controller/      # Endpoints REST
├── service/         # Regra de negócio
├── dao/             # JDBC + SQL
├── model/           # POJOs (entidades)
└── infra/           # ConnectionFactory (Oracle)

````

---

# 🚀 Tecnologias Utilizadas
- **Java 17**
- **Quarkus 3**
- **RESTEasy + Jackson**
- **JDBC + Oracle**
- **Maven**
- **Docker**
- **Render (Deploy)**

---

# 🔧 Como Rodar o Projeto Localmente

### 1) Configurar variáveis de ambiente
```bash
export ORACLE_URL="jdbc:oracle:thin:@host:1521:ORCL"
export ORACLE_USER="usuario"
export ORACLE_PASSWORD="senha"
````

### 2) Build

```bash
mvn -DskipTests package
```

### 3) Executar

```bash
java -jar target/quarkus-app/quarkus-run.jar
```

API em:
👉 **[http://localhost:8080](http://localhost:8080)**

---

# 🌐 Endpoints Principais

### Challenge

```
GET    /challenge
GET    /challenge/{id}
POST   /challenge
PUT    /challenge/{id}
DELETE /challenge/{id}
```

### Curso

```
GET    /curso
POST   /curso
```

### Usuário

```
POST /usuario
GET  /usuario
```

### Professor

```
POST /professor
GET  /professor
```

---

# 🗄 Banco de Dados (Oracle)

### Estrutura das tabelas:

* TB_CURSO
* TB_PROFESSOR
* TB_USUARIO
* TB_CHALLENGE (FK → TB_CURSO)

Scripts DDL e INSERTs estão em `/sql`.

---

# 🐳 Deploy no Render

O projeto inclui:

* **Dockerfile**
* **render.yaml** configurado

Basta criar um Web Service no Render e enviar:

```
buildCommand: mvn -DskipTests package
startCommand: java -jar quarkus-run.jar
```

Variáveis obrigatórias:

* `ORACLE_URL`
* `ORACLE_USER`
* `ORACLE_PASSWORD`

---

# 👨‍💻 Autores

Projeto desenvolvido como parte da **Global Solution (FIAP)** — Engenharia de Software.

---

# ✔ Status do Projeto

🚀 **Concluído e pronto para Deploy**

````

---

# 🧪 **2. Arquivo de Testes HTTP — `api-tests.http`**

Este arquivo pode ser usado no VSCode, IntelliJ, Insomnia, etc.

```http
### LISTAR CHALLENGES
GET http://localhost:8080/challenge

### CRIAR CHALLENGE
POST http://localhost:8080/challenge
Content-Type: application/json

{
  "nomeChallenge": "Design UI",
  "descricaoChallenge": "Criar protótipo",
  "tempo": "2024-05-18",
  "curso": { "id": 1 }
}

### LISTAR CURSOS
GET http://localhost:8080/curso

### CRIAR CURSO
POST http://localhost:8080/curso
Content-Type: application/json

{
  "nome": "Engenharia de Software",
  "descricao": "Graduação"
}

### CRIAR USUÁRIO
POST http://localhost:8080/usuario
Content-Type: application/json

{
  "nome": "Gabriel",
  "email": "gabriel@gmail.com",
  "senha": "123456",
  "plano": "Premium",
  "nivel": "Avançado"
}

### LISTAR USUÁRIOS
GET http://localhost:8080/usuario
````

---

# 🗃️ **3. Script de Carga Inicial (INSERTs)**

```sql
-- CURSOS
INSERT INTO TB_CURSO (NOME, DESCRICAO) VALUES ('Engenharia de Software', 'Curso de tecnologia FIAP');
INSERT INTO TB_CURSO (NOME, DESCRICAO) VALUES ('ADS', 'Análise e Desenvolvimento de Sistemas');

-- PROFESSORES
INSERT INTO TB_PROFESSOR (NOME, FORMACAO) VALUES ('João Silva', 'Doutor em Computação');
INSERT INTO TB_PROFESSOR (NOME, FORMACAO) VALUES ('Maria Oliveira', 'Mestre em IA');

-- USUÁRIOS
INSERT INTO TB_USUARIO (NOME, EMAIL, SENHA, PLANO, NIVEL)
VALUES ('Gabriel', 'gabriel@fiap.com.br', '123456', 'Premium', 'Avançado');

-- CHALLENGES
INSERT INTO TB_CHALLENGE (NOME_CHALLENGE, DESCRICAO_CHALLENGE, TEMPO, CURSO_ID)
VALUES ('Criar API REST', 'Desafio de backend', SYSDATE, 1);
```

---

# 📐 **4. Diagrama DER (ASCII)**
