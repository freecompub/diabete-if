package ifdiabetes.freecompub.com.diabetesinsulintherapy.Modele;



import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by s826210 on 15/02/2016.
 */

public class BloodSample extends RealmObject {


    private Date prelevementDate;

    private double bloodGlucoseLevels;

    private double CarbohydrateFoods;

    private double insulinForFood;

    private double insulinForCorection;

    private boolean afterMeal;

    private boolean beforeMeal;

    public Date getPrelevementDate() {
        return prelevementDate;
    }

    public void setPrelevementDate(Date prelevementDate) {
        this.prelevementDate = prelevementDate;
    }

    public double getBloodGlucoseLevels() {
        return bloodGlucoseLevels;
    }

    public void setBloodGlucoseLevels(double bloodGlucoseLevels) {
        this.bloodGlucoseLevels = bloodGlucoseLevels;
    }

    public double getCarbohydrateFoods() {
        return CarbohydrateFoods;
    }

    public void setCarbohydrateFoods(double carbohydrateFoods) {
        CarbohydrateFoods = carbohydrateFoods;
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
