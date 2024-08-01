package onlineshop.constants;

public abstract class RegexValidation {

    /* BASE REGEX */
    public static final String TEXT_REGEX = "^[a-zA-Z0-9\\s+\\,\\.\\!\\\"-_]+$";
    public static final String TEXT_REGEX_ERROR_MSG = "Is not a text!";
    public static final String INPUT_REGEX_CHECK_BUTTON = "0|1";
    public static final String INPUT_REGEX_CHECK_BUTTON_ERROR_MSG = "Input value is not correct!";

    /*ID *************************************************************************************************** */
    public static final String ID_REGEX = "^[0-9]{1,5}$";
    public static final String ID_REGEX_ERROR_MSG = "Id must contains digits between 1 and 5 symbols!";
    public static final String ID_MIN_MAX_ERROR_MSG = "Invalid id format!";

    /*USERNAME ********************************************************************************************* */
    public static final String USERNAME_REGEX = "^[a-zA-Z0-9]{3,20}$";
    public static final String USERNAME_REGEX_ERROR_MSG = "Username must contains only letters and digits between 3 and 20 symbols!";
    public static final String USERNAME_MIN_MAX_ERROR_MSG = "Invalid username!";

    /*PASSWORD ********************************************************************************************* */
    public static final String PASSWORD_REGEX = "^[a-zA-Z0-9]{3,35}$";
    public static final String PASSWORD_REGEX_ERROR_MSG = "The password must be between 3 and 35 symbols! It can contains letters and digits.";
    public static final String PASSWORD_MIN_MAX_ERROR_MSG = "Invalid password!";

    /*EMAIL ************************************************************************************************ */
    public static final String EMAIL_REGEX
            = "^[a-zA-Z0-9.!#$%&'*+\\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?" +
            "(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";
    public static final String EMAIL_REGEX_ERROR_MSG = "Email is not valid!";
    public static final String EMAIL_MIN_MAX_ERROR_MSG = "Invalid email!";

    /*FIRST NAME ******************************************************************************************* */
    public static final String FIRST_NAME_REGEX = "^[a-zA-Z]{2,35}$";
    public static final String FIRST_NAME_REGEX_ERROR_MSG = "First name must be only letters and digits, between 2 and 35 symbols!";
    public static final String FIRST_NAME_MIN_MAX_ERROR_MSG = "Invalid username";

    /*LAST NAME ******************************************************************************************** */
    public static final String LAST_NAME_REGEX = "^[a-zA-Z]{2,35}$";
    public static final String LAST_NAME_REGEX_ERROR_MSG = "Last name must be only letters and digits, between 2 and 35 symbols!";
    public static final String LAST_NAME_MIN_MAX_ERROR_MSG = "Invalid lastname.";

    /* CATEGORY NAME **************************************************************************************** */
    public static final String CATEGORY_NAME_REGEX = "^[a-zA-Z ]{2,35}$";
    public static final String CATEGORY_ERROR_MSG = "Invalid category.";

    /* CATEGORY DESCRIPTION **************************************************************************************** */
    public static final String CATEGORY_DESCRIPTION_REGEX = "^[a-zA-Z .!0-9]{2,250}$";
    public static final String CATEGORY_DESCRIPTION_REGEX_ERROR_MSG = "Category description can contains only letters, digits and white spaces!";
    public static final String CATEGORY_DESCRIPTION_MIN_MAX_ERROR_MSG = "Invalid description lenght. It muss be between 2 and 250 symbols!";

    /*PRODUCT NAME **************************************************************************************** */
    public static final String PRODUCT_NAME_REGEX = "^[a-zA-Z .!0-9]{2,20}$";
    public static final String PRODUCT_NAME_REGEX_ERROR_MSG = "Product name can contains only text and digits!";
    public static final String PRODUCT_NAME_MIN_MAX_ERROR_MSG = "Product name length is not in range (2 - 20)!";

    /*PRODUCT DESCRIPTION **************************************************************************************** */
    public static final String PRODUCT_DESCRIPTION_REGEX = "^[a-zA-Z .!0-9]{2,210}$";
    public static final String PRODUCT_DESCRIPTION_REGEX_ERROR_MSG = "Category name can contains only text and digits!";
    public static final String PRODUCT_DESCRIPTION_MIN_MAX_ERROR_MSG = "Category length is not in range (2 - 210)!";

    /* PRODUCT CONDITION **************************************************************************************** */
    public static final String PRODUCT_CONDITION_REGEX = "^[a-zA-Z .!0-9]{2,20}$";
    public static final String PRODUCT_CONDITION_MSG = "Product condition length is not in range (2 - 20)!";

    /*QUANTITY **************************************************************************************** */
    public static final String PRODUCT_QUANTITY_DIGIT_SIZE_INT_MSG = "Length of value is not in range (1 - 2)!";
    public static final String PRODUCT_QUANTITY_MIN_ERROR_MSG = "Quantity is not in correct (min 1).";
    public static final String PRODUCT_QUANTITY_MAX_ERROR_MSG = "Quantity is not in correct (max 10).";

    /*PRICE **************************************************************************************** */
    public static final String PRODUCT_PRICE_DIGIT_SIZE_INT_MSG = "Length of value is not in range (1 - 5 .2)!";
    public static final String PRODUCT_PRICE_MIN_ERROR_MSG = "Price is not in correct (min 0.00).";
    public static final String PRODUCT_PRICE_MAX_ERROR_MSG = "Price is not in correct (max 10000.00).";

    /* ADDRESS **************************************************************************************** */
    public static final String ADDRESS_REGEX = "[a-zA-Z0-9]{10,100}";
    public static final String ADDRESS_ERROR_MSG = "Address length must be between 10 and 100 characters";

}
