<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="shortcut icon" href="assets/img/ico.png" >
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="assets/css/bootstrap.css">
	<link rel="stylesheet" href="assets/css/style.css">
	<link rel="stylesheet" href="assets/css/bootstrap-responsive.css">
	<link rel="stylesheet" href="assets/css/bootstrap-table.css">
	<title>TechGold | Minhas Solicita��es</title>
</head>
<body>
	<c:import url="barra-menus.jsp"></c:import>
	<br /><br /><br />

	<div class="container">
			<div align="center"><a class="btn btn-danger" href="solicitacoesTecnico" role="button"> ${qtdAberto} Abertas <i class="fa fa-question-circle"></i></a>
			 - <a class="btn btn-info" href="solicitacoesAndamentoTecnico" role="button">${qtdAndamento} Andamento <i class="fa fa-share"></i></a>
			 - <a class="btn btn-warning" href="solicitacoesAgendadosTecnico" role="button">${qtdAgendado} Agendadas <i class="fa fa-clock-o"></i></a>
			 - <a class="btn btn-success" href="solicitacoesAguardandoTecnico" role="button"> ${qtdAguardando} Aguardando <i class="fa fa-thumbs-o-up"></i></a>
			 </div>
		</div>
		<br/>
		<legend></legend>

	<h4>Exportar Solicita��es</h4>
        <div id="toolbar">
            <select class="form-control">
                <option value="">Export Basic</option>
                <option value="all">Export All</option>
                <option value="selected">Export Selected</option>
            </select>
        </div>
	<table id="table"
	               data-toggle="table"
	               data-show-columns="true"
	               data-show-toggle="true"
	               data-pagination="true"
	               data-show-pagination-switch="true"
	               data-show-refresh="true"
	               data-search="true"
	               data-key-events="true"
	               data-show-export="true"
	               data-click-to-select="true"
	               data-toolbar="#toolbar">
	    <thead>
			<tr>
				<th data-field="state" data-checkbox="true"></th>
				<th>Data da Solicita��o</th>
				<th>Site</th>
				<th>Cliente</th>
				<th>Usu�rio Afetado</th>
				<th>Problema Relatado</th>
				<th>Status da Solicita��o</th>
				<th>A��es</th>
			</tr>
		</thead>
		<c:forEach var="solicitacao" items="${solicitacoes}">
			<tr>
				<td></td>
				<td>
					<a class="dcontexto"> <f:formatDate value="${solicitacao.dataAbertura.time}" pattern="dd/MM/yyyy"/>
						<span>- Hora: <f:formatDate value="${solicitacao.dataAbertura.time}" pattern="HH:mm"/> <br/>
							- Aberto por: ${solicitacao.abriuChamado}</span></a>
				</td>
				<td>${solicitacao.onsiteOffsite}</td>
				<td>
					<a class="dcontexto"> ${solicitacao.cliente.nome}
						<span>- Tel.: ${solicitacao.cliente.telefone1} <br/><br/>
							- Endere�o: ${solicitacao.cliente.endereco}</span></a>
				</td>
				<td>${solicitacao.usuario}</td>
				<td>
					<a class="dcontexto"> ${solicitacao.descricaoProblema}
						<span>Resolu��o: ${solicitacao.resolucao} <br/><br/>
							Observa��o: ${solicitacao.obs}</span></a>
				</td>
				<td>
					<a class="dcontexto"> ${solicitacao.status}
						<span>Data: <f:formatDate value="${solicitacao.agendado.time}" pattern="dd/MM/yyyy"/><br/>
								Hora: <f:formatDate value="${solicitacao.agendadoHora.time}" pattern="HH:mm"/></span></a>
				</td>
				<td>
					<a href="solicitacaoEditFuncionario?id=${solicitacao.id}"><i class="fa fa-pencil-square-o fa-lg"></i></a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<br /><br /><br />
	<c:import url="rodape.jsp"></c:import>
</body>
	<script src="assets/js/jquery.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/bootstrap-table.js"></script>
	<script src="assets/js/bootstrap-table-export.js"></script>
	<script src="assets/js/tableExport.js"></script>
	<script src="assets/js/bootstrap-table-key-events.js"></script>
</html>