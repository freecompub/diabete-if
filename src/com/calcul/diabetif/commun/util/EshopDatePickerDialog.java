package com.calcul.diabetif.commun.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;

public class EshopDatePickerDialog extends DatePickerDialog {

	private int maxYear;
	private int maxMonth;
	private int maxDay;
	private Date chosenDate=null;


	public EshopDatePickerDialog(Context context, OnDateSetListener callBack,
			int year, int monthOfYear, int dayOfMonth) {
		super(context, callBack, year, monthOfYear, dayOfMonth);

		// initialize picker with max date (equals today) 
		final Calendar cal = Calendar.getInstance();
		this.maxDay =  cal.get(Calendar.DAY_OF_MONTH);
		this.maxMonth = cal.get(Calendar.MONTH);
		this.maxYear =  cal.get(Calendar.YEAR);
				
		setTitle(DateUtil.formatShortDate(this.maxYear,  this.maxMonth, this.maxDay));
	}

	

	@Override
	public void onDateChanged(DatePicker view, int year, int month, int day) {
		int currentYear = year;
		int currentMonth = month;
		int currentDay = day;
		
		// make sure selected date is before today
		if (year >= this.maxYear) {
			currentYear = this.maxYear;
			if (month >= this.maxMonth) {
				currentMonth = this.maxMonth;
				if (day >= this.maxDay) {
					currentDay = this.maxDay;
				}
			}
		}
		super.onDateChanged(view, currentYear, currentMonth, currentDay);
		
		setTitle(DateUtil.formatShortDate(currentYear, currentMonth, currentDay));
	}
	
//	public void update() {
//		this.updateDate(this.maxYear, this.maxMonth, this.maxDay);
//	}
	
	/** save selected date */
	public void save(int year, int month, int day) {

		this.chosenDate = new Date(new GregorianCalendar(year,month,day).getTimeInMillis());
	}

	/** returns selected date */
	public Date getChosenDate() {
		return this.chosenDate;
	}

}
