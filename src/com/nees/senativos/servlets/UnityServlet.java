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
 * This class gets given emotions from server
 * and put in a queue
 */
@WebServlet("/UnityServlet")
public class UnityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Queue<String> queue =  EmotionsHandler.getEmotions();
	
    public UnityServlet() {
        super();
    }

    /**
     * Pattern:
     * .../UnityBridge/UnityServlet?emotion=emotion_name
     * 
     * 
     */
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * if verbParam == getemotion 
		 * 		if queue is empty 
		 * 			send neutral
		 * 		else
		 * 			peak and remove
		 * else
		 * 		bad reques
		 *   
		 */
		final String verbParam = request.getParameter("verb");
		final OutputStream out = response.getOutputStream();
		if(verbParam.equals("getemotion")) {
			if(!isEmotionQueueEmpty()) {
				while(!isEmotionQueueEmpty()) {
					((ServletOutputStream) out).print(messageBundle());
					break;
				}
			}
			else {
				((ServletOutputStream) out).print("neutral");
				response.getWriter().println("Queue empty, sending neutral");
			}
		} else {
			//bad request
			((ServletOutputStream) out).print("neutral");
		}
		
	}

	private String  messageBundle() {
		String msg = queue.peek();
		queue.remove();
		return msg;
	}
	
	private boolean isEmotionQueueEmpty() {
		return queue.isEmpty();
	}
	
}
