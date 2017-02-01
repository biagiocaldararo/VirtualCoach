package com.virtualcoach.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;

import com.virtualcoach.model.Giocatore;

public class IO {
	public static String selectFile(String title, boolean esporta) {		
		JFileChooser jfc = new JFileChooser();
		jfc.setDialogTitle(title);
		
		if (esporta) {
			jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			jfc.setSelectedFile(new File("rosa.txt"));
		}
		
		int status = jfc.showSaveDialog(null);
		
		if(status == JFileChooser.APPROVE_OPTION){
			return jfc.getSelectedFile().getPath();
		}

		return null;
	}


	public static List<String> importFromFile(String path) {
		List<String> list = new ArrayList<String>();
		BufferedReader br = null;

		try {
			String riga;
			br = new BufferedReader(new FileReader(path));

			while ((riga = br.readLine()) != null) {
				list.add(riga.trim());
			}

		} catch (IOException e) {
			list = null;
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				// DO NOTHING
			}
		}

		return list;
	}

	public static void exportIntoFile(List<Giocatore> rosa, String path) {
		try {
			File file = new File(path);

			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);

			for (Giocatore g : rosa) {
				bw.write(g.getNome());
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			// DO NOTHING
		}
	}
	
	/*
	 * JFileChooser jfc = new JFileChooser(); jfc.setDialogTitle(title); int
	 * status = jfc.showOpenDialog(null); String result;
	 * 
	 * if (status == JFileChooser.APPROVE_OPTION){ if (esporta) {
	 * jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
	 * jfc.setSelectedFile(new File("rosa.txt")); } result =
	 * jfc.getSelectedFile().getPath(); } else { result = null; }
	 * 
	 * return result; }
	 */
}
