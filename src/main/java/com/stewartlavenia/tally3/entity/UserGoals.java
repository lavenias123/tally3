package com.stewartlavenia.tally3.entity;

//import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserGoals {

	private Long goals_pk;
	private Long user_fk;
	private int calorie_limit;
	private int daily_calorie_total;
	private int carbs_grams_limit;
	
	public Long getUser_fk() {
		return user_fk;
	}
	public void setUser_fk(Long user_fk) {
		this.user_fk = user_fk;
	}
	public int getCalorie_limit() {
		return calorie_limit;
	}
	public void setCalorie_limit(int calorie_limit) {
		this.calorie_limit = calorie_limit;
	}
	public int getDaily_calorie_total() {
		return daily_calorie_total;
	}
	public void setDaily_calorie_total(int daily_calorie_total) {
		this.daily_calorie_total = daily_calorie_total;
	}
	public int getCarbs_grams_limit() {
		return carbs_grams_limit;
	}
	public void setCarbs_grams_limit(int carbs_grams_limit) {
		this.carbs_grams_limit = carbs_grams_limit;
	}
	public Long getGoals_pk() {
		return goals_pk;
	}
	public void setGoals_pk(Long goals_pk) {
		this.goals_pk = goals_pk;
	}
	
	/* @JsonIgnore
	public Long getUserPK() {
		return goals_pk;	
	} 
	*/


}