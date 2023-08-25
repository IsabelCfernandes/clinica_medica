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

        <%  if (blnConectado) {%>
        <p class="TituloAplicacao">SGC - Sistema de Gestão de Clínicas 1.0</p>
        <p class="TituloPagina">Cadastro de Funcionários - Edição</p >
        <form name="formEditarFuncionario" method="post" action="atualizarFuncionario" target="_parent">

            <p>Nome do Funcionario:
                <input type="text" name="txtNomeFuncionario" size="50" maxlength="50"
                       value="<%=Funcionario.getNomeCompleto()%>"/>
            </p>
            <p>Data de Nascimento (dd/mm/aaaa):
                <input type="text" name="txtDiaNascimento" size="2" maxlength="2"
                       value="<%=Funcionario.getDiaNascimento()%>"/>
                <input type="text" name="txtMesNascimento" size="2" maxlength="2"
                       value="<%=Funcionario.getMesNascimento()%>"/>
                <input type="text" name="txtAnoNascimento" size="4" maxlength="4"
                       value="<%=Funcionario.getAnoNascimento()%>"/>

                <%
                    if (Funcionario.getSexo().equals("M")) {
                %>

                - Sexo:
                <input type="radio" name="rbSexo" value="M" checked="checked"/>Masculino
                <input type="radio" name="rbSexo" value="F"/>Feminino
            </p>

            <%     } else {

            %>
            - Sexo:
            <input type="radio" name="rbSexo" value="M"/>Masculino
            <input type="radio" name="rbSexo" value="F" checked="checked"/>Feminino      
        </p>

        <%                }%>
        <p>Numero do RG:
            <input type="text" name="txtRG" size="12" maxlength="12"
                   value="<%=Funcionario.getNumeroRG()%>"/>

            - Orgão Emissor do RG:
            <input type="text" name="txtOrgaoEmissor" size="12" maxlength="12"
                   value="<%=Funcionario.getOrgaoEmissor()%>"/>

            - CPF:
            <input type="text" name="txtCPF" size="12" maxlength="12"
                   value="<%=Funcionario.getNumeroCPF()%>"/>
        </p>
        <p>Endereço/Numero:
            <input type="text" name="txtEndereco" size="50" maxlength="50"
                   value="<%=Funcionario.getEndereco()%>"/>

            - Numero:
            <input type="text" name="txtNumero" size="12" maxlength="12"
                   value="<%=Funcionario.getNumero()%>"/>
        </p>
        <p>Complemento:
            <input type="text" name="txtComplemento" size="50" maxlength="50"
                   value="<%=Funcionario.getComplemento()%>"/>
        </p>
        <p>Bairro:
            <input type="text" name="txtBairro" size="12" maxlength="12"
                   value="<%=Funcionario.getBairro()%>"/>

            - Cidade:
            <input type="text" name="txtCidade" size="15" maxlength="15"
                   value="<%=Funcionario.getCidade()%>"/>

            - Estado:
            <input type="text" name="txtEstado" size="2" maxlength="2"
                   value="<%=Funcionario.getEstado()%>"/>
        </p>
        <p>Telefone:
            <input type="text" name="txtTelefone" size="50" maxlength="50"
                   value="<%=Funcionario.getTelefone()%>"/>

            - Celular:
            <input type="text" name="txtCelular" size="12" maxlength="12"
                   value="<%=Funcionario.getCelular()%>"/>
        </p>
        <p>CTPS:
            <input type="text" name="txtCtps" size="50" maxlength="50"
                   value="<%=Funcionario.getNumeroCTPS()%>"/>

            - PIS:
            <input type="text" name="txtPis" size="12" maxlength="12"
                   value="<%=Funcionario.getNumeroPIS()%>"/>
        </p>
        <p><input type="hidden" name="codigo_funcionario" size="50" maxlength="50"
                  value="<%=intCodigoFuncionario%>"/>
        </p>
        <br>
        <p><input type="submit" name="btnAtualiza" value="Atualizar"/>
            <span class="LinkVoltar"><a href="javascript:history.back()">[Voltar]</a></span>
        </p>
    </form>
    <p class="RodapePagina">Copyright(c) 2023</p>
    <%}%>
</body>
</html>