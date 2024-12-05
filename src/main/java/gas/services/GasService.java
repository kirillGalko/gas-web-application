package gas.services;

import gas.dto.GasDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import gas.model.Gas;
import org.springframework.stereotype.Service;
import gas.repositories.GasRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Main service creating, getting and removing data
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class GasService {
    private final GasRepository gasRepository;

    /**
     * Current tariff. Can be changed
     */
    private static final float TARIFF = (float) 5.58;

    /**
     * Gets the data from database by id
     * @param id - id of the record in database
     * @return record from database mapped to data transfer object
     */
    public GasDto getById (int id){
        log.info("Get by id: " + id);
        Gas gas = gasRepository.findById(id).orElseThrow();
        return mapToDto(gas);
    }

    /**
     * Gets all records from database
     * @return list of database records mapped to data transfer object
     */
    public List<GasDto> getAll(){
        log.info("Get all");
        return gasRepository.findAll()
                .stream()
                .map(GasService::mapToDto)
                .toList();
    }

    /**
     * Creates new record in database
     * @param current - current reading of the metering device
     */
    public void create (Integer current){
        log.info("Create new record");
        Gas gas = mapToEntity(current);
        gasRepository.save(gas);
    }

    /**
     * removes record from database
     * @param id - id of the record in the database
     */
    public void delete(Integer id){
        log.info("Delete record: " + id);
        gasRepository.deleteById(id);
    }

    /**
     * Maps entity object to data transfer object
     * @param gas - entity object of Gas class
     * @return mapped to DTO object
     */
    public static GasDto mapToDto(Gas gas){
        GasDto gasDto = new GasDto();
        gasDto.setId(gas.getId());
        gasDto.setPrevious(gas.getPrevious());
        gasDto.setCurrent(gas.getCurrent());
        gasDto.setSpent(gas.getSpent());
        gasDto.setAmount(gas.getAmount());
        gasDto.setDateTransferred(gas.getDateTransferred());
        gasDto.setDatePaid(gas.getDatePaid());
        return gasDto;
    }

    /**
     * Maps data transfer object to entity object
     * @param current - current reading of the metering device
     * @return object of Gas class
     */
    public Gas mapToEntity(Integer current){
        Gas gas = new Gas();
        Optional<Gas> optionalGasMaxCurrent = gasRepository.findTopByOrderByCurrentDesc();
        if (optionalGasMaxCurrent.isEmpty()){
            log.error("Показания не введены");
        }
        int previous = optionalGasMaxCurrent.get().getCurrent();
//        int current = gasDto.getCurrent();
        int spent = current - previous;
        float amount = spent * TARIFF;
        gas.setPrevious(previous);
        gas.setCurrent(current);
        gas.setSpent(spent);
        gas.setAmount(amount);
        gas.setDateTransferred(LocalDateTime.now());
        gas.setDatePaid(LocalDateTime.now());
        return gas;
    }
}

