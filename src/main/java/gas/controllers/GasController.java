package gas.controllers;

import gas.dto.GasDto;
import gas.model.Gas;
import org.springframework.web.bind.annotation.*;
import gas.services.GasService;

import java.util.List;
import java.util.Map;

/**
 * Main application controller
 */
@RestController
@RequestMapping("/api")
public class GasController {
    private final GasService gasService;

    public GasController(GasService gasService){
        this.gasService = gasService;
    }

    @GetMapping(path = "/{id}")
    public GasDto getDataById(@PathVariable Integer id){
        return gasService.getById(id);
    }

    @GetMapping(path = "/statistics")
    public List<GasDto> getAllData(){
        return gasService.getAll();
    }

    @PostMapping("/new")
    public Map<String, Boolean> create(@RequestParam Integer current){
        gasService.create(current);
        return Map.of("result", true);
    }

    @DeleteMapping("/delete")
    public Map<String, Boolean> delete(@RequestParam Integer id){
        gasService.delete(id);
        return Map.of("result", true);
    }
}
