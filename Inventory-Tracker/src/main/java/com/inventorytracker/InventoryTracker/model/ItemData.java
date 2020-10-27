package com.inventorytracker.InventoryTracker.model;

import java.util.ArrayList;

public class ItemData {


    /**
     * Returns the results of searching the Jobs data by field and search term.
     * <p>
     * For example, searching for employer "Enterprise" will include results
     * with "Enterprise Holdings, Inc".
     *
     * @param column  Job field that should be searched.
     * @param value   Value of the field to search for.
     * @param allJobs The list of jobs to search.
     * @return List of all jobs matching the criteria.
     */
    public static ArrayList<Item> findByColumnAndValue(String column, String value, Iterable<Item> allJobs) {

        ArrayList<Item> results = new ArrayList<>();

        if (value.toLowerCase().equals("all")) {
            return (ArrayList<Item>) allJobs;
        }

        if (column.equals("all")) {
            results = findByValue(value, allJobs);
            return results;
        }
        for (Item item : allJobs) {

            String aValue = getFieldValue(item, column);

            if (aValue != null && aValue.toLowerCase().contains(value.toLowerCase())) {
                results.add(item);
            }
        }

        return results;
    }

    public static String getFieldValue(Item item, String fieldName) {
        String theValue;
        if (fieldName.equals("name")) {
            theValue = item.getName();
        }

        else {

            theValue = "";

        }

        return theValue;
    }

    /**
     * Search all Job fields for the given term.
     *
     * @param value   The search term to look for.
     * @param allJobs The list of jobs to search.
     * @return List of all jobs with at least one field containing the value.
     */
    public static ArrayList<Item> findByValue(String value, Iterable<Item> allJobs) {
        String lower_val = value.toLowerCase();

        ArrayList<Item> results = new ArrayList<>();

        for (Item item : allJobs) {

            if (item.getName().toLowerCase().contains(lower_val)) {
                results.add(item);
            }

        }

        return results;
    }
}