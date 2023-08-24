package servlets;

import banco_dados.conexaoBancoDados;
import banco_dados.funcionarios;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import util.conversao;

public class pesquisarFuncionario extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //DECLARACAO DE VARIAVEIS
        ResultSet rsRegistro;
        PrintWriter out;
        String strOpcaoPesquisa, strNome, strRG, strCPF, strCampoPesquisa, strValorPesquisa, strDataNascimento;
        int intCodigoFuncionario;
        conversao Converter = new conversao();

        //ATRIBUINDO VALORES AS VARIAVEIS | TRAZENDO DO FORMULARIO HTML
        strOpcaoPesquisa = request.getParameter("rbOpcaoPesquisa");
        strNome = request.getParameter("txtNomeFuncionario");
        strRG = request.getParameter("txtRG");
        strCPF = request.getParameter("txtCPF");

        if (strOpcaoPesquisa.equals("Nome")) {
            strCampoPesquisa = "NOME";
            strValorPesquisa = strNome;
        } else if (strOpcaoPesquisa.equals("RG")) {
            strCampoPesquisa = "RG";
            strValorPesquisa = strRG;
        } else {
            strCampoPesquisa = "CPF";
            strValorPesquisa = strCPF;
        }

        response.setContentType("text/html;charset=UTF-8");
        out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />");
        out.println("<title>SGC - Versão 1.0</title>");
        out.println("<link href='clinica_medica.css' rel='stylesheet' type='text/css' />");
        out.println("</head>");
        out.println("<body class='FundoPagina'>");
        out.println("<p class='TituloAplicacao'>SGC - Sistema de Gestão de Clínicas 1.0</p>");
        out.println("<p class='TituloPagina'>Pesquisa de Funcionários</p>");

        try {
            conexaoBancoDados conexao = new conexaoBancoDados();
            funcionarios funcionario = new funcionarios();

            if (conexao.abrirConexao()) {
                funcionario.configurarConexao(conexao.obterConexao());
                intCodigoFuncionario = funcionario.localizarRegistro(strCampoPesquisa, strValorPesquisa);

                if (intCodigoFuncionario != 0) {

                    rsRegistro = funcionario.lerRegistro(intCodigoFuncionario);
                    strDataNascimento = Converter.DateToString(rsRegistro.getDate("Data_Nascimento"));

                    out.println("Nome do funcionário: " + rsRegistro.getString("Nome_Completo") + "<br>");
                    out.println("Data de Nascimento: " + strDataNascimento + " - Sexo: " + rsRegistro.getString("Sexo") + "<br>");

                    out.println("RG: " + rsRegistro.getString("Numero_RG") + " - Orgão Emissor: " + rsRegistro.getString("Orgao_Emissor") + "<br>");
                    out.println("CPF: " + rsRegistro.getString("Numero_CPF") + "<br>");
                    
                    
                    out.println("Endereço: " + rsRegistro.getString("Endereco") + " - Numero: " + rsRegistro.getString("Numero") + "<br>");
                    out.println("Complemento: " + rsRegistro.getString("Complemento") + "<br>");
                    out.println("Cidade: " + rsRegistro.getString("Cidade") + " - Estado: " + rsRegistro.getString("Estado") + "<br>");
                    out.println("Telefone: " + rsRegistro.getString("Telefone") + " - Celular: " + rsRegistro.getString("Celular") + "<br>");
                    out.println("CTPS: " + rsRegistro.getString("Numero_CTPS") + " - PIS: " + rsRegistro.getString("Numero_PIS") + "<br>");
                    out.println("<br><br>");
                    out.println("<a href='editar_funcionario.jsp?codigo_funcionario=" + intCodigoFuncionario + "'>[Editar]</a> "
                            + "<a href='excluir_funcionario.jsp?codigo_funcionario=" + intCodigoFuncionario + "'>[Excluir]</a>");
                    out.println("<p class='LinkVoltar'><a href='javascript:history.back()'>[voltar]</a></p>");

                } else {

                    out.println("<h2>Funcionário não encontrado!</h2>");
                    out.println("<br><br><br>");
                    out.println("<p class='LinkVoltar'><a href='javascript:history.back()'>[voltar]</a></p>");

                }

                conexao.fecharConexao();

            } else {
                out.println("<h2>Não foi possivel estabelecer conexão com o banco de dados!</h2>");
            }

        } catch (Exception erro) {
            erro.printStackTrace();
            out.println("<h2>Erro do Sistema: processo de cadastro de usuário!</h2>");
            out.println("<br><br><br>");
            out.println("<p class='LinkVoltar'><a href='javascript:history.back()'>[voltar]</a></p>");

        }

        out.println("<p class='RodapePagina'>Copyright(c) 2023</p>");
        out.println("</body>");
        out.println("</html>");
    }
}
