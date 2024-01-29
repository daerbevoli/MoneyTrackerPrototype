package be.uantwerpen.fti.ei;

public enum ValidActivityNames  {
    RESTAURANT,
    AIRPLANE,
    FOOD,
    DRINKS,
    TAXI,
    CONCERT;

    /**
     * Check if the provided category name is a valid ActivityCategory.
     * @param categoryName the name of the category to check
     * @return true if valid, false otherwise
     */
    public static boolean contains(String testName) {
        for (ValidActivityNames activity : ValidActivityNames.values()) {
            if (activity.name().equalsIgnoreCase(testName.trim())) {
                return true;
            }
        }
        return false;
    }
}
