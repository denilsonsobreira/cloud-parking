package one.digitalinnovation.parking.service;

import one.digitalinnovation.parking.controller.dto.ParkingDTO;
import one.digitalinnovation.parking.model.Parking;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ParkingService {

    private static Map<String, Parking> parkingMap = new HashMap<>();

    static {
        String id1 = getUUID();
        String id2 = getUUID();
        Parking parking1 = new Parking(id1,"DMS-1111","SC","Celta","black", LocalDateTime.now(),null,6.45d);
        Parking parking2 = new Parking(id2,"ASD-1234","CE","Volvo",null, LocalDateTime.now(),LocalDateTime.now(),6.45d);
        parkingMap.put(id1,parking1);
        parkingMap.put(id2,parking2);
    }

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-","");
    }

    public List<Parking> findAll() {
        return parkingMap.values().stream().collect(Collectors.toList());
    }

    public Parking findById(String id) {
        return parkingMap.get(id);
    }

    public Parking create(Parking parkingCreate) {
        String id = getUUID();
        parkingCreate.setId(id);
        parkingCreate.setEntryDate(LocalDateTime.now());
        parkingMap.put(id,parkingCreate);
        return parkingMap.get(id);
    }
}
