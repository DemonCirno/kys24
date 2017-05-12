package kys24.goods.service;


import kys24.goods.entity.Variety;

import java.util.List;

/**
 * @author Duolaimon
 *         17-4-29 下午3:39
 */
public interface VarietyService {
    List<Variety> getAllVarietyList();

    void addVariety(Variety variety);

    void setVariety(Variety variety) throws Exception;

    Variety removeVariety(int varietyId) throws Exception;
}
