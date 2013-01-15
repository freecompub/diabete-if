package com.calcul.diabetif.journal.dao;

import java.util.List;

import com.calcul.diabetif.commun.dao.BaseCommonDAO;
import com.calcul.diabetif.journal.model.GenericProduct;

public interface GenericProductDAO extends BaseCommonDAO<GenericProduct, Long> {

	public void addGenericProduct(String label);
	public List<GenericProduct> getSearchingList();
	public List<GenericProduct> getGenericProductListByKeywordInSearchingList(String keyword);
	public List<GenericProduct> getShoppingList();
	public GenericProduct getGenericProductByKeywordInSearchingList(String keyword);
	public void addGenericProductToShoppingList(GenericProduct genericProduct);
	public void updateCrossingStatus(GenericProduct genericProduct, Boolean isCrossed);
	public void removeProductFromShoppingList(GenericProduct genericProduct);
	public boolean isInShoppingList(GenericProduct genericProduct);
	public boolean isCrossed(GenericProduct genericProduct);
	public long countGenericProductsInSearchingList();
	public long countShoppingListe();
	public long countSearchingListe();
	public long countCrossedProduct();
}
