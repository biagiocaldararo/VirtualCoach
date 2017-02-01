package com.virtualcoach.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.virtualcoach.model.Rosa;
import com.virtualcoach.utils.IO;

public class AzioneEsportaRosa extends Azione {

	@Override
	public String esegui(HttpServletRequest request) throws ServletException {
		String path = IO.selectFile("Seleziona la cartella di destinazione", true);
		
		if(path!=null){
			HttpSession sessione = request.getSession();
			Rosa rosa = (Rosa) sessione.getAttribute("rosa");
			IO.exportIntoFile(rosa.getGiocatori(), path);
		}
		
		return "esporta_rosa";
	}

}
