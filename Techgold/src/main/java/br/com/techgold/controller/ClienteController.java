package br.com.techgold.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.techgold.dao.ClienteDao;
import br.com.techgold.dao.SolicitacaoDao;
import br.com.techgold.modelo.Cliente;

@Controller
public class ClienteController {
	
	@RequestMapping("/home")
	public String home(HttpSession session, Model model) {
		if (session.getAttribute("clienteLogado") != null) {
			SolicitacaoDao dao = new SolicitacaoDao();
			Cliente cliente = (Cliente) session.getAttribute("clienteLogado");
			model.addAttribute("solicitacoes",
					dao.listaSolicitacoesAbertasPorId(cliente.getId()));
					
			Long ab, and, age;
			SolicitacaoDao daoAberto = new SolicitacaoDao();
			ab = daoAberto.listaQtdSolicitacoesAbertasPorIdDoCliente(cliente.getId());
			model.addAttribute("qtdAberto", ab);
			
			SolicitacaoDao daoAgendadas = new SolicitacaoDao();
			age = daoAgendadas.listaQtdSolicitacoesAgendadasPorIdDoCliente(cliente.getId());
			model.addAttribute("qtdAgendado", age);
			
			SolicitacaoDao daoAndamento = new SolicitacaoDao();
			and = daoAndamento.listaQtdSolicitacoesAndamentoPorIdDoCliente(cliente.getId());
			model.addAttribute("qtdAndamento", and);
			
			model.addAttribute("qtdTotal", ab + age + and);

			return "cliente/home";
		} else {
			return "redirect:login";
		}
	}
	
	@RequestMapping("/atualizarCadastroCliente")
	public String atualizarCadastroCliente(Cliente cliente, HttpSession session) {
		if (session.getAttribute("clienteLogado") != null) {
			ClienteDao dao = new ClienteDao();
			dao.atualizar(cliente);
			return "redirect:home";
		} else {
			return "redirect:atualizarCadastro";
		}
	}
	
	@RequestMapping("/clienteForm")
	public String clienteForm(HttpSession session) {
		if (session.getAttribute("funcionarioLogado") != null) {
			return "admin/cliente-form";
		} else {
			return "redirect:loginFuncionario";
		}
	}
	
	@RequestMapping("/gravarCliente")
	public String gravaUsuario(Cliente cliente) {
		ClienteDao dao = new ClienteDao();
		dao.salvar(cliente);
		return "redirect:clienteForm";
	}
	
	@RequestMapping("/atualizarCadastro")
	public String atualizarDados(HttpSession session) {
		if (session.getAttribute("clienteLogado") != null) {
			return "cliente/atualiza-dados";
		} else {
			return "redirect:login";
		}
	}
	
	@RequestMapping("/atualizarCliente")
	public String atualizar(Cliente cliente, HttpSession session) {
		if (session.getAttribute("funcionarioLogado") != null) {
			ClienteDao dao = new ClienteDao();
			dao.atualizar(cliente);
			return "redirect:clientesList";
		} else {
			return "redirect:atualizarDados";
		}
	}
	
	@RequestMapping("/clientesList")
	public String clientesList(HttpSession session, Model model) {
		if (session.getAttribute("funcionarioLogado") != null) {
			List<Cliente> clientes = new ArrayList<Cliente>();
			ClienteDao dao = new ClienteDao();
			clientes = dao.listaCliente();
			model.addAttribute("clientes", clientes);
			return "admin/cliente-list";
		} else {
			return "redirect:homePage";
		}
	}
	
	@RequestMapping("/clienteEdit")
	public String clienteEdit(Long id, HttpSession session, Model model) {
		if (session.getAttribute("funcionarioLogado") != null) {
			ClienteDao dao = new ClienteDao();
			Cliente clienteEditado = new Cliente();
			clienteEditado = dao.buscarPorId(id);
			model.addAttribute("cliente", clienteEditado);
			return "admin/cliente-edit";
		} else {
			return "redirect:loginFuncionario";
		}
	}
}