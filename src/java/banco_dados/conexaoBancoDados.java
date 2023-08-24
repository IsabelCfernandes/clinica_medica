package banco_dados;

//importar biblioteca
import java.sql.*;

/*
    Classe publica de Conexao
 */
public class conexaoBancoDados {

    //cria variavel tipo Connection
    Connection conBanco;

    /*
    Método abrir Conexao
     */
    public boolean abrirConexao() {
        //variavel url tipo string com a url de conexao com o bd, passando login e senha
        String url = "jdbc:postgresql://localhost:5432/clinica_medica";

        try {
            //tente, conectar a classe do postgres
            Class.forName("org.postgresql.Driver");
            //a conexão passa a url com link, usuario e senha do bd
            conBanco = DriverManager.getConnection(url,"postgres","123456");
            //se tudo der certo, retorne verdadeiro | no metodo o retorno é obrigatório.
            return true;
        } //cria a variavel erro, tipo Exception como parametro de catch
        catch (Exception erro) {
            //se houver erro ele será impresso no console
            erro.printStackTrace();
            //o valor retornará false
            return false;
        }
    }

    /*
    Método fechar Conexao
     */
    public boolean fecharConexao() {
        try {
            conBanco.close();
            return true;
        } catch (SQLException erro) {
            erro.printStackTrace();
            return false;
        }
    }
    
    /*
    Método retornar Conexao
     */
    //cria um metodo obteConexao tipo de retorno é um objeto Connection
    public Connection obterConexao(){
        //avisa que quando for chamado ele retorne o valor armazenado na
        //variavel conBanco que é se integre perfeitamente ao objeto Connection
        return conBanco;
    }
    
}
