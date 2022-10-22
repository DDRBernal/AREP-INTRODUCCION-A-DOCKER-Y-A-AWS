package co.edu.escuelaing.sparkdockerdemolive;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class RoundRobin {

    public volatile static Map<String, Integer> serverMap = new TreeMap<>();
    private static AtomicInteger indexAtomic = new AtomicInteger(0);

    public RoundRobin() {
        serverMap.put("35000", 1);
        serverMap.put("35001", 2);
        serverMap.put("35002", 3);
    }

    public static String getServer() {
        ArrayList<String> serverList = new ArrayList<>();
        Set<String> serverSet = serverMap.keySet();
        Iterator<String> iterator = serverSet.iterator();
        while(iterator.hasNext()){
            String server = iterator.next();
            Integer weight = serverMap.get(server);
            for (int i = 0; i < weight; i++) {
                serverList.add(server);
            }
        }
        if (indexAtomic.get() >= serverList.size()) {
            indexAtomic.set(0);
        }
        String server = serverList.get(indexAtomic.getAndIncrement());
        return server;
    }

}

