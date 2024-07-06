package evaMS.reportes_service.requests;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;

import java.util.Date;

@Getter
public class ReporteTresUltimosMesRequest {
    @Temporal(TemporalType.DATE)
    private Date fechaActual;
}
