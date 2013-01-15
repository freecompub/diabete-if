package com.calcul.diabetif.journal.dao;

import java.sql.SQLException;
import java.util.List;

import android.util.Log;

import com.calcul.diabetif.commun.dao.BaseCommonDAOImpl;
import com.calcul.diabetif.commun.database.DatabaseUtil;
import com.calcul.diabetif.journal.model.GenericProduct;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;



public class GenericProductDAOImpl extends
		BaseCommonDAOImpl<GenericProduct, Long> implements GenericProductDAO {

	private static final String TAG = GenericProductDAOImpl.class
			.getSimpleName();

	public GenericProductDAOImpl(Class<GenericProduct> dataClass)
			throws SQLException {
		super(dataClass);
	}

	public GenericProductDAOImpl(ConnectionSource connectionSource,
			Class<GenericProduct> dataClass) throws SQLException {
		super(connectionSource, dataClass);
	}

	public GenericProductDAOImpl(ConnectionSource connectionSource,
			DatabaseTableConfig<GenericProduct> tableConfig)
			throws SQLException {
		super(connectionSource, tableConfig);
	}

	
	public void addGenericProduct(String label) {
		Log.v(TAG, "addGenericProduct() label:" + label);
		GenericProduct genericProduct = new GenericProduct();
		genericProduct.setLabel(label);
		genericProduct.setIsCrossed(false);
		genericProduct.setIsInShoppingList(false);

		createOrFail(genericProduct);
	}


	public List<GenericProduct> getSearchingList() {
		Log.v(TAG, "getAllGenericProduct()");
		List<GenericProduct> products = null;
		try {
			QueryBuilder<GenericProduct, Long> queryBuilder = queryBuilder();
			queryBuilder.orderBy(GenericProduct.FIELD_LABEL, true);
			queryBuilder.where().eq(GenericProduct.FIELD_IS_IN_SHOPPING_LIST, false);
			PreparedQuery<GenericProduct> preparedQuery = queryBuilder
					.prepare();
			products = query(preparedQuery);
		} catch (SQLException e) {
			DatabaseUtil.throwAndroidSQLException(TAG, e);
		}
		return products;
	}

	

	public List<GenericProduct> getShoppingList() {
		Log.v(TAG, "getAllGenericProductInShoppingList()");
		List<GenericProduct> products = null;
		try {
			QueryBuilder<GenericProduct, Long> queryBuilder = queryBuilder();
			queryBuilder.orderBy(GenericProduct.FIELD_LABEL, true);
			queryBuilder.where().eq(GenericProduct.FIELD_IS_IN_SHOPPING_LIST, true);
			PreparedQuery<GenericProduct> preparedQuery = queryBuilder
					.prepare();
			products = query(preparedQuery);
		} catch (SQLException e) {
			DatabaseUtil.throwAndroidSQLException(TAG, e);
		}
		return products;
	}
	

	public List<GenericProduct> getGenericProductListByKeywordInSearchingList(String keyword) {
		Log.v(TAG, "getGenericProductListByKeywordInSearchingList() keyword:" + keyword);
		List<GenericProduct> products = null;
		try {
			QueryBuilder<GenericProduct, Long> queryBuilder = queryBuilder();
			if (keyword != null && keyword.length() > 0) {
				queryBuilder.where().like(GenericProduct.FIELD_LABEL,
						new SelectArg(keyword + "%")).and().eq(GenericProduct.FIELD_IS_IN_SHOPPING_LIST, false);;
			}
			queryBuilder.orderBy(GenericProduct.FIELD_LABEL, true);
//			queryBuilder.where().eq(GenericProduct.FIELD_IS_IN_SHOPPING_LIST, false);
			products = query(queryBuilder.prepare());
		} catch (SQLException e) {
			DatabaseUtil.throwAndroidSQLException(TAG, e);
		}
		Log.v(TAG, "liste size() :" + products.size());
		return products;
	}

	
	public GenericProduct getGenericProductByKeywordInSearchingList(String keyword) {
		Log.v(TAG, "getGenericProductByKeyword() keyword:" + keyword);
		GenericProduct product = null;
		try {
			QueryBuilder<GenericProduct, Long> queryBuilder = queryBuilder();
			if (keyword != null && keyword.length() > 0) {
				queryBuilder.where().eq(GenericProduct.FIELD_LABEL,
						new SelectArg(keyword)).and().eq(GenericProduct.FIELD_IS_IN_SHOPPING_LIST, false);
			}
			else
			{
				queryBuilder.where().eq(GenericProduct.FIELD_IS_IN_SHOPPING_LIST, false);
			}
			queryBuilder.orderBy(GenericProduct.FIELD_LABEL, true);
			
			product = queryForFirst(queryBuilder.prepare());
		} catch (SQLException e) {
			DatabaseUtil.throwAndroidSQLException(TAG, e);
		}
		return product;
	}


	public long countGenericProductsInSearchingList() {
		Log.v(TAG, "countGenericProducts()");
		long count = -1;
		try {
			QueryBuilder<GenericProduct, Long> queryBuilder = queryBuilder();
			queryBuilder.setCountOf(true);
			count = countOf(queryBuilder.prepare());
		} catch (SQLException e) {
			DatabaseUtil.throwAndroidSQLException(TAG, e);
		}
		return count;
	}


	public void addGenericProductToShoppingList(GenericProduct genericProduct) {
		Log.v(TAG, "addGenericProductToShoppingList()");
		this.updateShoppingList(genericProduct, true);

	}


	public void removeProductFromShoppingList(GenericProduct genericProduct) {
		Log.v(TAG, "removeProductFromShoppingList()");
		this.updateShoppingList(genericProduct, false);

	}


	public boolean isInShoppingList(GenericProduct genericProduct) {
		long count = -1;
		Log.v(TAG, "isInShoppingList()");
		try {
			QueryBuilder<GenericProduct, Long> queryBuilder = queryBuilder();
			queryBuilder.setCountOf(true);
			queryBuilder.where().eq(GenericProduct.FIELD_LABEL,
					new SelectArg(genericProduct.getLabel())).and().eq(GenericProduct.FIELD_IS_IN_SHOPPING_LIST, new SelectArg(Boolean.TRUE));
			count = countOf(queryBuilder.prepare());
		} catch (SQLException e) {
			DatabaseUtil.throwAndroidSQLException(TAG, e);
		}
		return count > 0;
	}
	
	public boolean isCrossed(GenericProduct genericProduct) {
		long count = -1;
		Log.v(TAG, "isInShoppingList()");
		try {
			QueryBuilder<GenericProduct, Long> queryBuilder = queryBuilder();
			queryBuilder.setCountOf(true);
			queryBuilder.where().eq(GenericProduct.FIELD_LABEL,
					new SelectArg(genericProduct.getLabel())).and().eq(GenericProduct.FIELD_ISCROSSED, new SelectArg(Boolean.TRUE));
			count = countOf(queryBuilder.prepare());
		} catch (SQLException e) {
			DatabaseUtil.throwAndroidSQLException(TAG, e);
		}
		return count > 0;
	}
	public void updateShoppingList(GenericProduct genericProduct,
			boolean isInShoppingList) {
		Log.v(TAG, "updateShoppingStrikeout()");
		try {
			UpdateBuilder<GenericProduct, Long> updateBuilder = updateBuilder();
			updateBuilder.updateColumnValue(GenericProduct.FIELD_IS_IN_SHOPPING_LIST,
					isInShoppingList);
			updateBuilder.updateColumnValue(GenericProduct.FIELD_ISCROSSED, false);
			updateBuilder.where().eq(GenericProduct.FIELD_LABEL,
					new SelectArg(genericProduct.getLabel()));
			update(updateBuilder.prepare());
		} catch (SQLException e) {
			DatabaseUtil.throwAndroidSQLException(TAG, e);
		}
	}


	public long countShoppingListe() {
		Log.v(TAG, "countShoppingListe()");
		long count = -1;
		count = this.getShoppingList().size();
		return count;
	}


	public long countSearchingListe() {
		Log.v(TAG, "countSearchingListe()");
		return getSearchingList().size();
	}


	public void updateCrossingStatus(GenericProduct genericProduct,
			Boolean isCrossed) {
		Log.v(TAG, "updateShoppingStrikeout()");
		try {
			UpdateBuilder<GenericProduct, Long> updateBuilder = updateBuilder();
			updateBuilder.updateColumnValue(GenericProduct.FIELD_ISCROSSED,
					isCrossed);
			updateBuilder.where().eq(GenericProduct.FIELD_LABEL,
					new SelectArg(genericProduct.getLabel()));
			update(updateBuilder.prepare());
		} catch (SQLException e) {
			DatabaseUtil.throwAndroidSQLException(TAG, e);
		}
		
	}


	public long countCrossedProduct() {
		Log.v(TAG, "countCrossedProduct()");
		long count = -1;
		try {
			QueryBuilder<GenericProduct, Long> queryBuilder = queryBuilder();
			queryBuilder.setCountOf(true);
			queryBuilder.where().eq(GenericProduct.FIELD_ISCROSSED, true);
			count = countOf(queryBuilder.prepare());
		} catch (SQLException e) {
			DatabaseUtil.throwAndroidSQLException(TAG, e);
		}
		return count;
	}

}