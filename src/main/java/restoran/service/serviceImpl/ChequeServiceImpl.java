package restoran.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import restoran.dto.request.ChequeRequest;
import restoran.dto.response.*;
import restoran.entity.Cheque;
import restoran.entity.MenuItem;
import restoran.entity.Restaurant;
import restoran.entity.User;
import restoran.exception.NotFoundException;
import restoran.repository.ChequeSRepo;
import restoran.repository.MenuItemRepo;
import restoran.repository.UserRepo;
import restoran.service.ChequeService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChequeServiceImpl implements ChequeService {
    private final UserRepo userRepository;
    private final MenuItemRepo menuItemRepo;
    private final ChequeSRepo chequeSRepo;

    @Override
    public SimpleResponse giveCheck(Long id, ChequeRequest chequeRequest) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new RuntimeException("not found"));

        List<Long> menu = new ArrayList<>();
        if (chequeRequest.getMenuIdes() != null) {
            menu.addAll(chequeRequest.getMenuIdes());
        }
        List<MenuItem> allMenuId = menuItemRepo.getAllMenuId(menu);

        int totalPrice = 0;
        for (MenuItem menuItem : allMenuId) {
            totalPrice += menuItem.getPrice();
        }

        double serviceChargePercentage = 0.1;
        double serviceCharge = totalPrice * serviceChargePercentage;
        Cheque cheque = new Cheque();
        cheque.setTotalPrice(totalPrice);
        cheque.setService((int) serviceCharge);
        cheque.setUser(user);
        chequeSRepo.save(cheque);
//        for (MenuItem menuItem : allMenuId) {
//            cheque.getMenuItems().add(menuItem);
//
//        }
        for (int i = 0; i < allMenuId.size(); i++) {
            cheque.getMenuItems().add(allMenuId.get(i));
            log.error(String.valueOf(i));
        }
//        cheque.getMenuItems().
//        cheque.getMenuItems().addAll(allMenuId);

        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Cheque created successfully")
                .build();



    }

    @Override
    public ChequeResponse findCheck(Long cheId) {
        Cheque cheque = chequeSRepo.findById(cheId)
                .orElseThrow(() -> new NotFoundException("Not found Cheque with Id " + cheId));

        User user = cheque.getUser();
        if (user == null) {
            throw new NotFoundException("User is not found for the cheque");
        }


        Restaurant restaurant = user.getRestaurant();
        if (restaurant != null) {
            Integer service1 = restaurant.getService();

        } else {
            throw new NotFoundException("Restaurant is not found for the user");
        }

        List<MenuItemResponse> menuItemResponses = new ArrayList<>();
        if (restaurant != null && restaurant.getMenuItems() != null) {
            for (MenuItem menuItem : restaurant.getMenuItems()) {
                menuItemResponses.add(new MenuItemResponse(
                        menuItem.getName(),
                        menuItem.getImage(),
                        menuItem.getPrice(),
                        menuItem.getDescription(),
                        menuItem.getIsVegetarian()
                ));
            }
        }

        return ChequeResponse.builder()
                .firstName(user.getFirstName())
                .menuItemResponses(menuItemResponses)
                .service(String.valueOf(cheque.getService()) + " = 10%")
                .totalPrice(cheque.getTotalPrice())
                .build();
    }
    @Override
    public SimpleResponse delete(Long chequeId) {
        Cheque checkNotFound = chequeSRepo.findById(chequeId).orElseThrow(() -> new NotFoundException("check not found"));
chequeSRepo.delete(checkNotFound);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("success deleted")
                .build();
    }

}
