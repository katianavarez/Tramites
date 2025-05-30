/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bos;

import dtos.LicenciaDTO;
import dtos.PersonaDTO;
import dtos.PlacaDTO;
import dtos.VehiculoDTO;
import entidades.Licencia;
import entidades.Persona;
import entidades.Placa;
import entidades.Vehiculo;
import enums.EstadoPlaca;
import enums.TipoPlaca;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import idaos.ILicenciaDAO;
import idaos.IPersonaDAO;
import idaos.IPlacaDAO;
import idaos.IVehiculoDAO;
import interfaces.IPlacaBO;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import mappers.PersonaMapper;
import mappers.PlacaMapper;
import mappers.VehiculoMapper;
import org.apache.commons.lang3.RandomStringUtils;

/**
 *
 * @author katia
 */
public class PlacaBO implements IPlacaBO{
    private final IPlacaDAO placaDAO;
    private final IVehiculoDAO vehiculoDAO;
    private final ILicenciaDAO licenciaDAO;
    private final IPersonaDAO personaDAO;
    private final PlacaMapper placaMapper;
    private final VehiculoMapper vehiculoMapper;
    private final PersonaMapper personaMapper;

    public PlacaBO(IPlacaDAO placaDAO, IVehiculoDAO vehiculoDAO, ILicenciaDAO licenciaDAO, IPersonaDAO personaDAO) {
        this.placaDAO = placaDAO;
        this.vehiculoDAO = vehiculoDAO;
        this.licenciaDAO = licenciaDAO;
        this.personaDAO = personaDAO;
        this.placaMapper = new PlacaMapper();
        this.vehiculoMapper = new VehiculoMapper();
        this.personaMapper = new PersonaMapper();
    }

    @Override
    public PlacaDTO registrarPlacaAutoNuevo(PlacaDTO dto, String rfcPersona) throws NegocioException {
        if (dto == null) {
            throw new NegocioException("Los datos son obligatorios.");
        }
        if (dto.getNumeroSerie() == null || dto.getNumeroSerie().isBlank()) {
            throw new NegocioException("El número de serie es obligatorio.");
        }

        try {
            Persona persona = personaDAO.buscarPersonaPorRFC(rfcPersona);
            if (persona == null) throw new NegocioException("No se encontró a la persona.");

            Licencia licencia = licenciaDAO.licenciaVigente(persona.getId());
            if (licencia == null) {
                throw new NegocioException("La persona no tiene licencia vigente.");
            }
            
            VehiculoDTO vehiculoDTO = new VehiculoDTO();
            vehiculoDTO.setNumeroSerie(dto.getNumeroSerie());
            vehiculoDTO.setMarca(dto.getMarca());
            vehiculoDTO.setLinea(dto.getLinea());
            vehiculoDTO.setColor(dto.getColor());
            vehiculoDTO.setModelo(dto.getModelo());
            Vehiculo vehiculo = vehiculoDAO.registrarVehiculo(vehiculoMapper.toEntity(vehiculoDTO));
            vehiculoDTO = vehiculoMapper.toDTO(vehiculo);
            
            dto.setNumeroPlaca(generarNumeroPlaca());
            dto.setFechaEmision(LocalDate.now());
            dto.setEstado(EstadoPlaca.ACTIVA);
            dto.setTipo(TipoPlaca.NUEVO);
            dto.setCosto(1500.0);
            dto.setIdVehiculo(vehiculoDTO.getId());

            Placa placa = placaMapper.toEntity(dto);
            placa.setVehiculo(vehiculo);
            placa.setPersona(persona);
            placa = placaDAO.registrarPlaca(placa);

            return placaMapper.toDTO(placa);

        } catch (PersistenciaException e) {
            throw new NegocioException("No se pudo registrar la placa para auto nuevo.", e);
        }
    }
    
    @Override
    public PlacaDTO registrarPlacaAutoUsado(PlacaDTO dto) throws NegocioException {
        if (dto.getNumeroSerie() == null || dto.getNumeroSerie().isBlank()) {
            throw new NegocioException("El número de serie es obligatorio.");
        }
        if (dto.getFechaRecepcion() == null) {
            throw new NegocioException("La fecha de recepción es obligatoria.");
        }

        try {
            Vehiculo vehiculo = vehiculoDAO.buscarVehiculoPorNumSerie(dto.getNumeroSerie());
            if (vehiculo == null) {
                throw new NegocioException("El vehículo no está registrado.");
            }
            placaDAO.desactivarPlacasAnteriores(dto.getNumeroSerie());
            
            dto.setNumeroPlaca(generarNumeroPlaca());
            dto.setFechaEmision(LocalDate.now());
            dto.setEstado(EstadoPlaca.ACTIVA);
            dto.setTipo(TipoPlaca.USADO);
            dto.setCosto(1000.0);

            Placa placa = placaMapper.toEntity(dto);
            placa.setVehiculo(vehiculo);
            placaDAO.registrarPlaca(placa);
            return placaMapper.toDTO(placa);

        } catch (PersistenciaException e) {
            throw new NegocioException("No se pudo registrar la placa para auto usado.", e);
        }
    }


    private String generarNumeroPlaca() {
        String letras = RandomStringUtils.randomAlphabetic(3).toUpperCase();
        String numeros = String.format("%03d", new Random().nextInt(1000));
        return letras + "-" + numeros;
    }

    @Override
    public List<PlacaDTO> obtenerHistorialPorNumSerie(String numeroSerie) throws NegocioException {
        if (numeroSerie == null || numeroSerie.isBlank()) {
            throw new NegocioException("El número de serie es obligatorio.");
        }

        try {
            List<Placa> placas = placaDAO.obtenerHistorialPorNumSerie(numeroSerie);
            return placas.stream().map(placaMapper::toDTO).toList();
        } catch (PersistenciaException e) {
            throw new NegocioException("No se pudo obtener el historial de placas.", e);
        }
    }
}
