# Documentação da API TaskFlow

Bem-vindo à documentação da API TaskFlow, uma ferramenta para gerenciamento de quadros, listas e tarefas, semelhante a ferramentas populares de gerenciamento de projetos como o Trello. Este documento fornece informações abrangentes sobre com orodar o projeto e os endpoints disponíveis.

## Como rodar

### Configuração do Projeto no Eclipse

Para configurar o projeto TaskFlow no Eclipse, siga as etapas abaixo:

1. **Faça o Download do Projeto:** Você pode baixar o projeto clicando [aqui](https://github.com/renatog17/task-flow/archive/refs/heads/main.zip).

2. **Abra o Eclipse:** Certifique-se de que o Eclipse esteja instalado em seu sistema e esteja aberto.

3. **Importe o Projeto:**
   - No Eclipse, vá em `File > Import`.

   ![Passo 3](https://github.com/renatog17/task-flow/assets/54106116/2270592a-2e42-48b8-827d-1329e367cab9)

4. **Escolha a Opção Maven:**
   - No menu de seleção, escolha `Maven > Existing Maven Projects`.

   ![Passo 4](https://github.com/renatog17/task-flow/assets/54106116/efd2a8a9-b336-4368-829a-1cf9edef3aeb)

5. **Selecione a Pasta do Projeto:**
   - Navegue até a pasta onde você fez o download do projeto e marque a caixa de seleção conforme indicado na imagem abaixo.

   ![Passo 5](https://github.com/renatog17/task-flow/assets/54106116/45a49de0-b0b0-495f-bfa7-3adda3d2b78e)

6. **Aguarde a Atualização do Projeto pelo Maven.**

7. **Encontre a Classe TaskFlowApplication:**
   - No projeto importado, procure pela classe `TaskFlowApplication`.

   ![Passo 7](https://github.com/renatog17/task-flow/assets/54106116/3286a4b4-04de-4fdd-b227-30ef1436e65a)

8. **Execute o Método Main:** Execute o método main da classe `TaskFlowApplication`.

Essas etapas devem permitir que você importe e execute o projeto TaskFlow no Eclipse com sucesso. Certifique-se de que todas as dependências do Maven estejam resolvidas corretamente antes de executar o projeto.




## Autenticação

Antes de fazer solicitações à API TaskFlow, certifique-se de estar devidamente autenticado e de ter as permissões necessárias. Os detalhes de autenticação variarão de acordo com sua implementação específica.

## Endpoints
### Quadros (Boards)

#### Operações CRUD para Quadros

- **Criar Quadro**
  - **Método HTTP:** POST
  - **Endpoint:** `/quadros`
  - **Descrição:** Cria um novo quadro.
  
- **Ler Quadro**
  - **Método HTTP:** GET
  - **Endpoint:** `/quadros/{board_id}`
  - **Descrição:** Recupera informações sobre um quadro específico pelo seu ID.
  
- **Atualizar Quadro**
  - **Método HTTP:** PUT
  - **Endpoint:** `/quadros/{board_id}`
  - **Descrição:** Atualiza os detalhes de um quadro específico.
  
- **Excluir Quadro**
  - **Método HTTP:** DELETE
  - **Endpoint:** `/quadros/{board_id}`
  - **Descrição:** Exclui um quadro e todas as listas e tarefas associadas.

### Listas (Lists)

#### Operações CRUD para Listas

- **Criar Lista**
  - **Método HTTP:** POST
  - **Endpoint:** `/listas`
  - **Descrição:** Cria uma nova lista em um quadro específico.
  
- **Ler Lista**
  - **Método HTTP:** GET
  - **Endpoint:** `/listas/{list_id}`
  - **Descrição:** Recupera informações sobre uma lista específica pelo seu ID.
  
- **Atualizar Lista**
  - **Método HTTP:** PUT
  - **Endpoint:** `/listas/{list_id}`
  - **Descrição:** Atualiza os detalhes de uma lista específica.
  
- **Excluir Lista**
  - **Método HTTP:** DELETE
  - **Endpoint:** `/listas/{list_id}`
  - **Descrição:** Exclui uma lista e todas as tarefas associadas.

### Tarefas (Tasks)

#### Operações CRUD para Tarefas

- **Criar Tarefa**
  - **Método HTTP:** POST
  - **Endpoint:** `/tarefas`
  - **Descrição:** Cria uma nova tarefa em uma lista específica.
  
- **Ler Tarefa**
  - **Método HTTP:** GET
  - **Endpoint:** `/tarefas/{task_id}`
  - **Descrição:** Recupera informações sobre uma tarefa específica pelo seu ID.
  
- **Atualizar Tarefa**
  - **Método HTTP:** PUT
  - **Endpoint:** `/tarefas/{task_id}`
  - **Descrição:** Atualiza os detalhes de uma tarefa específica.
  
- **Excluir Tarefa**
  - **Método HTTP:** DELETE
  - **Endpoint:** `/tarefas/{task_id}`
  - **Descrição:** Exclui uma tarefa.

## Tratamento de Erros

A API retorna códigos de status HTTP apropriados junto com mensagens de erro para ajudar na solução de problemas. Certifique-se de tratar os erros adequadamente em sua aplicação.

Esta documentação deve fornecer todas as informações necessárias para começar a usar a API TaskFlow. Se você tiver alguma dúvida ou precisar de assistência adicional, entre em contato com nossa equipe de suporte.

Gerencie suas tarefas com eficiência usando o TaskFlow!
