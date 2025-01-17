package bon_appetit.api.services;

import bon_appetit.api.models.Reservation;
import bon_appetit.api.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public Reservation create(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public Reservation findById(Integer id) {
        return reservationRepository.findById(id).orElse(null);
    }

    public List<Reservation> findAll() {
        List<Reservation> reservations = new ArrayList<>();
        reservationRepository.findAll().forEach(reservations::add);
        return reservations;
    }

    public void deleteById(Integer id) {
        reservationRepository.deleteById(id);
    }
}