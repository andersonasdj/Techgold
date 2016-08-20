package br.com.techgold.teste;

import java.util.Calendar;

import br.com.techgold.dao.EmailDao;
import br.com.techgold.mail.JavaMailApp;
import br.com.techgold.modelo.Cliente;
import br.com.techgold.modelo.Email;
import br.com.techgold.modelo.Solicitacao;

public class Teste {

		public static void main(String[] args) {
			
			Cliente anderson = new Cliente();
			anderson.setEmail("andersonasdj@gmail.com");
			
			Solicitacao solicitacaoAnderson = new Solicitacao();
			solicitacaoAnderson.setId(1l);
			solicitacaoAnderson.setUsuario("Anderson");
			solicitacaoAnderson.setOnsiteOffsite("onsite");
			solicitacaoAnderson.setDescricaoProblema("Teste de chamado");
			solicitacaoAnderson.setDataAbertura(Calendar.getInstance());
			
			EmailDao dao = new EmailDao();
			Email emailConfig = new Email();
			emailConfig = dao.listaEmailConfigAbertura();
			
			
			System.out.println(emailConfig.getEmail());
			System.out.println(emailConfig.isSslStatus());
			
			
			JavaMailApp mailSend = new JavaMailApp(emailConfig);
			
			mailSend.enviaEmail(anderson, solicitacaoAnderson);
			
			
			
			
			
			
		}
}
