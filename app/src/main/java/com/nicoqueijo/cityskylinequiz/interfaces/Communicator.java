package com.nicoqueijo.cityskylinequiz.interfaces;

/**
 * Used to send a result message from a DialogFragment to its hosting activity.
 */
public interface Communicator {
    void onDialogMessage(String message);
}
