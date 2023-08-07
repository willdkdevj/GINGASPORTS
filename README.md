# Aplicação REST utilizando Spring3 - Ginga Sports
O objetivo é usar o Spring Boot para desenvolver uma API Rest invocando as operações de CRUD utilizando o framework Spring na versão 3

[![Spring Badge](https://img.shields.io/badge/-Spring-greenlight?style=flat-square&logo=Spring&logoColor=white&link=https://maven.apache.org/)](https://spring.io/)
[![Maven Badge](https://img.shields.io/badge/-Maven-black?style=flat-square&logo=Apache-Maven&logoColor=white&link=https://maven.apache.org/)](https://maven.apache.org/)
[![JPA Badge](https://img.shields.io/badge/-JPA-blue?style=flat-square&logo=GitHub&logoColor=white&link=https://docs.jboss.org/author/display/AS71/JPA%20Reference%20Guide.html)](https://docs.jboss.org/author/display/AS71/JPA%20Reference%20Guide.html)
[![Hibernate Badge](https://img.shields.io/badge/-Hibernate-green?style=flat-square&logo=Hibernate&logoColor=white&link=https://docs.jboss.org/hibernate/orm/current/quickstart/html_single/)](https://docs.jboss.org/hibernate/orm/current/quickstart/html_single/)

![Badge em Desenvolvimento](http://img.shields.io/static/v1?label=status&message=Em%20Desenvolvimento&color=GREEN&style=flat-square)

## Indice

*   [Objetivos](#objetivos)
    *   [Objetivos Principais](#objetivos-principais)
*   [Tratamento de Requisições](#tratamento-de-requisições---dados-recebidos-formulário)
    *   [Validação de Conteúdo](#validação-de-conteúdo-validation-constraints)
*   [Persistência com Hibernate](#persistência-com-hibernate-jpamysql)
    *   [Container de Banco de Dados](#container-de-banco-de-dados-mysql)
*   [Dados Retornados](#dados-retornados)
*   [Tratamento de Erros](#tratamento-de-erros)
*   [Autenticação e Autorização](#autenticação-e-autorização-spring-security)
    *   [Objetivos](#objetivos-1)
    *   [Controle de Acesso](#controle-de-acesso)
*   [Configuração do JWT](#configuração-do-jwt-implementando-o-algoritmo-bcrypt)
    *   [Algoritmo BCrypt](#algoritmo-bcrypt-spring)
    *   [JWT AUTH0](#jwt-auth0)
    *   [Validar Acesso a API](#validar-acesso-a-api)
    *   [Algoritmo BCrypt](#criteria-api)
    *   [Algoritmo BCrypt](#criteria-api)
    *   [Algoritmo BCrypt](#criteria-api)



## Objetivos
O objetivo é utilizar o Spring Boot para desenvolver uma API Rest, com algumas funcionalidades. O intuito é criar um **CRUD**, sendo as quatro operações fundamentais das aplicações: cadastro, listagem, atualização e exclusão de informações. Também como será aplicado validações das informações que chegam na API, usando o **Bean Validation**. Além disso, será utilizado o conceito de paginação e ordenação das informações que a API retornará ao solicitante.
for-the-badge

### Objetivos Principais
*	Desenvolvimento de uma API Rest
*	CRUD (Create, Read, Update e Delete)
*	Validações
*	Paginação e ordenação

<img align="right" width="400" height="250" src="https://github.com/willdkdevj/analovespet/blob/master/assets/spring3.jpg">


## Tratamento de Requisições - Dados Recebidos (Formulário)
As requisições que chegarem a API podem conter informações em seu corpo, comumente encaminhadas através de uma requisição do tipo *POST*, desta forma, trataremos estes dados enviados através do recurso de **Record** (*disponível na versão do Java15*). Este recurso funciona como se fosse uma classe imutável, para facilitar o dado que é trafegado através do conceito do *Data Transfer Object - DTO*, tornando o código mais legível e simples. Já nos retornando todas as funcionalidades comuns nos POJO's tradicionais, pois seria necessário digitarmos os métodos getters e setters, criar construtor, e todas as outras verbosidades do Java que é suprimida com o uso do *Record*.

### Validação de Conteúdo (Validation-Constraints)
Foi aplicado também a dependência do jakarta.validation.constraints justamente para validar os dados que são recebidos pela API. Desta forma, a partir de sua implementação foi utilizadas anotações em nossas classes *Record* para checagem dos valores recebidos.
```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
```
As anotações utilizadas foram:
*   NotNull - Verifica se o valor atribuído ao atributo está nulo;
*   NotBlank - Verifica se o valor atribuido ao atributo está nulo ou em branco;
*   Email - Valida se o valor fornecido ao atributo condiz com a sintaxe de e-mail;
*   Pattern - Verifica se o valor atribuido ao atributo sem as características fornecidas pelo regex;
*   Valid - Informa que o atributo também é uma classe DTO e que também precisa ser validada;

## Persistência com Hibernate (JPA/MySQL)
Foi utilizado o banco de dados **MySQL** para armazenar as informações da API e junto com ele foi implementado a biblioteca Flyway a fim de obter o controle do histórico de evolução do banco de dados, um conceito que é denominado *Migration*. Para isso, foi acrescentado a dependência do JPA, MySQL e do Flyway além de utilizar o **application.properties** a fim de passar as credenciais do banco.
```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.flywaydb</groupId>
        <artifactId>flyway-core</artifactId>
    </dependency>
    <dependency>
        <groupId>org.flywaydb</groupId>
        <artifactId>flyway-mysql</artifactId>
    </dependency>
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <scope>runtime</scope>
    </dependency>
```

```java
    spring.datasource.url=jdbc:mysql://localhost:3306/gingasports
    spring.datasource.username=root
    spring.datasource.password=root
```
A camada de persistência da aplicação será feita com a **JPA** (Java Persistence API), com o ***Hibernate*** como implementação dessa especificação e usando os módulos do Spring Boot, para tornar esse processo o mais simples possível.

``` xml
    <dependency>
        <groupId>org.flywaydb</groupId>
        <artifactId>flyway-mysql</artifactId>
    </dependency>
```
Para cada mudança que quisermos executar no banco de dados, precisamos criar um arquivo com a extenção ***sql*** no projeto e, nele, escrever o trecho do comando SQL que será executado no banco de dados.

Também é necessário salvá-los em um diretório específico, na pasta em "main > resources". Com o atalho, "Alt + Insert", selecionamos a opção "Directory" e digitamos o nome da pasta: ***"db/migration"***.

> NOTA: é importante sempre parar o projeto ao criar os arquivos de migrations, para evitar que o Flyway os execute antes da hora, com o código ainda incompleto, causando com isso problemas.

Esse tipo de arquivo sempre começará com "V", seguido pelo número que repersenta a ordem de criação dos arquivos e, depois de dois underlines, um nome descritivo.

Será necessário atribuir mais uma anotação ao método do Controller, o @Transactional do **org.springframework.transaction.annotation**. Como esse é um método de escrita, que consiste em um *insert* no banco de dados, precisamos ter uma transação ativa com ele.

> NOTA: Caso ocorra algum problema no versionamento de scripts pelo flyway, ele gera um registro apontando a sua não execução em sua tabela, então, após corrigir o problema será necessário excluir o registro para rodar novamente o projeto. Desta forma execute o comando *DELETE FROM flyway_schema_history WHERE success = 0;*


### Container de Banco de Dados (MySQL)
Foi utilizado o container **Docker** para disponíbilidar um sistema de gerenciamento de dados **MySQL** para ser utilizado pela aplicação. Desta forma, através do comando abaixo foi disponibilizado a versão *lastest* do SGBD, na qual foi mapeada a porta 3306 do container com a porta 3306 do sistema hospedeiro. Além de fornecido um nome ao contairner a fim de ser identificado com a aplicação através da tag *--name* **mysql_gingasports_**.
```bash
    docker run -e MYSQL_ROOT_PASSWORD=root --name mysql_gingasports_ -d -p 3306:3306 mysql
```

A partir daí foi acessado o container em execução para criar uma instância de *database* a fim de alocar as tabelas a serem migradas pelo *Flyway*, com os seguintes comandos:

```bash
    docker exec -it mysql-analovespet /bin/bash
```
> Comando executado para acessar o terminal do container

```bash
    mysql -u root -p
```
> Comando executado para acessar o SGBD (MySQL)

```bash
    CREATE DATABASE gingasports;
```
> Comando (SQL) a fim de criar uma database para armazenar as tabelas

## Dados Retornados 
Para retornarmos dados foram utilizados objetos apropriados para esta exposição para evitar de expor dados desnescessários ou inapropriados. Desta forma, foram criados objetos DTO com o Record e com Page.
Com o Record já sabemos que ele é imutável e só apresenta os dados que acharmos pertinente. Já o Page fornece alguns elementos a fim do recebitador manuseie como achar necessário.

No método listar() do controller inserimos como parâmetro um Pageable com os seguintes paràmetros.
```java
    @GetMapping
    public ResponseEntity<Page<DadosListagemVeterinario>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){

        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemVeterinario::new);

        return ResponseEntity.ok(page);
    }
```
Começando pelo parâmetro do método, a anotação **PageableDefault** nos permite customizar como será o retorno da página ao usuário, deste modo, o parâmetro size= 10 informa que será retornado 10 registro por página, já o parâmetro sort = {"nome"} ordena os registro através do valor obtido em nome.
Já a parametrização do método findAll() existe a implementação que recebe um objeto do tipo Pabeable na qual mapeamos um objeto DTO a partir do objeto de retorno do banco de dados e criamos um construtor neste DTO a fim que a referência ao método ***DadosListagemVeterinario::new*** funcione conforme esperamos para devolver o objeto **Page**.

> É possível traduzir ou nomear de outra forma os parâmetros do Page para o que acharmos necessários ao passa-los no arquivo de configuração ***application.properties***

```java
    spring.data.web.pageable.page-parameter=pagina
    spring.data.web.pageable.size-parameter=tamanho
    spring.data.web.sort.sort-parameter=ordem
```

Já para atualizar um registro podemos manipular os dados obtidos pelo banco de dados e verificar se os dados fornecidos pela classe *Record* estão presentes, para só neste caso, realizar a atualização do registro. Para isso implementamos o seguinte método no controller:

```java
    @PostMapping("/save")
    @Transactional
    public ResponseEntity savePlayer(@RequestBody @Valid PlayerDTO playerDTO, UriComponentsBuilder uriBuilder) {
        ResponseDTO responseDTO = service.save(playerDTO);
        return ResponseEntity.created(uriBuilder.path("/player/{id}").buildAndExpand(responseDTO.id()).toUri()).body(responseDTO);
    }
```
Com o método **getReferenceById** é obtida uma instância do registro da entidade (Veterinario) no banco, e a partir daí passamos os dados fornecidos no formulário para valida-lo antes de modificar o registo. E só esta etapa basta para atualizar o registro no banco de dados.

Para excluir um registro não será realizado o processo físico de eliminar um registro persistido em uma tabela de dados. O que será realizado será a exclusão lógica, na qual um parâmetro booleano informa se o registro está ativo ou não para ser retornado ao solicitante. Desta forma, foi modificada a tabela *veterinarios* a fim de inserir um novo parãmetro, denominado ativo, onde:
*   1 o registro pode ser retornado;
* E 0 o registro não pode ser retornado;

Este processo foi possível através do versionamento por script SQL realizado pelo Flyway. Desta forma, a classe de controller foi adicionado o seguinte método.
```java
    @DeleteMapping("/inactive/{id}")
    @Transactional
    public ResponseEntity<ResponseDTO> deletePlayer(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
```

Assim toda vez que for selecionado a exclusão de um registro, ao passa-lo como parâmetro, deste será averiguado sua existência no banco de dados e caso encontrado, será alterado seu atributo **ativo** para *false*. Desta forma, foi modificada a instância também do objeto *Veterinario* que agora em seu construtor insere o valor *true* no parâmetro ativo ao cria-lo.

## Tratamento de Erros
O próprio Spring Boot nos ajuda para tratar como é devolvida a stack para o cliente. Normalmente, é retornado os seguintes parâmetros na stack:
*   Timestamp - Informa quando ocorreu o erro apresentando data e hora;
*   Status - Numeral que representa o código HTTP para aquele problema encontrado;
*   Error - Informa o tipo de erro encontrado conforme o código de status;
*   Trace - Informa todo o caminho realizado até encontrar o erro;
*   Message - Apresenta uma possível solução ou qual foi a origem do problema.

Desta forma, vemos como são muitas as informações retornadas, e muitas vezes não queremos que o nosso cliente final tenha ciência de toda esta informação. Principalmente o *trace* que não ajudará em nada o cliente sobre o problema ocorrido. Assim, podemos omiti-lo do retorno ao acrescentar no **application.properties** a seguinte linha:

```xml
    server.error.include-stacktrace=never
```

Por padrão, exceções não tratadas no código são interpretadas pelo **Spring Boot** como erro 500. O correto era utilizarmos a estrutura de *try/catch* para validar nosso código e tratar possíveis erros que venham a ocorrer, mas ao invés de duplicarmos o try-catch no código, podemos usar outro recurso do Spring para isolar esse tipo de tratamento de erros. 

Neste projeto, foi utilizada uma classe para concentrar os tratamentos e os retornos a serem retornado ao cliente utilizando o recurso do Spring, através da anotação ***RestControllerAdvice***.
```java
    @RestControllerAdvice
    public class ErrorHandling {
        @ExceptionHandler(EntityNotFoundException.class)
        public ResponseEntity error404(){
            return ResponseEntity.notFound().build();
        }
    }
```

Assim, temos o tratamento do erro 500 - feito pelo próprio Spring, nós somente configuramos no application.properties - e a classe que trata o erro 404. Podemos ter mais métodos tratando outros códigos de erros.

Agora o erro 400, este erro indica que o servidor não conseguiu processar uma requisição por erro de validação nos dados enviados pelo cliente. 
Para trata-lo foi utilizada a seguinte estrutura na qual utiliza um objeto *Record* para auxilia-la na apresentação de uma mensagem ao cliente.
```java
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity error404(MethodArgumentNotValidException ex){
        var errors = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(ValidationError::new).toList());
    }
    
    private record ValidationError(String campo, String mensagem){
        public ValidationError(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
```
Desta forma, caso seja enviada uma requisição que não contenha um dos parâmetros exigidos para a requisição será apresentado o campo e uma mensagem informando sobre sua necessidade.

## Autenticação e Autorização (Spring Security)
Para usarmos no Spring Boot, também vamos utilizar esse mesmo módulo, que já existia antes do Boot, o Spring Security, sendo um módulo dedicado para tratarmos das questões relacionadas com segurança em aplicações.

### Objetivos
*   Autenticação;
*   Autorização (controle de acesso);
*   Proteção contra-ataques (CSRF, clickjacking, etc.);

Em suma, o Spring Security possui três objetivos. Um deles é providenciar um serviço para customizarmos como será o controle de autenticação no projeto. Isto é, como os usuários efetuam login na aplicação. 

O Spring Security possui, também, a autorização, sendo o controle de acesso para liberarmos a requisição na API ou para fazermos um controle de permissão. Há, também, um mecanismo de proteção contra os principais ataques que ocorre em uma aplicação, como o CSRF (Cross Site Request Forgery) e o clickjacking.

São esses os três principais objetivos do Spring Security, nos fornecer uma ferramenta para implementarmos autenticação e autorização no projeto e nos proteger dos principais ataques. Isso para não precisarmos implementar o código que protege a aplicação, sendo que já temos disponível.

### Controle de Acesso
A API back-end não deve ser pública, ou seja, receber requisições sem um controle de acesso. A partir disso, entra o Spring Security para nos auxiliar na proteção dessa API no back-end.

> NOTA: Autenticação em aplicação Web (Stateful) != Autenticação em API Rest (Stateless)

O cliente da API dispara uma requisição, onde o servidor processará essa requisição e devolverá a resposta. Na próxima requisição, o servidor não sabe identificar quem é que está enviando, ele não armazena essa sessão. Assim, o processo de autenticação funciona um pouco diferente, caso esteja acostumado com a aplicação Web.

Há diversas estratégias para lidarmos com a autenticação. Foi utilizado a estratégia de Tokens, com o **JWT - JSON Web Tokens** como protocolo padrão para lidar com o gerenciamento desses tokens.

![Buscar Por Nome](https://github.com/willdkdevj/analovespet/blob/master/assets/jwt-autenticacao.png)

> Esse diagrama contém um esquema do processo de autenticação na API

O primeiro passo é a requisição ser disparada pelo aplicativo para a nossa API, e no corpo desta requisição é exibido o JSON com o login e senha digitados na tela de login. O segundo passo é capturar esse login e senha e verificar se o usuário está cadastrado no sistema, isto é, teremos que consultar o banco de dados. Por isso, precisaremos ter uma tabela em que vamos armazenar os usuários e suas respectivas senhas, que podem acessar a API.

Se for válido, a API gera um Token, que nada mais é que uma string. A geração desse Token segue o formato JWT, e esse token é devolvido na resposta para a aplicação de cliente, sendo quem disparou a requisição.

Esse é o processo de uma requisição para efetuar o login e autenticar em uma API Rest, usando tokens. Isto é, teremos um controller mapeando a URL de autenticação, receberemos um DTO com os dados do login e faremos uma consulta no banco de dados. Se tiver tudo certo, geramos um token e devolvemos para o cliente que disparou a requisição.

Esse token deve ser armazenado pelo aplicativo mobile/front-end. Há técnicas para guardar isso de forma segura, porque esse token que identifica se o usuário está logado. Assim, nas requisições seguintes entra o processo de autorização, que consta no diagrama a seguir:

![Buscar Por Nome](https://github.com/willdkdevj/analovespet/blob/master/assets/jwt-autorizacao.png)

Será disparada uma requisição para a API. No entanto, além de enviar o JSON com os dados do veterinário no corpo da resposta, a requisição deve incluir um cabeçalho chamado *Authorization*. Neste cabeçalho, é levado o token obtido no processo anterior, o de login.

A diferença será essa: todas as URLs e requisições que desejarmos proteger, teremos que validar se na requisição está vindo o cabeçalho authorization com um token. E é necessário validar este token, gerado pela API.

Portanto, o processo de autorização é: primeiro, chega uma requisição na API e ela lê o cabeçalho authorization, captura o token enviado e valida se foi gerado pela API. Agora é necessário um código para verificar a validade do token.

Pelo fato do token estar vindo, o usuário já está logado. Portanto, o usuário foi logado previamente e recebeu o token. Este token informa se o login foi efetuado ou não. Caso seja válido, seguimos com o fluxo da requisição.

## Configuração do JWT (Implementando o Algoritmo BCrypt)
O processo de autorização funciona assim justamente porque a nossa API deve ser Stateless. Ou seja, não armazena estado e não temos uma sessão informando se o usuário está logado ou não. É como se em cada requisição tivéssemos que logar o usuário.

Desta forma, após inserir as dependencias necessárias para trabalhar com o **Spring Security** e criarmos a estrutura para o usuário no banco de dados, foi necessário criar um método para resgatar este usuário no banco de dados a fim de ser validado. Assim, foi criado um classe de serviço a fim de implementar uma interface do *Spring Security* a fim de sobrescrever o método padrão.
```java
    @Service
    public class AutenticationService implements UserDetailsService {

        @Autowired
        private UsuarioRepository repository;
        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            return repository.findByLogin(username);
        }
    }
```

Além da classe, foi criado um repositório para manipular os dados obtidos através da JPA e criamos o método **findByLogin** que utilizará o nome atribuido ao login para buscar no banco de dados.
```java
    public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
        UserDetails findByLogin(String login);
    }

```

A próxima alteração é configurar o *Spring Security* para ele não usar o processo de **segurança tradicional**, que é o *Stateful*. Pois estou trabalhando com uma API Rest, o processo de autenticação precisa ser *Stateless*.

Foi criada uma classe de configuração e nesta classe que foi concentrada as informações de segurança do **Spring Security**.

```java
    @Configuration
    @EnableWebSecurity
    public class SecurityConfigurations {

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity https) throws Exception {
            return https.csrf().disable()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and().build();
        }
    }
```

Foi incluída a configuração do processo de autenticação, que precisa ser *stateless*. Para isso, foi criado um método cujo retorno será um objeto chamado **SecurityFilterChain**, do próprio *Spring*. 

> NOTA: O objeto SecurityFilterChain do Spring é usado para configurar o processo de autenticação e de autorização.

No método, é necessário devolver um objeto **SecurityFilterChain**, contudo, ele não é instanciado. O que foi feito foi utilizado o próprio **HttpSecurity**, do Spring, a fim de resgatar do ***HTTP***.

Além disso, foi configurado a desabilitação do ***CSRF (Cross-Site Request Forgery)*** a fim de inativar a proteção contra-ataques do tipo CSRF (Cross-Site Request Forgery), pois será trabalhado o processo de autenticação via tokens. Nesse cenário, o próprio token é uma proteção contra esses tipos de ataques e seria redundante.

Também foi criado um controller a fim de analisar estas requisições para entrada e realizar a devolutiva do Token de autorização, desta forma implementamos o método da seguinte forma.
```java
    @RestController
    @RequestMapping("/login")
    public class AuteinticationController {
        @Autowired
        private AuthenticationManager manager;

        public ResponseEntity efetuarLogin(@RequestBody @Valid FormAutenticacao form){
            var token = new UsernamePasswordAuthenticationToken(form.login(), form.senha());
            var authenticate = manager.authenticate(token);
        }
    }
```

O processo de autenticação está na **classe AutenticationService**, a classe de serviço. pois é necessário chamar o método loadUserByUsername, já que é ele que usa o repository para efetuar o select no banco de dados. Porém, *não chamamos a classe service de forma direta no Spring Security*. Foi necessário chamar outra classe do Spring que na qual chamará a AutenticationService em segundo plano.

Essa classe é a ***Authentication Manager** do Spring, responsável por disparar o processo de autenticação. Na qual a injetamos através da anotação @Autowired, mas para isso também é necessário configura-la na classe de configuração, pois o Spring não a injeta sem ter uma instância configurada.

Para usarmos o objeto, utilizamos o método *authenticate()* chamando o objeto manager, e desta forma informar o token. O método authenticate(token) recebe o DTO do Spring. Por isso, precisamos converter para UsernamePasswordAuthenticationToken - como se fosse um DTO do próprio Spring.


### Algoritmo BCrypt (Spring)
As senhas, por uma boa prática de segurança, não podem ficar expostas a quem realizar a consulta no banco de dados. Desta forma, foi implementado um algoritmo de *hash* a fim de mascara-las para não serem exibidas.

Para fazer sua configuração criamos um método na classe de configuração que instancia um objeto ***BCryptPasswordEncoder***, do próprio Spring.

```java
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
```

### JWT AUTH0
**JWT (JSON Web Token)** é um padrão que define uma forma compacta e segura de transmitir dados junto com uma assinatura entre duas partes. A carga em um JWT é um objeto JSON que afirma algumas declarações. Essa carga útil pode ser facilmente verificada e confiável pelo verificador, pois é assinada digitalmente. Os JWTs podem ser assinados usando uma chave secreta ou um par de chaves pública/privada .

Um JWT consiste basicamente em três partes:
*   Cabeçalho;
*   Carga útil;
*   Assinatura;

Cada uma dessas seções representa uma string codificada em Base64 separada por pontos ('.') como um delimitador.

Para isso, foi instalada a dependência obtida pelo site http://jwt.io para utilizar no projeto e criado outro *service* a fim de implementa-la.
```java
    @Service
    public class JWTToken {

        @Value("${api.security.token.secret}")
        private String secret;
    public String getToken(Usuario usuario){
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API Ana Loves Pets") // Configurado a qual API pertence o webservice
                    .withSubject(usuario.getLogin()) // Obtido o nome do usuário que acessou
                    .withExpiresAt(dataDeExpiracao()) // Informado uma quantidade limite de acesso para o token fornecido
                    .sign(algorithm);
        } catch (JWTCreationException ex){
                throw new RuntimeException("Erro ao gerar o Token JWT");
        }
    }

        private Instant dataDeExpiracao() {
            return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
        }
    }
```

### Validar Acesso a API
O Spring tem uma classe chamada ***DispatcherSevlet***, responsável por receber todas as requisições do projeto. Ela descobre qual controller será preciso chamar em cada requisição. Depois que a requisição passa pelo DispatcherSevlet, os **Handler Interceptors** são executados. Com ele, identificamos o controller a ser chamado e outras informações relacionadas ao *Spring*.

Já os ***Filters*** aparecem antes mesmo da execução do Spring, onde decidimos se a requisição será interrompida ou se chamaremos, ainda, outro filter. Portanto, foi implementado um filter ou um interceptor ao projeto. Desta forma, ele terá o papel de ser executado como o "interceptador" da requisição.
```java
    @Component
    public class SecurityFilter extends OncePerRequestFilter {
        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            var jwtToken = recoverToken(request);
            if(jwtToken != null) {
                var login = service.getSubject(jwtToken);
                var usuario = repository.findByLogin(login);

                var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            filterChain.doFilter(request, response);
        }

        private String recoverToken(HttpServletRequest request) {
            var authorization = request.getHeader("Authorization");
            if(authorization.isEmpty()) throw new JWTTokenException("Token JWT não enviado no cabeçalho [Authorization]");

            return authorization.replace("Bearer: ", "");
        }
    }
```

Para determinar que esta sessão está valida e o usuário tem permissão para acesso utilizamos um método para verificar se este usuário foi o mesmo que gerou o token, desta forma, utilizamos o método getSubject() na classe de serviço JWTToken para validar este dado.
```java
    public  String getSubject(String jwtToken){
       try {
           var algorithm = Algorithm.HMAC256(secret);
           return JWT.require(algorithm)
                   .withIssuer("API Ana Loves Pets")
                   .build()
                   .verify(jwtToken)
                   .getSubject();
       } catch (JWTCreationException ex){
           throw new RuntimeException("Token JWT inválido!");
       }
   }
```
Agora que foi desabilitado o processo de autenticação padrão do Spring, para implementarmos um customizado, é necessário informar ao Spring que o usuário foi autenticado e esta autorizado para acessar a API. Para isso, após validarmos que existe um subject (login) autenticado pelo token passamos ao Spring o objeto autenticador para liberar acesso ao método através da classe ***SecurityContextHolder***.
```java
    if(jwtToken != null) {
        var login = service.getSubject(jwtToken);
        var usuario = repository.findByLogin(login);

        var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
```

Se faz necessário informar quais métodos deve ser autenticados e quais devem ser liberados sem a necessidade de um token de validação. Isso é possível através do método na qual estamos liberando o acesso ao *endpoint* de login, pois é a partir dele que é gerado o token através do método *requestMatchers()*, e impor que os demais tenham autenticação através do método *anyRequest().authenticated()*. Além disso, precisamos ordernar a execução da lista de filtros, visto que, o *Spring* tem um filtro padrão para verificar se o usuário está logado. Desta forma, precisamos informar que ele deve ser executado após o nosso, através do método *and().addFilterBefore()*.

```java
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity https) throws Exception {
        return https.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                .requestMatchers(HttpMethod.POST, "/login").permitAll()
                .anyRequest().authenticated()
                .and().addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
```

> OBS: Se não especificarmos uma ordem de execução dos filtros, o do Spring será chamado primeiro, e a aplicação não chamará o filtro do JWT.


## Agradecimentos
Obrigado por ter acompanhado aos meus esforços ao aplicar o conceito para uma implementação de uma API REST utlizando o framework do Spring na versão 3. :octocat:

Como diria um velho mestre:
> *"Cedo ou tarde, você vai aprender, assim como eu aprendi, que existe uma diferença entre CONHECER o caminho e TRILHAR o caminho."*
>
> *Morpheus - The Matrix*
> 
