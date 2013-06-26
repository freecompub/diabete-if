package com.calcul.diabetif.journal.dao;

import java.util.Date;
import java.util.List;

import com.calcul.diabetif.commun.dao.BaseCommonDAO;
import com.calcul.diabetif.journal.model.Prelevement;

public interface PrelevementDAO extends BaseCommonDAO<Prelevement, Long> {

	public void addPrelevement(Prelevement prelevement);
	public List<Prelevement> getPrelevementListe(Date start, Date end);

}
