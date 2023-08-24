package banco_dados;

import java.sql.*;
import model.c_usuarios;

public class usuarios {

    //ATRIBUTOS da classe
    private Connection conBanco;
    private PreparedStatement psComando;
    private ResultSet rsRegistros;
//--------------------------------------------------------------------------------------

    //METODO de conexao
    public void configurarConexao(Connection conBanco) {
        this.conBanco = conBanco;
    }
//--------------------------------------------------------------------------------------

    //METODO inserir registro
    public boolean inserirRegistro(c_usuarios usuario) {
        String strComandoSQL;

        try {
            // Monta a consulta SQL para inserção de um novo registro de usuário
            strComandoSQL = "INSERT INTO usuarios(Identificacao_Usuario, Senha_Acesso, Cadastro_Funcionario, Cadastro_Usuario, Cadastro_Paciente, Cadastro_Especialidade,"
                    + "Cadastro_Medico, Cadastro_Convenio, Agendamento_Consulta, Cancelamento_Consulta, Modulo_Administrativo, Modulo_Agendamento, Modulo_Atendimento)"
                    + " VALUES('" + usuario.getIdUsuario() + "',"
                    + "'" + usuario.getSenhaAcesso() + "'," + "'"
                    + usuario.getCadastroFuncionario() + "'," + "'"
                    + usuario.getCadastroUsuario() + "'," + "'"
                    + usuario.getCadastroPaciente() + "'," + "'"
                    + usuario.getCadastroEspecialidade() + "'," + "'"
                    + usuario.getCadastroMedico() + "'," + "'"
                    + usuario.getCadastroConvenio() + "'," + "'"
                    + usuario.getAgendamentoConsulta() + "'," + "'"
                    + usuario.getCancelamentoConsulta() + "'," + "'"
                    + usuario.getModuloAdministrativo() + "'," + "'"
                    + usuario.getModuloAgendamento() + "'," + "'"
                    + usuario.getModuloAtendimento() + "')";

            // Prepara a consulta SQL
            psComando = conBanco.prepareStatement(strComandoSQL);
            // Executa a inserção
            psComando.executeUpdate();

            return true;
            
        } catch (Exception erro) {
            erro.printStackTrace();
            return false;
        }
    }
//--------------------------------------------------------------------------------------
    //METODO localizar registro

    public int localizarRegistro(String strUsuario) {

        int intCodigoUsuario = 0;
        String strComandoSQL;

        try {
            // Monta a consulta SQL para localizar o registro do usuário
            strComandoSQL = "SELECT Registro_Usuario FROM usuarios WHERE Identificacao_Usuario = '" + strUsuario + "'";
            // Prepara a consulta SQL
            psComando = conBanco.prepareStatement(strComandoSQL);
            // Executa a consulta
            rsRegistros = psComando.executeQuery();
            // Move para o primeiro resultado
            rsRegistros.next();
            // Obtém o valor do campo "Registro_Usuario"
            intCodigoUsuario = rsRegistros.getInt("Registro_Usuario");

        } catch (Exception erro) {
            erro.printStackTrace();
            //return 0;
        }
        return intCodigoUsuario;
    }
//--------------------------------------------------------------------------------------

    //METODO ler registro
    public ResultSet lerRegistro(int intCodigoUsuario) {

        //variaveis do metodo
        String strComandoSQL;

        try {
            strComandoSQL = "SELECT * FROM usuarios WHERE Registro_Usuario = " + intCodigoUsuario;
            psComando = conBanco.prepareStatement(strComandoSQL);
            rsRegistros = psComando.executeQuery();
            rsRegistros.next();
            return rsRegistros;

        } catch (Exception erro) {
            erro.printStackTrace();
            return null;
        }
    }
//--------------------------------------------------------------------------------------

    //METODO alterar registro
    public boolean alterarRegistro(c_usuarios usuario) {
        String strComandoSQL;

        try {
            // Monta a consulta SQL para inserção de um novo registro de usuário
            strComandoSQL = "UPDATE usuarios SET Identificacao_Usuario = '" + usuario.getIdUsuario() + "',"
                    + "Senha_Acesso = '" + usuario.getSenhaAcesso() + "',"
                    + "Cadastro_Funcionario = '" + usuario.getCadastroFuncionario() + "',"
                    + "Cadastro_Usuario = '" + usuario.getCadastroUsuario() + "',"
                    + "Cadastro_Paciente = '" + usuario.getCadastroPaciente() + "',"
                    + "Cadastro_Especialidade = '" + usuario.getCadastroEspecialidade() + "',"
                    + "Cadastro_Medico = '" + usuario.getCadastroMedico() + "',"
                    + "Cadastro_Convenio = '" + usuario.getCadastroConvenio() + "',"
                    + "Agendamento_Consulta = '" + usuario.getAgendamentoConsulta() + "',"
                    + "Cancelamento_Consulta = '" + usuario.getCancelamentoConsulta() + "',"
                    + "Modulo_Administrativo = '" + usuario.getModuloAdministrativo() + "',"
                    + "Modulo_Agendamento = '" + usuario.getModuloAgendamento() + "',"
                    + "Modulo_Atendimento = '" + usuario.getModuloAtendimento() + "'"
                    + " WHERE Registro_Usuario = " + usuario.getCodigoUsuario();
            psComando = conBanco.prepareStatement(strComandoSQL);
            psComando.executeUpdate();
            return true;
        } catch (Exception erro) {
            erro.printStackTrace();
            return false;
        }

    }
//--------------------------------------------------------------------------------------

    //METODO excluir
    
    public boolean excluirRegistro(int intCodigoUsuario){
        
        String strComandoSQL;
        
        try{
            strComandoSQL = "DELETE FROM usuarios WHERE registro_Usuario = "+intCodigoUsuario;
            psComando = conBanco.prepareStatement(strComandoSQL);
            psComando.executeUpdate();
            return true;
        }catch(Exception erro){
            erro.printStackTrace();
            return false;
        }
    }
    
}
