package restoran.service;

import restoran.dto.request.StopListRequest;
import restoran.dto.response.SimpleResponse;
import restoran.dto.response.StopListREsponse;

import java.util.List;

public interface StopListService {
    SimpleResponse toStop(Long menuId, StopListRequest stopListRequest);

    SimpleResponse update(StopListRequest stopListRequest, Long stopListId);

    StopListREsponse findById(Long stopListId);

    List<StopListREsponse> findAll();

    SimpleResponse delete(Long stopListId);
}
