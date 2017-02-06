<%@page import="com.virtualcoach.model.Rosa"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ page import="com.virtualcoach.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>virtualcoach/home</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="http://code.jquery.com/jquery-2.1.3.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/typeahead.js/0.11.1/typeahead.bundle.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/corejs-typeahead/0.11.1/bloodhound.min.js"></script>
<link href="css/index.css" rel="stylesheet" type="text/css">
<link href="css/table.css" rel="stylesheet" type="text/css">
</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<span class="navbar-brand">Virtual Coach 2K17</span>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li>
						<div id="prefetch">
							<form class="navbar-form navbar-left" action="aggiungiGiocatore.do" method="post">
        						<div class="form-group input-group">
									<input class="typeahead" type="text" placeholder="Inserisci qui il giocatore" name="giocatore_da_aggiungere" /> 
									<input type="submit" name="submit" value="Inserisci"/>
								</div>
								<script src="javascript/suggestions.js"></script>
							</form>
						</div>
					</li>
					<li>								
						<form class="navbar-form navbar-left" action="calcolaFormazione.do" method="post">
							<div class="form-group input-group">
								<select class="modulo" name="modulo">
									<option value="consigliato">Modulo (Auto)</option>
									<option value="343">3-4-3</option>
									<option value="352">3-5-2</option>
									<option value="433">4-3-3</option>
									<option value="442">4-4-2</option>
									<option value="451">4-5-1</option>
									<option value="541">5-4-1</option>
									<option value="532">5-3-2</option>
									<option value="424">4-2-4</option>
								</select> 
								<input type="submit" name="submit" value="Schiera formazione" />
							</div>
						</form>
					</li>
					<li><a href="importaRosa.do?">Importa Rosa</a></li>
					<c:if test="${rosa != null and !rosa.vuota}">
						<li><a href="esportaRosa.do?">Esporta Rosa</a></li>
					</c:if>
					<c:if test="${rosa != null and !rosa.vuota}">
						<li><a href="rimuoviRosa.do?">Rimuovi Rosa</a></li>
					</c:if>
					<li class="error">
						<font color="red">${errori["giocatore_da_aggiungere"]}</font> 
						<font color="red">${nontrovato}</font>
					</li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container-fluid text-center">
		<div class="row content">
			<div class="col-sm-3 sidenav">
					<br>
					<table class="table-fill">
						<tbody class="table-hover">
							<c:forEach var="giocatoreCorrente" items="${rosa.giocatori}">
								<tr>
									<td>
										<object data="images/giocatori/${giocatoreCorrente.nome}.jpg" class="miniature-m">
  											<object data="images/squadre/${giocatoreCorrente.squadra.nome}.png" class="miniature-m"></object>
										</object>
									</td>
									<td class="text-center"><a href="infoGiocatore.do?giocatore_info=${giocatoreCorrente.nome}"><font color="black">${giocatoreCorrente.nome}</font></a></td> 
									<td>
										<c:if test="${!giocatoreCorrente.jolly}">
											<button color="#24b8ff">${giocatoreCorrente.ruolo}</button>
										</c:if>
										<c:if test="${giocatoreCorrente.jolly}">
											<a href="cambiaRuolo.do?giocatore_da_modificare=${giocatoreCorrente.nome}">
												<button>
													<c:if test="${giocatoreCorrente.ruolo == 'C'}"><font color="blue">C</font></c:if>
													<c:if test="${giocatoreCorrente.ruolo == 'A'}"><font color="blue">A</font></c:if>
												</button>
											</a>
										</c:if>
									</td>
									<td>(${giocatoreCorrente.squadra.nome})</td>
									<td><a href="rimuoviGiocatore.do?giocatore_da_rimuovere=${giocatoreCorrente.nome}"><button><font color="red">X</font></button></a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
			</div>
			<div class="col-sm-7 text-left">
				<c:if test="${formazione != null and not empty formazione}">
					<link href="css/background.css" rel="stylesheet" type="text/css">
					<c:forEach var="lista" items="${formazione}">
						<center class="formazione">
						<c:forEach var="giocatoreInFormazione" items="${lista}">
							<object data="images/giocatori/${giocatoreInFormazione.nome}.jpg" class="miniature">
  								<object data="images/squadre/${giocatoreInFormazione.squadra.nome}.png" class="miniature"></object>
							</object>
							<a href="infoGiocatore.do?giocatore_info=${giocatoreInFormazione.nome}"><font color="black"><button class="giocatore">${giocatoreInFormazione.nome}</button></font></a>
						</c:forEach>
						</center>
					</c:forEach>
					<center class="panchina">
					<c:forEach var="giocatoreNelleRiserve" items="${riserve}">
							<object data="images/giocatori/${giocatoreNelleRiserve.nome}.jpg" class="miniature">
  								<object data="images/squadre/${giocatoreNelleRiserve.squadra.nome}.png" class="miniature"></object>
							</object>
							<a href="infoGiocatore.do?giocatore_info=${giocatoreNelleRiserve.nome}"><font color="black"><button class="riserve">${giocatoreNelleRiserve.nome}</button></font></a></td> 
					</c:forEach>
					</center>
				</c:if>
			</div>
			<c:if test="${giocatoreSelezionato != null}">
				<div class="col-sm-2 sidenav-right" align="left">
					<center>
						<object data="images/giocatori/${giocatoreSelezionato.nome}.jpg" class="miniature-l">
  							<object data="images/squadre/${giocatoreSelezionato.squadra.nome}.png" class="miniature-l" alt=""></object>
						</object>
					</center>
					<p><b>NOME:</b> ${giocatoreSelezionato.nome}</p> 
		   			<p><b>RUOLO:</b> ${giocatoreSelezionato.ruolo}</p>
					<p><b>QUOT. INIZ:</b> ${giocatoreSelezionato.quotazione_iniziale}, 
		   			<b>QUOT. ATT:</b> ${giocatoreSelezionato.quotazione_attuale}</p>
		   			<p><b>FANTAMEDIA:</b> ${giocatoreSelezionato.fantamedia}</p>
					<p><b>DISPONOBILE:</b><c:if test="${giocatoreSelezionato.disponibile}">SI </c:if>
		   								  <c:if test="${!giocatoreSelezionato.disponibile}">NO </c:if></p>
					<c:if test="${giocatoreSelezionato.disponibile}">   
					   <p><b>TITOLARE:</b><c:if test="${giocatoreSelezionato.titolare}">SI</c:if>
		   	   							  <c:if test="${!giocatoreSelezionato.titolare}">NO</c:if></p>
		   	   		   <p><b>PERC. DI GIOCARE/ENTRARE:</b> ${giocatoreSelezionato.percentuale}</p>
		   	   		</c:if>
					<p><b>SQUADRA:</b> ${giocatoreSelezionato.squadra.nome}</p> 
		   			<p><b>PUNTI:</b> ${giocatoreSelezionato.squadra.punti}</p> 
		   			<p><b>ULTIME 5 PARTITE:</b> ${giocatoreSelezionato.squadra.andamento}</p>
					<p><b>PROS. PARTITA
		   				<c:if test="${giocatoreSelezionato.squadra.giocaInCasa}"> (CASA): </c:if>
		   				<c:if test="${!giocatoreSelezionato.squadra.giocaInCasa}"> (TRAS): </c:if></b>
		   					${giocatoreSelezionato.squadra.prossimo_avversario.nome}</p> 
		  			<p><b>PUNTI:</b> ${giocatoreSelezionato.squadra.prossimo_avversario.punti}</p>
		   			<p><b>ULTIME 5 PARTITE:</b> ${giocatoreSelezionato.squadra.prossimo_avversario.andamento}</p>
		   			<p><b>INDICE VC: </b>${giocatoreSelezionato.risultato_finale}</p>
    			</div>
    		</c:if>
		</div>
	</div>

	<footer class="container-fluid text-center">
	</footer>

</body>
</html>
