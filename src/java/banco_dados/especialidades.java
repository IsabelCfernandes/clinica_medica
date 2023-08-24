package banco_dados;

import java.sql.*;

/**
 * Classe Especialidades
 */
public class especialidades {

    //atributos privados
    /*
    private Connection conBanco;: Este é um atributo que armazenará
    a conexão com o banco de dados. A variável conBanco é do tipo Connection,
    que é uma classe fornecida pelo driver JDBC para gerenciar a conexão com 
    o banco de dados.
     */
    private Connection conBanco;

    /*
    private PreparedStatement psComando;: Este é um atributo que será
    usado para preparar e executar consultas SQL parametrizadas. A variável
    psComando é do tipo PreparedStatement, que é uma classe fornecida pelo 
    driver JDBC para criar consultas parametrizadas.
     */
    private PreparedStatement psComando;

    /*
    private ResultSet rsRegistros;: Este é um atributo que será usado
    para armazenar os resultados das consultas realizadas no banco de dados.
    A variável rsRegistros é do tipo ResultSet, que é uma classe fornecida
    pelo driver JDBC para representar conjuntos de resultados de consultas.
     */
    private ResultSet rsRegistros;

    /*
    Metodo sem retorno para configurar conexao com o banco usando objeto
    Connection conBanco
     */
    public void configurarConexao(Connection conBanco) {
        this.conBanco = conBanco;
    }

    /*
    Metodo Inserir registro, passando parametro string strDescricao
    retorna booleano.
    
    Este método, inserirRegitro, recebe uma descrição de especialidade
    como parâmetro e tenta inserir um novo registro na tabela
    "especialidades" do banco de dados. Ele constrói um comando
    SQL para inserção, usando a descrição fornecida.
    O método prepareStatement é utilizado para preparar o comando
    SQL para execução, e em seguida executeUpdate é chamado para
    efetivamente executar o comando de inserção. Se a inserção for 
    bem-sucedida, o método retorna true, caso contrário, ele captura 
    exceções (erros) e retorna false, imprimindo o rastreamento do erro.
     */
    public boolean inserirRegitro(String strDescricao) {
        String strComandoSQL;

        try {
            strComandoSQL = "INSERT INTO especialidades(Descricao_Especialidade) VALUES ('" + strDescricao + "')";
            psComando = conBanco.prepareStatement(strComandoSQL);
            psComando.executeUpdate();
            return true;
        } catch (Exception erro) {
            erro.printStackTrace();
            return false;
        }
    }

    public boolean alterarRegistro(int intCodigo, String strDescricao) {
        String strComandoSQL;

        try {
            strComandoSQL = "UPDATE especialidades SET Descricao_Especialidade = '" + strDescricao + "' WHERE Codigo_Especialidade = " + intCodigo;
            psComando = conBanco.prepareStatement(strComandoSQL);
            psComando.execute();
            return true;
        } catch (Exception erro) {
            erro.printStackTrace();
            return false;
        }

    }

    public boolean excluirRegistro(int intCodigo) {
        String strComandoSQL;

        try {
            strComandoSQL = "DELETE FROM especialidades WHERE Descricao_Especialidade = '" + intCodigo;
            psComando = conBanco.prepareStatement(strComandoSQL);
            psComando.execute();
            return true;
        } catch (Exception erro) {
            erro.printStackTrace();
            return false;
        }
    }

    public ResultSet listarRegistro(String strOrdem) {
        String strComandoSQL;

        try {
            if (strOrdem == "CÓDIGO") {
                strComandoSQL = "SELECT * FROM especialidades ORDER BY Codigo_Especialidade";
            } else {
                strComandoSQL = "SELECT * FROM especialidades ORDER BY Descricao_Especialidade";
            }
            psComando = conBanco.prepareStatement(strComandoSQL);
            rsRegistros = psComando.executeQuery();
            return rsRegistros;
        } catch (Exception erro) {
            erro.printStackTrace();
            return null;
        }
    }

}
