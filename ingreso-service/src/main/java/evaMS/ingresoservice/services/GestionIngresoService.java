package evaMS.ingresoservice.services;

import evaMS.ingresoservice.clients.DescuentoAplicadoFeignClient;
import evaMS.ingresoservice.clients.PrecioPorRepFeignClient;
import evaMS.ingresoservice.clients.RepEspecificaFeignClient;
import evaMS.ingresoservice.clients.VehiculoFeignClient;
import evaMS.ingresoservice.dto.RepEspecificaEntity;
import evaMS.ingresoservice.dto.VehiculoEntity;
import evaMS.ingresoservice.entities.IngresoARepEntity;
import evaMS.ingresoservice.repositories.IngresoARepRepository;
import evaMS.ingresoservice.request.NuevaRepAplicadaRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GestionIngresoService {
    @Autowired
    VehiculoFeignClient vehiculoFeignClient;

    @Autowired
    RepEspecificaFeignClient repEspecificaFeignClient;

    @Autowired
    DescuentoAplicadoFeignClient descuentoAplicadoFeignClient;

    @Autowired
    PrecioPorRepFeignClient precioPorRepFeignClient;

    @Autowired
    IngresoARepService ingresoARepService;




    public Integer asignarNuevaRepEspecificaAIngreso(NuevaRepAplicadaRequest request){
        RepEspecificaEntity repPorAsignar=new RepEspecificaEntity();
        VehiculoEntity vehiculo=vehiculoFeignClient.getVehiculoById(request.getIdVehiculo());

        Integer precioReparacion= precioPorRepFeignClient.getPrecioByRepYTipoDeMotor(request.getTipoDeReparacion(),vehiculo.getTipoMotor());

        repPorAsignar.setIdIngresoARep(request.getIdIngreso());
        repPorAsignar.setNombreDeLaRep(request.getTipoDeReparacion());
        repPorAsignar.setPrecioDeLaReparacion(precioReparacion);

        repEspecificaFeignClient.saveRepEspecifica(repPorAsignar);

        return precioReparacion;
    }

    @Transactional
    public IngresoARepEntity saveIngresoARep(IngresoARepEntity ingreso){

        VehiculoEntity vehiculo=vehiculoFeignClient.getVehiculoById((long) ingreso.getIdVehiculo());


        return ingresoARepService.saveReparacion(ingreso);
    }

}
