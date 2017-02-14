package com.sdzee.tp.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.tp.beans.Client;
import com.sdzee.tp.forms.CreationClientForm;

/**
 * Servlet implementation class CreationClient
 */
@WebServlet("/creationClient")
public class CreationClient extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String ATT_CLIENT = "client";
	public static final String ATT_FORM = "form";
	public static final String VUE_SUCCES = "/WEB-INF/afficherClient.jsp";
	public static final String VUE_FORM = "/WEB-INF/creerClient.jsp";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreationClient() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/* Affichage de la page d'inscription */
		this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /* Préparation de l'objet formulaire */
		CreationClientForm form = new CreationClientForm();

        /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
		Client client = form.insertClient(request);

		/* Stockage du résultat et des messages d'erreur dans l'objet request */
		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_CLIENT, client);

		/* Transmission de la paire d'objets request/response à notre JSP */
		if (form.getErreurs().isEmpty()) {
			this.getServletContext().getRequestDispatcher(VUE_SUCCES).forward(request, response);		
		} else {
			this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);			
		}

		
	}

}
