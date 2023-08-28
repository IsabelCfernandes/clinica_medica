package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import banco_dados.conexaoBancoDados;
import banco_dados.especialidades;

public class atualizarEspecialidade extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out;
        response.setContentType("text/html;charset=UTF-8");
        out = response.getWriter();

        String strDescricaoEspecialidade;
        int intCodigoEspecialidade;

        strDescricaoEspecialidade = request.getParameter("txtDescricao");
        intCodigoEspecialidade = Integer.parseInt(request.getParameter("codigo_especialidade"));
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />");
        out.println("<title>SGC - Versão 1.0</title>");
        out.println("<link href='clinica_medica.css' rel='stylesheet' type='text/css'");
        out.println("</head>");
        out.println("<body class='FundoPagina'>");
        out.println("<p class='TituloAplicacao'>SGC - Sistema de gestão de Clínicas 1.0</p>");
        out.println("<p class='TituloPagina'>Cadastro de Especialidades</p>");

        try {
            conexaoBancoDados conexao = new conexaoBancoDados();
            especialidades especialidade = new especialidades();

            if (conexao.abrirConexao()) {
                especialidade.configurarConexao(conexao.obterConexao());
                if (especialidade.alterarRegistro(intCodigoEspecialidade, strDescricaoEspecialidade)) {
                    out.println("<h2>Dados da Especialidade Atualizados com sucesso!</h2>");
                    out.println("<br><br><br><br>");
                    out.println("<a href='menu_especialidades.html'>Fechar</a>");
                } else {
                    out.println("Não foi possível Atualizar a Especialidade!");
                    out.println("<a href='menu_especialidades.html'>Voltar</a>");

                }
            } else {
                out.println("Não foi possível Conectar com o banco de dados!");
                out.println("<a href='menu_especialidades.html'>Voltar</a>");
            }

        } catch (Exception erro) {
            erro.printStackTrace();
            out.println("<h2>Erro do sistema: Processo de Atualização de Funcionário!</h2>");
            out.println("<a href='menu_especialidades.html'>Voltar</a>");

        }

        out.println("<p class='RodapePagina'>Copyright(c) 2023</p>");
        out.println("</body>");
        out.println("</html>");
    }

}
