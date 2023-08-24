<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.Date" %>
<%@page import="model.c_funcionarios" %>
<jsp:useBean id="conexao" scope="page" class="banco_dados.conexaoBancoDados"/>
<jsp:useBean id="funcionario" scope="page" class="banco_dados.funcionarios"/>
<jsp:useBean id="converter" scope="page" class="util.conversao"/>

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
            Date dtDataNascimento;
            int intDia, intMes, intAno;
            boolean blnConectado;
            c_funcionarios Funcionario = new c_funcionarios();
            int intCodigoFuncionario = Integer.parseInt(request.getParameter("codigo_funcionario"));
            blnConectado = false;
            if (conexao.abrirConexao()) {
                funcionario.configurarConexao(conexao.obterConexao());
                rsRegistro = funcionario.lerRegistro(intCodigoFuncionario);
                dtDataNascimento = rsRegistro.getDate("Data_Nascimento");
                intDia = converter.DiaData(dtDataNascimento);
                intMes = converter.MesData(dtDataNascimento);
                intAno = converter.AnoData(dtDataNascimento);

                Funcionario.setNomeCompleto(rsRegistro.getString("Nome_Completo"));
                Funcionario.setNumeroRG(rsRegistro.getString("Numero_RG"));
                Funcionario.setOrgaoEmissor(rsRegistro.getString("Orgao_Emissor"));
                Funcionario.setNumeroCPF(rsRegistro.getString("Numero_CPF"));
                Funcionario.setEndereco(rsRegistro.getString("Endereco"));
                Funcionario.setNumero(rsRegistro.getString("Numero"));
                Funcionario.setComplemento(rsRegistro.getString("Complemento"));
                Funcionario.setBairro(rsRegistro.getString("Bairro"));
                Funcionario.setCidade(rsRegistro.getString("Cidade"));
                Funcionario.setEstado(rsRegistro.getString("Estado"));
                Funcionario.setTelefone(rsRegistro.getString("Telefone"));
                Funcionario.setCelular(rsRegistro.getString("Celular"));
                Funcionario.setNumeroCTPS(rsRegistro.getString("Numero_CTPS"));
                Funcionario.setNumeroPIS(rsRegistro.getString("Numero_PIS"));
                Funcionario.setDataNascimento(Integer.toString(intDia), Integer.toString(intMes), Integer.toString(intAno));
                Funcionario.setNomeCompleto(rsRegistro.getString("Nome_Completo"));
                Funcionario.setSexo(rsRegistro.getString("Sexo"));

                conexao.fecharConexao();
                blnConectado = true;

            } else {
                out.println("<p>Falha na conexão com obanco de dados!</p>");
            }
        %>

        <%  if (blnConectado) { %>
            <p class="TituloAplicacao">SGC - Sistema de Gestão de Clínicas 1.0</p>
            <p class="TituloPagina">Cadastro de Funcionários - Edição< / p >
            <form name="formEditaFuncionario" method="post" action="atualizaFUncionario" target="_parent">
                
                <p>Nome do Funcionario:
                    <input type="text" name="txtNomeFuncionario" size="50" maxlength="50"/>
                
            </form>
        <%} else {
            
                }%>
        <p class="TituloAplicacao">SGC - Sistema de Gestão de Clínicas 1.0</p>
        <p class="TituloPagina">Cadastro de Funcionários - Edição</p>

        <h1>Hello World!</h1>
    </body>
</html>