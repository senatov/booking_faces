package org.springframework.webflow.booking;


import lombok.ToString;
import org.springframework.stereotype.Service;

import javax.faces.model.SelectItem;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@ToString
@Service
public class ReferenceData {

  private List<SelectItem> bedOptions;

  private List<SelectItem> smokingOptions;

  private List<SelectItem> creditCardExpMonths;

  private List<SelectItem> creditCardExpYears;

  private List<SelectItem> pageSizeOptions;

  public List<SelectItem> getBedOptions() {

    if (bedOptions == null) {
      bedOptions = new ArrayList<>();
      bedOptions.add(new SelectItem(1, "One king-size bed"));
      bedOptions.add(new SelectItem(2, "Two double beds"));
      bedOptions.add(new SelectItem(3, "Three beds"));
    }
    return bedOptions;
  }

  public List<SelectItem> getSmokingOptions() {

    if (smokingOptions == null) {
      smokingOptions = new ArrayList<>();
      smokingOptions.add(new SelectItem(Boolean.TRUE, "Smoking"));
      smokingOptions.add(new SelectItem(Boolean.FALSE, "Non-Smoking"));
    }
    return smokingOptions;
  }

  public List<SelectItem> getCreditCardExpMonths() {

    if (creditCardExpMonths == null) {
      creditCardExpMonths = new ArrayList<>();
      creditCardExpMonths.add(new SelectItem(Integer.valueOf(1), "Jan"));
      creditCardExpMonths.add(new SelectItem(Integer.valueOf(2), "Feb"));
      creditCardExpMonths.add(new SelectItem(Integer.valueOf(3), "Mar"));
      creditCardExpMonths.add(new SelectItem(Integer.valueOf(4), "Apr"));
      creditCardExpMonths.add(new SelectItem(Integer.valueOf(5), "May"));
      creditCardExpMonths.add(new SelectItem(Integer.valueOf(6), "Jun"));
      creditCardExpMonths.add(new SelectItem(Integer.valueOf(7), "Jul"));
      creditCardExpMonths.add(new SelectItem(Integer.valueOf(8), "Aug"));
      creditCardExpMonths.add(new SelectItem(Integer.valueOf(9), "Sep"));
      creditCardExpMonths.add(new SelectItem(Integer.valueOf(10), "Oct"));
      creditCardExpMonths.add(new SelectItem(Integer.valueOf(11), "Nov"));
      creditCardExpMonths.add(new SelectItem(Integer.valueOf(12), "Dec"));
    }
    return creditCardExpMonths;
  }

  public List<SelectItem> getCreditCardExpYears() {

    if (creditCardExpYears == null) {
      creditCardExpYears = new ArrayList<>();
      Calendar cal = Calendar.getInstance();
      int year = cal.get(Calendar.YEAR);
      creditCardExpYears.add(new SelectItem(year, String.valueOf(year++)));
      creditCardExpYears.add(new SelectItem(year, String.valueOf(year++)));
      creditCardExpYears.add(new SelectItem(year, String.valueOf(year++)));
      creditCardExpYears.add(new SelectItem(year, String.valueOf(year++)));
      creditCardExpYears.add(new SelectItem(year, String.valueOf(year++)));
    }
    return creditCardExpYears;
  }

  public List<SelectItem> getPageSizeOptions() {

    if (pageSizeOptions == null) {
      pageSizeOptions = new ArrayList<>();
      pageSizeOptions.add(new SelectItem(Integer.valueOf(5), "5"));
      pageSizeOptions.add(new SelectItem(Integer.valueOf(10), "10"));
      pageSizeOptions.add(new SelectItem(Integer.valueOf(20), "20"));
    }
    return pageSizeOptions;
  }

}
