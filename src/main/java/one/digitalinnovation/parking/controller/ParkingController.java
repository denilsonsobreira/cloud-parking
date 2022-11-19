package one.digitalinnovation.parking.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import one.digitalinnovation.parking.controller.dto.ParkingCreateDTO;
import one.digitalinnovation.parking.controller.dto.ParkingDTO;
import one.digitalinnovation.parking.controller.mapper.ParkingMapper;
import one.digitalinnovation.parking.model.Parking;
import one.digitalinnovation.parking.service.ParkingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking")
@Api(tags = "Parking Controller")
public class ParkingController {

    private final ParkingService parkingService;
    private final ParkingMapper parkingMapper;

    public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper) {
        this.parkingService = parkingService;
        this.parkingMapper = parkingMapper;
    }

    @GetMapping("/")
    @ApiOperation("Find all parkings")
    public ResponseEntity<List<ParkingDTO>> findAll() {
        List<Parking> parkingList = parkingService.findAll();
        return ResponseEntity.ok(parkingMapper.toParkingDTOList(parkingList));
    }

    @GetMapping("/{id}")
    @ApiOperation("Find one parking by id")
    public ResponseEntity<ParkingDTO> findByID(@PathVariable String id) {
        Parking parking = parkingService.findById(id);
        return ResponseEntity.ok(parkingMapper.toParkingDTO(parking));
    }

    @PostMapping
    @ApiOperation("Create a parking")
    public ResponseEntity<ParkingDTO> create(@RequestBody ParkingCreateDTO dto) {
        Parking parkingCreate = parkingMapper.toParkingCreate(dto);
        Parking parking = parkingService.create(parkingCreate);
        //ParkingDTO result = parkingMapper.toParkingDTO(parking);
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingMapper.toParkingDTO(parking));
    }
}
