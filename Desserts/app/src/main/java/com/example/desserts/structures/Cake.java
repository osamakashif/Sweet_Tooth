package com.example.desserts.structures;

import android.media.Image;

import java.util.List;

/**
 * Cake is a concrete Dessert class inheriting Edible.
 * @author Osama Kashif
 */
public class Cake extends Edible{

    private final int weight;
    private final int slice;

    /**
     * Constructor for Cake
     * @param name - Name of Cake
     * @param Id - ID of Cake
     * @param cost - Cost of Cake
     * @param images - Images for Cake
     * @param basicDescription - Basic Description of Cake
     * @param ingredientsContained - Ingredients contained in Cake
     * @param dietsSuitableFor - Diets Cake is suitable for
     * @param numberViewed - Number of times the Cake is viewed
     * @param peopleServed - People served by one serving of Cake
     * @param weight - Weight of one serving of Cake
     * @param slice - Number of slices in a serving of Cake
     */
    public Cake (String name, long Id, float cost, List<Image> images, String basicDescription, List<String> ingredientsContained, List<String> dietsSuitableFor, long numberViewed, int peopleServed, int weight, int slice) {
        super(name, Id, cost, images, basicDescription, ingredientsContained, dietsSuitableFor, numberViewed, peopleServed);
        this.weight = weight;
        this.slice = slice;
        this.addDescription(this.specificDescription());
    }

    /**
     * Method to construct the customised full description for Cake.
     * @return Full custom description for Cake
     */
    private String specificDescription() {
        String description = "";
        description = description + this.getBasicDescription() + "\n" + this.getAdditionalDetails();
        description = description + "\nWeight: " + weight + "\nSlice(s): " + slice;
        return description;
    }

}