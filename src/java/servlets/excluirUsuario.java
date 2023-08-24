package servlets;

import banco_dados.conexaoBancoDados;
import banco_dados.usuarios;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class excluirUsuario extends HttpServlet {

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
        out.println("<p class='TituloPagina'>Cadastro de Usuários</p>");

        try {
            conexaoBancoDados conexao = new conexaoBancoDados();
            usuarios usuario = new usuarios();
            if (conexao.abrirConexao()) {
                usuario.configurarConexao(conexao.obterConexao());

                if (usuario.excluirRegistro(Integer.parseInt(request.getParameter("codigo_usuario")))) {

                    out.println("<h2>Registro de usuário excluído com sucesso!</h2>");
                    out.println("<br><br><br><br>");
                    out.println("<a href='menu_usuarios.html'>Fechar</a>");

                } else {
                    out.println("<h2>Não foi possível excluir o registro do usuário!</h2>");
                    conexao.fecharConexao();

                }

            } else {
                out.println("<h2>Não foi possível estabelecer conexão com o banco de dados!</h2>");
            }

        } catch (Exception erro) {
            erro.printStackTrace();
            out.println("<h2>Erro do sistema: processo de exclusão de usuário!</h2>");
        }
        out.println("<p class='RodapePagina'>Copyright(c) 2023</p>");
        out.println("</body>");
        out.println("</html>");

    }

}
