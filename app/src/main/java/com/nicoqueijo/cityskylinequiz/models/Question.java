package com.nicoqueijo.cityskylinequiz.models;

import com.nicoqueijo.cityskylinequiz.activities.QuizGameActivity;

import java.io.Serializable;
import java.util.List;

/**
 * The model class for each question object. Each question has four possible choices, one of those
 * being the correct choice. Each of those choices is represented by a City object.
 *
 * Serializable must be implemented in order to pass Question objects between intents.
 */
public class Question implements Serializable {

    private City correctChoice;
    private City choice1;
    private City choice2;
    private City choice3;
    private City choice4;

    /**
     * Constructor for the Question object
     *
     * @param choices the list of cities representing the question choices.
     */
    public Question(List<City> choices) {
        correctChoice = choices.get(QuizGameActivity.CORRECT_CHOICE);
        choice1 = choices.get(QuizGameActivity.CHOICE_1);
        choice2 = choices.get(QuizGameActivity.CHOICE_2);
        choice3 = choices.get(QuizGameActivity.CHOICE_3);
        choice4 = choices.get(QuizGameActivity.CHOICE_4);
    }

    /**
     * Accessor for the correct city choice
     *
     * @return the correct city choice
     */
    public City getCorrectChoice() {
        return correctChoice;
    }

    /**
     * Mutator for the correct city choice
     *
     * @param correctChoice the new correct city choice
     */
    public void setCorrectChoice(City correctChoice) {
        this.correctChoice = correctChoice;
    }

    /**
     * Accessor for the first city choice
     *
     * @return the first city choice
     */
    public City getChoice1() {
        return choice1;
    }

    /**
     * Mutator for the first city choice
     *
     * @param choice1 the new first city choice
     */
    public void setChoice1(City choice1) {
        this.choice1 = choice1;
    }

    /**
     * Accessor for the second city choice
     *
     * @return the second city choice
     */
    public City getChoice2() {
        return choice2;
    }

    /**
     * Mutator for the second city choice
     *
     * @param choice2 the new second city choice
     */
    public void setChoice2(City choice2) {
        this.choice2 = choice2;
    }

    /**
     * Accessor for the third city choice
     *
     * @return the third city choice
     */
    public City getChoice3() {
        return choice3;
    }

    /**
     * Mutator for the third city choice
     *
     * @param choice3 the new third city choice
     */
    public void setChoice3(City choice3) {
        this.choice3 = choice3;
    }

    /**
     * Accessor for the fourth city choice
     *
     * @return the fourth city choice
     */
    public City getChoice4() {
        return choice4;
    }

    /**
     * Mutator for the fourth city choice
     *
     * @param choice4 the new fourth city choice
     */
    public void setChoice4(City choice4) {
        this.choice4 = choice4;
    }

}
