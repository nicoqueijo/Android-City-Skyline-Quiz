package com.nicoqueijo.cityskylinequiz.model;

import java.util.ArrayList;

/**
 * The model class for each question object.
 * COMPLETE JAVADOC @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 */
public class Question {

    private City correctChoice;
    private City choice1;
    private City choice2;
    private City choice3;
    private City choice4;

    /**
     * JAVADOC THIS @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
     */
    public Question(City correctChoice, ArrayList<City> cities) {
        /*
            1) Assign correctChoice to this.correctChoice
            2) Assign correctChoice to a random choice between choice1, choice2, choice3, choice4
            3) Assign three random non-repeating cities to the remaining choices

            **Alternative way of doing this: do the randomization in the previous activity and
            * pass the three random choices instead of the whole city list and doing the randomization
            * here **
         */
    }
}
