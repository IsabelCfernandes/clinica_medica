<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="conexao" scope="page" class="banco_dados.conexaoBancoDados"/>
<jsp:useBean id="funcionario" scope="page" class="banco_dados.funcionarios"/> 
<jsp:useBean id="converter" scope="page" class="util.conversao"/>
<%@page import="java.sql.ResultSet"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SGC - Versão 1.0</title>
        <link href="clinica_medica.css" rel="stylesheet" type="text/css" />
    </head>
    <body class="FundoPagina">

        <%
            ResultSet rsRegistros;

            if (conexao.abrirConexao()) {

                funcionario.configurarConexao(conexao.obterConexao());
                rsRegistros = funcionario.listarRegistros();

                if (rsRegistros != null) {
                    out.println("<h2><center>Lista de Funcionários </center></h2>");
                    out.println("<br>");
                    out.println("<table align='center' bgcolor='moccasin'>");
                    out.println("<tr>"
                            + "<th>Nome</th>"
                            + "<th>RG</th>"
                            + "<th>CPF</th>"
                            + "<th>Nascimento</th>"
                            + "<th>Endereço</th>"
                            + "</tr>");
                    while (rsRegistros.next()) {
                        out.println("<tr>");
                        out.println("<td>" + rsRegistros.getString("Nome_Completo") + "</td>");
                        out.println("<td>" + rsRegistros.getString("Numero_RG") + "</td>");
                        out.println("<td>" + rsRegistros.getString("Numero_CPF") + "</td>");
                        out.println("<td>" + converter.DateToString(rsRegistros.getDate("Data_Nascimento")) + "</td>");
                        out.println("<td>" + rsRegistros.getString("Endereco") + "</td>");
                        out.println("</tr>");
                    }
                    out.println("</table>");
                    out.println("<br>");
                } else {
                    out.println("<p>Falha na exibição dos Registros!</p>");
                    conexao.fecharConexao();
                }
            } else {
                out.println("<p>Falha na na conexao com o banco de dados!</p>");
            }
        %>

        <span class="LinkVoltar"><a href="javascript:history.back()">[Voltar]</a></span> </p>
    <p class="RodapePagina">Copyright(c) 2023</p>

</body>
</html>
