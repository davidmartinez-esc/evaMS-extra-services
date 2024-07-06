package evaMS.rdringresoservice.services;


import evaMS.rdringresoservice.clients.IngresoFeignClient;
import evaMS.rdringresoservice.clients.PrecioPorRepFeignClient;
import evaMS.rdringresoservice.clients.VehiculoFeignClient;
import evaMS.rdringresoservice.dto.INRepsEfectuadasYMonto;
import evaMS.rdringresoservice.dto.IReporteMes;
import evaMS.rdringresoservice.dto.NRepsEfectuadasYMonto;
import evaMS.rdringresoservice.dto.ReporteMesDto;
import evaMS.rdringresoservice.entities.RepEspecificaEntity;
import evaMS.rdringresoservice.models.IngresoARepEntity;
import evaMS.rdringresoservice.models.VehiculoEntity;
import evaMS.rdringresoservice.repositories.RepEspecificaRepository;
import evaMS.rdringresoservice.requests.BorrarReparacionEspecificaRequest;
import evaMS.rdringresoservice.requests.GetNRepsEfectuadasYMontoRequest;
import evaMS.rdringresoservice.requests.NuevaRepAplicadaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RepEspecificaService {
    @Autowired
    RepEspecificaRepository repEspecificaRepository;

    @Autowired
    PrecioPorRepFeignClient precioPorRepFeignClient;

    @Autowired
    VehiculoFeignClient vehiculoFeignClient;

    @Autowired
    IngresoFeignClient ingresoFeignClient;


    public ArrayList<RepEspecificaEntity> getAllReparacionesEspecificas(){
        return (ArrayList<RepEspecificaEntity>) repEspecificaRepository.findAll();
    }

    public RepEspecificaEntity saveTipoDeRep(RepEspecificaEntity reparacion){
        return repEspecificaRepository.save(reparacion);
    }


    public RepEspecificaEntity updateRepEspecifica(RepEspecificaEntity repEspecifica) {
        return repEspecificaRepository.save(repEspecifica);
    }

    public boolean deleteRepEspecifica(Long id) throws Exception {
        try{
            repEspecificaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    public List<RepEspecificaEntity> getRepEspecificaByIdIngreso(int idIngreso){
        return repEspecificaRepository.findByIdIngresoARep(idIngreso);
    }

    public Integer getMontoTotalDelIngreso(int idIngreso){
        return repEspecificaRepository.getCostoDeLasReparacionesDeIngreso(idIngreso);
    }

    public String deleteRepEspecificaSegunNombreDeLaRep(BorrarReparacionEspecificaRequest request){
        repEspecificaRepository.
                deleteReparacionEspecificaByIdIngresoNombre(request.getIdIngresoARep(),request.getNombreReparacion());
        return "Se logró borrar con exito la reparacion";
    }

    public ReporteMesDto getReporteMesPorRep(int mesActual, int actualYear, String nombreRep){
        IReporteMes reporte=repEspecificaRepository.getReporteReparacionPorMes(mesActual,actualYear,nombreRep);
        ReporteMesDto reporteToReturn=new ReporteMesDto(reporte.getTotalRecaudadoMesActual(), reporte.getNumeroReparacionesMesActual());
        return reporteToReturn;

    }

    public Integer asignarNuevaRepEspecificaAIngreso(NuevaRepAplicadaRequest request){
        RepEspecificaEntity repPorAsignar=new RepEspecificaEntity();
        IngresoARepEntity ingreso=ingresoFeignClient.getIngresoARepById((long)request.getIdIngreso());
        VehiculoEntity vehiculo=vehiculoFeignClient.getVehiculoById((long)ingreso.getIdVehiculo());

        Integer precioReparacion= precioPorRepFeignClient.getPrecioByRepYTipoDeMotor(request.getTipoDeReparacion(),vehiculo.getTipoMotor());

        repPorAsignar.setIdIngresoARep(request.getIdIngreso());
        repPorAsignar.setNombreDeLaRep(request.getTipoDeReparacion());
        repPorAsignar.setPrecioDeLaReparacion(precioReparacion);
        repPorAsignar.setPatente(vehiculo.getPatente());

        repPorAsignar.setHoraReparacion(request.getHoraReparacion());
        repPorAsignar.setFechaReparacion(request.getFechaReparacion());

        saveTipoDeRep(repPorAsignar);

        return precioReparacion;
    }

    public NRepsEfectuadasYMonto getNumeroDeRepsEfectuadasByPatenteYTipoVehiculo(GetNRepsEfectuadasYMontoRequest request){
        NRepsEfectuadasYMonto dto=new NRepsEfectuadasYMonto();

        INRepsEfectuadasYMonto interfaz= repEspecificaRepository.getNumeroDeRepsYMontoByPatenteTipoVehiculo(request.getPatente(),request.getNombreDeLaRep());
        dto.setMontoTotal(interfaz.getMontoTotal());
        dto.setNumeroDeReparaciones(interfaz.getNumeroDeReparaciones());

        return dto;
    }




}

