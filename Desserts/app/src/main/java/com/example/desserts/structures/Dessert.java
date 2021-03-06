package com.example.desserts.structures;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * The Dessert interface is to be used for the dessert items throughout the application and the
 * dependency injection. It mostly has getters for features common for each dessert.
 *
 * @author Osama Kashif
 */
public interface Dessert extends Serializable {

    /**
     * Gets the name of the Dessert.
     *
     * @return String name of Dessert
     */
    public String getName();

    /**
     * Gets the ID of the Dessert.
     *
     * @return long ID.
     */
    public long getId();

    /**
     * Gets the cost of the Dessert.
     *
     * @return double cost.
     */
    public double getCost();

    /**
     * Gets the description of the Dessert.
     *
     * @return String description
     */
    public String getDescription();

    /**
     * Gets some of the ingredients contained in the Dessert for allergy reasons.
     *
     * @return List of Ingredients Contained
     */
    public List<String> getContainedIngredients();

    /**
     * Gets the diets the Dessert is suitable for.
     *
     * @return List of Diets the Dessert is suitable for
     */
    public List<String> getDietsSuitableFor();

    /**
     * Gets the number of times the Dessert has been viewed.
     *
     * @return long number viewed.
     */
    public long getNumberViewed();

    /**
     * Increases the number of people who viewed the app at runtime.
     */
    public void increaseNumberViewed();

    /**
     * Gets the Dessert category.
     *
     * @return - Returns category of the Dessert.
     */
    public String getCategory();

}
