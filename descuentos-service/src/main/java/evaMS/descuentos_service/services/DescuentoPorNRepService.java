package evaMS.descuentos_service.services;

import evaMS.descuentos_service.entities.DescuentoPorNRepEntity;
import evaMS.descuentos_service.repositories.DescuentoPorNRepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DescuentoPorNRepService {
    @Autowired
    DescuentoPorNRepRepository descuentoRepository;

    public ArrayList<DescuentoPorNRepEntity> getDescuentoPorNReps(){
        return (ArrayList<DescuentoPorNRepEntity>) descuentoRepository.findAll();
    }

    public DescuentoPorNRepEntity saveDescuentoPorNRep(DescuentoPorNRepEntity descuento){
        return descuentoRepository.save(descuento);
    }


    public DescuentoPorNRepEntity updateDescuentoPorNRep(DescuentoPorNRepEntity descuento) {
        return descuentoRepository.save(descuento);
    }

    public boolean deleteDescuentoPorNRep(Long id) throws Exception {
        try{
            descuentoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    public Integer getDescuentoByNRepYTipoDeMotor(int numeroDeReps,String tipoDeMotor){
        Integer descuento;
        String numeroDeRepStr=numeroDeRepsAString(numeroDeReps);

        if (numeroDeRepStr=="NO FIGURA"){
            return 0;
        }

        descuento=descuentoRepository.getDescuentoByNumeroDeRepsTipoDeMotor(numeroDeRepStr,tipoDeMotor);
        if (descuento==null){
            descuento=0;
        }
        return descuento;



    }

    private String numeroDeRepsAString(int numeroDeReps){
        if (numeroDeReps>=1 && numeroDeReps<=2){
            return "1-2";
        } else if (5>=numeroDeReps && numeroDeReps>=3) {
            return "3-5";
        }
        else if (9>=numeroDeReps && numeroDeReps>=6) {
            return "6-9";
        } else if (numeroDeReps>=10) {
            return "10-MAS";
        }
        return "NO FIGURA";


    }

}
