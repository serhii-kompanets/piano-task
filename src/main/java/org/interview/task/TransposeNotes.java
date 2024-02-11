package org.interview.task;

import java.util.ArrayList;
import java.util.List;

public class TransposeNotes {
    private static List<Integer> transposeNote(List<Integer> note, int semitones) {
        int newOctave = note.get(0);
        int newNote = note.get(1) + semitones;

        while (newNote < 1) {
            newNote += 12;
            newOctave -= 1;
        }

        while (newNote > 12) {
            newNote -= 12;
            newOctave += 1;
        }

        List<Integer> newNoteList = new ArrayList<>();
        newNoteList.add(newOctave);
        newNoteList.add(newNote);

        return newNoteList;
    }

    public static List<List<Integer>> transposePiece(List<List<Integer>> piece, int semitones) {
        List<List<Integer>> transposedPiece = new ArrayList<>();
        for (List<Integer> note : piece) {
            transposedPiece.add(transposeNote(note, semitones));
        }
        return transposedPiece;
    }

    public static boolean checkRange(List<List<Integer>> piece) {
        for (List<Integer> note : piece) {
            if (note.get(0) < -3 || note.get(0) > 5 || note.get(1) < 1 || note.get(1) > 12) {
                return false;
            }
        }
        return true;
    }
}
