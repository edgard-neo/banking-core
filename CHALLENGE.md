Desafio Técnico — Sistema Bancário Simplificado

**Objetivo**

Implementar o núcleo de domínio de um sistema bancário simples em Java puro, com ênfase em arquitetura limpa, modelagem de domínio rica, encapsulamento forte, regras de negócio consistentes e testes que validam comportamento real.

Esse desafio não é sobre frameworks ou infraestrutura. É sobre como você modela regras de negócio, protege o domínio e escreve código claro e defensável.

**Contexto**  
Você está construindo a parte mais crítica de uma fintech: o core que garante a consistência das operações financeiras.  
Essa camada não conhece HTTP, banco de dados ou frameworks — ela só conhece as regras do negócio e precisa ser correta, testável e fácil de entender.

**Requisitos Funcionais**

1. **Criar Conta**

   - Campos: ID (gerado automaticamente), nome do titular (obrigatório e válido), saldo (inicia em 0), status (ATIVA por padrão), data de criação, histórico de operações (inicialmente vazio).
   - O saldo e o histórico não podem ser alterados diretamente.

2. **Depósito**

   - Adiciona valor positivo em conta ATIVA.
   - Atualiza o saldo e registra operação no histórico (tipo: DEPÓSITO).

3. **Saque**

   - Remove valor positivo, apenas se houver saldo suficiente e a conta estiver ATIVA.
   - Atualiza o saldo e registra no histórico (tipo: SAQUE).

4. **Transferência entre contas** ⭐ **(principal diferencial)**

   - Transfere valor de uma conta origem para uma conta destino.
   - Regras:
     - Ambas as contas devem existir e estar ATIVAS
     - Conta origem deve ter saldo suficiente
     - Valor deve ser positivo
     - A operação é atômica: valida todas as condições antes de aplicar qualquer mudança
   - Histórico:
     - Origem: TRANSFERÊNCIA_ENVIADA (com valor, data e ID da conta destino)
     - Destino: TRANSFERÊNCIA_RECEBIDA (com valor, data e ID da conta origem)

5. **Bloquear / Desbloquear Conta**

   - Altera o status entre ATIVA e BLOQUEADA.
   - Conta bloqueada não permite depósito, saque nem transferência (origem ou destino).
   - Registra a alteração no histórico (tipo: ALTERACAO_STATUS).

6. **Consultas**
   - Buscar conta por ID
   - Obter histórico completo da conta (ordenado por data, decrescente, somente leitura)

**Arquitetura (Clean Architecture leve)**

Estrutura de pacotes obrigatória:

```
src/main/java/
├── domain/          → Entidades, value objects, enums
├── service/         → Lógica de negócio e casos de uso principais
├── repository/      → Interface + implementação in-memory
├── usecase/         → Orquestração dos fluxos (entrada/saída do core)
├── dto/             → Objetos de transferência
├── mapper/          → Conversão entre domínio e DTOs
├── exception/       → Exceções de domínio
└── util/            → Utilitários (ex: gerador de ID, datas)
```

**Princípios obrigatórios**

- Camadas não pulam umas às outras
- Entidades de domínio nunca são expostas externamente (sempre use DTOs)
- Saldo e histórico só mudam via métodos comportamentais (ex: depositar(), sacar())
- Histórico é imutável do ponto de vista externo
- Java 11+ (features de versões mais novas são bem-vindas, mas não obrigatórias)

**Restrições**

- Sem Spring, Lombok, JPA, Mockito ou qualquer biblioteca externa
- Sem banco de dados real (use repositório in-memory)
- Sem reflexão ou anotações desnecessárias

**Exceções de domínio (ligadas diretamente às regras de negócio)**

- `ContaNaoEncontradaException` → conta não existe
- `ContaBloqueadaException` → operação não permitida em conta bloqueada
- `SaldoInsuficienteException` → saque ou transferência sem saldo
- `ValorInvalidoException` → valor ≤ 0 ou inválido

**Testes (JUnit 5 puro)**

- Priorize validação de comportamento, não cobertura numérica
- Cobrir cenários principais e falhas esperadas:
  - Criação de conta (válida e inválida)
  - Depósito e saque (sucesso e rejeições por regra)
  - Transferência (sucesso, falhas por saldo, status ou conta inexistente)
  - Atomicidade: nada muda se alguma validação falhar
  - Impacto do bloqueio em todas as operações
  - Histórico correto, ordenado e imutável
  - Mapper funcionando ida e volta
- Inclua testes de fluxo completo (usecase → service → repository)

**Diferenciais reais (opcionais, mas de alto impacto quando bem feitos)**

1. Atomicidade clara e simples na transferência (validação prévia + aplicação)
2. Domínio fortemente encapsulado (métodos expressivos, records para operações, construtores controlados)
3. Thread-safety básica na transferência (ex: synchronized no método crítico) — documente como exploração
4. README profissional com:
   - Visão geral do problema
   - Diagrama simples das camadas (ASCII ou texto)
   - Decisões de design justificadas (ex: por que in-memory? por que sem mocks?)
   - Como executar os testes
   - Reflexão sobre onde estão as regras de negócio e como o sistema evolui

**O que será avaliado por um tech lead**

- As regras de negócio estão isoladas no domínio/service?
- O código é expressivo, legível e sem vazamento de abstrações?
- Os testes protegem o comportamento crítico?
- As decisões técnicas são justificadas e sensatas?
- O projeto transmite maturidade e clareza de pensamento?

**Checklist final**

**Obrigatório**

- Domínio protegido e rico
- Transferência atômica com histórico duplo detalhado
- Exceções ligadas a regras
- Separação estrita de camadas
- Testes de comportamento (sucesso + falhas)
- README claro e profissional

**Diferencial (faça apenas se sair natural e limpo)**

- Thread-safety simples e justificada
- Uso elegante de features modernas (records, etc.)

Esse desafio, executado com foco e simplicidade, demonstra mais maturidade técnica do que muitos projetos complexos com frameworks.
