/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.arek.zadanko.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.arek.zadanko.model.viewmodel.RaportFilter;
import pl.arek.zadanko.model.viewmodel.RaportFilter.FilterType;
import pl.arek.zadanko.model.viewmodel.RaportRow;
import pl.arek.zadanko.services.RaportService;

/**
 *
 * @author Arkadiusz Gibes <arkadiusz.gibes@yahoo.com>
 */
@Controller
public class RaportController {

    @Autowired
    RaportService raportService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    String raport(Model model,
            @RequestParam(name = "surnameFilter", defaultValue = "empty") String filterSurname,
            @RequestParam(name = "minDateFilter", defaultValue = "empty") String minDate,
            @RequestParam(name = "maxDateFilter", defaultValue = "empty") String maxDate,
            @RequestParam(name = "minTotalAmountFilter", defaultValue = "empty") String minAmount,
            @RequestParam(name = "maxTotalAmountFilter", defaultValue = "empty") String maxAmount) {

        /**
         * Building raport filter here
         */
        RaportFilter filter = new RaportFilter();
        buildFilters(minDate, maxDate, filter, filterSurname, minAmount, maxAmount);
        
        /**
         * Geting raport from service by given filter and 
         */
        List<RaportRow> raportData = this.raportService.generateRaport(filter);
        model.addAttribute("raport", raportData);

        return "raport";
    }

    private void buildFilters(String minDate, String maxDate, RaportFilter filter, String filterSurname, String minAmount, String maxAmount) throws NumberFormatException {
        /**
         * checking params and build some filters here.
         */
        if (!minDate.equals("empty") && !maxDate.equals("empty")) {

            try {
                buildDateFilter(filter, maxDate, minDate);
            } catch (ParseException ex) {
                Logger.getLogger(RaportController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (!filterSurname.equals("empty")) {
            buildSurnameFilter(filter, filterSurname);
        } else if (!minAmount.equals("empty") && !maxAmount.equals("empty")) {

            buildPriceFilter(filter, maxAmount, minAmount);
        }
    }

    private void buildDateFilter(RaportFilter filter, String maxDate, String minDate) throws ParseException {
        SimpleDateFormat parser = new SimpleDateFormat("dd-MM-yyyy");
        filter.setMaxDate(parser.parse(maxDate));
        filter.setMinDate(parser.parse(minDate));
        filter.setFilterType(FilterType.DATE);
    }

    private void buildSurnameFilter(RaportFilter filter, String filterSurname) {
        filter.setFilterType(FilterType.NAME);
        filter.setSurname(filterSurname);
    }

    private void buildPriceFilter(RaportFilter filter, String maxAmount, String minAmount) throws NumberFormatException {
        filter.setMaxprice(Float.parseFloat(maxAmount));
        filter.setMinprice(Float.parseFloat(minAmount));
        filter.setFilterType(FilterType.PRICE);
    }
}
