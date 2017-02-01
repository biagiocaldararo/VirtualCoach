package com.virtualcoach.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class AzioneRimuoviRosa extends Azione {

	@Override
	public String esegui(HttpServletRequest request) throws ServletException {
		request.getSession().removeAttribute("rosa");
		return "rimuovi_rosa";
	}
}
