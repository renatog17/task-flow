# Documentação da API TaskFlow

Bem-vindo à documentação da API TaskFlow, uma ferramenta para gerenciamento de quadros, listas e tarefas, semelhante a ferramentas populares de gerenciamento de projetos como o Trello. Este documento fornece informações abrangentes sobre com orodar o projeto e os endpoints disponíveis.

## Índice

1. [Como rodar](#como-rodar)
    - [Configuração do Projeto no Eclipse](#configuração-do-projeto-no-eclipse)
2. [Autenticação](#autenticação)
3. [Endpoints](#endpoints)
    - [Operações CRUD para Quadros](#operações-crud-para-quadros)
    - [Operações CRUD para Listas](#operações-crud-para-listas)
    - [Operações CRUD para Tarefas](#operações-crud-para-tarefas)
4. [Tratamento de Erros](#tratamento-de-erros)


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



<!--
## Autenticação

Antes de fazer solicitações à API TaskFlow, certifique-se de estar devidamente autenticado e de ter as permissões necessárias. Os detalhes de autenticação variarão de acordo com sua implementação específica.
-->

## Endpoints

### Operações CRUD para Quadros
|Descrição|Método HTTP|Endereço |
|-|-|-|
|Criar| POST| `/quadros`|
|Ler| GET| `/quadros/{board_id}`|
|Listar todos| GET|`/quadros/lista`|
|Atualizar| `PUT|/quadros/{board_id}`|
|Ecluir|DELETE|`/quadros/{board_id}`|
  
### Operações CRUD para Listas
|Descrição|Método HTTP|Endereço |
|-|-|-|
|Criar| POST| `/listas`|
|Ler| GET| `/listas/{list_id}`|
|Listar todos| GET|`/listas/lista`|
|Atualizar| PUT|`/listas/{list_id}`|
|Ecluir|DELETE|`/listas/{list_id}`|

### Operações CRUD para Tarefas
|Descrição|Método HTTP|Endereço |
|-|-|-|
|Criar| POST| `/tarefas`|
|Ler| GET| `/tarefas/{task_id}`|
|Listar todos| GET|`/tarefas/lista`|
|Atualizar| PUT|`/tarefas/{task_id}`|
|Ecluir|DELETE|`/tarefas/{task_id}`|

<!--
## Tratamento de Erros

A API retorna códigos de status HTTP apropriados junto com mensagens de erro para ajudar na solução de problemas. Certifique-se de tratar os erros adequadamente em sua aplicação.

Esta documentação deve fornecer todas as informações necessárias para começar a usar a API TaskFlow. Se você tiver alguma dúvida ou precisar de assistência adicional, entre em contato com nossa equipe de suporte.

Gerencie suas tarefas com eficiência usando o TaskFlow!
-->
