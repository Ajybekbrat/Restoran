package restoran.service.serviceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import restoran.dto.request.StopListRequest;
import restoran.dto.response.SimpleResponse;
import restoran.dto.response.StopListREsponse;
import restoran.entity.MenuItem;
import restoran.entity.StopList;
import restoran.exception.NotFoundException;
import restoran.repository.MenuItemRepo;
import restoran.repository.StopListRepo;
import restoran.service.StopListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StopListServiceImpl implements StopListService {
    private final MenuItemRepo menuItemRepo;
    private final StopListRepo stopListRepo;

    @Override @Transactional
    public SimpleResponse toStop(Long menuId, StopListRequest stopListRequest) {
        MenuItem menuItem = menuItemRepo.findById(menuId).orElseThrow(() -> new NotFoundException("Menu not found"));

        if (!menuItem.isToStopList()) {
            StopList stopList;
            if (menuItem.getStopList() == null) {
                stopList = new StopList();
            } else {
                stopList = stopListRepo.findById(menuItem.getStopList().getId())
                        .orElseThrow(() -> new NotFoundException("StopList not found"));
            }
            stopList.setReason(stopListRequest.getReason());


            menuItem.setToStopList(true);
            menuItem.setStopList(stopList);
            stopList.setMenuItem(menuItem);

            stopListRepo.save(stopList);

            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message("Successfully saved to stop list")
                    .build();
        } else {

            StopList stopList = menuItem.getStopList();
            menuItem.setToStopList(false);
            menuItemRepo.save(menuItem);

            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message("Successfully removed from stop list")
                    .build();
        }
    }

    @Override @Transactional
    public SimpleResponse update(StopListRequest stopListRequest, Long stopListId) {
        StopList stopList = stopListRepo.findById(stopListId).orElseThrow(() -> new RuntimeException("stopList not found"));
        stopList.setReason(stopListRequest.getReason());
        stopListRepo.save(stopList);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully updated stop list")
                .build();
    }

    @Override
    public StopListREsponse findById(Long stopListId) {
        StopList stopList = stopListRepo.findById(stopListId).orElseThrow(() -> new NotFoundException("stopList not found"));
         StopListREsponse stopListREsponse = new StopListREsponse();
         stopListREsponse.setName(stopList.getReason());
        return stopListREsponse;
    }

    @Override
    public List<StopListREsponse> findAll() {
        List<StopListREsponse>stopListREsponses = new ArrayList<>();
        for (StopList stopList : stopListRepo.findAll()) {
            StopListREsponse stopListREsponse = new StopListREsponse();
            stopListREsponse.setName(stopList.getReason());
            stopListREsponses.add(stopListREsponse);
        }
        return stopListREsponses;
    }

    @Override
    public SimpleResponse delete(Long stopListId) {
        StopList stopList = stopListRepo.findById(stopListId)
                .orElseThrow(() -> new NotFoundException("Stop list not found"));
        MenuItem menuItem = stopList.getMenuItem();
        if (menuItem != null) {
            menuItem.setStopList(null);
        }
        stopListRepo.delete(stopList);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully " +
                        "deleted stop list")
                .build();
    }
}
