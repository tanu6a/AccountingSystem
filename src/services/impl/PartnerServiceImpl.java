package services.impl;


import dao.PartnerDao;
import dao.impl.PartnerDaoImpl;
import entities.Partner;
import entities.TypePartner;
import services.PartnerService;
import services.ServiceException;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public class PartnerServiceImpl extends AbstractService implements PartnerService {
    private static volatile PartnerService INSTANCE = null;

    private PartnerDao partnerDao = PartnerDaoImpl.getInstance();

    @Override
    public Partner createPartner(long tin, String name, String fullName, TypePartner typePartner) {
        Partner partner = new Partner();
        try {
            startTransaction();
            partner.setTin(tin);
            partner.setName(name);
            partner.setFullname(fullName);
            partner.setTypePartner(typePartner);

            partner = partnerDao.save(partner);

            commit();
            return partner;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error creating Partner " + partner, e);
        }
    }

    @Override
    public Partner get(Serializable tin) {
        try {
            return partnerDao.get(tin);
        } catch (SQLException e) {
            throw new ServiceException("Error getting Partner by tin" + tin);
        }
    }

    @Override
    public void update(Partner partner) {
        try {
            partnerDao.update(partner);
        } catch (SQLException e) {
            throw new ServiceException("Error updating Partner " + partner);
        }
    }

    @Override
    public int delete(Serializable tin) {
        try {
            return partnerDao.delete(tin);
        } catch (SQLException e) {
            throw new ServiceException("Error deleting Partner by tin" + tin);
        }
    }

    @Override
    public Partner getByTin(long tin) {
        try {
            return partnerDao.getByTin(tin);
        } catch (SQLException e) {
            throw new ServiceException("Error getting by TIN:" + tin);
        }
    }

    @Override
    public List<Partner> getAll() {
        try {
            startTransaction();
            List<Partner> list = partnerDao.getAll();
            commit();
            return list;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error getting Partners");
        }
    }

    public static PartnerService getInstance() {
        PartnerService partnerService = INSTANCE;
        if (partnerService == null) {
            synchronized (PartnerServiceImpl.class) {
                partnerService = INSTANCE;
                if (partnerService == null) {
                    INSTANCE = partnerService = new PartnerServiceImpl();
                }
            }
        }

        return partnerService;
    }
}
