package services;

import entities.Partner;
import entities.TypePartner;

import java.io.Serializable;
import java.util.List;

public interface PartnerService {
    Partner createPartner(long tin, String name, String fullname, TypePartner typePartner);
    Partner get(Serializable tin);
    void update(Partner partner);
    int delete(Serializable tin);

    Partner getByTin(long userId);

    List<Partner> getAll();
}
