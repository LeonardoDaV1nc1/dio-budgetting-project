API de Orçamento Inteligente com Spring AI
Este repositório contém a minha solução avançada e evoluída para o projeto final da trilha de Spring Boot da DIO. A aplicação demonstra de forma prática como integrar recursos de Inteligência Artificial — como processamento de áudio, modelos de linguagem e Tool Calling — a uma API Java robusta estruturada em arquitetura limpa, contando com autenticação segura via JWT, isolamento de dados por usuário e documentação interativa.

O que o projeto faz
A aplicação atua como um assistente financeiro inteligente capaz de processar comandos em linguagem natural (via texto ou áudio) para gerenciar transações financeiras associadas de forma segura ao usuário autenticado. O fluxo principal executa as seguintes etapas:

Autenticação: O usuário se cadastra e obtém um token JWT válido para acessar os recursos da API.

Entrada de Dados: O cliente envia uma interação simulando um comando de voz ou texto contendo o token de autorização.

Transcrição e Compreensão (ChatClient): O Spring AI converte o áudio (se houver) e interpreta a intenção do usuário.

Tool Calling com Contexto de Usuário: O modelo aciona a ferramenta de backend correspondente, garantindo que as operações (como persistir, resgatar ou remover) reflitam apenas os dados do usuário dono do token JWT.

Resposta Final: O sistema retorna uma confirmação clara e contextualizada.

Melhorias e Evoluções Implementadas por Mim
Além da base apresentada nas aulas, decidi expandir significativamente a segurança, as regras de negócio e a facilidade de uso da aplicação através de implementações próprias:

Segurança Avançada e Autenticação JWT:

Registro e Login de Usuários: Implementação de endpoints para cadastro e autenticação de novos usuários no sistema.

Proteção com Spring Security e JWT: Restrição de acesso aos endpoints da API utilizando tokens JSON Web Token.

Associação de Transações por Usuário: As transações financeiras agora possuem vínculo direto com o usuário autenticado no token, garantindo privacidade e isolamento dos dados.

Novas Ferramentas (Tool Calling Avançado):

Transações Retroativas (PersistPastTransactionUseCase): Adicionada a ferramenta persist-past-transaction, permitindo que a IA compreenda quando o usuário menciona uma data anterior e registre o gasto com a data correta.

Remoção da Última Transação (RemoveLastTransactionUseCase): Criada a ferramenta remover-last-transaction, dando autonomia para o assistente excluir o último lançamento financeiro realizado pelo usuário autenticado.

Validações Robustas de Entrada (TransactionRequest e PastTransactionRequest):

Implementação de regras de validação baseadas em Bean Validation nos DTOs de entrada para barrar dados inconsistentes logo na borda da aplicação.

Documentação de API com Swagger / OpenAPI:

Disponibilização de uma interface gráfica interativa para explorar e testar todos os endpoints da aplicação de forma visual.

Tecnologias Usadas
Java 17+

Spring Boot

Spring Security & JWT (autenticação, autorização e proteção de rotas)

Spring AI (integração com LLMs, transcrição e Tool Calling)

Springdoc OpenAPI / Swagger (documentação interativa de endpoints)

Spring Validation / Bean Validation (garantia de integridade nos payloads de entrada)

Spring Data / JPA & MySQL (persistência de dados e relacionamentos)

Docker Compose (orquestração do ambiente de banco de dados)

Arquitetura Limpa / DDD (separação estrita entre domínio, aplicação e infraestrutura)

Gradle (gerenciamento de dependências)

⚙️ Como Executar a Aplicação
Para rodar o projeto localmente, siga os passos abaixo:

Clone o repositório:

Bash
git clone https://github.com/leodvc82/dio-budgetting.git
Suba o banco de dados MySQL utilizando o Docker Compose na raiz do projeto:

Bash
docker-compose up -d
Defina sua chave de API da OpenAI como uma variável de ambiente no seu sistema operacional:

Bash
export OPENAI_API_KEY="sua_chave_de_api_aqui"
Execute a aplicação usando o Gradle:

Bash
./gradlew bootRun
Documentação da API (Swagger)
Com a aplicação rodando, você pode acessar a interface interativa do Swagger para testar os endpoints de autenticação e transações diretamente pelo navegador:

http://localhost:8080/swagger-ui/index.html

Como Testar o Fluxo Principal
Você pode interagir com os endpoints REST da aplicação (ou via Swagger, inserindo o token JWT no botão Authorize):

Exemplo de transação em tempo real: "Gastei 50 reais em alimentação hoje" (Aciona persist-transaction vinculada ao seu usuário)

Exemplo de transação retroativa: "Gastei 120 reais em mercado no dia 10 do mês passado" (Aciona persist-past-transaction)

Exemplo de remoção: "Apague minha última transação" (Aciona remover-last-transaction afetando apenas o seu histórico)

O que eu aprendi durante o desafio
Desenvolver e evoluir este projeto me permitiu consolidar conhecimentos essenciais:

Segurança de APIs com JWT: Compreendi como estruturar o fluxo completo de registro, autenticação e proteção de rotas sensíveis atrelando o contexto do usuário às requisições.

Isolamento de Dados no Domínio: Aprendi a modelar a relação entre transações financeiras e usuários autenticados, garantindo que o Tool Calling da IA respeite os limites de segurança de cada conta.

Documentação e DX (Developer Experience): Pratiquei a integração de documentação interativa com Swagger e a facilidade de provisionamento de infraestrutura local com o Docker Compose.

Integração Prática de IA com Backend: Aprofundeni o uso de métodos Java seguros (@Tool) guiados por um modelo de linguagem integrado a uma arquitetura limpa e validada.