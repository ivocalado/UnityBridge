package com.nees.senativos.servlets;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Queue;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nees.senativos.requesthandler.EmotionsHandler;

/**
 * Servlet implementation class UnityServlet
 * 
 * This class gets given emotions from server and put in a queue
 */
@WebServlet("/UnityServlet")
public class UnityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Queue<String> queue = EmotionsHandler.getInstance().getEmotions();

	public UnityServlet() {
		super();
	}

	/**
	 * Pattern: ../UnityBridge/OntologyServlet?verb=giveemotion
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final OutputStream out = response.getOutputStream();
		if (!isEmotionQueueEmpty())
			((ServletOutputStream) out).print(messageBundle());
		else
			((ServletOutputStream) out).print("neutral");
	}

	private String messageBundle() {
		String msg = queue.peek();
		queue.remove();
		return msg;
	}

	private boolean isEmotionQueueEmpty() {
		return queue.isEmpty();
	}

}
