<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Elementos sintáticos JSP. Lá dissemos que existe uma ação denominada useBean.
Ela permite que uma classe Java pura, como as duas que criamos anteriormente, possa ser utilizada
em aplicações web. Esse tipo de classe é comumente denominado componente JavaBean, ou simplesmente bean.
Para uso da ação useBean, é necessário configurar três parâmetros:
1) id: trabalha como uma instanciação da classe Java, como na declaração de um objeto;
2) class: nome do pacote e da classe;
3) scope: define a abrangência da instância da classe. Pode conter os seguintes valores:
I - page: escopo de toda a página JSP em que o bean foi declarado;
II - request: o bean instanciado pode ser utilizado por qualquer página JSP capaz de atender à mes-
ma solicitação do cliente;
III- session: qualquer página JSP que compartilha a mesma seção da página JSP responsável pela criação do bean tem acesso a este valor;
IV- application: o bean criado pela página JSP pode ser utilizado em todas as páginas da aplicação à
qual ela pertence.
Em nosso código JSP, isso foi feito com as duas classes: conexaoBancoDados e especialidades.
-->
<jsp:useBean id="conexao" scope="page" class="banco_dados.conexaoBancoDados"/>
<jsp:useBean id="especialidade" scope="page" class="banco_dados.especialidades"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Conexão com o Banco de Dados</title>
        <link href="clinica_medica.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <h2>Conexão com o banco de dados</h2>
        <%

            if (conexao.abrirConexao()) {
                especialidade.configurarConexao(conexao.obterConexao());
                if (especialidade.inserirRegitro("Clínica Geral")) {
                    out.println("<h2>Especialidade 'Clínica Geral' Cadastrada com Sucesso! </h2>");
                } else {
                    out.println("<h3>Erro ao tentar cadastrar especialidade</h3>");
                }
            } else {

                out.println("<p>Falha na conexão com o banco de dados!</p>");

            }


        %>
    </body>
</html>
