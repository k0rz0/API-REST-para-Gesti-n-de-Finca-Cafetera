package com.taller.api_1.servicios;

import com.taller.api_1.entidades.VariedadCafe;
import com.taller.api_1.repositorios.VariedadCafeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VariedadCafeService {

    @Autowired
    private VariedadCafeRepository repository;

    public List<VariedadCafe> listarTodas() {
        return repository.findAll();
    }

    public Optional<VariedadCafe> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public VariedadCafe guardar(VariedadCafe variedad) {
        validarVariedad(variedad);
        return repository.save(variedad);
    }

    public Optional<VariedadCafe> actualizar(Long id, VariedadCafe detalles) {
        return repository.findById(id).map(existente -> {
            validarVariedad(detalles);
            existente.setNombre(detalles.getNombre());
            existente.setOrigen(detalles.getOrigen());
            existente.setAltitudOptima(detalles.getAltitudOptima());
            return repository.save(existente);
        });
    }

    public boolean eliminar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    private void validarVariedad(VariedadCafe variedad) {
        if (variedad.getNombre() == null || variedad.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la variedad no puede estar vacío.");
        }

        if (variedad.getOrigen() == null || variedad.getOrigen().trim().isEmpty()) {
            throw new IllegalArgumentException("El origen de la variedad no puede estar vacío.");
        }

        if (variedad.getAltitudOptima() != null && variedad.getAltitudOptima() < 0) {
            throw new IllegalArgumentException("La altitud óptima no puede ser negativa.");
        }
    }
}
