import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Funcionario extends Pessoa {
    protected float salario;
    protected Date dtAdmissao;
    protected Date dtDemissao;
    protected String senha;

    protected Funcionario(String nome, float salario, String senha) {
        super(nome);
        this.salario = salario;
        this.dtAdmissao = new Date();
        this.senha = senha;
    }

    protected void setDtDemissao(String dtDemissao) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = dateFormat.parse(dtDemissao);
            if (dtDemissao != null && date.after(dtAdmissao))       
                this.dtDemissao = date;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void setDtAdmissao(String dtAdmissao) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            this.dtAdmissao = dateFormat.parse(dtAdmissao);    
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected String getSenha() {
        return senha;
    }    

    protected void setSalario(float salario) {
        if (salario > 0)
            this.salario = salario;
    }
}
