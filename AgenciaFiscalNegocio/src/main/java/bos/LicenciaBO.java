/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bos;

import dtos.LicenciaDTO;
import entidades.Licencia;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import idaos.ILicenciaDAO;
import interfaces.ILicenciaBO;
import java.time.LocalDate;
import mappers.LicenciaMapper;

/**
 * Implementación de la interfaz ILicenciaBO que gestiona la lógica de negocio
 * para el registro y la validación de licencias de conducir.
 * 
 * @author katia
 */
public class LicenciaBO implements ILicenciaBO{
    private final ILicenciaDAO licenciaDAO;
    private final LicenciaMapper mapper;

    public LicenciaBO(ILicenciaDAO licenciaDAO) {
        this.licenciaDAO = licenciaDAO;
        this.mapper = new LicenciaMapper();
    }

    /**
     * Registra una nueva licencia, si la persona no cuenta con una vigente.
     * Calcula el costo dependiendo la duración que se quiera.
     * @param dto Datos de lalicencia a registrar.
     * @return Objeto LicenciaDTO con el costo y fecha asignados.
     * @throws NegocioException en caso de que la persona ya tenga licencia vigente
     * o datos inválidos.
     */
    @Override
    public LicenciaDTO registrarLicencia(LicenciaDTO dto) throws NegocioException {
        try {
            if (dto.getDuracion() == null) {
                throw new NegocioException("La duración es obligatoria.");
            }
            if (dto.getIdPersona() == null) {
                throw new NegocioException("Debe tener una persona.");
            }

            Licencia licenciaVigente = licenciaDAO.licenciaVigente(dto.getIdPersona());
            if (licenciaVigente != null) {
                throw new NegocioException("Ya tiene una licencia vigente.");
            }

            dto.setFechaExpedicion(LocalDate.now());

            double costo;
            switch (dto.getDuracion()) {
                case UNO -> costo = 500;
                case DOS -> costo = 900;
                case TRES -> costo = 1200;
                default -> throw new NegocioException("Duración inválida.");
            }
            dto.setCosto(costo);

            Licencia licencia = mapper.toEntity(dto);
            licenciaDAO.registrarLicencia(licencia);

            return dto;

        } catch (PersistenciaException e) {
            throw new NegocioException("No se pudo registrar la licencia.", e);
        }
    }

    /**
     * Consulta si una persona tiene una licencia vigente.
     * @param idPersona Identificador único de la persona.
     * @return La licencia vigente si se encuentra.
     * @throws NegocioException si no hay licencia vigente o si ocurre algún error.
     */
    @Override
    public LicenciaDTO licenciaVigente(Long idPersona) throws NegocioException {
        try {
            Licencia licencia = licenciaDAO.licenciaVigente(idPersona);
            if (licencia == null) {
                throw new NegocioException("La persona no tiene licencia vigente.");
            }
            return mapper.toDTO(licencia);
        } catch (PersistenciaException e) {
            throw new NegocioException("No se pudo obtener la licencia vigente.", e);
        }
    }
}
