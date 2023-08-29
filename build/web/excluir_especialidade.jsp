<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<jsp:useBean id="conexao" scope="page" class="banco_dados.conexaoBancoDados"/>
<jsp:useBean id="especialidade" scope="page" class="banco_dados.especialidades"/> 

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
            int intCodigoEspecialidade = Integer.parseInt((request.getParameter("Codigo")));
            blnConectado = false;

            if (conexao.abrirConexao()) {
                especialidade.configurarConexao(conexao.obterConexao());
                rsRegistro = especialidade.lerRegistro(intCodigoEspecialidade);
                strDescricao = rsRegistro.getString("Descricao_Especialidade");
                conexao.fecharConexao();
                blnConectado = true;
            } else {
                out.println("<p>Falha na Conexão com o Banco de dados!</p>");
            }

        %>

        <%  if (blnConectado) {%>
        <p class="TituloAplicacao">SGC - Sistema de Gestão de Clínicas 1.0</p>
        <p class="TituloPagina">Exclusão de Especialidade< / p >
        <form name="formExcluirEspecialidade" method="post" action="excluirEspecialidade" target="_parent">
            <p>Especialidade: <%=strDescricao%></p>
            <p><input type="hidden" name="codigo_especialidade" value="<%=intCodigoEspecialidade%>"/></p>
            <br>
            <p><input type="submit" name="btnExcluir" value="Excluir"/>
                <span class="LinkVoltar"><a href="javascript:history.back()">[Voltar]</a></span>
            </p>

        </form>
        <%}%>


    </body>
</html>
