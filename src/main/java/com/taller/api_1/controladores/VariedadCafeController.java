package com.taller.api_1.controladores;

import com.taller.api_1.entidades.VariedadCafe;
import com.taller.api_1.servicios.VariedadCafeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cafes")
public class VariedadCafeController {

    @Autowired
    private VariedadCafeService service;

    // 1. GET ALL
    @GetMapping
    public ResponseEntity<List<VariedadCafe>> listar() {
        return ResponseEntity.ok(service.listarTodas());
    }

    // 2. GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<VariedadCafe> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(variedad -> ResponseEntity.ok(variedad))
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. POST
    @PostMapping
    public ResponseEntity<VariedadCafe> crear(@RequestBody VariedadCafe variedad) {
        VariedadCafe nueva = service.guardar(variedad);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }

    // 4. PUT
    @PutMapping("/{id}")
    public ResponseEntity<VariedadCafe> actualizar(@PathVariable Long id, @RequestBody VariedadCafe detalles) {
        return service.actualizar(id, detalles)
                .map(variedad -> ResponseEntity.ok(variedad))
                .orElse(ResponseEntity.notFound().build());
    }

    // 5. DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (service.eliminar(id)) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.notFound().build(); // 404 Not Found
    }
}
