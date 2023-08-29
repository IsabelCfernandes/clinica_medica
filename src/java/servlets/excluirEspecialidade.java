package servlets;

import banco_dados.conexaoBancoDados;
import banco_dados.especialidades;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class excluirEspecialidade extends HttpServlet {

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
        out.println("<title>SGC - Versão 1.0</title>");
        out.println("<link href='clinica_medica.css' rel='stylesheet' type='text/css' />");
        out.println("</head>");
        out.println("<body class='FundoPagina'>");
        out.println("<p class='TituloAplicacao'>SGC - Sistema de Gestão de Clínicas 1.0</p>");
        out.println("<p class='TituloPagina'>Exclusão de Especialidade</p>");

        try {
            conexaoBancoDados conexao = new conexaoBancoDados();
            especialidades especialidade = new especialidades();

            if (conexao.abrirConexao()) {
                especialidade.configurarConexao(conexao.obterConexao());
                if (especialidade.excluirRegistro(Integer.parseInt(request.getParameter("codigo_especialidade"))))
                {
                    out.println("<h2>Registro de especialidade excluído com sucesso!</h2>");
                    out.println("<br><br><br><br>");
                    out.println("<a href='menu_especialidades.html'>Fechar</a>");
                } else {
                    out.println("<h2>Não foi possível excluir o registro da especialidade!</h2>");
                    conexao.fecharConexao();
                }
            } else {
                out.println("<h2>Não foi possível estabelecer conexão com o banco de dados!</h2>");

            }
        } catch (Exception erro) {
            erro.printStackTrace();
            out.println("<h2>Erro do sistema: processo de exclusão de especialidade!</h2>");
        }

        out.println("<p class='RodapePagina'>Copyright(c) 2023</p>");
        out.println("</body>");
        out.println("</html>");

    }

}
