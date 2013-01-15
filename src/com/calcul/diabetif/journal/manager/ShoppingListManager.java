package com.calcul.diabetif.journal.manager;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.calcul.diabetif.commun.context.ApplicationContextSingleton;
import com.calcul.diabetif.commun.util.StringUtil;
import com.calcul.diabetif.journal.dao.DAOFactory;
import com.calcul.diabetif.journal.dao.GenericProductDAO;
import com.calcul.diabetif.journal.model.GenericProduct;

public class ShoppingListManager {

	private static final String TAG = ShoppingListManager.class.getSimpleName();
	public static final String BROADCAST_SHOPPING_LIST_CHANGED = "com.carrefour.fid.android.intent.action.BROADCAST_SHOPPING_LIST_CHANGED";

	private List<GenericProduct> searchingList;

	public static ShoppingListManager manager() {
		return new ShoppingListManager();
	}

	public void insertDataGenericProduct() {
		Log.v(TAG, "insertDataGenericProduct()");
		DAOFactory daoFactory = DAOFactory.getInstance();
		GenericProductDAO genericProductDAO = daoFactory.getGenericProductDao();
		if (genericProductDAO.countGenericProductsInSearchingList() <= 0) {
			Context context = ApplicationContextSingleton
					.getApplicationContext();
			String[] labels = null;//context.getResources().getStringArray(
					//R.array.browser_shoppinglist_genericproduct_init_label);
			
			Log.v(TAG, "initial data for generic product : " + labels.length);

			if (labels != null &&  labels.length > 0) {
				daoFactory.beginTransaction();
				for (int i = 0; i < labels.length; i++) {
					genericProductDAO.addGenericProduct(labels[i]);
				}
				daoFactory.commitTransaction();
			}
		}
	}

	public void initSearchingList() {
		Log.v(TAG, "initSearchingList()");
		searchingList = DAOFactory.getInstance().getGenericProductDao()
				.getSearchingList();
	}
	
	public  List<GenericProduct> getShoppingList() {
		Log.v(TAG, "getShoppingList()");
		return DAOFactory.getInstance().getGenericProductDao()
				.getShoppingList();
	}


	public List<GenericProduct> getsearchingList() {
		Log.v(TAG, "getsearchingList()");
		//initSearchingList();
		return searchingList;
	}

	public void setGenericListByKeyword(String keyword) {
		Log.v(TAG, "setGenericListByKeyword() keyword:" + keyword);

		searchingList = new ArrayList<GenericProduct>();

		if (keyword != null && keyword.length() > 0) {
			String label = StringUtil.capitalize(keyword.trim());

			GenericProduct genericProduct = new GenericProduct();
			genericProduct.setId(null);
			genericProduct.setLabel(label);

			GenericProduct genericProductByKeyword = DAOFactory.getInstance()
					.getGenericProductDao().getGenericProductByKeywordInSearchingList(label);

			searchingList.add(genericProduct);
		}

		searchingList.addAll(DAOFactory.getInstance().getGenericProductDao()
				.getGenericProductListByKeywordInSearchingList(keyword));
	}

	private void sendBroadcastShoppingListChanged() {
		
		Log.v(TAG, "sendBroadcastShoppingListChanged()");
		Intent intent = new Intent();
		intent.setAction(ShoppingListManager.BROADCAST_SHOPPING_LIST_CHANGED);
		ApplicationContextSingleton.getApplicationContext().sendBroadcast(
				intent);
	}
	
    public void addGenericProductToShoppingList(GenericProduct genericProduct) {
    	Log.v(TAG, "addGenericProductToShoppingList(GenericProduct genericProduct)");
        DAOFactory.getInstance().getGenericProductDao().addGenericProductToShoppingList(genericProduct);
    }

    public void updateShoppingListCrosse(GenericProduct genericProduct, Boolean isCrossed) {
    	Log.v(TAG, "updateShoppingListCrosse(GenericProduct genericProduct, Boolean isCrossed)");
        DAOFactory.getInstance().getGenericProductDao().updateCrossingStatus(genericProduct, isCrossed);
    }
    
    public void removeProductFromShoppingList(GenericProduct genericProduct) {
    	Log.v(TAG, "removeProductFromShoppingList(GenericProduct genericProduct)");
        DAOFactory.getInstance().getGenericProductDao().removeProductFromShoppingList(genericProduct);
        sendBroadcastShoppingListChanged();
    }
    
    public void removeAllCrossedProductFromShoppingList() {
    	Log.v(TAG, "removeAllCrossedProductFromShoppingList()");
    	List<GenericProduct> shoppingListe= getShoppingList();
    	for (int i = 0; i < shoppingListe.size(); i++) {
    		if (shoppingListe.get(i).getIsCrossed()) {
    			removeProductFromShoppingList(shoppingListe.get(i));
			}
			
		}
    	sendBroadcastShoppingListChanged();
    }
    
    public boolean isInShoppingList(GenericProduct genericProduct) {
    	Log.v(TAG, "isInShoppingList(GenericProduct genericProduct)");
        return DAOFactory.getInstance().getGenericProductDao().isInShoppingList(genericProduct);
    }
    public boolean isCrossed(GenericProduct genericProduct) {
    	Log.v(TAG, "isCrossed(GenericProduct genericProduct)");
        return DAOFactory.getInstance().getGenericProductDao().isCrossed(genericProduct);
    }
    public long getShoppingListCount() {
    	Log.v(TAG, "getShoppingListCount()");
    	
    	return DAOFactory.getInstance().getGenericProductDao().countShoppingListe();
    }
	
    public long getSearchingListCount() {
    	Log.v(TAG, "getSearchingListCount()");
    	return 0;
    }
    
    public long CountCrossedProduct() {
    	Log.v(TAG, "CountCrossedProduct()");
    	return DAOFactory.getInstance().getGenericProductDao().countCrossedProduct();
    }
    
}
