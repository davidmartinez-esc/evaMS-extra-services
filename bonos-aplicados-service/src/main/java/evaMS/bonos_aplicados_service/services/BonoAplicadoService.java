package evaMS.bonos_aplicados_service.services;



import evaMS.bonos_aplicados_service.entities.BonoAplicadoEntity;
import evaMS.bonos_aplicados_service.repositories.BonoAplicadoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BonoAplicadoService {
    @Autowired
    BonoAplicadoRepository bonoAplicadoRepository;


    public ArrayList<BonoAplicadoEntity> getBonosAplicados(){
        return (ArrayList<BonoAplicadoEntity>) bonoAplicadoRepository.findAll();
    }

    public BonoAplicadoEntity saveBonoAplicado(BonoAplicadoEntity bonoAplicado){
        return bonoAplicadoRepository.save(bonoAplicado);
    }


    public BonoAplicadoEntity updateBonoAplicado(BonoAplicadoEntity bonoAplicado) {
        return bonoAplicadoRepository.save(bonoAplicado);
    }

    public boolean deleteBonoAplicado(Long id) throws Exception {
        try{
            bonoAplicadoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    public Integer getNumeroDeBonosAplicadosPorMesMarca(int mes,int anio,String marca){
        Integer response;
        response=bonoAplicadoRepository.contarBonosPorMes(mes,marca,anio);


        if (response==null){
            response=0;
        }
        return response;
    }
}
