package com.example.geektrust;

import com.example.geektrust.service.ExecuteCommands;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        try(Stream<String> lines = Files.lines(new File("sample_input/input2.txt").toPath())){
            List<String> getAllLines = lines.map(String::trim)
                    .filter(string -> !string.matches(" "))
                    .collect(Collectors.toList());
            ExecuteCommands executeCommands = new ExecuteCommands();
            executeCommands.processCommands(getAllLines);
        }
        catch (IOException ioException){
            System.out.println("IOException handled");
        }
    }
}
