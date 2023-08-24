package model;

public class c_funcionarios extends c_pessoa {

    private int codigo_funcionario;
    private String numero_ctps;
    private String numero_pis;

    public c_funcionarios() {

        //pega da classe super os 16 outros parâmetros
        super("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "");
        //adiciona mais esses dois
        this.numero_ctps = "";
        this.numero_pis = "";
    }

    public c_funcionarios(
            String nome_completo,
            String numero_rg,
            String orgao_emissor,
            String numero_cpf,
            String endereco,
            String numero,
            String complemento,
            String bairro,
            String cidade,
            String estado,
            String telefone,
            String celular,
            String sexo,
            String numero_ctps,
            String numero_pis,
            String dia_nascimento,
            String mes_nascimento,
            String ano_nascimento
    ) {
        super(nome_completo, numero_rg, orgao_emissor, numero_cpf, endereco,
                numero, complemento, bairro, cidade, estado, telefone, celular,
                sexo, dia_nascimento, mes_nascimento, ano_nascimento);
        this.numero_ctps = numero_ctps;
        this.numero_pis = numero_pis;
    }

    public int getCodigoFuncionario() {
        return codigo_funcionario;
    }

    public void setCodigoFuncionario(int codigo_funcionario) {
        this.codigo_funcionario = codigo_funcionario;
    }

    public String getNumeroCTPS() {
        return numero_ctps;
    }

    public void setNumeroCTPS(String numero_ctps) {
        this.numero_ctps = numero_ctps;
    }

    public String getNumeroPIS() {
        return numero_pis;
    }

    public void setNumeroPIS(String numero_pis) {
        this.numero_pis = numero_pis;
    }
    
}
