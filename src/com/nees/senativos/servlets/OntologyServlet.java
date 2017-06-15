package com.nees.senativos.servlets;

import java.io.IOException;
import java.util.Queue;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nees.senativos.requesthandler.EmotionsHandler;

/**
 * Servlet implementation class OntologyServlet
 */
@WebServlet("/OntologyServlet")
public class OntologyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Queue<String> queue = EmotionsHandler.getEmotions();
	
    public OntologyServlet() {
        super();
    }

    /**
     * Pattern:
     * .../UnityBridge/OntologyServlet?verb=giveemotion
     * 
     */
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		String givenEmotion = request.getParameter("emotion");
		
		queue.add(givenEmotion);
		
		response.getWriter().println(givenEmotion + " was added");
	}
	
}
