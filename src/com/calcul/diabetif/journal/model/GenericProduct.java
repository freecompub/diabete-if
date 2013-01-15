package com.calcul.diabetif.journal.model;


import com.calcul.diabetif.journal.dao.GenericProductDAOImpl;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = GenericProduct.TABLE_NAME, daoClass = GenericProductDAOImpl.class)
public class GenericProduct {

	/* Database Field Names */
	public static final String TABLE_NAME = "GENERIC_PRODUCT";
	public static final String FIELD_ID = "id";
	public static final String FIELD_LABEL = "label";
	public static final String FIELD_ISCROSSED = "isCrossed";
	public static final String FIELD_IS_IN_SHOPPING_LIST = "isInShoppingList";

	@DatabaseField(columnName = FIELD_ID, generatedId = true)
	private Long id;
	@DatabaseField(columnName = FIELD_LABEL)
	private String label;
	@DatabaseField(columnName = FIELD_ISCROSSED, dataType = DataType.BOOLEAN_OBJ)
	private Boolean isCrossed;
	
	@DatabaseField(columnName = FIELD_IS_IN_SHOPPING_LIST, dataType = DataType.BOOLEAN_OBJ)
	private Boolean isInShoppingList;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Boolean getIsCrossed() {
		return isCrossed;
	}

	public void setIsCrossed(Boolean isCrossed) {
		this.isCrossed = isCrossed;
	}

	public Boolean getIsInShoppingList() {
		return isInShoppingList;
	}

	public void setIsInShoppingList(Boolean isInShoppingList) {
		this.isInShoppingList = isInShoppingList;
	}

	
}
