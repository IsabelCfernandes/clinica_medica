package banco_dados;

//IMPORTACOES | BIBLIOTECAS | CLASSES
import java.sql.*;
import java.util.Date;
import model.c_funcionarios;
import util.conversao;

public class funcionarios {

    //VARIAVEIS
    private Connection conBanco;
    private PreparedStatement psComando;
    private ResultSet rsRegistros;

    //METODO QUE CONFIGURA CONEXAO
    public void configurarConexao(Connection conBanco) {

        this.conBanco = conBanco;

    }
//--------------------------------------------------------------------------------------

    //METODO INSERIR REGISTRO
    public boolean inserirRegistro(c_funcionarios funcionario) {

        //uso da classe conversao, chamando o metodo conversao para a variavel Converter
        conversao Converter = new conversao();
        Date dtDataNascimento;
        String strComandoSQL, strDataInvertida;

        try {
            //dtDataNascimento tipo date, recebe o getDataNascimento de funcionario convertido p/ string
            dtDataNascimento = Converter.StringToDate(funcionario.getDataNascimento());

            //se ele não for nulo vai ser armazenado também na variavel string data invertida
            //porém usando o metodo DataInvertida para realizar uma conversão que coloca os numeros na
            //ordem aceita pelo banco de dados
            if (dtDataNascimento != null) {
                strDataInvertida = Converter.DataInvertida(dtDataNascimento);
            } else {
                //senao o valor da variavel fica nulo
                strDataInvertida = null;
            }
            //se não estiver nulo a data invertida
            if (!(strDataInvertida.equals("null"))) {
                //receberá ainda aspas do bd.
                strDataInvertida = "'" + strDataInvertida + "'";
            }

            strComandoSQL = "INSERT INTO funcionarios (Nome_Completo, Numero_RG, Orgao_Emissor,"
                    + "Numero_CPF, Endereco, Numero, Complemento, Bairro, Cidade, Estado, Telefone,"
                    + "Celular, Numero_CTPS, Numero_Pis, Sexo, Data_Nascimento)"
                    + "VALUES('" + funcionario.getNomeCompleto() + "',"
                    + "'" + funcionario.getNumeroRG() + "',"
                    + "'" + funcionario.getOrgaoEmissor() + "',"
                    + "'" + funcionario.getNumeroCPF() + "',"
                    + "'" + funcionario.getEndereco() + "',"
                    + "'" + funcionario.getNumero() + "',"
                    + "'" + funcionario.getComplemento() + "',"
                    + "'" + funcionario.getBairro() + "',"
                    + "'" + funcionario.getCidade() + "',"
                    + "'" + funcionario.getEstado() + "',"
                    + "'" + funcionario.getTelefone() + "',"
                    + "'" + funcionario.getCelular() + "',"
                    + "'" + funcionario.getNumeroCTPS() + "',"
                    + "'" + funcionario.getNumeroPIS() + "',"
                    + "'" + funcionario.getSexo() + "',"
                    + strDataInvertida + ")";

            psComando = conBanco.prepareStatement(strComandoSQL);
            psComando.executeUpdate();
            return true;
        } catch (Exception erro) {
            erro.printStackTrace();
            return false;
        }
    }
//--------------------------------------------------------------------------------------

    //METODO LOCALIZAR REGISTRO
    public int localizarRegistro(String strCampo, String strValor) {
        int intCodigoFuncionario = 0;
        String strComandoSQL;

        try {
            if (strCampo.equals("NOME")) {
                strComandoSQL = "SELECT Codigo_Funcionario FROM funcionarios WHERE Nome_Completo LIKE"
                        + "'%" + strValor + "%'";
            } else if (strCampo.equals("RG")) {
                strComandoSQL = "SELECT Codigo_Funcionario FROM funcionarios WHERE Numero_Rg = "
                        + "'" + strValor + "'";
            } else {
                strComandoSQL = "SELECT Codigo_Funcionario FROM funcionarios WHERE Numero_Cpf = "
                        + "'" + strValor + "'";
            }
            psComando = conBanco.prepareStatement(strComandoSQL);
            rsRegistros = psComando.executeQuery();
            rsRegistros.next();

            intCodigoFuncionario = rsRegistros.getInt("Codigo_Funcionario");

        } catch (Exception erro) {
            erro.printStackTrace();
        }
        return intCodigoFuncionario;
    }

    //METODO LER REGISTRO
    public ResultSet lerRegistro(int intCodigoFuncionario) {
        String strComandoSQL;

        try {
            strComandoSQL = "SELECT * FROM funcionarios WHERE Codigo_Funcionario = "
                    + intCodigoFuncionario;

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

    //METODO ALTERAR REGISTRO
    public boolean alterarRegistro(c_funcionarios funcionario) {
        conversao Converter = new conversao();
        Date dtDataNascimento;
        String strDataInvertida, strComandoSQL;

        try {
            //dtDataNascimento tipo date, recebe o getDataNascimento de funcionario convertido p/ string
            dtDataNascimento = Converter.StringToDate(funcionario.getDataNascimento());

            if (dtDataNascimento != null) {
                strDataInvertida = Converter.DataInvertida(dtDataNascimento);
            } else {
                strDataInvertida = null;
            }
            //se não estiver nulo a data invertida
            if (!(strDataInvertida.equals("null"))) {
                //receberá ainda aspas do bd.
                strDataInvertida = "'" + strDataInvertida + "'";
            }else{
                
            }

            strComandoSQL = "UPDATE funcionarios SET Nome_Completo = '" + funcionario.getNomeCompleto()
                    + "',"
                    + "Numero_rg = '" + funcionario.getNumeroRG() + "',"
                    + "Orgão_emissor = '" + funcionario.getOrgaoEmissor() + "',"
                    + "Numero_cpf = '" + funcionario.getNumeroCPF() + "',"
                    + "Endereco = '" + funcionario.getEndereco() + "',"
                    + "Numero = '" + funcionario.getNumero() + "',"
                    + "Complemento = '" + funcionario.getComplemento() + "',"
                    + "Bairro = '" + funcionario.getBairro() + "',"
                    + "Cidade = '" + funcionario.getCidade() + "',"
                    + "Telefone = '" + funcionario.getTelefone() + "',"
                    + "Celular = '" + funcionario.getCelular() + "',"
                    + "Numero_ctps = '" + funcionario.getNumeroCTPS() + "',"
                    + "Numero_pis = '" + funcionario.getNumeroPIS() + "',"
                    + "Sexo = '" + funcionario.getSexo() + "',"
                    + "Data_Nascimento = " + strDataInvertida
                    + "WHERE Codigo_Funcionario = " + funcionario.getCodigoFuncionario();

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
    public boolean excluirRegistro(int intCodigoFuncionario) {

        String strComandoSQL;

        try {
            strComandoSQL = "DELETE FROM funcionarios WHERE registro_Usuario = " + intCodigoFuncionario;
            psComando = conBanco.prepareStatement(strComandoSQL);
            psComando.executeUpdate();
            return true;
        } catch (Exception erro) {
            erro.printStackTrace();
            return false;
        }
    }

    //--------------------------------------------------------------------------------------
    //METODO listar
    public ResultSet listarRegistros() {

        String strComandoSQL;
        try {

            strComandoSQL = "SELECT * FROM funcionarios ORDER BY Nome_Completo";

            psComando = conBanco.prepareStatement(strComandoSQL);
            rsRegistros = psComando.executeQuery();
            return rsRegistros;
        }catch (Exception erro){
            erro.printStackTrace();
            return null;
        }

    }
}
