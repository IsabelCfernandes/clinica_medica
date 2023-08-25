<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="model.c_funcionarios"%>
<jsp:useBean id="conexao" scope="page" class="banco_dados.conexaoBancoDados"/>
<jsp:useBean id="funcionario" scope="page" class="banco_dados.funcionarios"/> 
<jsp:useBean id="converter" scope="page" class="util.conversao"/> 

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SGC - Versão 1.0</title>
        <link href="clinica_medica.css" rel="stylesheet" type="text/css" />
    </head>
    <body class="FundoPagina">


    <body>
        <%
            ResultSet rsRegistro;
            Date dtDataNascimento;
            int intDia, intMes, intAno;
            boolean blnConectado;
            c_funcionarios Funcionario = new c_funcionarios();
            int intCodigoFuncionario = Integer.parseInt((request.getParameter("codigo_funcionario")));
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
                out.println("<p>Falha na conexão com o banco de dados! </p>");
            }
        %>

        <%  if (blnConectado) {%>

        <p class="TituloAplicacao">SGC - Sistema de Gestão de Clínicas 1.0</p>
        <p class="TituloPagina">Exclusão de Funcionários - Exclusão< / p >
        <form name="formExcluirFuncionario" method="post" action="excluirFuncionario" target="_parent">
            <h2>Tem certeza que quer excluir o funcionário abaixo? <br></h2>
            <p>Nome do Funcionário: <%=Funcionario.getNomeCompleto()%></p>
            <p><input type="hidden" name="codigo_funcionario" value="<%=intCodigoFuncionario%>"/></p>
            <br>
             <p><input type="submit" name="btnExcluir" value="Excluir"/>
            <span class="LinkVoltar"><a href="javascript:history.back()">[Voltar]</a></span>
        </p>
    </form>
    <p class="RodapePagina">Copyright(c) 2023</p>
    <%}%>
    </body>
</html>
