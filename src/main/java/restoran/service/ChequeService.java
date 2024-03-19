package restoran.service;

import restoran.dto.request.ChequeRequest;
import restoran.dto.response.ChequeResponse;
import restoran.dto.response.SimpleResponse;

public interface ChequeService {
    SimpleResponse giveCheck(Long userId, ChequeRequest chequeRequest);

    ChequeResponse findCheck(Long cheId);

    SimpleResponse delete(Long chequeId);
}
