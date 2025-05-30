/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bos;

import dtos.AutomovilDTO;
import dtos.PlacaDTO;
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
import mappers.AutomovilMapper;
import mappers.PersonaMapper;
import mappers.PlacaMapper;
import mappers.VehiculoMapper;
import org.apache.commons.lang3.RandomStringUtils;

/**
 *  Clase de negocio que implementa las operaciones relacionadas con las placas.
 * 
 * @author katia
 */
public class PlacaBO implements IPlacaBO{
    private final IPlacaDAO placaDAO;
    private final IVehiculoDAO vehiculoDAO;
    private final ILicenciaDAO licenciaDAO;
    private final IPersonaDAO personaDAO;
    private final PlacaMapper placaMapper = new PlacaMapper();
    private final VehiculoMapper vehiculoMapper = new VehiculoMapper();
    private final PersonaMapper personaMapper = new PersonaMapper();
    private final AutomovilMapper automovilMapper = new AutomovilMapper();

    public PlacaBO(IPlacaDAO placaDAO, IVehiculoDAO vehiculoDAO, ILicenciaDAO licenciaDAO, IPersonaDAO personaDAO) {
        this.placaDAO = placaDAO;
        this.vehiculoDAO = vehiculoDAO;
        this.licenciaDAO = licenciaDAO;
        this.personaDAO = personaDAO;
    }

    /**
     * Registra una nueva placa para un automóvil nuevo.
     * Verifica que estén los campos necesarios.
     *
     * @param dto Objeto con los datos de la placa y vehículo.
     * @param rfcPersona RFC de la persona.
     * @return La placa registrada con los datos completos.
     * @throws NegocioException Si los datos son inválidos o ocurre un error en el registro.
     */
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
            
            AutomovilDTO automovilDTO = new AutomovilDTO();
            automovilDTO.setNumeroSerie(dto.getNumeroSerie());
            automovilDTO.setMarca(dto.getMarca());
            automovilDTO.setLinea(dto.getLinea());
            automovilDTO.setColor(dto.getColor());
            automovilDTO.setModelo(dto.getModelo());

            Vehiculo vehiculo = vehiculoDAO.registrarVehiculo(automovilMapper.toEntity(automovilDTO));
            dto.setIdVehiculo(vehiculo.getId());

            dto.setNumeroPlaca(generarNumeroPlaca());
            dto.setFechaEmision(LocalDate.now());
            dto.setEstado(EstadoPlaca.ACTIVA);
            dto.setTipo(TipoPlaca.NUEVO);
            dto.setCosto(1500.0);

            Placa placa = placaMapper.toEntity(dto);
            placa.setVehiculo(vehiculo);
            placa.setPersona(persona);
            placa = placaDAO.registrarPlaca(placa);

            return placaMapper.toDTO(placa);

        } catch (PersistenciaException e) {
            throw new NegocioException("No se pudo registrar la placa para auto nuevo.", e);
        }
    }
    
    /**
     * Registra una nueva placa para un automóvil que ya esté en el sistema,
     * desactivando la placa anterior.
     *
     * @param dto Objeto con los datos de la nueva placa.
     * @param rfcPersona RFC de la persona.
     * @return La placa registrada.
     * @throws NegocioException Si los datos son inválidos o ocurre un error en el registro.
     */
    @Override
    public PlacaDTO registrarPlacaAutoUsado(PlacaDTO dto, String rfcPersona) throws NegocioException {
        if (dto.getNumeroSerie() == null || dto.getNumeroSerie().isBlank()) {
            throw new NegocioException("El número de serie es obligatorio.");
        }        

        try {
            Persona persona = personaDAO.buscarPersonaPorRFC(rfcPersona);
            if (persona == null) {
                throw new NegocioException("No se encontró a la persona.");
            }
            Licencia licencia = licenciaDAO.licenciaVigente(persona.getId());
            if (licencia == null) {
                throw new NegocioException("La persona no tiene licencia vigente.");
            }
            Vehiculo vehiculo = vehiculoDAO.buscarVehiculoPorNumSerie(dto.getNumeroSerie());
            if (vehiculo == null) {
                throw new NegocioException("El vehículo no está registrado.");
            }
            placaDAO.desactivarPlacasAnteriores(dto.getNumeroSerie());
            
            dto.setNumeroPlaca(generarNumeroPlaca());
            dto.setFechaEmision(LocalDate.now());
            dto.setFechaRecepcion(LocalDate.now());
            dto.setEstado(EstadoPlaca.ACTIVA);
            dto.setTipo(TipoPlaca.USADO);
            dto.setCosto(1000.0);

            Placa placa = placaMapper.toEntity(dto);
            placa.setVehiculo(vehiculo);
            placa.setPersona(persona);
            placaDAO.registrarPlaca(placa);
            return placaMapper.toDTO(placa);

        } catch (PersistenciaException e) {
            throw new NegocioException("No se pudo registrar la placa para auto usado.", e);
        }
    }

    /**
     * Genera una placa de forma aleatoria para autos nuevos.
     *
     * @return DTO con datos de la nueva placa.
     */
    @Override
    public PlacaDTO generarPlacaAutoNuevo() {
        PlacaDTO dto = new PlacaDTO();
        dto.setNumeroPlaca(generarNumeroPlaca());
        dto.setFechaEmision(LocalDate.now());
        dto.setCosto(1500.0);
        dto.setTipo(TipoPlaca.NUEVO);
        dto.setEstado(EstadoPlaca.ACTIVA);
        return dto;
    }
    
    /**
     * Genera un número de placa aleatorio con formato de tres letras + un guion + tres números.
     *
     * @return Número de placa generado.
     */
    private String generarNumeroPlaca() {
        String letras = RandomStringUtils.randomAlphabetic(3).toUpperCase();
        String numeros = String.format("%03d", new Random().nextInt(1000));
        return letras + "-" + numeros;
    }

    /**
     * Obtiene el historial de placas de un vehículo por medio de su número de serie.
     *
     * @param numeroSerie Número de serie del vehículo.
     * @return Lista de placas que ha tenido el vehículo.
     * @throws NegocioException Si ocurre un error durante la búsqueda.
     */
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
