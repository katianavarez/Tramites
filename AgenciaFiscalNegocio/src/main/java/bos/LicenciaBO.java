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
