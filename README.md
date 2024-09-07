<!DOCTYPE html>
<html>
<body>

<h1 align="center">Pet-Fi</h1>
<hr>
<h3 align="center">Aplicação para gerar um cadastro do pet na venda de uma coleira, possibilitando gerar um QR code para ser impresso na coleira com os dados.</h3>
<hr>
</br>
<div align="center">
  <a href="#">//TODO link app</a>
</div>
</br>
<div align="center">
  <span>Login: admin / Senha: admin</span>
  </br>
</div>
<hr>
</br>

<div align="center">
  <details>
      <summary><strong>Requisitos</strong></summary>
      <br/>
      <div align="left">
        <details>
          <summary><strong>Requisitos do Sistema</strong></summary>
          <br/>
          <ul>
          <hr>
            <span>Privado:<span>
            <br>
            <li>Ter um usuário/senha para logar no sistema.
            </li>
            <li>Realizar cadastro com os dados do pet/dono e armazenar em um banco de dados.
            </li>
            <li>Possibilidade de atualizar, deletar os dados caso necessário.
            </li>
            <li>Buscar os pets cadastrados.
            </li>
            <br>
            <span>Publico:<span>
            <br>
            <li>Buscar todos pets cadastrados.
            </li>
            <li>Localizar o pet escaneando o QR code na coleira.
            </li>
            <li>Publicidade da loja que vende a coleira.
            </li>
            <br/>
          <hr>
          </ul>
        </details>
      </div>
      <div align="left">
        <details>
          <summary><strong>Casos de uso</strong></summary>
        <div align="left">
        <hr>
        <details>
          <summary><strong>Privado</strong></summary>
          <br/>
          <hr>
          <span>Login/logout do sistema.</span>
          <ul>
            <li>Usuário tenta acessar o sistema.
            <ol><u>Entrada esperada:</u></ol>
            <ol>-Login/senha válidos, registrados no banco de dados.</ol>
            </li>
            <ol><u>Saída esperada:</u></ol>
            <ol>-Acesso a tela home/privada para cadastrar/localizar pets, em caso login e/ou senha válidos.</ol>
            <ol>-Impedir o acesso caso login e/ou senha inválidos, exibir mensagem de erro.</ol>
          </ul>
          <span>CRUD do sistema.</span>
          <ul>
            <li>Página home: registrar, buscar, alterar e excluir dados de um pet/dono no banco de dados.
            <ol><u>Entrada esperada:</u></ol>
            <ol>-Pet: id, nome, qr code, informacões adicionais, sexo, foto, procurado
            </ol>
            <ol>-Cliente: id, nome, telefone, facebook, instagram, cidade </ol>
            <ol><u>Saída esperada:</u>
            </ol>
            <ol>-Registro/atualização de um pet no sistema.</ol>
            <ol>-Registro/atualização de um cliente sistema.
            </ol>
            <ol>-Dados de um pet localizado pelo campo selecionado na pesquisa.</ol>
            <ol>-Lista com todos os pets em sistema.</ol>
            </li>
            <li>Página home: gerador de qr code, possibilidade de download do qrcode para impressão em coleiras.
            <ol><u>Entrada esperada:</u></ol>
            <ol>-Pet: id </ol>
            <ol><u>Saída esperada:</u></ol>
            <ol>-Url com qr code do pet requisitado pelo id. </ol>
            <li>Página home: possibilidade de alterar o atributo "procurado" do pet através de um flag booleano.
            <ol><u>Entrada esperada:</u></ol>
            <ol>-Pet: id </ol>
            <ol><u>Saída esperada:</u></ol>
            <ol>-Procurado = true/false </ol>
            </li>
          </ul>
          <hr>
        </details>
      </div>
      <div align="left">
        <details>
          <summary><strong>Público</strong></summary>
          <br/>
          <hr>
          <span>Acesso a home/pública via qr code.</span>
          <ul>
            <li>
            Usuário escaneou o qr code na coleira.
            <ol><u>Entrada esperada:</u></ol>
            <ol>-Url home/pública com id do pet.</ol>
            <ol><u>Saída esperada:</u></ol>
            <ol>-Todos os dados registrados no banco de dados do pet selecionado.</ol>
            </li>
          </ul>
          <span>Buscar todos os pets em sistema.</span>
          <ul>
            <li>Usuário não sabe/conseguiu ler o qr code.
            <ol><u>Entrada esperada:</u></ol>
            <ol>Acesso a página home/pública.</ol>
            <ol>Busca de pet por código de identificação.</ol>
            <ol>Filtro de pets por cidade</ol>
            <ol>Flag do atributo "procurado" em true/false</ol>
            <ol><u>Saída esperada:</u></ol>
            <ol>-Lista com todos os pets em cadastrados no banco de dados do sistema, filtrados ou não.</ol>
            <ol>-Dados de um pet localizado por id.</ol>
            </li>
          </ul>
          <hr>
        </details>
      </div>
    </details>
   </div>
  </details>
</div>
<br>
<div align="center">
  <details>
    <summary><strong>App Roadmap</strong></summary>
    <div align="left">
      </br>
      <hr>      
      <p>- Back-End:</p>
      <span>Task 1 : CRUD de sistema, relacionamentos entre entidades.</span>
      <ul>
        <li><s>Configuração com banco de dados H2 para testes, profile TEST</s></li>
        <li><s>Criação das entidades e mapeamento Hibernate</s></li>
        <li>Validações com Spring Validation.</li>
        <li><s>Criação de todos controllers, pelo menos com o método "find" para testar relacionamentos.
        </s></li>
        <li><s>Database seeding para testes.</s>
        </li>
        <li><s>Controller/Service com CRUD completo para entidades user, pet, cliente.</s>
        <li><s>Camada DTO</s></li>
        </li>
        <li><s>Exceptions configuradas.</s>
       </li>
        <li><s>Upload de foto de perfil do pet, salvando no banco de dados.</s>
        </li>
        <li><s>Geração de link url para qr code.</s></li>
        <li><s>Configuração e testes com banco de dados postgreSQL / profile DEV</s></li>
      </ul>
      <span>Task 2 - Spring security, autenticando o sistema e autorizando páginas de acordo com o perfil.</span>
      <ul>
        <li><s>Validação com banco de dados, login/senha para acessar o sistema.</s>
        </li>
        <li><s>Validação para não acessar página home/privada sem estar logado.</s>
        </li>
      </ul>
      <p>- Front-End:</p>
       <ul>
        <li><s>Configuração Material Design for Bootstrap 5 & Angular 17.</s></li>
        <li><s>Criação front com angular.</s></li>
        <li><s>Criação modelds.</s></li>
        <li><s>Criação services.</s></li>
        <li><s>Criação layouts básicos.</s></li>
        <li><s>Página admin/home - crud completo cliente.</s></li>
        <li><s>Página admin/home - crud completo pet.</s></li>
        <li><s>Página admin/home - geração de qrcode.</s></li>
        <li><s>Página admin/home - listando todos os pets, com recurso para atualizar/excluir em cada pet.</s></li>
        <li><s>Página admin/home buscando pet, filtrados por nome, código.</s></li>    
        <li><s>Ao ser encaminhado pelo qr code escaneado, página com dados pet/cliente.</s></li>      
      </ul>
      <hr>      
    </div>
  </details>
</div>
</br>
<div align="center">
  <details>
    <summary><strong>UML</strong></summary>
    </br>
    <hr>
    <img src="./resources/pet-fi.jpg" alt="uml">
    <hr>
    </details>
</div>
</br>
<div align="center">
  <details>
    <summary><strong>Imagens</strong></summary>
    </br>
    <hr>
     <img src="" alt="TODO">
    <hr>
    </details>
</div>
</br>
<hr>
<div align="center">
  <h3>Tecnologias utilizadas</h3>
  <p>Java, Spring Boot, Angular, Bootstrap, PostgreSQL, Git e Github.<p>
</div>