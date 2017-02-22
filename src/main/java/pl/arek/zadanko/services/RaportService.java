/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.arek.zadanko.services;

import java.util.List;
import pl.arek.zadanko.model.viewmodel.RaportRow;
import pl.arek.zadanko.model.viewmodel.RaportFilter;

/**
 *
 * @author Arkadiusz Gibes <arkadiusz.gibes@yahoo.com>
 */
public interface RaportService {

    public List<RaportRow> generateRaport(RaportFilter filter);
}
