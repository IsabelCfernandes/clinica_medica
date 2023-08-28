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
            int intCodigoEspecialidade = Integer.parseInt((request.getParameter("codigo_especialidade")));

            if(conexao.abrirConexao()){
            
            
            }

        %>

    </body>
</html>
