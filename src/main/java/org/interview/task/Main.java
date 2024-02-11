package org.interview.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.interview.task.TransposeNotes.checkRange;
import static org.interview.task.TransposeNotes.transposePiece;

public class Main {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java TransposeNotes input_file semitones output_file");
            System.exit(1);
        }

        String inputFileName = args[0];
        int semitones = Integer.parseInt(args[1]);
        String outputFileName = args[2];

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

            List<List<Integer>> piece = objectMapper.readValue(new File(inputFileName),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, List.class));

            List<List<Integer>> transposedPiece = transposePiece(piece, semitones);

            if (!checkRange(transposedPiece)) {
                System.out.println("Error: Transposed notes fall out of the keyboard range.");
                System.exit(1);
            }

            objectMapper.writeValue(new File(outputFileName), transposedPiece);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
