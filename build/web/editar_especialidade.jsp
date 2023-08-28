<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.Date" %>
<jsp:useBean id="conexao" scope="page" class="banco_dados.conexaoBancoDados"/>
<jsp:useBean id="especialidade" scope="page" class="banco_dados.especialidades"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SGC - Versão 1.0</title>
        <link href="clinica_medica.css" rel="stylesheet" type="text/css" />
    </head>
    <body class="FundoPagina">

        <%
            ResultSet rsRegistro;
            boolean blnConectado;
            String strDescricao = "";

            int intCodigoEspecialidade = Integer.parseInt(request.getParameter("Codigo"));
            blnConectado = false;

            if (conexao.abrirConexao()) {
                especialidade.configurarConexao(conexao.obterConexao());
                rsRegistro = especialidade.lerRegistro(intCodigoEspecialidade);
                strDescricao = rsRegistro.getString("Descricao_Especialidade");

                conexao.fecharConexao();
                blnConectado = true;
            } else {
                out.println("<p>Falha na conexão com obanco de dados!</p>");
            }
        %>


        <% if (blnConectado) {%>
        <p class="TituloAplicacao">SGC - Sistema de Gestão de Clínicas 1.0</p>
        <p class="TituloPagina">Cadastro de Especialidades - Edição</p >
        <form name="formEditarEspecialidade" method="post" action="atualizarEspecialidade" target="_parent">


        <p>Descrição: <input type="text" name="txtDescricao" size="45" maxlength="45" value="<%=strDescricao%>"/>
            <input type="submit" name="btnAtualizar" value="Atualizar"/>
        </p>
        <br>
        <span class="LinkVoltar"><a href="javascript:history.back()">[Voltar]</a></span>
        <input type="hidden" name="codigo_especialidade" value="<%=intCodigoEspecialidade%>"/></p>

        </form>

        <p class="RodapePagina">Copyright(c) 2023</p>

        <%}%>



    </body>
</html>
