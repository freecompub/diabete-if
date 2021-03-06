package com.calcul.diabetif.journal.model;


import com.calcul.diabetif.journal.dao.PrelevementDAOImpl;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName = Prelevement.TABLE_NAME, daoClass = PrelevementDAOImpl.class)
public class Prelevement {

    /* Database Field Names */
    public static final String TABLE_NAME = "PRELEVEMENT";
    public static final String FIELD_ID = "id";
    public static final String FIELD_DATE = "prelevementDate";
    public static final String FIELD_BLOOD_GLUCOSE = "bloodGlucose";
    public static final String FIELD_FOOD_GLUCOSE = "foodGlucose";
    public static final String FIELD_INSULIN_FOR_FOOD = "insulinForFood";
    public static final String FIELD_INSULIN_FOR_CORRECTION = "insulinForCorection";
    public static final String FIELD_AFTER_MEAL = "afterMeal";
    public static final String FIELD_BEFORE_MEAL = "beforeMeal";

    @DatabaseField(columnName = FIELD_ID, generatedId = true)
    private Long id;
    @DatabaseField(columnName = FIELD_DATE, dataType = DataType.DATE_LONG)
    private Date prelevementDate;
    @DatabaseField(columnName = FIELD_BLOOD_GLUCOSE, dataType = DataType.DOUBLE)
    private double bloodGlucose;

    @DatabaseField(columnName = FIELD_FOOD_GLUCOSE, dataType = DataType.DOUBLE)
    private double foodGlucose;

    @DatabaseField(columnName = FIELD_INSULIN_FOR_FOOD, dataType = DataType.DOUBLE)
    private double insulinForFood;

    @DatabaseField(columnName = FIELD_INSULIN_FOR_CORRECTION, dataType = DataType.DOUBLE)
    private double insulinForCorection;

    @DatabaseField(columnName = FIELD_AFTER_MEAL)
    private boolean afterMeal;

    @DatabaseField(columnName = FIELD_BEFORE_MEAL)
    private boolean beforeMeal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPrelevementDate() {
        return prelevementDate;
    }

    public void setPrelevementDate(Date prelevementDate) {
        this.prelevementDate = prelevementDate;
    }

    public double getBloodGlucose() {
        return bloodGlucose;
    }

    public void setBloodGlucose(double bloodGlucose) {
        this.bloodGlucose = bloodGlucose;
    }

    public double getFoodGlucose() {
        return foodGlucose;
    }

    public void setFoodGlucose(double foodGlucose) {
        this.foodGlucose = foodGlucose;
    }

    public double getInsulinForFood() {
        return insulinForFood;
    }

    public void setInsulinForFood(double insulinForFood) {
        this.insulinForFood = insulinForFood;
    }

    public double getInsulinForCorection() {
        return insulinForCorection;
    }

    public void setInsulinForCorection(double insulinForCorection) {
        this.insulinForCorection = insulinForCorection;
    }

    public boolean isAfterMeal() {
        return afterMeal;
    }

    public void setAfterMeal(boolean afterMeal) {
        this.afterMeal = afterMeal;
    }

    public boolean isBeforeMeal() {
        return beforeMeal;
    }

    public void setBeforeMeal(boolean beforeMeal) {
        this.beforeMeal = beforeMeal;
    }


}
