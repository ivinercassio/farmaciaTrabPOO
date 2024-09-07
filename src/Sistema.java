import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sistema {
    private List<Medicamento> vetMedicamentos;
    private List<Funcionario> vetFuncionarios;
    private List<Compra> vetCompras;
    private Funcionario logado;
    private static Sistema instance;
    private static Scanner scanner;
    private String entradaUsu;

    public static Sistema getInstance(){
        if (instance == null) 
            instance = new Sistema();
        return instance;
    }

    private Sistema(){
        this.vetMedicamentos = new ArrayList<Medicamento>();
        this.vetFuncionarios = new ArrayList<Funcionario>();
        this.vetCompras = new ArrayList<Compra>();
        scanner = new Scanner(System.in);
    }

    public void init(){
        // INICIALIZAR COM 3 FUNCIONARIOS
        Funcionario func = Gerente.getInstance("Marcos", 1500, "15/05/2020", "123");
        vetFuncionarios.add(func);
        func = Vendedor.getInstance("Joana", 1100, "10/09/2022", "123");
        vetFuncionarios.add(func);
        func = Vendedor.getInstance("Getulio", 1100, "08/12/2015", "123");
        vetFuncionarios.add(func);

        // INICIALIZAR COM 5 MEDICAMENTOS
        Medicamento med = Medicamento.getInstance("Neosaldina", 9.5f);
        vetMedicamentos.add(med);
        med = Medicamento.getInstance("Neosoro", 12.3f);
        vetMedicamentos.add(med);
        med = Medicamento.getInstance("Glifage", 19.6f);
        vetMedicamentos.add(med);
        med = Medicamento.getInstance("Dipirona", 23.68f);
        vetMedicamentos.add(med);
        med = Medicamento.getInstance("Cimegripe", 22.15f);
        vetMedicamentos.add(med);

        //INICIALIZAR COM 2 VENDAS
        List<Item> vetItems = new ArrayList<>();
        Item itens = Item.getInstance("Neosaldina", vetMedicamentos.get(0).getValorAtual()*130, 130);
        vetItems.add(itens);
        itens = Item.getInstance("Dipirona", vetMedicamentos.get(3).getValorAtual()*50, 50);
        vetItems.add(itens);
        Cliente clientNome = Cliente.getInstance("Paulo");
        Compra compras = Compra.getInstance(clientNome, "Joana", vetItems.toArray(new Item[0]));
        vetCompras.add(compras);

        vetItems = new ArrayList<>();
        itens = Item.getInstance("Glifage", vetMedicamentos.get(2).getValorAtual()*99, 99);
        vetItems.add(itens);
        itens = Item.getInstance("Neosoro", vetMedicamentos.get(1).getValorAtual()*10, 10);
        vetItems.add(itens);
        itens = Item.getInstance("Cimegripe", vetMedicamentos.get(4).getValorAtual()*60, 60);
        vetItems.add(itens);
        clientNome = Cliente.getInstance("Mathias");
        compras = Compra.getInstance(clientNome, "Getulio", vetItems.toArray(new Item[0]));
        vetCompras.add(compras);

        imprimeVetor();
    }

    // APAGAR ESSA FUNÇÃO DEPOIS
    private void imprimeVetor(){
        System.out.println("FUNCIONARIOS");
        for (int i = 0; i < vetFuncionarios.size(); i++) 
            System.out.println("COD: " + vetFuncionarios.get(i).idPessoa + " - NOME: " + vetFuncionarios.get(i).nome);
        
        System.out.println("MEDICAMENTOS");
        for (int i = 0; i < vetMedicamentos.size(); i++) 
            System.out.println("COD: " + vetMedicamentos.get(i).getIdMedicamento() + " - NOME: " + vetMedicamentos.get(i).getNome());
        System.out.println();
        
        System.out.println("VENDAS");
        for (int i = 0; i < vetCompras.size(); i++){
            System.out.println("Vendedor: "+vetCompras.get(i).getFuncionarioNom());
            System.out.println("Id: "+vetCompras.get(i).getIdCompra());
            System.out.println("Nome do Cliente: "+vetCompras.get(i).getCliente().nome);
    
            Item[] itens = vetCompras.get(vetCompras.get(i).getIdCompra()-1).getItens();
            for (Item item : itens) {
                if (item != null) {
                    System.out.println("Nome do Medicamento: " + item.getMedicamentoNom());
                    System.out.println("Quantidade: " + item.getQuantidade());
                    System.out.println("Valor Pago: " + item.getValorPago());
                    }
                }
        }
    }

    public void menuPrincipal(){
        int escolha;
        do {
            System.out.println("\n----------- MENU PRINCIPAL -----------");
            System.out.println("1 - Entrar");
            System.out.println("2 - Encerrar programa");
            System.out.println("--------------------------------------");
            System.out.print("Opção desejada: ");
            escolha = scanner.nextInt();

            if (escolha == 1)
                realizarLogin();
            else 
                System.out.println("FIM!!");
        } while (escolha < 1 || escolha > 2);      
    }

    private void realizarLogin(){
        System.out.println("\n----------- MENU USUÁRIO -----------");
        System.out.print("Nome do Funcionário: ");
        scanner.nextLine(); // limpar scanner
        String nome = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        System.out.println("------------------------------------");
        this.entradaUsu = nome;
        this.logado = login(nome, senha);

        if (this.logado == null){
            System.out.println("Usuário não encontrado!\n");
            menuPrincipal();
        }else if (this.logado instanceof Gerente)
            menuGerente();
        else if(this.logado instanceof Vendedor)
            System.out.println("sou vendedor!");
            menuVendedor();
    }

    private Funcionario login(String nome, String senha){
        for (int i = 0; i < vetFuncionarios.size(); i++) 
            if ((!vetFuncionarios.isEmpty()) && nome.equals(vetFuncionarios.get(i).nome) && senha.equals(vetFuncionarios.get(i).getSenha()))
                return vetFuncionarios.get(i);
        return null;
    }

    private void menuGerente(){
        int escolha;
        do {
            System.out.println("\n---------- MENU GERENTE ---------");
            System.out.println("1 - Gerenciar Medicamentos");
            System.out.println("2 - Gerenciar Funcionários");
            System.out.println("3 - Gerenciar Compras");
            System.out.println("4 - Trocar Usuário");
            System.out.println("5 - Sair");
            System.out.println("---------------------------------");
            System.out.print("Opção desejada: ");
            escolha = scanner.nextInt();
        } while (escolha < 1 || escolha > 5);

        switch (escolha) {
            case 1:
                gerenciarMedicamento();
                break;
            case 2:
                // gerenciarFuncionario();
                break;
            case 3:
                gerenciarCompra();  
                break;
            case 4:
                realizarLogin();
                break;
            default:
                menuPrincipal();
                break;
        }
    }

    private void menuVendedor(){
        int escolha;
        do {
            System.out.println("\n---------- MENU VENDEDOR ---------");
            System.out.println("1 - Realizar Venda");
            System.out.println("2 - Histórico de Vendas");
            System.out.println("3 - Trocar Usuário");
            System.out.println("4 - Sair");
            System.out.println("---------------------------------");
            System.out.print("Opção desejada: ");
            escolha = scanner.nextInt();
        } while (escolha < 1 || escolha > 5);

        switch (escolha) {
            case 1:
                realizarVenda();
                break;
            case 2:
                String semNom = "";
                historicoVendas(semNom);
                break;
            case 3:
                realizarLogin();
                break;
            default:
                menuPrincipal();
                break;
        }
    }

    public void realizarVenda(){
        List<Item> vetItems = new ArrayList<>();
        int continuarCompra = 0;
        int achouMed = 1;
        float valorTotal = 0;
        
        scanner.nextLine(); //Limpar Scanner
        System.out.print("Informe o nome do Cliente: ");
        String nomeCliente = scanner.nextLine();
        Cliente cliente = Cliente.getInstance(nomeCliente);

        while(continuarCompra == 0){
            System.out.print("Informe o nome do Medicamento que deseja comprar: ");
            String nomeMed = scanner.nextLine();

            System.out.print("Informe a quantidade que deseja comprar: ");
            int quantMed = scanner.nextInt();
        
            for(int x = 0; x<vetMedicamentos.size(); x++){
                if(vetMedicamentos.get(x).getNome().equals(nomeMed)){
                    valorTotal = quantMed * vetMedicamentos.get(x).getValorAtual();
                    achouMed = 0;
                    break;
                }   
            }

            if(achouMed == 0){
                Item itens = Item.getInstance(nomeMed, valorTotal, quantMed);
                vetItems.add(itens);
                //System.out.println("Item adicionado");
                achouMed = 1;
            }else{
                System.out.println("Medicamento não encontrado");
            }
            
            
            System.out.print("Parar de comprar sim (digite 1) ou não (digite 0): ");
            int informe = scanner.nextInt();
            scanner.nextLine();
            if(informe == 1){
                continuarCompra = 1;
            }
        }

        Compra compras = Compra.getInstance(cliente, entradaUsu, vetItems.toArray(new Item[0]));
        vetCompras.add(compras);

        System.out.println("Venda Realizada com Sucesso");
        menuVendedor();
    }

    public void historicoVendas(String nomVendedor){
        if(nomVendedor.equals("")){
            System.out.println("Histórico de vendas do/a funcionário/a "+ entradaUsu);    
        }else{
            System.out.println("Histórico de vendas do/a funcionário/a "+ nomVendedor);
        }

        for(int x = 0; x<vetCompras.size(); x++){
            if(vetCompras.get(x).getFuncionarioNom().equals(entradaUsu) || vetCompras.get(x).getFuncionarioNom().equals(nomVendedor)){
                System.out.println("----------------------------------------");
                System.out.println("Id: "+vetCompras.get(x).getIdCompra());
                System.out.println("Nome do Cliente: "+vetCompras.get(x).getCliente().nome);

                Item[] itens = vetCompras.get(vetCompras.get(x).getIdCompra()-1).getItens();
                for (Item item : itens) {
                    if (item != null) {
                        System.out.println("Nome do Medicamento: " + item.getMedicamentoNom());
                        System.out.println("Quantidade: " + item.getQuantidade());
                        System.out.println("Valor Pago: " + item.getValorPago());
                    }
                }
                System.out.println("----------------------------------------");
            }
        }
    }

    public void gerenciarCompra(){
        int escolha;
        do {
            System.out.println("\n---------- MENU GERENTE VENDAS ---------");
            System.out.println("1 - Ver todas as vendas realizadas");
            System.out.println("2 - Ver todas as vendas de um vendedor");
            System.out.println("3 - Ver uma venda pelo id");
            System.out.println("4 - Sair");
            System.out.println("---------------------------------");
            System.out.print("Opção desejada: ");
            escolha = scanner.nextInt();
            scanner.nextLine();
        } while (escolha < 1 || escolha > 4);
        switch (escolha) {
            case 1:
                for(int x = 0; x<vetCompras.size(); x++){
                    System.out.println("----------------------------------------");
                    System.out.println("Vendedor: "+vetCompras.get(x).getFuncionarioNom());
                    System.out.println("Id: "+vetCompras.get(x).getIdCompra());
                    System.out.println("Nome do Cliente: "+vetCompras.get(x).getCliente().nome);
    
                    Item[] itens = vetCompras.get(vetCompras.get(x).getIdCompra()-1).getItens();
                    for (Item item : itens) {
                        if (item != null) {
                            System.out.println("Nome do Medicamento: " + item.getMedicamentoNom());
                            System.out.println("Quantidade: " + item.getQuantidade());
                            System.out.println("Valor Pago: " + item.getValorPago());
                        }
                    }
                    System.out.println("----------------------------------------");
                }
                gerenciarCompra();
                break;
            case 2:
                int achaVendedor = 0;
                System.out.print("Informe o nome do vendedor que deseja ver as vendas: ");
                String vendedorNom = scanner.nextLine();
                for (int i = 0; i < vetFuncionarios.size(); i++){
                    if(vetFuncionarios.get(i).nome.equalsIgnoreCase(vendedorNom)){
                        achaVendedor = 1;
                        historicoVendas(vendedorNom);   
                    }
                }
                if(achaVendedor == 0){
                    System.out.println("Vendedor não encontrado");
                }
                gerenciarCompra();
                break;
            case 3:
                int acharId = 0;
                System.out.print("Informe o id da venda que deseja ver: ");
                int verId = scanner.nextInt();
                scanner.nextLine();
                for(int x = 0; x<vetCompras.size(); x++){
                    if(vetCompras.get(x).getIdCompra() == verId){
                        acharId = 1;
                        System.out.println("----------------------------------------");
                        System.out.println("Id: "+vetCompras.get(x).getIdCompra());
                        System.out.println("Vendedor: "+vetCompras.get(x).getFuncionarioNom());
                        System.out.println("Nome do Cliente: "+vetCompras.get(x).getCliente().nome);
        
                        Item[] itens = vetCompras.get(vetCompras.get(x).getIdCompra()-1).getItens();
                        for (Item item : itens) {
                            if (item != null) {
                                System.out.println("Nome do Medicamento: " + item.getMedicamentoNom());
                                System.out.println("Quantidade: " + item.getQuantidade());
                                System.out.println("Valor Pago: " + item.getValorPago());
                            }
                        }
                        System.out.println("----------------------------------------");
                    }
                }
                if(acharId == 0){
                    System.out.println("Id não localizado");
                }
                gerenciarCompra();
                break;
            default:
                menuGerente();
                break;
        }
    }

    public void gerenciarMedicamento(){
        int escolha;
        do {
            System.out.println("\n---------- MENU MEDICAMENTO ---------");
            System.out.println("1 - Cadastrar Novo Medicamento");
            System.out.println("2 - Editar Medicamento");
            System.out.println("3 - Deletar Medicamento");
            System.out.println("4 - Listar Medicamento Pelo ID");
            System.out.println("5 - Listar Todos Medicamentos");
            System.out.println("6 - Voltar ao Menu Gerente");
            System.out.println("--------------------------------------");
            System.out.print("Opção desejada: ");
            escolha = scanner.nextInt();
        } while (escolha < 1 || escolha > 6);

        switch (escolha) {
            case 1:
                // cadastrar
                System.out.print("\nNome: ");
                scanner.nextLine(); // limpar scanner
                String nome = scanner.nextLine();
                System.out.print("Valor: R$");
                float valor = scanner.nextFloat();
                scanner.nextLine(); // limpar scanner
                System.out.println("Restrições: ");
                String restricao = scanner.nextLine();
                System.out.println("Efeitos Colaterais: ");
                String efeitos = scanner.nextLine();
                Medicamento med = Medicamento.getInstance(nome, valor);
                med.setRestricao(restricao);
                med.setEfeitosColaterais(efeitos);
                if (vetMedicamentos.add(med))
                    System.out.println("\nCadastrado com sucesso!!");
                else 
                    System.out.println("\nErro ao cadastrar!!");
                gerenciarMedicamento();
                break;
            case 2:
                // editar 
                System.out.print("\nID do Medicamento: ");
                int idMedicamento = scanner.nextInt();
                med = vetMedicamentos.get(idMedicamento-1);
                menuEditarMedicamento(med);
                gerenciarMedicamento();
                break;
            case 3:
                // deletar // EXCLUIR 2 VEZES DAH ERRO!! PROBLEMA COM O ID 
                System.out.print("\nID do Medicamento: ");
                idMedicamento = scanner.nextInt();
                if (vetMedicamentos.get(idMedicamento-1) != null){ 
                    vetMedicamentos.remove(idMedicamento-1);
                    System.out.println("\nDeletado com sucesso!!");
                }else 
                    System.out.println("\nErro ao deletar!");
                gerenciarMedicamento();
                break;
            case 4:
                // listar por id 
                System.out.print("\nID do Medicamento: ");
                idMedicamento = scanner.nextInt();
                med = vetMedicamentos.get(idMedicamento-1);
                if (med != null) 
                    System.out.println("ID: " + med.getIdMedicamento() + " - NOME: " + med.getNome());
                else 
                    System.out.println("\nMedicamento não encontrado!");
                gerenciarMedicamento();
                break;
            case 5: 
                // listar todos
                if (!vetMedicamentos.isEmpty()) {
                    System.out.println("\nMEDICAMENTOS");
                    for (int i = 0; i < vetMedicamentos.size(); i++)
                    System.out.println("ID: " + vetMedicamentos.get(i).getIdMedicamento() + " - NOME: " + vetMedicamentos.get(i).getNome());
                }else 
                    System.out.println("Não há medicamentos cadastrados!");
                gerenciarMedicamento();
                break;
            default:
                // voltar
                menuGerente();
                break;
        }
    }

    private void menuEditarMedicamento(Medicamento med){
        int escolha;
        do {
            System.out.println("\n---------- EDITAR MEDICAMENTO ---------");
            System.out.println("1 - Editar Nome");
            System.out.println("2 - Editar Valor");
            System.out.println("3 - Editar Restrições");
            System.out.println("4 - Editar Efeitos Colaterais");
            System.out.println("-----------------------------------------");
            System.out.print("Opção desejada: ");
            escolha = scanner.nextInt();
        } while (escolha < 1 || escolha > 4);

        switch (escolha) {
            case 1:
                System.out.print("\nNovo nome: ");
                scanner.nextLine(); // limpar scanner
                String nome = scanner.nextLine();
                med.setNome(nome);
                break;
            case 2:
                System.out.print("\nNovo valor: R$");
                scanner.nextLine(); // limpar scanner
                Float valor = scanner.nextFloat();
                med.setValorAtual(valor);
                break;
            case 3:
                System.out.println("\nNovas restrições: ");
                String restricao = scanner.nextLine();
                med.setRestricao(restricao);
                break;        
            default:
                System.out.println("\nNovos efeitos colaterais: ");
                String efeito = scanner.nextLine();
                med.setEfeitosColaterais(efeito);
                break;
        }
    }
    
    private void gerenciarFuncionario(){
        int escolha;
        do {
            System.out.println("\n---------- MENU FUNCIONÁRIO ---------");
            System.out.println("1 - Cadastrar Novo Funcionário");
            System.out.println("2 - Editar Funcionário");
            System.out.println("3 - Deletar Funcionário");
            System.out.println("4 - Listar Funcionário Pelo ID");
            System.out.println("5 - Listar Todos Funcionários");
            System.out.println("6 - Voltar ao Menu Gerente");
            System.out.println("--------------------------------------");
            System.out.print("Opção desejada: ");
            escolha = scanner.nextInt();
        } while (escolha < 1 || escolha > 6);

        switch (escolha) {
            case 1:
            // cadastrar
                scanner.nextLine(); // limpar scanner
                System.out.println("Cargo: ");
                String cargo = scanner.next();
                System.out.print("\nNome: ");
                String nome = scanner.nextLine();
                System.out.print("Salário: R$");
                float salario = scanner.nextFloat();
                scanner.nextLine(); // limpar scanner
                System.out.println("Data Admissão: (formato: dd/MM/yyyy)");
                String dtAdmissao = scanner.next();
                System.out.print("Senha: ");
                String senha = scanner.next();
                Funcionario func = null;
                if (cargo.equals("Gerente"))
                    func = Gerente.getInstance(nome, salario, dtAdmissao, senha);
                else if (cargo.equals("Vendedor"))
                    func = Vendedor.getInstance(nome, salario, dtAdmissao, senha);
                System.out.println("Data Demissão: (formato: dd/MM/yyyy)");
                String dtDemissao = scanner.next();
                System.out.print("CPF: ");
                String cpf = scanner.next();
                System.out.print("Cidade: ");
                String cidade = scanner.next();
                System.out.println("Data Nascimento: (formato: dd/MM/yyyy)");
                String dtNasc = scanner.next();
                func.setDtDemissao(dtDemissao);
                func.setCpf(cpf);
                func.setCidade(cidade);
                func.setDtNasc(dtNasc);
                if (vetFuncionarios.add(func))
                    System.out.println("\nCadastrado com sucesso!!");
                else 
                    System.out.println("\nErro ao cadastrar!!");
                gerenciarFuncionario();
                break;
            case 2:
                // editar
                System.out.print("\nID do Funcionário: ");
                int idFuncionario = scanner.nextInt();
                Funcionario funcionario = vetFuncionarios.get(idFuncionario-1);
                menuEditarFuncionario(funcionario);
                break;
            case 3:
                
                break;
            case 4:
                
                break;
            case 5:
                
                break;        
            default:
                // voltar 
                menuGerente();
                break;
        }
    }

    private void menuEditarFuncionario(Funcionario func){
        int escolha;
        do {
            System.out.println("\n---------- MENU FUNCIONÁRIO ---------");
            System.out.println("1 - Editar Nome");
            System.out.println("2 - Editar CPF");
            System.out.println("3 - Editar Cidade");
            System.out.println("4 - Editar Data Nascimento");
            System.out.println("5 - Editar Salário");
            System.out.println("6 - Editar Data Admissão");
            System.out.println("7 - Editar Data Demissão");
            System.out.println("8 - Editar Senha");
            System.out.println("--------------------------------------");
            System.out.print("Opção desejada: ");
            escolha = scanner.nextInt();
        } while (escolha < 1 || escolha > 8);

        switch (escolha) {
            case 1:
                System.out.print("\nNovo nome: ");
                scanner.nextLine(); // limpar scanner
                String nome = scanner.next();
                func.setNome(nome);
                break;
            case 2:
                System.out.print("Novo CPF: ");
                String cpf = scanner.next();
                func.setCpf(cpf);
                break;
            case 3:
                System.out.print("Nova cidade: ");
                String cidade = scanner.nextLine();
                func.setCidade(cidade);
                break;
            case 4: 
                System.out.println("Nova data nascimento: (formato dd/MM/yyyy)");
                String data = scanner.next();
                func.setDtNasc(data);
                break;
            case 5:
                System.out.print("Novo salário: R$");
                Float salario = scanner.nextFloat();
                func.setSalario(salario);
                break;
            default:
                break;
        }
        gerenciarFuncionario();
    }

}
