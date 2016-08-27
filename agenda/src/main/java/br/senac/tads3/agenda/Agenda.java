package br.senac.tads3.agenda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;



public class Agenda extends ConexaoBD{
    
    Scanner entrada = new Scanner(System.in);   
    
    public void incluir(){
        System.out.print("Digite o nome completo do contato: ");    
        String nome = entrada.nextLine();
        System.out.print("Digite a data de nascimento no formato dd/mm/aaaa: ");
        String dataNasc = entrada.nextLine();
        System.out.print("Digite o e-mail: ");
        String email = entrada.nextLine();
        System.out.print("Digite o número do telefone no formato 99 99999-9999");
        String telefone = entrada.nextLine();
        //1 - Abrir Conexao
        PreparedStatement stmt = null;
        Connection conn = null;
        
        String sql = "INSERT INTO TB_CONTATO (NM_CONTATO, DT_NASCIMENTO,"
                + "VL_TELEFONE, VL_EMAIL, DT_CADASTRO)"
                + "VALUES (?,?,?,?,?)";
        
        try {
            conn = obterConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            
            DateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
            Date dataNasc = null;
            
            dataNasc = formatador.parse(strDataNasc);
            stmt.setDate(2, new java.sql.Date(dataNasc.getTime()));
            
            stmt.setString(3, telefone);
            stmt.setString(4, email);
            
            stmt.setDate(5, new java.sql.Date(System.currentTimeMillis()));


        //2 - Executar SQL
            stmt.executeUpdate();
            System.out.println("Contato cadastrado com sucesso");

        }catch(SQLException e){
            
        }catch(ClassNotFoundException e){
            
        }
//3 - Fechar Conexão
    }
    
    public static void main(String[] args) {
        Agenda instancia = new Agenda();
        
        do{
            System.out.println("***** DIGITE UMA OPÇÃO *****");
            System.out.println("(1) Listar contatos");
            System.out.println("(2) Incluir novo contato");
            System.out.println("(9) Sair");
            System.out.print("Opção: ");
            
            int opcao = entrada.nextInt();
            
            switch (opcao) {
                case 1:                    
                    break;
                case 2:
                    instancia.incluir();
                    break;                    
                case 9:
                    System.exit(0);
                    break;
                default:
                    System.out.println("OPÇÃO INVÁLIDA");
            }
        }while(true);
    }
}
