package com.example.geektrust;

import java.util.*;
import java.util.stream.Collectors;

public class Ride {
    private static Map<String, List<Integer>> drivers;
    private static Map<String, List<Integer>> riders;
    private static Map<Integer, List<String>> matches;

    Ride(){
        drivers = new HashMap<>();
        riders = new HashMap<>();
        matches = new HashMap<>();
    }

    public void addDriver(String driverID, List<Integer> driverCoordinates){
        drivers.put(driverID, driverCoordinates);
    }

    public void addRider(String riderID, List<Integer> riderCoordinates){
        riders.put(riderID, riderCoordinates);
    }

    public void match(String riderID){
        int noOfRiders = drivers.size();
        int count = 0;
        List<Integer> riderCoordinates = riders.get(riderID);
        for (Map.Entry<String, List<Integer>> mapElement : drivers.entrySet()) {
            if(count == noOfRiders || count >= 5) break;
            List<Integer> driverCoordinates = mapElement.getValue();
            int distance = (int) Math.sqrt((driverCoordinates.get(0) - riderCoordinates.get(0))^2 +
                    (driverCoordinates.get(1) - riderCoordinates.get(1))^2);
            List<String> drivers = new ArrayList<>();
            if(!matches.get(distance).isEmpty()) drivers = matches.get(distance);
            drivers.add(mapElement.getKey());
            matches.put(distance, drivers);
            count++;
        }
        sortMatches();
    }

    

    private void sortMatches(){
        Map<Integer, List<String>> sortedMatches = matches.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));

        for(Map.Entry<Integer, List<String>> mapElements : matches.entrySet()){
            Integer distance = mapElements.getKey();
            List<String> driverIds = mapElements.getValue();
            if(driverIds.size() >= 2){
                Collections.sort(driverIds);
            }
            matches.put(distance, driverIds);
        }
    }
}
