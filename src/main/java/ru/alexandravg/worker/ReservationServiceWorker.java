package ru.alexandravg.worker;

import org.springframework.stereotype.Component;
import ru.alexandravg.domain.Reservation;
import ru.alexandravg.domain.ReservationRequest;
import ru.alexandravg.service.ReservationService;

import java.util.List;
import java.util.UUID;

//todo here is some working code
@Component
public class ReservationServiceWorker implements ReservationService {
    @Override
    public List<Reservation> getAllReservations() {
        return null;
    }

    @Override
    public List<Reservation> getReservationsByName(String name) {
        return null;
    }

    @Override
    public Boolean makeReservation(List<ReservationRequest> reservationRequest) {
        return null;
    }

    @Override
    public void cancelReservation(UUID id) {

    }
}
