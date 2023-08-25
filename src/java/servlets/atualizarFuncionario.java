package servlets;

import banco_dados.conexaoBancoDados;
import banco_dados.funcionarios;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import model.c_funcionarios;

public class atualizarFuncionario extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out;
        response.setContentType("text/html;charset=UTF-8");
        out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />");
        out.println("<title>SGC - Versão 1.0</title>");
        out.println("<link href='clinica_medica.css' rel='stylesheet' type='text/css'");
        out.println("</head>");
        out.println("<body class='FundoPagina'>");
        out.println("<p class='TituloAplicacao'>SGC - Sistema de gestão de Clínicas 1.0</p>");
        out.println("<p class='TituloPagina'>Cadastro de Funcionários</p>");

        try {

            conexaoBancoDados conexao = new conexaoBancoDados();
            funcionarios funcionario = new funcionarios();

            c_funcionarios Funcionario = new c_funcionarios(request.getParameter("txtNomeFuncionario"),
                    request.getParameter("txtRG"),
                    request.getParameter("txtOrgaoEmissor"),
                    request.getParameter("txtCPF"),
                    request.getParameter("txtEndereco"),
                    request.getParameter("txtNumero"),
                    request.getParameter("txtComplemento"),
                    request.getParameter("txtBairro"),
                    request.getParameter("txtCidade"),
                    request.getParameter("lstEstado"),
                    request.getParameter("txtTelefone"),
                    request.getParameter("txtCelular"),
                    String.valueOf(request.getParameter("rbSexo").charAt(0)),
                    request.getParameter("txtPis"),
                    request.getParameter("txtCtps"),
                    request.getParameter("txtDiaNascimento"),
                    request.getParameter("txtMesNascimento"),
                    request.getParameter("txtAnoNascimento"));
                    Funcionario.setCodigoFuncionario(Integer.parseInt(request.getParameter("codigo_funcionario")));

            if (conexao.abrirConexao()) {
                funcionario.configurarConexao(conexao.obterConexao());
                if (funcionario.alterarRegistro(Funcionario)) {
                    out.println("<h2>Funcionário Atualizado com sucesso!</h2>");
                    out.println("<br><br><br><br>");
                    out.println("<a href='menu_funcionarios.html'>Voltar</a>");
                } else {
                    out.println("Não foi possível Atualizar o Funcionário!");
                    out.println("<a href='menu_funcionarios.html'>Voltar</a>");

                }
            } else {
                out.println("Não foi possível Conectar com o banco de dados!");
                out.println("<a href='menu_funcionarios.html'>Voltar</a>");

            }
        } catch (Exception erro) {
            erro.printStackTrace();
            out.println("<h2>Erro do sistema: Processo de Atualização de Funcionário!</h2>");
            out.println("<a href='menu_funcionarios.html'>Voltar</a>");

        }

        out.println("<p class='RodapePagina'>Copyright(c) 2023</p>");
        out.println("</body>");
        out.println("</html>");

    }

}
