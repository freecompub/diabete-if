package com.calcul.diabetif.journal.dao;

import com.calcul.diabetif.commun.dao.BaseCommonDAO;
import com.calcul.diabetif.journal.model.Prelevement;

public interface PrelevementDAO extends BaseCommonDAO<Prelevement, Long> {

	public void addPrelevement(Prelevement prelevement);

}
