# Sistema Bancário Simplificado

## O que é isso?

Um sistema bancário básico feito em Java puro. A ideia era entender melhor como estruturar um domínio de negócio de verdade, sem ficar dependendo de frameworks pra fazer tudo.

Não tem API REST nem banco de dados aqui. O foco foi construir o **core do sistema** com as regras de negócio bem organizadas e testadas. Tipo, antes de sair fazendo Spring Boot e PostgreSQL, quis entender como as coisas funcionam por baixo.

## O que dá pra fazer

- Criar contas bancárias
- Fazer depósitos e saques
- Transferir dinheiro entre contas
- Bloquear e desbloquear contas
- Ver histórico de todas as operações

A parte legal é que tudo funciona de forma "atômica" nas transferências - ou tudo dá certo, ou nada muda. E as validações (saldo insuficiente, conta bloqueada, etc) tão todas no lugar certo.

## Como organizei

```
src/main/java/
├── domain/          # As regras de negócio vivem aqui
├── service/         # Lógica das operações bancárias
├── repository/      # Onde guarda os dados (em memória por enquanto)
├── usecase/         # Orquestra as operações
├── dto/             # Objetos pra entrada e saída
├── mapper/          # Converte entre domínio e DTOs
├── exception/       # Erros específicos do sistema
└── util/            # Funções auxiliares
```

## Por que fiz algumas coisas assim

**Java puro, sem Spring:** Queria focar nas regras de negócio e entender arquitetura antes de usar frameworks que fazem tudo automaticamente.

**Repositório em memória:** Isola o domínio. Mais pra frente posso trocar por um banco real sem mexer na lógica.

**Sem setters públicos no saldo:** A conta se protege sozinha. Você não consegue simplesmente mudar o saldo - tem que passar pelas operações de depósito/saque.

**Exceções customizadas:** Quando algo dá errado, o erro diz exatamente qual regra de negócio foi quebrada (tipo "SaldoInsuficienteException"), não um erro genérico.

**Records do Java 17:** Usei pra representar as operações no histórico. São imutáveis e deixam o código mais limpo.

## Regras que o sistema garante

- Não dá pra sacar mais do que tem na conta
- Conta bloqueada não pode fazer operações
- Transferências são "tudo ou nada" - se der erro, nenhuma conta muda
- Histórico fica registrado certinho com data/hora
- Valores precisam ser maiores que zero

## Testes

Fiz os testes focando no comportamento real, não em mocks. Por exemplo, testo se uma transferência realmente moveu o dinheiro corretamente, se o histórico foi registrado nas duas contas, se dá erro quando deveria dar, etc.

Usei JUnit 5 puro, nada muito elaborado. O importante era garantir que as regras de negócio funcionam.

## Como rodar

Precisa de Java 11+ e Maven.

```bash
# Rodar os testes
mvn test

# Compilar
mvn compile
```

## O que aprendi fazendo isso

- Como organizar camadas sem depender de framework
- Onde colocar cada tipo de validação
- Como proteger o domínio de mudanças indevidas
- A importância de testes que validam comportamento real
- Clean Architecture na prática (sem complicar demais)

## Próximos passos (se eu continuar)

- Adicionar persistência com JPA
- Fazer uma API REST por cima
- Adicionar autenticação e segurança
- Criar relatórios de movimentação
- Implementar diferentes tipos de conta (corrente, poupança)

## Observações

Fiz sem frameworks de propósito pra entender os fundamentos. É mais difícil no começo, mas depois fica mais fácil entender o que os frameworks fazem por você.

A ideia é que esse core possa ser usado em qualquer contexto - web, desktop, mobile - porque ele não depende de nada externo.
