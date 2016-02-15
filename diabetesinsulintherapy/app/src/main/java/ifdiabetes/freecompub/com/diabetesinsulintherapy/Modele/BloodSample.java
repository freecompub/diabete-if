package ifdiabetes.freecompub.com.diabetesinsulintherapy.Modele;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.Date;

/**
 * Created by s826210 on 15/02/2016.
 */
@Table(name = "BloodSample")
public class BloodSample extends Model {
    @Column(name = "id", index = true)
    private Long id;

    @Column(name = "prelevementDate")
    private Date prelevementDate;

    @Column(name = "bloodGlucoseLevels")
    private double bloodGlucoseLevels;

    @Column(name = "CarbohydrateFoods")
    private double CarbohydrateFoods;

    @Column(name = "insulinForFood")
    private double insulinForFood;

    @Column(name = "insulinForCorection")
    private double insulinForCorection;

    @Column(name = "afterMeal")
    private boolean afterMeal;

    @Column(name = "beforeMeal")
    private boolean beforeMeal;


    public void setId(Long id) {
        this.id = id;
    }

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
