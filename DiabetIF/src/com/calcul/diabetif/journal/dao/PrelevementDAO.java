package com.calcul.diabetif.journal.dao;

import com.calcul.diabetif.commun.dao.BaseCommonDAO;
import com.calcul.diabetif.journal.model.Prelevement;

import java.util.Date;
import java.util.List;

public interface PrelevementDAO extends BaseCommonDAO<Prelevement, Long> {

    void addPrelevement(Prelevement prelevement);

    List<Prelevement> getPrelevementListe(Date start, Date end);

}
